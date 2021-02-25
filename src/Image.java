import javax.swing.*;
import java.sql.*;

public class Image extends Document {
    public int imageID;
    public String resolution;

    public Image (String title, String description, String nameOfFile, String path, String authorName, String genreName, int imageID, String resolution) {
        super(title, description, nameOfFile, path, authorName, genreName);
        this.imageID = imageID;
        this.resolution = resolution;
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
            String query = "select ID_DOC from IMAGE WHERE ID = " + this.imageID + ";";
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



    public void modify(String title, String description, String nameOfFile, String path, String authorName, String genreName, int imageID, String resolution) {
        super.modify(title, description, nameOfFile, path, authorName, genreName);
        this.imageID = imageID;
        this.resolution = resolution;
    }


    public void add() {
        //tbd
    }
}
