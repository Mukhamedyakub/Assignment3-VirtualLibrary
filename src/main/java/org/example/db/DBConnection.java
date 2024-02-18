package org.example.db;

import lombok.*;
import org.example.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    public static final Connection connection = getConnection();

    public static Connection getConnection() {
        if(connection != null){
            return connection;
        }
        Connection con = null;
        String connectionString = "jdbc:postgresql://" + Config.properties.getProperty("db.host") +
                ":" + Config.properties.getProperty("db.port") + "/" + Config.properties.getProperty("db.name");

        try {
            con = DriverManager.getConnection(connectionString, Config.properties.getProperty("db.user"),
                    Config.properties.getProperty("db.password"));
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
        return con;
    }
}

