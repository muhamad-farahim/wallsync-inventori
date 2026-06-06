package com.mycompany.buat_apk.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.users.CreateUser;
import com.mycompany.buat_apk.domains.entities.users.UpdateUser;
import com.mycompany.buat_apk.domains.entities.users.User;
import com.mycompany.buat_apk.domains.repositories.UserRepository;

public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Long createUser(CreateUser data) {
        if (data == null || data.getUsername() == null || data.getUsername().trim().isEmpty()) {
            System.err.println("Validation failed: Username is required.");
            return 0L;
        }
        if (data.getPassword() == null || data.getPassword().trim().isEmpty()) {
            System.err.println("Validation failed: Password is required.");
            return 0L;
        }
        
        try {
            Long id = this.userRepo.createUserReturnId(data);
            return (id != null) ? id : 0L;
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return 0L;
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> list = this.userRepo.getAllUser();
            return (list != null) ? list : new ArrayList<>();
        } catch (SQLException e) {
            System.err.println("Error fetching all users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public User getUserById(Long id) {
        if (id == null || id <= 0) {
            System.err.println("Validation failed: Invalid user ID.");
            return null;
        }
        
        try {
            return this.userRepo.getUserById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching user by ID: " + e.getMessage());
            return null;
        }
    }

    public User getUserByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            System.err.println("Validation failed: Username cannot be blank.");
            return null;
        }
        
        try {
            return this.userRepo.getUserByUsername(username.trim());
        } catch (SQLException e) {
            System.err.println("Error fetching user by username: " + e.getMessage());
            return null;
        }
    }

    public boolean updateUser(UpdateUser data) {
        if (data == null || data.getId() == null || data.getId() <= 0) {
            System.err.println("Validation failed: Invalid user ID for update.");
            return false;
        }
        if (data.getUsername() == null || data.getUsername().trim().isEmpty()) {
            System.err.println("Validation failed: Updated username cannot be empty.");
            return false;
        }

        try {
            this.userRepo.updateUser(data);
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUserById(Long id) {
        if (id == null || id <= 0) {
            System.err.println("Validation failed: Invalid user ID for deletion.");
            return false;
        }
        
        try {
            this.userRepo.deleteUser(id);
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }
}
