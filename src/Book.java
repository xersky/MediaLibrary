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
    @Override
    public void show(){
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT book.ID, document.TITLE, document.DESCRIPTION, document.NAME_OF_FILE, document.PATH, author.NAME, genre.NAME, book.ISBN, book.NBR_PAGES FROM book, document, author, genre WHERE document.ID = book.ID_DOC AND author.ID = document.ID_AUTHOR AND genre.ID = document.ID_GENRE;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Book ID - Book Title - Book Description - Book Name of File - Book's path - Book's author's name - Book's genre - Book ISBN - Book number of pages");
            while(rs.next()){
                System.out.println(rs.getInt("book.ID") + " - " + rs.getString("document.TITLE") + " - " + rs.getString("document.DESCRIPTION") + " - " + rs.getString("document.NAME_OF_FILE") + " - " + rs.getString("document.PATH") + " - " + rs.getString("author.NAME") + " - " + rs.getString("genre.NAME") + " - " + rs.getString("book.ISBN") + " - " + rs.getString("book.NBR_PAGES"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }

    @Override
    public void search() {

    }

    public void add() {
        //tbd
    }
}

