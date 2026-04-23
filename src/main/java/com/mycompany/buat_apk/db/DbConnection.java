package com.mycompany.buat_apk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mycompany.buat_apk.config.AppConfig;

public class DbConnection {

    public static Connection getConnection() throws SQLException {
        String dbUrl = AppConfig.databaseUrl;
        String dbUsername = AppConfig.databaseUsername;
        String dbPassword = AppConfig.databasePassword;
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
