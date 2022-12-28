package src.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
    public void deleteUser() throws SQLException {
        Statement st;
        Connection conn = tools.JDBCUtil.getConnection();
        String accountStr;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的用户名");
        accountStr = sc.nextLine();
        String sql = "delete from users where account = " + '\"' + accountStr + '\"' + ";" ;
        System.out.println(sql);
        st = conn.createStatement();
        st.execute(sql);
        st.close();
        conn.close();
    }
}
