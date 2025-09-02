package uniquindio.edu.co.Model;

import uniquindio.edu.co.DB.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Library {

    private String name;
    private static Library instance;
    private ArrayList<Book> books;
    private ArrayList<User> users;


    private Library(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library("Library JP");
        }
        return instance;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void searchBook(String search) {
        ArrayList<Book> searchedBooks = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().equals(search)||b.getAuthor().equals(search)) {
                searchedBooks.add(b);
            }
        }
        System.out.println(searchedBooks);
    }

    public ArrayList<Book> searchBookAll(String author, String title, String isbn) {
        return this.books.stream()
                .filter(book -> author == null || book.getAuthor().equalsIgnoreCase(author))
                .filter(book -> title==null || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> isbn == null || book.getIsbn().equals(isbn))
                .collect(Collectors.toCollection(ArrayList::new));


    }


    public void rateBook(User user, Book book, int rating){
        if(rating < 1 || rating > 5){
            System.out.println("Por favor califique correctamente");
        }

        if(book.getRates() == null) {
            book.setRates(new ArrayList<>());
        }
        Rating rat = new Rating(rating, user, book);
        book.getRates().add(rat);

        int s = 0;
        for(Rating r : book.getRates()){
            s += r.getRate();
        }
        int average = s / book.getRates().size();
        book.setAverageRate(average);

        saveRatingToDatabase(rat);
    }

    private void saveRatingToDatabase(Rating rating) {
        String sql = "INSERT INTO ratings (user_id, book_id, rate) VALUES (?, ?, ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rating.getUser().getId());
            stmt.setInt(2, rating.getBook().getId());
            stmt.setInt(3, rating.getRate());

            stmt.executeUpdate();
            System.out.println("Rating guardado en la BD con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
