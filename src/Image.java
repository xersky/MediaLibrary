import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Image extends Document {
    final static String type = "IMAGE";
    final static int typeID = 3;
    public int imageID;
    public String resolution;

    public Image(){}

    @Override
    public void getInfo() {
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT image.ID, image.ID_DOC, document.TITLE, document.DESCRIPTION, document.NAME_OF_FILE, document.PATH, author.NAME, genre.NAME, image.RESOLUTION FROM image, document, author, genre WHERE document.ID = image.ID_DOC AND author.ID = document.ID_AUTHOR AND genre.ID = document.ID_GENRE AND image.ID = " + this.imageID + ";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                this.title = rs.getString("document.TITLE");
                this.description = rs.getString("document.DESCRIPTION");
                this.nameOfFile = rs.getString("document.NAME_OF_FILE");
                this.path = rs.getString("document.PATH");
                this.authorName = rs.getString("author.NAME");
                this.genreName = rs.getString("genre.NAME");
                this.resolution = rs.getString("image.RESOLUTION");
                this.docID = rs.getInt("image.ID_DOC");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }



    public int getDocID() {
        return DatabaseController.dbQuery(this.imageID, Image.type);
    }



    public void modify(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Modify the document: ");

        System.out.println("Enter the new resolution: ");
        this.resolution = sc.next();
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "UPDATE image SET RESOLUTION = \"" + this.resolution + "\" WHERE ID = " + this.imageID  + "; ";
            stmt.executeUpdate(query);
            query = "SELECT ID_DOC FROM image WHERE ID = " + this.imageID + " ;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                this.docID = rs.getInt("ID_DOC");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.modifyDocBasicInfo();
    }


    public static void show(){
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT image.ID, document.TITLE, document.DESCRIPTION, document.NAME_OF_FILE, document.PATH, author.NAME, genre.NAME, image.RESOLUTION FROM image, document, author, genre WHERE document.ID = image.ID_DOC AND author.ID = document.ID_AUTHOR AND genre.ID = document.ID_GENRE;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Image ID - Image Title - Image Description - Image Name of File - Image's path - Image's author's name - Image's genre - Image Resolution");
            while(rs.next()){
                System.out.println(rs.getInt("image.ID") + " - " + rs.getString("document.TITLE") + " - " + rs.getString("document.DESCRIPTION") + " - " + rs.getString("document.NAME_OF_FILE") + " - " + rs.getString("document.PATH") + " - " + rs.getString("author.NAME") + " - " + rs.getString("genre.NAME") + " - " + rs.getString("image.RESOLUTION"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        System.out.println();

    }

    @Override
    public void search() {
        this.getInfo();
        System.out.println(this.imageID + " - " + this.title + " - " + this.description + " - " + this.nameOfFile + " - " + this.path + " - " + this.authorName + " - " + this.genreName + " - " + this.resolution);
    }

    public void add() {
        Scanner sc = new Scanner(System.in);

        this.addDocBasicInfo();
        System.out.println("Enter the resolution: ");
        this.resolution = sc.next();
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "UPDATE document SET ID_TYPE = " + typeID + " WHERE ID = " + this.docID + "; ";
            stmt.executeUpdate(query);
            query = "INSERT INTO image (RESOLUTION, ID_DOC) VALUES (\"" + this.resolution  + "\", " + this.docID + ");";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }
}
