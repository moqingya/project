package src.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Init {
    public void initRecord() throws SQLException {
        String account;
        String password;
        Statement st;
        Connection conn = tools.JDBCUtil.getConnection();
        String sql = "truncate table users";
        st = conn.createStatement();
        st.execute(sql);
        for (int i = 1 ; i < 300; i++) {
            account = "A" + i;
            password = "B" + i;
            sql = "insert into users values (" + '\"' + account + '\"' + "," + '\"' + password + '\"' + "," + '\"' + "0" + '\"' + ");";
            st.execute(sql);
        }
        tools.JDBCUtil.close(conn, st);
    }
}
