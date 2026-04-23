package com.mycompany.buat_apk.config;

import io.github.cdimascio.dotenv.Dotenv;

public class AppConfig {
    
    public static String databaseUrl;
    public static String databaseUsername;
    public static String databasePassword;
    

    public static void setEnvironmentVariable(Dotenv dotenv) {
        AppConfig.databaseUrl = dotenv.get("DB_URL");
        AppConfig.databaseUsername = dotenv.get("DB_USERNAME");
        AppConfig.databasePassword = dotenv.get("DB_PASSWORD");
    }
}
