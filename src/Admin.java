import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
public class Admin extends User {

    private String password;


    public void dbLogin(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter ur credentials:");
        this.username = sc.next();
        this.password = sc.next();

        if (DatabaseController.getPassword(this.username).equals(this.password)) {
            System.out.println("pog");
        } else System.out.println("not pog");
    }

}

