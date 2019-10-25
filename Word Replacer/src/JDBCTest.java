import java.util.Scanner;

public class JDBCTest {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        JDBC test = new JDBC();
        final int NUMBER_OF_ATTEMPTS = 3;
        String userName, userPass;

        System.out.println("Enter username");
        userName = read.next();


        if(test.validateUser(userName)) {
            System.out.println("User exists in database");
            System.out.println("Enter password");
            userPass = read.next();

            for (int i = 0; i < NUMBER_OF_ATTEMPTS; i++) {
                if (test.validatePassword(userPass, userName)) {
                    System.out.println("Password is correct! Access granted");
                    break;
                } else {
                    System.out.println("Incorrect password");
                    userPass = read.next();
                }
            }
        }

        else
            System.out.println("User does not exist in database");

    }
}
