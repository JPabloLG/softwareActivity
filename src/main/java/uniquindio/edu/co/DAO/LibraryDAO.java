package uniquindio.edu.co.DAO;

import uniquindio.edu.co.DB.JDBC;
import uniquindio.edu.co.Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    /**
     * Busca libros en la base de datos por cualquier combinación de autor, título o ISBN.
     * @param author El autor a buscar (parcial o completo). Null para ignorar.
     * @param title El título a buscar (parcial o completo). Null para ignorar.
     * @param isbn El ISBN exacto a buscar. Null para ignorar.
     * @return Una lista de libros que coinciden con los criterios.
     */
    public ArrayList<Book> searchBookAll(String author, String title, String isbn) {
        ArrayList<Book> searchedBooks = new ArrayList<>();
        // Esta consulta SQL ignora los parámetros que son nulos
        String sql = "SELECT * FROM book WHERE " +
                "(? IS NULL OR author LIKE ?) AND " +
                "(? IS NULL OR title LIKE ?) AND " +
                "(? IS NULL OR isbn = ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Preparamos los parámetros para la búsqueda LIKE (búsqueda parcial)
            String authorParam = (author == null) ? null : "%" + author + "%";
            String titleParam = (title == null) ? null : "%" + title + "%";

            // Asignamos los parámetros a la consulta. Cada '?' se llena en orden.
            stmt.setString(1, author);
            stmt.setString(2, authorParam);
            stmt.setString(3, title);
            stmt.setString(4, titleParam);
            stmt.setString(5, isbn);
            stmt.setString(6, isbn);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("isbn"),
                            rs.getInt("average_rate")
                    );
                    searchedBooks.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchedBooks;
    }

    public ArrayList<Book> searchBook(String search) {
        ArrayList<Book> searchedBooks = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE title = ? OR author = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, search);
            stmt.setString(2, search);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book b = new Book(rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("average_rate"));

                b.setId(rs.getInt("id")); // ✅ aquí se asigna el id
                searchedBooks.add(b);
            }
            return searchedBooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchedBooks;
    }
}
