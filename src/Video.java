import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Video extends Document{
    final static String type = "VIDEO";
    final static int typeID = 2;
    public int videoID;
    public String duration;
    public String quality;

    public Video(){}

    @Override
    public void getInfo() {
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT video.ID, video.ID_DOC, document.TITLE, document.DESCRIPTION, document.NAME_OF_FILE, document.PATH, author.NAME, genre.NAME, video.DURATION, video.QUALITY FROM video, document, author, genre WHERE document.ID = video.ID_DOC AND author.ID = document.ID_AUTHOR AND genre.ID = document.ID_GENRE AND video.ID = " + this.videoID + ";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                this.title = rs.getString("document.TITLE");
                this.description = rs.getString("document.DESCRIPTION");
                this.nameOfFile = rs.getString("document.NAME_OF_FILE");
                this.path = rs.getString("document.PATH");
                this.authorName = rs.getString("author.NAME");
                this.genreName = rs.getString("genre.NAME");
                this.duration = rs.getString("video.DURATION");
                this.quality = rs.getString("video.QUALITY");
                this.docID = rs.getInt("video.ID_DOC");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

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


    public static void show(){
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

        System.out.println();
    }

    @Override
    public void search() {
        this.getInfo();
        System.out.println(this.videoID + " - " + this.title + " - " + this.description + " - " + this.nameOfFile + " - " + this.path + " - " + this.authorName + " - " + this.genreName + " - " + this.duration + " - " + this.quality);
    }

    public void add() {
        Scanner sc = new Scanner(System.in);

        this.addDocBasicInfo();
        System.out.println("Enter the duration: ");
        this.duration = sc.next();
        System.out.println("Enter the quality: ");
        this.quality = sc.next();

        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "UPDATE document SET ID_TYPE = " + typeID + "  WHERE ID = " + this.docID + "; ";
            stmt.executeUpdate(query);
            query = "INSERT INTO video (DURATION, QUALITY, ID_DOC) VALUES (\'" + this.duration + "\' , \'" + this.quality + "\' , " + this.docID + ");";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

}
