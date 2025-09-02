package uniquindio.edu.co.DAO;

import uniquindio.edu.co.DB.JDBC;
import uniquindio.edu.co.Model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, averageRate) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setDouble(4, book.getAverageRate());

            stmt.executeUpdate();

            // Recuperar el ID generado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getInt(1));
            }

            System.out.println("✅ Libro guardado en BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book findById(int id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getInt("averageRate")

                );
                //  book.setId(rs.getInt("id")); // ✅ aquí se asigna el id
                //  return book;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book findByIsbn(String isbn) {
        String sql = "SELECT * FROM book WHERE isbn = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getInt("average_rate")

                );
                // book.setId(rs.getInt("id")); // ✅ aquí se asigna el id
                // return book;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Búsqueda avanzada y flexible por cualquier combinación de criterios.
     * Este método construye una consulta SQL que ignora los filtros que se pasan como null.
     *
     * @param author El autor a buscar (parcial o completo). Se ignora si es null.
     * @param title El título a buscar (parcial o completo). Se ignora si es null.
     * @param isbn El ISBN exacto a buscar. Se ignora si es null.
     * @return Una lista de libros que coinciden con los criterios proporcionados.
     */

    //Método de Helen
    @Override
    public List<Book> search(String author, String title, String isbn) {
        List<Book> books = new ArrayList<>();
        // Esta consulta SQL ignora los filtros para los parámetros que son nulos
        String sql = "SELECT * FROM book WHERE " +
                "(? IS NULL OR author LIKE ?) AND " +
                "(? IS NULL OR title LIKE ?) AND " +
                "(? IS NULL OR isbn = ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Preparamos los parámetros para la búsqueda LIKE (búsqueda parcial)
            String authorParam = (author == null) ? null : "%" + author + "%";
            String titleParam = (title == null) ? null : "%" + title + "%";

            // Asignamos los valores a cada '?' en la consulta, en orden
            stmt.setString(1, author);       // Para el '? IS NULL' del autor
            stmt.setString(2, authorParam);  // Para el 'author LIKE ?'

            stmt.setString(3, title);        // Para el '? IS NULL' del título
            stmt.setString(4, titleParam);   // Para el 'title LIKE ?'

            stmt.setString(5, isbn);         // Para el '? IS NULL' del ISBN
            stmt.setString(6, isbn);         // Para el 'isbn = ?'

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("isbn"),
                            rs.getInt("average_rate")
                    );

                    book.setId(rs.getInt("id"));
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book b = new Book(rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("average_rate"));

                b.setId(rs.getInt("id")); // ✅ aquí se asigna el id
                books.add(b);
            }
            return books;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, isbn = ?, average_rate = ? WHERE id = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setDouble(4, book.getAverageRate());
            stmt.setInt(5, book.getId());

            stmt.executeUpdate();
            System.out.println("✅ Libro actualizado en BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM book WHERE id = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Libro eliminado de BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
