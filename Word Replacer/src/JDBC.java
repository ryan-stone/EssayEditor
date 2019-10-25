/* This class requires a JDBC plugin. For MySQL, that's connector/j. IntelliJ can install this plugin automatically
 * if you use Eclipse, you'll have to download it from this URL
 *
 * https://dev.mysql.com/downloads/connector/j/
 *
 * and set it up in your Eclipse project manually.
 */
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

class JDBC {
    private String serverURL, serverUser, serverPassword, sql;
    private Connection conn;
    private ResultSet userName;


    JDBC() {
        //filestream objects have a hard time reading .properties files. Class loaders are more reliable
        try (InputStream is = JDBC.class.getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(is);
            serverURL      = prop.getProperty("url");
            serverUser     = prop.getProperty("user");
            serverPassword = prop.getProperty("password");
            conn     = null;
            userName = null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void queryAll() {
        try {
            sql = "SELECT username, password " +
                    "FROM USER_INFO ";
            conn = DriverManager.getConnection(serverURL, serverUser, serverPassword);

            Statement stmt = conn.createStatement();
            userName = stmt.executeQuery(sql);

            while(userName.next()) {
                System.out.println(userName.getString("username") + "\t"
                                 + userName.getString("password"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } //try-catch
        } //try-catch-finally

    }

    boolean validateUser(String userName) {
        boolean userFlag = false;
        try {
            sql = "SELECT username " +
                    "FROM USER_INFO " +
                    "WHERE username = '" + userName + "'";
            conn = DriverManager.getConnection(serverURL, serverUser, serverPassword);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString("username").equals(userName)) {
                    System.out.println(rs.getString("username") + "\t");
                    userFlag = true;
                }
            }
            //processing here
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } //try-catch-finallyu
        return userFlag;
    }

    boolean validatePassword(String userPass, String userName) {
        boolean userFlag = false;
        try {
            sql = "SELECT password " +
                    "FROM USER_INFO " +
                    "WHERE password = '" + userPass + "' " +
                    "AND username = '" + userName + "'";

            conn = DriverManager.getConnection(serverURL, serverUser, serverPassword);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString("password").equals(userPass)) {
                    System.out.println(rs.getString("password") + "\t");
                    userFlag = true;
                }
            }
            //processing here
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } //try-catch-finallyu
        return userFlag;
    }

    void registerUser(String userName, String userPass) {
        sql = "INSERT INTO USER_INFO " +
                "VALUES(?,?)";
        int statementId = 0;

        try {
            conn = DriverManager.getConnection(serverURL, serverUser, serverPassword);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, userName);
            pstmt.setString(2, userPass);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                this.userName = pstmt.getGeneratedKeys();
                if (this.userName.next())
                    statementId = this.userName.getInt(1);
            }
            //processing here
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
