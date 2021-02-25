import javax.swing.*;
import java.sql.*;

public class Audio extends Document{
    public int audioID;
    public String duration;

    public Audio (String title, String description, String nameOfFile, String path, String authorName, String genreName, int audioID, String duration) {
        super(title, description, nameOfFile, path, authorName, genreName);
        this.audioID = audioID;
        this.duration = duration;
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
            String query = "select ID_DOC from AUDIO WHERE ID = " + this.audioID + ";";
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




    public void modify(String title, String description, String nameOfFile, String path, String authorName, String genreName, int audioID, String duration) {
        super.modify(title, description, nameOfFile, path, authorName, genreName);
        this.audioID = audioID;
        this.duration = duration;
    }


    public void add() {

    }
}
