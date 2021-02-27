import javax.swing.event.DocumentEvent;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        Audio audio = new Audio();
        Video video = new Video();
        Image image = new Image();
        DatabaseController.dbConnection();
        LoginPage.Login();
        Document.show();

        System.out.println("Enter the ID of the book to modify: ");
        book.bookID = sc.nextInt();
        book.display();


    }

}