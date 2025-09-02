package uniquindio.edu.co.Model;

public class Rating {

    private int id;
    private int rate;
    private User user;
    private Book book;

    public Rating(int rate, User user, Book book) {
        this.rate = rate;
        this.user = user;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rate=" + rate +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
