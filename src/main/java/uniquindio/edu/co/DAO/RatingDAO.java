package uniquindio.edu.co.DAO;

import uniquindio.edu.co.Model.Rating;
import java.util.List;

public interface RatingDAO {
    void save(Rating rating);
    List<Rating> findByBookId(int bookId);
    double getAverageRating(int bookId);
}
