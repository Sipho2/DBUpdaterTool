package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Database.Server;

public class Main2 {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true";
        String username = "sa"; // Replace with your SQL Server username
        String password = "password"; // Replace with your SQL Server password
        Server app = new Server.Builder()
        						.setPassword(password)
        						.setUserName(username)
        						.setServerName("localhost")
        						.build();
        try {
            // Try to connect to the database
            Connection connection = DriverManager.getConnection(app.getServerUrl(), app.getUserName(), app.getPassword());
            System.out.println(app.Authenticate());
            System.out.println("Connection successful!");
            connection.close();
        } catch (SQLException e) {
            // Print error if connection fails
            System.out.println("Connection failed: " + e.getMessage());
        } 
    }
}
