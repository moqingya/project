package src.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Alter {
    public  void alterStatement(String account, String password, String state) throws SQLException {
        Statement st;
        ResultSet rs = null;
        Connection conn = tools.JDBCUtil.getConnection();
        String sql = null;
        String altsql1 = "update users set state = " + '\"' + "1" + '\"' + " where state = " + '\"' + "0" + '\"' + " and account = "
                + '\"' +  account + '\"' + ";";;
        String altsql0 = "update users set state = " + '\"' + "0" + '\"' + " where state = " + '\"' + "1" + '\"' + " and account = "
                + '\"' +  account + '\"' + ";";;
        if(state.equals("0")) sql = altsql1;
            else  if(state.equals("1")) sql = altsql0;
        st = conn.createStatement();
        st.execute(sql);
        if(state.equals("0")) {
            System.out.println("已登录游戏");
        } else {
            System.out.println("已退出登录");
        }
    }
}
