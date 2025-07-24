package crudwebapp;
 // Adjust package name as per your project

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/crud_db";
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = ""; // Your MySQL password

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Ensure the JDBC driver is loaded (optional for modern JDBC, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            // Common errors: Access denied (wrong user/pass), Unknown database, Can't connect to MySQL server
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}