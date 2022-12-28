package src.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public static void insertAccount(String account, String password) throws SQLException {
        Statement st;
        ResultSet rs = null;
        Connection conn = tools.JDBCUtil.getConnection();
        String sql = "insert into users values (" + '\"' + account + '\"' + "," + '\"' + password + '\"' + "," + '\"' + "0" + '\"' + ");";
        System.out.println(sql);
        st = conn.createStatement();
        st.execute(sql);
        st.close();
        conn.close();
    }
}
