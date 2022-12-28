package src.view;

import src.pojo.FrontPlayer;
import src.dao.Alter;
import src.pojo.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import static java.lang.Thread.sleep;

public class SelectRoomWindow extends WindowAdapter {
    public SelectRoomWindow() {
    }

    JFrame frame = new JFrame("游戏大厅");
    ImageIcon bg = new ImageIcon("res/img/room.jpg");
    JLabel back = new JLabel(bg);
    JLayeredPane layeredPane = new JLayeredPane();
    JPanel ground;
    JPanel p = new JPanel();
    JButton[] b = {new JButton("0号房间"),new JButton("1号房间"),new JButton("2号房间"),new JButton("3号房间"),new JButton("4号房间"),new JButton("5号房间"),new JButton("6号房间"),
             new JButton("7号房间"),new JButton("8号房间"),new JButton("9号房间"),new JButton("10号房间"),new JButton("11号房间")};
    JLabel[] roomNumLable=new JLabel[12];
    JLabel head=new JLabel("游戏大厅",JLabel.CENTER);
    Font font = new Font("宋体",Font.BOLD,20);
    Font font2 = new Font("宋体",Font.BOLD,15);
    int x = 450;
    int y = 210;
    int width = 650;
    int height = 450;

    public SelectRoomWindow(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void showSelectRoom(FrontPlayer frontPlayer){
        bg.setImage(bg.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        ground = (JPanel) frame.getContentPane();
        ground.add(back);
        layeredPane.add(ground,JLayeredPane.DEFAULT_LAYER);
        p.setOpaque(false);
        p.setBounds(0,0,width,height);
        layeredPane.add(p,JLayeredPane.MODAL_LAYER);
        head.setFont(new Font("宋体", Font.BOLD, 20));
        for(int i=0;i<12;i++){
            roomNumLable[i]=new JLabel("",JLabel.CENTER);
        }
        b[0].addActionListener(e -> frontPlayer.setSelectRoom("0"));
        b[1].addActionListener(e -> frontPlayer.setSelectRoom("1"));
        b[2].addActionListener(e -> frontPlayer.setSelectRoom("2"));
        b[3].addActionListener(e -> frontPlayer.setSelectRoom("3"));
        b[4].addActionListener(e -> frontPlayer.setSelectRoom("4"));
        b[5].addActionListener(e -> frontPlayer.setSelectRoom("5"));
        b[6].addActionListener(e -> frontPlayer.setSelectRoom("6"));
        b[7].addActionListener(e -> frontPlayer.setSelectRoom("7"));
        b[8].addActionListener(e -> frontPlayer.setSelectRoom("8"));
        b[9].addActionListener(e -> frontPlayer.setSelectRoom("9"));
        b[10].addActionListener(e -> frontPlayer.setSelectRoom("10"));
        b[11].addActionListener(e -> frontPlayer.setSelectRoom("11"));
        frame.addWindowListener(new SelectRoomWindow());

        p.setBorder(BorderFactory.createEmptyBorder(20,20,26,25));
        p.setLayout(new GridLayout(6,4,40,0));
        for(int i=0;i<4;i++){
            p.add(b[i]);
            b[i].setFont(font);
        }
        for(int i=0;i<4;i++){
            p.add(roomNumLable[i]);
            roomNumLable[i].setFont(font2);
            roomNumLable[i].setForeground(Color.WHITE);
        }
        for(int i=4;i<8;i++){
            p.add(b[i]);
            b[i].setFont(font);
        }
        for(int i=4;i<8;i++){
            p.add(roomNumLable[i]);
            roomNumLable[i].setFont(font2);
            roomNumLable[i].setForeground(Color.WHITE);
        }
        for(int i=8;i<12;i++){
            p.add(b[i]);
            b[i].setFont(font);
        }
        for(int i=8;i<12;i++){
            p.add(roomNumLable[i]);
            roomNumLable[i].setFont(font2);
            roomNumLable[i].setForeground(Color.WHITE);
        }
        frame.setLayeredPane(layeredPane);
        frame.setBounds(x,y,width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void close(){
        frame.setVisible(false);
    }

    public void refresh(String s){
        String[] info = s.split(",");
        for (int i = 0;i < 12;i++){
            this.roomNumLable[i].setText("已有"+info[i]+"个玩家");
        }
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
