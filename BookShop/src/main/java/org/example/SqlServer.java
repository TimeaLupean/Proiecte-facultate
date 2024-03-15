package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServer {

    public static void main(String[] args) {
        // JDBC URL, username, and password of SQL Server
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;database=database;trustServerCertificate=true";
        String username = "SA";
        String password = "Am2mere!";

        // Establish a connection
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to the SQL Server database");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection connect() throws SQLException {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;database=database;trustServerCertificate=true";
        String username = "SA";
        String password = "Am2mere!";
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        return connection;
    }

}