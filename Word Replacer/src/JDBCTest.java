import java.util.Scanner;

public class JDBCTest {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        JDBC test = new JDBC();
        String userName;

        System.out.println("Enter username");
        userName = read.next();


        test.queryUser(userName);
    }
}
