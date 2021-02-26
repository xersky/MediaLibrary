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


    public void add() {
        //tbd
    }
}
