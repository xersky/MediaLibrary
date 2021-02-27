import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Audio extends Document{
    final static String type = "AUDIO";
    final static int typeID = 1;
    public int audioID;
    public String duration;

    public Audio(){}

    @Override
    public void getInfo() {
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT audio.ID, audio.ID_DOC, document.TITLE, document.DESCRIPTION, document.NAME_OF_FILE, document.PATH, author.NAME, genre.NAME, audio.DURATION FROM audio, document, author, genre WHERE document.ID = audio.ID_DOC AND author.ID = document.ID_AUTHOR AND genre.ID = document.ID_GENRE AND audio.ID = " + this.audioID + ";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                this.title = rs.getString("document.TITLE");
                this.description = rs.getString("document.DESCRIPTION");
                this.nameOfFile = rs.getString("document.NAME_OF_FILE");
                this.path = rs.getString("document.PATH");
                this.authorName = rs.getString("author.NAME");
                this.genreName = rs.getString("genre.NAME");
                this.duration = rs.getString("audio.DURATION");
                this.docID = rs.getInt("audio.ID_DOC");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public Audio (String title, String description, String nameOfFile, String path, String authorName, String genreName, int audioID, String duration) {
        super(title, description, nameOfFile, path, authorName, genreName);
        this.audioID = audioID;
        this.duration = duration;
    }

    @Override
    public int getDocID() {
        return DatabaseController.dbQuery(this.audioID, Audio.type);
    }


    public void modify(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Modify the document: ");

        this.modifyDocBasicInfo();
        System.out.println("Enter the new duration: ");
        this.duration= sc.next();

        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "UPDATE image SET DURATION = \"" + this.duration + "\" WHERE ID = " + this.audioID  + "; ";
            stmt.executeUpdate(query);
            query = "SELECT ID_DOC FROM audio WHERE ID = " + this.audioID + " ;";
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

        System.out.println();
    }

    @Override
    public void search() {
        this.getInfo();
        System.out.println(this.audioID + " - " + this.title + " - " + this.description + " - " + this.nameOfFile + " - " + this.path + " - " + this.authorName + " - " + this.genreName + " - " + this.duration);
    }

    public void add() {
        Scanner sc = new Scanner(System.in);

        this.addDocBasicInfo();
        System.out.println("Enter the duration: ");
        this.duration= sc.next();
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "UPDATE document SET ID_TYPE = " + typeID + " WHERE ID = " + this.docID + "; ";
            stmt.executeUpdate(query);
            query = "INSERT INTO audio (DURATION, ID_DOC) VALUES (\"" + this.duration + "\" , " + this.docID + ");";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }
}
