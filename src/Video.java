import javax.swing.*;
import java.sql.*;

public class Video extends Document{
    final static String type = "VIDEO";
    public int videoID;
    public String duration;
    public String quality;

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



    public void add() {
        //tbd
    }

}
