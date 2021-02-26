import javax.swing.*;
import java.sql.*;

public class DatabaseController {

    static final DatabaseModel pog = new DatabaseModel();

    public static void dbConnection() {

        pog.setdbUrl("jdbc:mysql://localhost:3306/mediat?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        pog.setdbUsername("root");
        pog.setDbPassword("lolilol123");

        try {
            Connection conn = DriverManager.getConnection(pog.getdbUrl(),pog.getdbUsername(),pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "select * from author;";
            ResultSet rs = stmt.executeQuery(query);
            if ( rs.next() ) {
                JOptionPane.showMessageDialog(null, "La base de donn\u00E9es est charg\u00E9 avec succès !", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }


    }


    public static int dbQuery(int ID, String type) {

        int ID_DOC = 0;

        try {
            Connection conn = DriverManager.getConnection(pog.getdbUrl(),pog.getdbUsername(),pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "select ID_DOC from " + type + " WHERE ID = '" + ID +"' ;";
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


    public static String getPassword(String user) {
        String password = "";
        try {
            Connection conn = DriverManager.getConnection(pog.getdbUrl(),pog.getdbUsername(),pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT password FROM user WHERE username = '" + user + "' ;";
            ResultSet rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Probl�me du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return password;
    }


}