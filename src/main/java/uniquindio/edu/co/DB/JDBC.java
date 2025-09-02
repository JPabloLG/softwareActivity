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

    public static List<Book> searchBook(String search) {
        List<Book> searchedBooks = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title = ? OR author = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, search);
            stmt.setString(2, search);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("averageRate")
                );
                searchedBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Mostrar resultados
        for (Book b : searchedBooks) {
            System.out.println(b.getTitle() + " - " + b.getAuthor());
        }
        return searchedBooks;
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
