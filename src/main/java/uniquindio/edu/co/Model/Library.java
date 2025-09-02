package uniquindio.edu.co.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public void searchBook(String searchName) {
        for (Book b : books) {
            //if (b.getTitle().equals(books.getTitle())) {

            }
        }
    }
}
