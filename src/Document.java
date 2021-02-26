import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public abstract class Document {

    //Les attributs
    public int docID;
    public String title;
    public String description;
    public String nameOfFile;
    public String path;
    public int authorID;
    public String authorName;
    public int genreID;
    public String genreName;


    public Document(String title, String description, String nameOfFile, String path, String authorName, String genreName) {
        this.title = title;
        this.description = description;
        this.nameOfFile = nameOfFile;
        this.path = path;
        this.authorName = authorName;
        this.genreName = genreName;
    }


    public void display() {
        String path = this.path;
        String nameOfFile = this.nameOfFile;

        try {
           Process process = Runtime.getRuntime().exec("cmd /c " + nameOfFile, null, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modify(String title, String description, String nameOfFile, String path, String authorName, String genreName) {
        this.title = title;
        this.description = description;
        this.nameOfFile = nameOfFile;
        this.path = path;
        this.authorName = authorName;
        this.genreName = genreName;
    }

    public abstract int getDocID();


    public void delete() {

        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM document WHERE ID = '" + this.getDocID() + "' ;";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }

    public abstract void add();

}
