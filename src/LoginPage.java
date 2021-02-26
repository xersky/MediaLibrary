public class LoginPage {

   public static void Login(){
       System.out.println("------------------Login Page------------------");
       Admin admin = new Admin();
       admin.dbLogin();
       System.out.println("----------------------------------------------");
    }
}
