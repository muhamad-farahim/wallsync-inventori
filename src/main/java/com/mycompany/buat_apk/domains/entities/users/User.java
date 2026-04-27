package com.mycompany.buat_apk.domains.entities.users;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String name;
    private String username;
    private String password;
    private LocalDateTime createdAt;

    // Default Constructor
    public User() {
    }

    // Constructor for creating a new user (ID and Timestamp handled by DB)
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Full Constructor for retrieving from DB
    public User(long id, String name, String username, String password, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
