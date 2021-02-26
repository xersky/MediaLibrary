import javax.swing.*;
import java.sql.*;

public class Image extends Document {
    final static String type = "IMAGE";
    public int imageID;
    public String resolution;

    public Image(){}
    public Image (String title, String description, String nameOfFile, String path, String authorName, String genreName, int imageID, String resolution) {
        super(title, description, nameOfFile, path, authorName, genreName);
        this.imageID = imageID;
        this.resolution = resolution;
    }


    public int getDocID() {
        return DatabaseController.dbQuery(this.imageID, Image.type);
    }



    public void modify(String title, String description, String nameOfFile, String path, String authorName, String genreName, int imageID, String resolution) {
        super.modify(title, description, nameOfFile, path, authorName, genreName);
        this.imageID = imageID;
        this.resolution = resolution;
    }

    @Override
    public void show(){
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

    }

    @Override
    public void search() {

    }

    public void add() {
        //tbd
    }
}
