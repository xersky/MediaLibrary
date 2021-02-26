public class Main {

    public static void main(String[] args) {

        DatabaseController.dbConnection();
        LoginPage.Login();
        Book book = new Book();
        book.show();
        Image image = new Image();
        image.show();
        Audio audio = new Audio();
        audio.show();
        Video video = new Video();
        video.show();

    }

}