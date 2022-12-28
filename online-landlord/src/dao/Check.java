package src.dao;

import src.pojo.Constant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Check {
    public  int checkLogin(String account, String password) throws SQLException {
        Statement st;
        ResultSet rs = null;
        Connection conn = tools.JDBCUtil.getConnection();
        String sql = "select * from users;";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {
            if (Objects.equals(rs.getString(1), account)) {
                if(Objects.equals(rs.getString(2), password)) {
                    tools.JDBCUtil.close(conn, st, rs);
                    return Constant.LOGIN_SUCCEED;
                } else {
                    tools.JDBCUtil.close(conn, st, rs);
                    return Constant.LOGIN_ERROR_PASSWORD_IS_FALSE;
                }
            }
        }
        tools.JDBCUtil.close(conn, st, rs);
        return Constant.LOGIN_ERROR_ACCOUNT_NOT_EXIST;
    }
    public  int checkRegister(String account, String password) throws SQLException {
        if(account == null || account.equals("") || password == null || password.equals(""))return  Constant.REGISTER_DEFAULT;
        Statement st;
        ResultSet rs = null;
        Connection conn = tools.JDBCUtil.getConnection();
        String sql = "select * from users;";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {
            if (Objects.equals(rs.getString(1), account)) {
                tools.JDBCUtil.close(conn, st, rs);
                return Constant.REGISTER_DEFAULT;
            }
        }
        tools.JDBCUtil.close(conn, st, rs);
        return Constant.REGISTER_SUCCEED;
    }

    public void printTableData() throws SQLException {
        Statement st;
        ResultSet rs = null;
        Connection conn = tools.JDBCUtil.getConnection();
        String sql = "select * from users;";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println("用户名:" + rs.getString(1) + ",密码:" + rs.getString(2));
        }
        tools.JDBCUtil.close(conn, st, rs);
    }
}
