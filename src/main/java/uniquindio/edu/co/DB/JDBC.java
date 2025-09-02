package uniquindio.edu.co.DB;

import uniquindio.edu.co.Model.Book;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBC {

    private static Connection connection;
    private static String url;
    private static String user;
    private static String password;
    private static boolean propertiesLoaded = false;

    private static void loadProperties() {
        if (propertiesLoaded) return;

        try (InputStream input = JDBC.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

            propertiesLoaded = true;
        } catch (Exception e) {
            System.err.println("Failed to load DB properties: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        loadProperties();

        try {
            // Check if connection is valid
            if (connection != null && !connection.isClosed() && connection.isValid(5)) {
                return connection;
            }

            // Create a new connection if needed
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("DB connection failed: " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }



    public static class TestConnection {
        public static void main(String[] args) {
            try {
                Connection conn = JDBC.getConnection();
                System.out.println("Connected to DB!");
                conn.close();
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }
        }
    }
}
