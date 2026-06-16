package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

import com.mycompany.buat_apk.domains.entities.stocks.CreateStock;
import com.mycompany.buat_apk.domains.repositories.StockRepository;
import com.mysql.cj.xdevapi.PreparableStatement;
import com.mycompany.buat_apk.domains.entities.stocks.DailyTransactionSummary;
import com.mycompany.buat_apk.domains.entities.stocks.TransactionItem;

public class StockRepo implements StockRepository {
    private final Connection conn;

	public StockRepo(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public CreateStock createStocks(CreateStock s) throws SQLException {
        Long cusId = s.getCustomerId();
        Long price = s.getPrice();
        String query = "";
        PreparedStatement stmt = null;

        if (cusId == null && price == null){
            query = "INSERT INTO stocks (user_id, product_id, quantity)" +
                " VALUES (?, ?, ?)";

            stmt = this.makePreperaredStatement(query);

            stmt.setLong(1, s.getUserId());
            stmt.setLong(2, s.getProductId());
            stmt.setLong(3, s.getQuantity());
        }
        else if (price != null) {
            if (cusId == null) {
                query =
                "INSERT INTO stocks " +
                "(user_id, product_id, quantity, price, description) " +
                "VALUES (?, ?, ?, ?, ?)";

                stmt = makePreperaredStatement(query);

                stmt.setLong(1, s.getUserId());
                stmt.setLong(2, s.getProductId());
                stmt.setLong(3, s.getQuantity());
                stmt.setLong(4, s.getPrice());
                stmt.setString(5, s.getDescription());
            }
            else {
                query =
                "INSERT INTO stocks " +
                "(user_id, product_id, quantity, price, customer_id, description) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

                stmt = makePreperaredStatement(query);

                stmt.setLong(1, s.getUserId());
                stmt.setLong(2, s.getProductId());
                stmt.setLong(3, s.getQuantity());
                stmt.setLong(4, s.getPrice());
                stmt.setLong(5, s.getCustomerId());
                stmt.setString(6, s.getDescription());
            }
        }
        else {
            throw new IllegalArgumentException("Both customer id have to be either empty of filled.");
        }

        stmt.executeUpdate();
        System.out.printf("Stock for product id of product id: %d has been created with quantity of %d",
                s.getProductId(), s.getQuantity());

		return null;
	}

    @Override
    public int getProductStocks(int product_id) throws SQLException {
        // We use SUM(quantity) to calculate the balance of all transactions
        String sql = "SELECT SUM(quantity) FROM stocks WHERE product_id = ?";

        // Using try-with-resources to ensure the connection and statement close automatically
        PreparedStatement pstmt = this.makePreperaredStatement(sql);

        pstmt.setInt(1, product_id);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
      
        }

        return 0;
    }

    private PreparedStatement makePreperaredStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    @Override
    public List<DailyTransactionSummary> getDailySummaryByMonth(YearMonth month) throws SQLException {
        String sql =
            "SELECT s.quantity, s.price, s.created_at " +
            "FROM stocks s " +
            "WHERE s.created_at >= ? AND s.created_at < ? " +
            "ORDER BY s.created_at ASC";

        Timestamp start = Timestamp.valueOf(month.atDay(1).atStartOfDay());
        Timestamp end = Timestamp.valueOf(month.plusMonths(1).atDay(1).atStartOfDay());

        PreparedStatement stmt = this.makePreperaredStatement(sql);
        stmt.setTimestamp(1, start);
        stmt.setTimestamp(2, end);

        ResultSet rs = stmt.executeQuery();

        Map<LocalDate, long[]> buckets = new TreeMap<>();
        while (rs.next()) {
            int qty = rs.getInt("quantity");
            long price = rs.getLong("price");
            if (rs.wasNull()) price = 0L;
            LocalDate day = rs.getTimestamp("created_at").toLocalDateTime().toLocalDate();
            long amount = price * Math.abs(qty);
            long[] totals = buckets.computeIfAbsent(day, k -> new long[2]);
            if (qty < 0) {
                totals[0] += amount;
            } else {
                totals[1] += amount;
            }
        }

        List<DailyTransactionSummary> result = new ArrayList<>();
        LocalDate cursor = month.atDay(1);
        LocalDate lastDay = month.atEndOfMonth();
        while (!cursor.isAfter(lastDay)) {
            long[] totals = buckets.get(cursor);
            long moneyIn = totals != null ? totals[0] : 0L;
            long moneyOut = totals != null ? totals[1] : 0L;
            result.add(new DailyTransactionSummary(cursor, moneyIn, moneyOut));
            cursor = cursor.plusDays(1);
        }
        return result;
    }
    
    @Override
    public List<TransactionItem> getAllTransactions()
    throws SQLException {

        String sql =
            "SELECT s.id, " +
            "p.name AS product_name, " +
            "c.name AS customer_name, " +
            "s.quantity, " +
            "s.price, " +
            "s.description, " +
            "s.created_at " +
            "FROM stocks s " +
            "JOIN products p ON p.id = s.product_id " +
            "LEFT JOIN customers c ON c.id = s.customer_id " +
            "ORDER BY s.created_at DESC";

        PreparedStatement stmt =
            this.makePreperaredStatement(sql);

        ResultSet rs = stmt.executeQuery();

        List<TransactionItem> result =
            new ArrayList<>();

        while(rs.next()) {

            TransactionItem item =
                new TransactionItem();

            item.setId(rs.getLong("id"));
            item.setProductName(
                rs.getString("product_name")
            );
            item.setCustomerName(
                rs.getString("customer_name")
            );
            item.setQuantity(
                rs.getInt("quantity")
            );
            item.setPrice(
                rs.getLong("price")
            );
            item.setDescription(
                rs.getString("description")
            );
            item.setCreatedAt(
                rs.getString("created_at")
            );

            result.add(item);
        }

        return result;
    }
}
