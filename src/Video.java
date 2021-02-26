import javax.swing.*;
import java.sql.*;

public class Video extends Document{
    final static String type = "VIDEO";
    public int videoID;
    public String duration;
    public String quality;

    public Video(){}
    public Video (String title, String description, String nameOfFile, String path, String authorName, String genreName, int videoID, String duration, String quality) {
        super(title, description, nameOfFile, path, authorName, genreName);
        this.videoID = videoID;
        this.duration = duration;
        this.quality = quality;
    }

    @Override
    public int getDocID() {
       return DatabaseController.dbQuery(this.videoID, Video.type);
    }



    public void modify(String title, String description, String nameOfFile, String path, String authorName, String genreName, int videoID, String duration, String quality) {
        super.modify(title, description, nameOfFile, path, authorName, genreName);
        this.videoID = videoID;
        this.duration = duration;
        this.quality = quality;
    }

    @Override
    public void show(){
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT video.ID, document.TITLE, document.DESCRIPTION, document.NAME_OF_FILE, document.PATH, author.NAME, genre.NAME, video.DURATION, video.QUALITY FROM video, document, author, genre WHERE document.ID = video.ID_DOC AND author.ID = document.ID_AUTHOR AND genre.ID = document.ID_GENRE;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Video ID - Video Title - Video Description - Video Name of File - Video's path - Video's author's name - Video's genre - Video Duration - Video Quality");
            while(rs.next()){
                System.out.println(rs.getInt("video.ID") + " - " + rs.getString("document.TITLE") + " - " + rs.getString("document.DESCRIPTION") + " - " + rs.getString("document.NAME_OF_FILE") + " - " + rs.getString("document.PATH") + " - " + rs.getString("author.NAME") + " - " + rs.getString("genre.NAME") + " - " + rs.getString("video.DURATION") + " - " + rs.getString("video.QUALITY"));
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
