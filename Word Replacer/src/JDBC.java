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

    void queryUser(String userName) {
        try {
            sql = "SELECT username " +
                   " FROM users " +
                   "WHERE username == " + userName;
            conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getString("username") + "\t");
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
