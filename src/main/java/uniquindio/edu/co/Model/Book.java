package uniquindio.edu.co.Model;

import java.util.ArrayList;

public class Book {

    private int id;
    private String title;
    private String author;
    private String isbn;
    private ArrayList<String> reviews;
    private int averageRate;
    private ArrayList<Integer> rates;

    public Book(String author, String isbn, String title, int averageRate) {
        this.author = author;
        this.isbn = isbn;
        this.title = title;
        this.averageRate = averageRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Integer> rates) {
        this.rates = rates;
    }

    public int getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(int averageRate) {
        this.averageRate = averageRate;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", reviews=" + reviews +
                ", averageRate=" + averageRate +
                ", rates=" + rates +
                '}';
    }
}
