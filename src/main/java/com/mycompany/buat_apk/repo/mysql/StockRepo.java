package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.buat_apk.domains.entities.stocks.CreateStock;
import com.mycompany.buat_apk.domains.repositories.StockRepository;
import com.mysql.cj.xdevapi.PreparableStatement;

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
        else if (cusId != null && price != null) {

            query = "INSERT INTO stocks (user_id, product_id, quantity, price, customer_id)" +
                " VALUES (?, ?, ?, ?, ?)";

            stmt = this.makePreperaredStatement(query);

            stmt.setLong(1, s.getUserId());
            stmt.setLong(2, s.getProductId());
            stmt.setLong(3, s.getQuantity());
            stmt.setLong(4, s.getPrice());
            stmt.setLong(5, s.getCustomerId());

        }else {
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
}
