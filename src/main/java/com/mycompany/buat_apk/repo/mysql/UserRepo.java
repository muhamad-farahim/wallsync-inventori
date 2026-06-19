package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.users.CreateUser;
import com.mycompany.buat_apk.domains.entities.users.UpdateUser;
import com.mycompany.buat_apk.domains.entities.users.User;
import com.mycompany.buat_apk.domains.enums.SortBy;
import com.mycompany.buat_apk.domains.repositories.UserRepository;

public class UserRepo implements UserRepository {
    private final Connection conn;

    public UserRepo(final Connection conn) {
        this.conn = conn;
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT id, name, username, password, created_at FROM users WHERE username = ?;";
        
        try (PreparedStatement stmt = this.conn.prepareStatement(query)) {
            stmt.setString(1, username);
            
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    User user = new User();
                    user.setId(res.getLong("id"));
                    user.setUsername(res.getString("username"));
                    user.setName(res.getString("name"));
                    user.setPassword(res.getString("password"));
                    user.setCreatedAt(res.getTimestamp("created_at").toLocalDateTime());
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        String query = "SELECT id, name, username, password, created_at FROM users WHERE id = ?;";
        
        try (PreparedStatement stmt = this.conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    User user = new User();
                    user.setId(res.getLong("id"));
                    user.setUsername(res.getString("username"));
                    user.setName(res.getString("name"));
                    user.setPassword(res.getString("password"));
                    user.setCreatedAt(res.getTimestamp("created_at").toLocalDateTime());
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, username, password, created_at FROM users;";
        
        try (PreparedStatement stmt = this.conn.prepareStatement(query);
             ResultSet res = stmt.executeQuery()) {
            
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setUsername(res.getString("username"));
                user.setName(res.getString("name"));
                user.setPassword(res.getString("password"));
                user.setCreatedAt(res.getTimestamp("created_at").toLocalDateTime());
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public Long createUserReturnId(CreateUser data) throws SQLException {
        String query = "INSERT INTO users (name, username, password) VALUES (?, ?, ?);";
        
        try (PreparedStatement stmt = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getName());
            stmt.setString(2, data.getUsername());
            stmt.setString(3, data.getPassword());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void updateUser(UpdateUser data) throws SQLException {
        boolean shouldUpdatePassword = data.getPassword() != null && !data.getPassword().trim().isEmpty();

        StringBuilder queryBuilder = new StringBuilder("UPDATE users SET name = ?, username = ?");
        if (shouldUpdatePassword) {
            queryBuilder.append(", password = ?");
        }
        queryBuilder.append(" WHERE id = ?;");

        try (PreparedStatement stmt = this.conn.prepareStatement(queryBuilder.toString())) {
            int paramIndex = 1;

            stmt.setString(paramIndex++, data.getName());
            stmt.setString(paramIndex++, data.getUsername());

            if (shouldUpdatePassword) {
                stmt.setString(paramIndex++, data.getPassword());
            }

            stmt.setLong(paramIndex, data.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?;";

        try (PreparedStatement stmt = this.conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<User> searchUsers(String query) throws SQLException {
        List<User> users = new ArrayList<>();
        boolean hasFilter = query != null && !query.trim().isEmpty();
        String sql = "SELECT id, name, username, password, created_at FROM users"
                + (hasFilter ? " WHERE name LIKE ? OR username LIKE ?" : "")
                + " ORDER BY id ASC;";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            if (hasFilter) {
                String wildcard = "%" + query.trim() + "%";
                stmt.setString(1, wildcard);
                stmt.setString(2, wildcard);
            }

            try (ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    User user = new User();
                    user.setId(res.getLong("id"));
                    user.setUsername(res.getString("username"));
                    user.setName(res.getString("name"));
                    user.setPassword(res.getString("password"));
                    user.setCreatedAt(res.getTimestamp("created_at").toLocalDateTime());
                    users.add(user);
                }
            }
        }
        return users;
    }

    @Override
    public List<User> getAllUsersSorted(SortBy sortBy) throws SQLException {
        List<User> users = new ArrayList<>();

        String orderBy = switch (sortBy) {
            case ID -> "id ASC";
            case NAME_ASC -> "name ASC";
            case USERNAME_ASC -> "username ASC";
            case CREATED_AT_DESC -> "created_at DESC";
        };

        String sql = "SELECT id, name, username, password, created_at FROM users ORDER BY " + orderBy + ";";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet res = stmt.executeQuery()) {

            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setUsername(res.getString("username"));
                user.setName(res.getString("name"));
                user.setPassword(res.getString("password"));
                user.setCreatedAt(res.getTimestamp("created_at").toLocalDateTime());
                users.add(user);
            }
        }
        return users;
    }
}
