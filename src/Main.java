import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int looper, insideLooper, repeat = 0;
        Book book = new Book();
        Audio audio = new Audio();
        Video video = new Video();
        Image image = new Image();

        DatabaseController.dbConnection();
        LoginPage.Login();
       do {
           ApplicationPage.menuApp();

        looper = sc.nextInt();
        switch (looper) {
            case 1 :
                Document.show();
                repeat = 1;
                break;
            case 2:
                Book.show();
                repeat = 1;
                break;
            case 3 :
                Image.show();
                repeat = 1;
                break;
            case 4:
                Video.show();
                repeat = 1;
                break;
            case 5:
                Audio.show();
                repeat = 1;
                break;
            case 6:
                ApplicationPage.menuSearchDoc();
                insideLooper = sc.nextInt();
                switch (insideLooper) {
                    case 1:
                        System.out.println("Entrer the ID of a book: ");
                        book.bookID = sc.nextInt();
                        book.search();
                        break;
                    case 2:
                        System.out.println("Entrer the ID of a image: ");
                        image.imageID = sc.nextInt();
                        image.search();
                        break;
                    case 3:
                        System.out.println("Entrer the ID of a audio: ");
                        audio.audioID = sc.nextInt();
                        audio.search();
                        break;
                    case 4:
                        System.out.println("Entrer the ID of a video: ");
                        video.videoID = sc.nextInt();
                        video.search();
                        break;
                    case 0:
                        repeat = 1;
                        break;

                }
                break;
            case 7:
                ApplicationPage.menuDisplayDoc();
                insideLooper = sc.nextInt();
                switch (insideLooper) {
                    case 1:
                        System.out.println("Entrer the ID of a book: ");
                        book.bookID = sc.nextInt();
                        book.display();
                        break;
                    case 2:
                        System.out.println("Entrer the ID of an image: ");
                        image.imageID = sc.nextInt();
                        image.display();
                        break;
                    case 3:
                        System.out.println("Entrer the ID of an audio: ");
                        audio.audioID = sc.nextInt();
                        audio.display();
                        break;
                    case 4:
                        System.out.println("Entrer the ID of an video: ");
                        video.videoID = sc.nextInt();
                        video.display();
                        break;
                    case 0:
                        repeat = 1;
                        break;

                }
                break;
            case 8:
                ApplicationPage.menuModifyDoc();
                insideLooper = sc.nextInt();
                switch (insideLooper) {
                    case 1:
                        System.out.println("Entrer the ID of a book: ");
                        book.bookID = sc.nextInt();
                        book.modify();
                        book.search();
                        break;
                    case 2:
                        System.out.println("Entrer the ID of a image: ");
                        image.imageID = sc.nextInt();
                        image.modify();
                        image.search();
                        break;
                    case 3:
                        System.out.println("Entrer the ID of a audio: ");
                        audio.audioID = sc.nextInt();
                        audio.modify();
                        audio.search();
                        break;
                    case 4:
                        System.out.println("Entrer the ID of a video: ");
                        video.videoID = sc.nextInt();
                        video.modify();
                        video.search();
                        break;
                    case 0:
                        repeat = 1;
                        break;
                }
                break;
            case 9:
                ApplicationPage.menuDeleteDoc();
                insideLooper = sc.nextInt();
                switch (insideLooper) {
                    case 1:
                        System.out.println("Entrer the ID of a book: ");
                        book.bookID = sc.nextInt();
                        book.delete();
                        break;
                    case 2:
                        System.out.println("Entrer the ID of a image: ");
                        image.imageID = sc.nextInt();
                        image.delete();
                        break;
                    case 3:
                        System.out.println("Entrer the ID of a audio: ");
                        audio.audioID = sc.nextInt();
                        audio.delete();
                        break;
                    case 4:
                        System.out.println("Entrer the ID of a video: ");
                        video.videoID = sc.nextInt();
                        video.delete();
                        break;
                    case 0:
                        repeat = 1;
                        break;
                }
                break;
            case 10:
                ApplicationPage.menuAddDoc();
                insideLooper = sc.nextInt();
                switch (insideLooper) {
                    case 1:
                        book.add();
                        book.search();
                        break;
                    case 2:
                        image.add();
                        image.search();
                        break;
                    case 3:
                        audio.add();
                        audio.search();
                        break;
                    case 4:
                        video.add();
                        video.search();
                        break;
                    case 0:
                        repeat = 1;
                        break;
                }
                break;
            case 0:
                repeat = 0;
                break;
        }
       }while(repeat == 1);
    }

}