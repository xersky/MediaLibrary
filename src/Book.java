import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Book extends Document{

    final static String type = "BOOK";
    final static int typeID = 4;
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
    public void getInfo() {
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT book.ID, book.ID_DOC, document.TITLE, document.DESCRIPTION, document.NAME_OF_FILE, document.PATH, author.NAME, genre.NAME, book.ISBN, book.NBR_PAGES FROM book, document, author, genre WHERE document.ID = book.ID_DOC AND author.ID = document.ID_AUTHOR AND genre.ID = document.ID_GENRE AND book.ID = " + this.bookID + ";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                this.title = rs.getString("document.TITLE");
                this.description = rs.getString("document.DESCRIPTION");
                this.nameOfFile = rs.getString("document.NAME_OF_FILE");
                this.path = rs.getString("document.PATH");
                this.authorName = rs.getString("author.NAME");
                this.genreName = rs.getString("genre.NAME");
                this.isbn = rs.getString("book.ISBN");
                this.numberOfPages = rs.getInt("book.NBR_PAGES");
                this.docID = rs.getInt("book.ID_DOC");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
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

    public static void show(){
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
        System.out.println();

    }


    public void search() {
        this.getInfo();
        System.out.println(this.bookID + " - " + this.title + " - " + this.description + " - " + this.nameOfFile + " - " + this.path + " - " + this.authorName + " - " + this.genreName + " - " + this.isbn + " - " + this.numberOfPages);
    }


    public void add() {
        Scanner sc = new Scanner(System.in);

        this.addDocBasicInfo();
        System.out.println("Enter the ISBN: ");
        this.isbn = sc.next();
        System.out.println("Enter the number of pages: ");
        this.numberOfPages = sc.nextInt();

        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "UPDATE document SET ID_TYPE = " + typeID + " WHERE ID = " + this.docID + "; ";
            stmt.executeUpdate(query);
            query = "INSERT INTO book (ISBN, NBR_PAGES, ID_DOC) VALUES (\'" + this.isbn + "\' , " + this.numberOfPages + ", " + this.docID + ");";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }



    }

}

