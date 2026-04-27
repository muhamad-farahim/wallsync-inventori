package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.buat_apk.domains.entities.users.User;
import com.mycompany.buat_apk.domains.repositories.UserRepository;

public class UserRepo implements UserRepository {
    private final Connection conn;

	public UserRepo(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT id, name, username, password, created_at FROM users WHERE username=?;";

        PreparedStatement stmt = this.conn.prepareStatement(query);
        
        stmt.setString(1, username);
        User user = new User();
    
        try (ResultSet res = stmt.executeQuery()) {
            if(res.next()) {
            user.setId(res.getLong("id"));
            user.setUsername(res.getString("username"));
            user.setName(res.getString("name"));
            user.setPassword(res.getString("password"));
            user.setCreatedAt(res.getTimestamp("created_at").toLocalDateTime());
            }
        }

		return user;
	}
     
}
