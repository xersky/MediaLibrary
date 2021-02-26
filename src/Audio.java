import javax.swing.*;
import java.sql.*;

public class Audio extends Document{
    final static String type = "AUDIO";
    public int audioID;
    public String duration;

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


    public void add() {

    }
}
