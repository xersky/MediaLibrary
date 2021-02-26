import javax.swing.*;
import java.sql.*;

public class Book extends Document{

    final static String type = "BOOK";
    public int bookID;
    public String isbn;
    public int numberOfPages;

    public Book(){}
    public Book(String title, String description, String nameOfFile, String path, String authorName, String genreName, int bookID, String isbn, int numberOfPages) {
        super(title, description, nameOfFile, path, authorName, genreName);
        this.bookID = bookID;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
    }


    @Override
    public int getDocID() {
        return DatabaseController.dbQuery(this.bookID, Book.type);
    }

    public void modify(String title, String description, String nameOfFile, String path, String authorName, String genreName, int bookID, String isbn, int numberOfPages) {
        super.modify(title, description, nameOfFile, path, authorName, genreName);
        this.bookID = bookID;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
    }



    public void add() {
        //tbd
    }
}

