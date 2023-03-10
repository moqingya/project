package src.view;

import src.pojo.FrontPlayer;
import src.dao.Alter;
import src.pojo.Constant;
import src.dao.Get;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class StartWindow extends WindowAdapter {
    JFrame frame = new JFrame("斗地主");
    JLayeredPane layeredPane = new JLayeredPane();
    ImageIcon back = new ImageIcon("res/img/start_back2.jpg");
    JLabel bg = new JLabel(back);
    JPanel pb = new JPanel();
    JButton b1 = new JButton("开始游戏");
    JButton b2 = new JButton("退出游戏");
    ImageIcon start = new ImageIcon("res/img/start.png");
    ImageIcon exit = new ImageIcon("res/img/exit.png");
    int x = 450;
    int y = 210;
    int width = 650;
    int height = 450;
//    int btn_width = 250;
//    int btn_height = 80;
    public StartWindow(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void show(FrontPlayer frontPlayer) {
        back.setImage(back.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        b1.setIcon(start);
        b1.setContentAreaFilled(false);
        b1.setBorder(null);
        b2.setIcon(exit);
        b2.setContentAreaFilled(false);
        b2.setBorder(null);
        layeredPane.setLayout(null);
        layeredPane.setBounds(0, 0, width, height);
        b1.setBounds(width / 2 - start.getIconWidth() / 2, height / 2 - 40, start.getIconWidth(), 60);
        b2.setBounds(width / 2 - exit.getIconWidth() / 2, height / 2 + 40, exit.getIconWidth(), 60);
        pb = (JPanel) frame.getContentPane();
        pb.add(bg);
        b1.setOpaque(false);
        b2.setOpaque(false);
        layeredPane.add(b1, JLayeredPane.MODAL_LAYER);
        layeredPane.add(b2, JLayeredPane.MODAL_LAYER);
        layeredPane.add(pb, JLayeredPane.DEFAULT_LAYER);
        frame.setLayeredPane(layeredPane);
        b1.addActionListener(e -> {
            frontPlayer.setInStartWindow(false);
            this.close();
        }); //打开至大厅窗口
        //退出游戏
        b2.addActionListener(e -> {
            try {
                new Alter().alterStatement(Constant.account, Constant.password, Get.getState(Constant.account));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.exit(0);
        });
        frame.addWindowListener(new SelectRoomWindow());
        frame.setBounds(x, y, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
    }

    public void open() {
        frame.setVisible(true);
    }
    public int[] getLocate(){
        Rectangle locate = this.frame.getBounds();
        int[] point = new int[2];
        point[0] = locate.x;
        point[1] = locate.y;
        return point;
    }
    public void windowClosing(WindowEvent arg0) {
        try {
            new Alter().alterStatement(Constant.account, Constant.password, "1");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
