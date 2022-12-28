package src.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Get {
    public static String getState(String account) throws SQLException {
        Statement st;
        ResultSet rs = null;
        Connection conn = tools.JDBCUtil.getConnection();
        String sql = "select * from users;";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {
            if (Objects.equals(rs.getString(1), account)) {
               return rs.getString(3);
            }
        }
        tools.JDBCUtil.close(conn,st);
        return "-1";
    }
}
