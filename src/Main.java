public class Main {

    public static void main(String[] args) {

        DatabaseController.dbConnection();
        Admin admin = new Admin();
        admin.dbLogin();


    }


}