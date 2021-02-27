import javax.swing.event.DocumentEvent;

public class Main {

    public static void main(String[] args) {
        Book book = new Book();
        Audio audio = new Audio();
        Video video = new Video();
        Image image = new Image();
        DatabaseController.dbConnection();
        LoginPage.Login();
        Document.show();

        book.addDocBasicInfo();

    }

}