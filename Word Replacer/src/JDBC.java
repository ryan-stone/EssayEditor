/* This class requires a JDBC plugin. For MySQL, that's connector/j. IntelliJ can install this plugin automatically
 * if you use Eclipse, you'll have to download it from this URL
 *
 * https://dev.mysql.com/downloads/connector/j/
 *
 * and set it up in your Eclipse project manually.
 */
import java.sql.*;

public class JDBC {
    String url, user, password, sql;
    Connection conn;
    ResultSet rs;


    JDBC() {
        url = "jdbc:mysql://localhost:3306/setest";
        user = "root";
        password = "eraser";
        conn = null;
        rs = null;


    }

    void queryAll() {
        try {
            sql = "SELECT username, pass " +
                    "FROM users, password " +
                    "WHERE users.user_id = password.user_id";
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getString("username") + "\t"
                                 + rs.getString("pass"));
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

    boolean queryUser(String userName) {
        boolean userFlag = false;
        try {
            sql = "SELECT username " +
                    "FROM users " +
                    "WHERE username = '" + userName + "'";
            conn = DriverManager.getConnection(url, user, password);

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

    boolean queryPassword(String userPass) {
        boolean userFlag = false;
        try {
            sql = "SELECT pass " +
                    "FROM password " +
                    "WHERE pass = '" + userPass + "'";
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString("pass").equals(userPass)) {
                    System.out.println(rs.getString("pass") + "\t");
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

    void registerUser(int userId, String userName, String userPass) {
        sql = "INSERT INTO users " +
                "VALUES(?,?)";
        insert(userId, userName, sql);
        sql = "INSERT INTO password " +
                "VALUES(?,?)";
        insert(userId, userPass, sql);
    }


    private int insert(int id, String field, String localSql) {
        int statementId = 0;

        try {
            conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(localSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setString(2, field);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    statementId = rs.getInt(1);
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
        return statementId;
    }
}
