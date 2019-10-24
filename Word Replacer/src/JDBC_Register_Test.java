import java.util.Scanner;

public class JDBC_Register_Test {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        JDBC test = new JDBC();
        test.queryAll();
        System.out.println("Enter new username");
        String userName = read.next();
        System.out.println("Enter new password");
        String userPass = read.next();

        test.registerUser(2, userName, userPass);
        test.queryAll();
    }
}
