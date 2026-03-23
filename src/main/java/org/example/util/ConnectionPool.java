package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

    static {
        try {
            Class.forName(ApplicationConfig.get("db.driver")); // 🔥 IMPORTANT
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    ApplicationConfig.get("db.url"),
                    ApplicationConfig.get("db.username"),
                    ApplicationConfig.get("db.password")
            );
        } catch (Exception e) {
            e.printStackTrace(); // debugging
            throw new RuntimeException("DB Connection Failed", e);
        }
    }
}