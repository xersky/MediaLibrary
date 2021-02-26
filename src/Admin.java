import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
public class Admin extends User {

    public String password;


    public void dbLogin(){
        String user,password;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter ur credentials:");
        user = sc.next();
        password = sc.next();

        if (DatabaseController.getPassword(user).equals(password)) {
            System.out.println("pog");
        } else System.out.println("not pog");
    }

}

