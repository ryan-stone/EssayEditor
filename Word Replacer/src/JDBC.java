/* This class requires a JDBC plugin. For MySQL, that's connector/j. IntelliJ can install this plugin automatically
 * if you use Eclipse, you'll have to download it from this URLu
 *
 * https://dev.mysql.com/downloads/connector/j/u
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

    boolean queryUser(String userName) {
        boolean userFlag = false;
        try {
            sql =  "SELECT username " +
                    "FROM users " +
                    "WHERE username = '" + userName + "'";
            conn = DriverManager.getConnection(url,user,password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                if(rs.getString("username").equals(userName)) {
                    System.out.println(rs.getString("username") + "\t");
                    userFlag = true;
                }
            }
            //processing here
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } //try-catch-finallyu
        return userFlag;
    }

    boolean queryPassword(String userPass) {
        boolean userFlag = false;
        try {
            sql =  "SELECT pass " +
                    "FROM password " +
                    "WHERE pass = '" + userPass + "'";
            conn = DriverManager.getConnection(url,user,password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                if(rs.getString("pass").equals(userPass)) {
                    System.out.println(rs.getString("pass") + "\t");
                    userFlag = true;
                }
            }
            //processing here
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } //try-catch-finallyu
        return userFlag;
    }

    int insert(int index, String word) {
        sql = "INSERT INTO grammar " +
                "VALUES(?,?)";
        int candidateId = 0;

        try {
            conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,index);
            pstmt.setString(2, word);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1) {
                rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);
            }
            //processing here
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return candidateId;
    }
}
