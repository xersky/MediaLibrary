import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Scanner;

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

    public Document(){}
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


    public abstract void getInfo();

    public abstract int getDocID();

    public abstract void search();

    public void delete() {
        //Capturing the path and name of file from the database to delete the document from the disk
        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT PATH, NAME_OF_FILE FROM document WHERE ID = " + this.getDocID() + " ;";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                this.path = rs.getString("PATH");
                this.nameOfFile = rs.getString("NAME_OF_FILE");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        //deleting the captured file
        try {
            Files.deleteIfExists(Paths.get(this.path + this.nameOfFile));
        }
        catch(NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e) {
            System.out.println("Invalid permissions.");
        }

        //deleting the document from the database
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

    public static void show(){
        Book.show();
        Audio.show();
        Video.show();
        Image.show();
    }

    public abstract void add();

    public void addDocBasicInfo(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the title: ");
        this.title = sc.nextLine();
        System.out.println("Enter the description: ");
        this.description = sc.nextLine();
        System.out.println("Enter the name of file: ");
        this.nameOfFile = sc.nextLine();
        System.out.println("Enter the path: ");
        this.path = sc.next();
        this.chooseAuthor();
        this.chooseGenre();

        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO document (TITLE, DESCRIPTION, NAME_OF_FILE, PATH, ID_AUTHOR, ID_GENRE, ID_TYPE) VALUES (\'" + this.title + "\',\'" + this.description + "\',\'" + this.nameOfFile + "\',\'" + this.path + "\',\'" + this.authorID + "\',\' "+ this.genreID + "\',5);";
            stmt.executeUpdate(query);
            query = "SELECT @@identity as pog;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                this.docID = rs.getInt("pog");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }

    public void modifyDocBasicInfo(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the new title: ");
        this.title = sc.nextLine();
        System.out.println("Enter the new description: ");
        this.description = sc.nextLine();
        System.out.println("Enter the new name of file: ");
        this.nameOfFile = sc.nextLine();
        System.out.println("Enter the new path: ");
        this.path = sc.next();
        this.chooseAuthor();
        this.chooseGenre();

        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "UPDATE document SET TITLE = '" + this.title + "', DESCRIPTION = '" + this.description + "' , NAME_OF_FILE = '" + this.nameOfFile + "' , PATH = '" + this.path + "' , ID_AUTHOR = " + this.authorID + ", ID_GENRE = " + this.genreID + " WHERE ID = " + this.docID + ";";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }


    public void chooseAuthor(){

        Scanner sc = new Scanner(System.in);

        int Selection;

        System.out.println("Enter the ID of an author or add a new one: ");


        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM author;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Author ID - Author Name");
            while(rs.next()){
                System.out.println(rs.getString("ID") + " - " + rs.getString("NAME"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        System.out.println("Enter other : (1- select author/2- add new author");
        Selection = sc.nextInt();

        if(Selection == 1) {
            System.out.println("Enter Author ID: ");
            this.authorID = sc.nextInt();
            sc.nextLine();
            try {
                Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
                Statement stmt = conn.createStatement();
                String query = "SELECT name FROM author WHERE ID = " + this.authorID + " ;";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    this.authorName = rs.getString("NAME");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }


        }else {
            System.out.println("Enter the author name: ");
            sc.nextLine();
            this.authorName = sc.nextLine();

            try {
                Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
                Statement stmt = conn.createStatement();
                String query = "INSERT INTO author (NAME) VALUES (\'" + this.authorName + "\');";
                stmt.executeUpdate(query);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            try {
                Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
                Statement stmt = conn.createStatement();
                String query = "SELECT ID FROM author WHERE NAME = \'" + this.authorName + "\' ;";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    this.authorID = rs.getInt("ID");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }
    public void chooseGenre(){

        Scanner sc = new Scanner(System.in);

        int Selection;

        System.out.println("Enter the ID of a genre or add a new one: ");


        try {
            Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM genre;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Genre ID - Genre Name");
            while(rs.next()){
                System.out.println(rs.getString("ID") + " - " + rs.getString("NAME"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        System.out.println("Enter other : (1- select Genre/2- add new Genre)");
        Selection = sc.nextInt();
        if(Selection == 1) {
            System.out.println("Enter Genre ID: ");
            this.genreID = sc.nextInt();
            sc.nextLine();
            try {
                Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
                Statement stmt = conn.createStatement();
                String query = "SELECT name FROM genre WHERE ID = " + this.genreID + " ;";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    this.genreName = rs.getString("NAME");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }


        }else {
            System.out.println("Enter the genre name: ");
            sc.nextLine();
            this.genreName = sc.nextLine();
            try {
                Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
                Statement stmt = conn.createStatement();
                String query = "INSERT INTO genre (NAME) VALUES (\'" + this.genreName + "\');";
                stmt.executeUpdate(query);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            try {
                Connection conn = DriverManager.getConnection(DatabaseController.pog.getdbUrl(),DatabaseController.pog.getdbUsername(), DatabaseController.pog.getdbPassword()) ;
                Statement stmt = conn.createStatement();
                String query = "SELECT ID FROM genre WHERE NAME = \'" + this.genreName + "\' ;";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    this.genreID = rs.getInt("ID");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Problème du chargement de la base de donn\u00E9es !\nVeuillez d\u00E9marrez le serveur mysql avant de lancer l'application\n"+ e,"Erreur", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }






}
