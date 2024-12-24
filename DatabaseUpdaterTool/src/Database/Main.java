package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true";
        String username = "sa"; // Replace with your SQL Server username
        String password = "password"; // Replace with your SQL Server password

        try {
            // Try to connect to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            connection.close();
        } catch (SQLException e) {
            // Print error if connection fails
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
