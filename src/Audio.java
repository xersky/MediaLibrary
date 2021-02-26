import javax.swing.*;
import java.sql.*;

public class Audio extends Document{
    final static String type = "AUDIO";
    public int audioID;
    public String duration;

    public Audio(){}
    public Audio (String title, String description, String nameOfFile, String path, String authorName, String genreName, int audioID, String duration) {
        super(title, description, nameOfFile, path, authorName, genreName);
        this.audioID = audioID;
        this.duration = duration;
    }

    @Override
    public int getDocID() {
        return DatabaseController.dbQuery(this.audioID, Audio.type);
    }


    public void modify(String title, String description, String nameOfFile, String path, String authorName, String genreName, int audioID, String duration) {
        super.modify(title, description, nameOfFile, path, authorName, genreName);
        this.audioID = audioID;
        this.duration = duration;
    }

    @Override
    public void show(){
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT audio.ID, document.TITLE, document.DESCRIPTION, document.NAME_OF_FILE, document.PATH, author.NAME, genre.NAME, audio.DURATION FROM audio, document, author, genre WHERE document.ID = audio.ID_DOC AND author.ID = document.ID_AUTHOR AND genre.ID = document.ID_GENRE;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Audio ID - Audio Title - Audio Description - Audio Name of File - Audio's path - Audio's author's name - Audio's genre - Audio Duration");
            while(rs.next()){
                System.out.println(rs.getInt("audio.ID") + " - " + rs.getString("document.TITLE") + " - " + rs.getString("document.DESCRIPTION") + " - " + rs.getString("document.NAME_OF_FILE") + " - " + rs.getString("document.PATH") + " - " + rs.getString("author.NAME") + " - " + rs.getString("genre.NAME") + " - " + rs.getString("audio.DURATION"));
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

    }
}