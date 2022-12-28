package src.controller;

/**
 * @author: 姚崇崇
 *
 */


import src.dao.Alter;
import src.dao.Check;
import src.dao.Get;
import src.dao.Insert;
import src.pojo.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login_Register_Page implements Runnable{
    public static int sign = -1;
    public  void  mainPage() {
        JFrame jf=new JFrame("玩家登录");
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.setSize(400,160);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel=new JPanel();

        JLabel txt=new JLabel("账号：");
        JLabel password=new JLabel("密码：");
        //创建账号框和密码框,并设置框的初始大小,然后放到面板中
        final JTextField textField=new JTextField();
        final JPasswordField passwordField=new JPasswordField();
        textField.setPreferredSize(new Dimension(300,30));
        passwordField.setPreferredSize(new Dimension(300,30));
        jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        JButton buttonLogin=new JButton("登录");
        JButton buttonRegister=new JButton("注册");
        JButton buttonReset=new JButton("重置");
        panel.add(txt);
        panel.add(textField);
        panel.add(password);
        panel.add(passwordField);

        panel.add(buttonLogin);
        panel.add(buttonRegister);
        panel.add(buttonReset);
        jf.setContentPane(panel);
        jf.setVisible(true);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = textField.getText(); //文本框输入的账号
                String password = new String(passwordField.getPassword()); //文本框输入的密码
//                int sign = 0;
                try {
                    sign = new Check().checkLogin(account, password);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if(new Get().getState(account).equals("1")) {
                        JOptionPane.showMessageDialog(
                                jf,
                                "玩家已登录！",
                                "提示",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }else if (sign == Constant.LOGIN_SUCCEED) {
                        JOptionPane.showMessageDialog(
                                jf,
                                "登录成功！",
                                "提示",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        new Alter().alterStatement(account, password, Get.getState(account));
                        Constant.account = account;
                        Constant.password = password;
                        Thread thread = new Thread(new Client());
                        thread.start();
                        jf.dispose();
    //                    jf.setVisible(false);
                    } else if (sign == Constant.LOGIN_ERROR_ACCOUNT_NOT_EXIST) {
                        JOptionPane.showMessageDialog(
                                jf,
                                "账号不存在！",
                                "错误",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else if (sign == Constant.LOGIN_ERROR_PASSWORD_IS_FALSE) {
                        JOptionPane.showMessageDialog(
                                jf,
                                "密码错误！",
                                "错误",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = textField.getText();
                String password = new String(passwordField.getPassword());
//                int sign = 0;
                try {
                    sign = new Check().checkRegister(account, password);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (sign == Constant.REGISTER_SUCCEED) {
                    JOptionPane.showMessageDialog(
                            jf,
                            "注册成功！",
                            "提示",
                            JOptionPane.INFORMATION_MESSAGE

                    );
                    try {
                        new Insert().insertAccount(account, password);
                        textField.setText("");
                        passwordField.setText("");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else if (sign == Constant.REGISTER_DEFAULT) {
                    JOptionPane.showMessageDialog(
                            jf,
                            "该账号已注册！",
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                passwordField.setText("");
            }
        });
    }

    public int getSign() {
        return sign;
    }

    @Override
    public void run() {
            mainPage();
    }

}

