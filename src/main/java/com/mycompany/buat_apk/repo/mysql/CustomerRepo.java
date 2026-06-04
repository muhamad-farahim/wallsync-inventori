package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.buat_apk.db.DbConnection;
import com.mycompany.buat_apk.domains.entities.customers.CreateCustomer;
import com.mycompany.buat_apk.domains.entities.customers.Customer;
import com.mycompany.buat_apk.domains.entities.customers.UpdateCustomer;
import com.mycompany.buat_apk.domains.repositories.CustomerRepository;

public class CustomerRepo implements CustomerRepository {

    @Override
    public Long createCustomerReturnId(CreateCustomer data) {
        String sql = "INSERT INTO customers (name, dob, subdistrict, phone) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, data.getName());
            pstmt.setDate(2, new java.sql.Date(data.getDob().getTime()));
            pstmt.setString(3, data.getSubdistrict());
            pstmt.setString(4, data.getPhone());
            
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, name, dob, subdistrict, phone, created_at FROM customers";
        
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getTimestamp("dob"), 
                    rs.getString("subdistrict"),
                    rs.getString("phone"),
                    rs.getTimestamp("created_at")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void updateCustomer(UpdateCustomer data) {
        String sql = "UPDATE customers SET name = ?, dob = ?, subdistrict = ?, phone = ? WHERE id = ?";
        
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, data.getName());
            pstmt.setDate(2, new java.sql.Date(data.getDob().getTime()));
            pstmt.setString(3, data.getSubdistrict());
            pstmt.setString(4, data.getPhone());
            pstmt.setLong(5, data.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomerById(Long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        String sql = "SELECT id, name, dob, subdistrict, phone, created_at FROM customers WHERE id = ?";
        
        try (Connection conn = DbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getTimestamp("dob"),
                        rs.getString("subdistrict"),
                        rs.getString("phone"),
                        rs.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Customer getCustomerByPhone(String phone) {

        String sql = """
            SELECT id, name, dob, subdistrict, phone, created_at
            FROM customers
            WHERE phone = ?
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, phone);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getTimestamp("dob"),
                        rs.getString("subdistrict"),
                        rs.getString("phone"),
                        rs.getTimestamp("created_at")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
