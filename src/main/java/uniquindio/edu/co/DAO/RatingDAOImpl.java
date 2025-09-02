package uniquindio.edu.co.DAO;

import uniquindio.edu.co.DB.JDBC;
import uniquindio.edu.co.Model.Rating;
import uniquindio.edu.co.Model.Book;
import uniquindio.edu.co.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAOImpl implements RatingDAO {

    @Override
    public void save(Rating rating) {
        String sql = "INSERT INTO rating (user_id, book_id, rate) VALUES (?, ?, ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rating.getUser().getId());
            stmt.setInt(2, rating.getBook().getId());
            stmt.setInt(3, rating.getRate());

            stmt.executeUpdate();
            System.out.println("✅ Rating guardado en BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Rating> findByBookId(int bookId) {
        List<Rating> ratings = new ArrayList<>();
        String sql = "SELECT id, user_id, book_id, rate FROM rating WHERE book_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Rating r = new Rating(
                        rs.getInt("rate"),
                        new User(rs.getInt("user_id"), "N/A", "N/A", "N/A"),
                        new Book("N/A", "N/A", "N/A", 0)
                );
                r.setId(rs.getInt("id"));
                r.getBook().setId(rs.getInt("book_id"));
                ratings.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }

    @Override
    public double getAverageRating(int bookId) {
        String sql = "SELECT AVG(rate) AS avg_rate FROM rating WHERE book_id = ?";
        double avg = 0;

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                avg = rs.getDouble("avg_rate");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avg;
    }
}
