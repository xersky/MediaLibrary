public class LoginPage {

   public static void Login(){
       System.out.println("------------------Login Page------------------");

       //starting a session
       Admin admin = new Admin();
       admin.dbLogin();
       System.out.println("----------------------------------------------");
    }
}
