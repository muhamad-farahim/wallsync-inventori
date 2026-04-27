package com.mycompany.buat_apk.domains.repositories;

import java.sql.SQLException;

import com.mycompany.buat_apk.domains.entities.users.User;

public interface UserRepository {
    public User getUserByUsername(String username) throws SQLException; 
}
