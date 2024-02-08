package org.example.db;

import lombok.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor
@Getter
@Setter
public class DBConnection {
    private String host;
    private String port;
    private String user;
    private String password;
    private String dbName;

    public Connection getConnection() {
        Connection connection = null;
        String connectionString = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        try {
            connection = DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
        return connection;
    }
}
