package uniquindio.edu.co.Model;

import java.util.ArrayList;

public class Book {

    private int id;
    private String title;
    private String author;
    private String isbn;
    private ArrayList<Rating> rates;
    private int averageRate;

    public Book(String title, String author, String isbn, int averageRate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.averageRate = averageRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Rating> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rating> rates) {
        this.rates = rates;
    }

    public int getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(int averageRate) {
        this.averageRate = averageRate;
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
                ", averageRate=" + averageRate +
                '}';
    }
}
