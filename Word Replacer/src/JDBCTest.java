import java.util.Scanner;

public class JDBCTest {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        JDBC test = new JDBC();
        final int NUMBER_OF_ATTENMPTS = 3;
        String userName, password;

        System.out.println("Enter username");
        userName = read.next();


        if(test.queryUser(userName)) {
            System.out.println("User exists in database");
            System.out.println("Enter password");
            password = read.next();
            for (int i = 0; i < NUMBER_OF_ATTENMPTS; i++) {
                if (test.queryPassword(password))
                    System.out.println("Password is correct! Access granted");
                else {
                    System.out.println("Incorrect password");
                    password = read.next();
                }
            }
        }

        else
            System.out.println("User does not exist in database");

    }
}
