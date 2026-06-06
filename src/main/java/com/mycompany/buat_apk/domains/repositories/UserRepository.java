package com.mycompany.buat_apk.domains.repositories;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.users.CreateUser;
import com.mycompany.buat_apk.domains.entities.users.UpdateUser;
import com.mycompany.buat_apk.domains.entities.users.User;

public interface UserRepository {
    public User getUserByUsername(String username) throws SQLException; 
    public User getUserById(Long id) throws SQLException;
    public List<User> getAllUser() throws SQLException;
    public Long createUserReturnId(CreateUser data) throws SQLException;
    public void updateUser(UpdateUser data) throws SQLException;
    public void deleteUser(Long id) throws SQLException;
}
