package uniquindio.edu.co.DAO;

import uniquindio.edu.co.Model.Book;
import java.util.List;

public interface BookDAO {
    void save(Book book);
    Book findById(int id);
    Book findByIsbn(String isbn);
    List<Book> search(String author, String title, String isbn);
    List<Book> findAll();
    void update(Book book);
    void delete(int id);
}
