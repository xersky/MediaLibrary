import java.util.Scanner;
public class Admin extends User {

    protected String password;

    public void dbLogin(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your username:");
        this.username = sc.next();
        System.out.println("Enter your password:");
        this.password = sc.next();

        while (!DatabaseController.getPassword(this.username).equals(this.password)){
            System.out.println("Invalid username or password!");
            System.out.println("Enter your username:");
            this.username = sc.next();
            System.out.println("Enter your password:");
            this.password = sc.next();
        }
        System.out.println("You've successfully logged in!");
    }

}

