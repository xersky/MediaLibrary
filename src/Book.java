import javax.swing.*;
import java.sql.*;

public class Book extends Document{

    public int bookID;
    public String isbn;
    public int numberOfPages;

    public Book(String title, String description, String nameOfFile, String path, String authorName, String genreName, int bookID, String isbn, int numberOfPages) {
        super(title, description, nameOfFile, path, authorName, genreName);
        this.bookID = bookID;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
    }



    public void display() {
        //tbd
    }

    @Override
    public int getDocID() {
        int ID_DOC = 0;
        DatabaseModel pog = new DatabaseModel();
        try {
            Connection conn = DriverManager.getConnection(pog.getdbUrl(),pog.getdbUrl(),pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "select ID_DOC from BOOK WHERE ID = " + this.bookID + ";";
            ResultSet rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                ID_DOC = rs.getInt("ID_DOC");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Probl�me du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return ID_DOC;
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

