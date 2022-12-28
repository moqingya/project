package src.view;

import src.pojo.FrontPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RoomWindow {
    private boolean isOn = false;
    private boolean isReady = false;
    JFrame frame = new JFrame("游戏房间");
    JPanel p = new JPanel();
    JLayeredPane layeredPane = new JLayeredPane();

    ImageIcon GG = new ImageIcon("res/img/GG.png");
    ImageIcon GG_ready1 = new ImageIcon("res/img/GG_ready1.png");
    ImageIcon GG_ready2 = new ImageIcon("res/img/GG_ready2.png");
    ImageIcon noPerson = new ImageIcon("res/img/defaultHeader.png");
    ImageIcon desk = new ImageIcon("res/img/desk.jpg");
    ImageIcon unready = new ImageIcon("res/img/unready.png");
    ImageIcon ready = new ImageIcon("res/img/ready.png");

    ImageIcon on = new ImageIcon("res/img/on.png");
    ImageIcon off = new ImageIcon("res/img/off.png");

    ImageIcon quit =  new ImageIcon("res/img/quit.png");
    JLabel bg = new JLabel(desk);
    JPanel ground;
    JButton b_ready = new JButton(ready);

    JButton b_on = new JButton(on);
    JButton b_off = new JButton(off);

    JButton b_exit = new JButton(quit);
    JLabel player1 = new JLabel(noPerson);
    JLabel player2 = new JLabel(noPerson);
    JPanel btn_group = new JPanel();
    JPanel btn_group1 = new JPanel();
    int x = 450;
    int y = 210;
    int width = 650;
    int height = 450;


    public RoomWindow(int x, int y){
        this.x = x;
        this.y = y;
    }


    public void show(FrontPlayer frontPlayer){
        ready.setImage(ready.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
        unready.setImage(unready.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));

        on.setImage(on.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
        off.setImage(off.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));

        quit.setImage(quit.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
        desk.setImage(desk.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        ground = (JPanel) frame.getContentPane();
        ground.add(bg);
        layeredPane.add(ground,JLayeredPane.DEFAULT_LAYER);
        p.setBorder(BorderFactory.createEmptyBorder(0,10,20,30));
        p.setLayout(new BorderLayout());
        p.setBounds(0,0,this.width,this.height);

        btn_group.add(b_ready);
        btn_group.add(b_exit);

        btn_group1.add(b_on);
        btn_group1.add(b_off);

        b_ready.setContentAreaFilled(false);
        b_ready.setBorder(null);
        b_exit.setContentAreaFilled(false);
        b_exit.setBorder(null);


        b_on.setContentAreaFilled(false);
        b_on.setBorder(null);
        b_off.setContentAreaFilled(false);
        b_off.setBorder(null);


        btn_group.setOpaque(false);


        btn_group1.setOpaque(false);


        p.add(btn_group,BorderLayout.NORTH);
        p.add(btn_group,BorderLayout.NORTH);

        b_on.addActionListener(e -> {
            if (!this.isOn){
                frontPlayer.setState("on");
                this.isOn = true;
                b_on.setIcon(off);
            } else {
                frontPlayer.setState("off");
                this.isOn= false;
                b_on.setIcon(on);
            }
        });

        b_ready.addActionListener(e -> {
            if (!this.isReady){
                frontPlayer.setState("ready");
                this.isReady = true;
                b_ready.setIcon(unready);
            } else {
                frontPlayer.setState("unready");
                this.isReady = false;
                b_ready.setIcon(ready);
            }
        });

        b_exit.addActionListener(e -> frontPlayer.setState("quit"));

        p.add(player1,BorderLayout.WEST);
        p.add(player2,BorderLayout.EAST);
        p.setOpaque(false);
        layeredPane.add(p,JLayeredPane.MODAL_LAYER);
        frame.setLayeredPane(layeredPane);
//        frame.add(p);
        frame.setBounds(x,y,width,height);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frontPlayer.setState("quit");

            }
        });
    }
    public void close(){
        frame.setVisible(false);
    }
    public void refresh(String s){
        if(s.charAt(1)=='0'){
            player1.setIcon(noPerson);
        }
        else if(s.charAt(1)=='1') {
            player1.setIcon(GG);
        }
        else if(s.charAt(1)=='2')
            player1.setIcon(GG_ready1);
        if(s.charAt(3)=='0'){
            player2.setIcon(noPerson);
        }
        else if(s.charAt(3)=='1'){
            player2.setIcon(GG);
        }
        else if(s.charAt(3)=='2')
            player2.setIcon(GG_ready2);
    }

    public int[] getLocate(){
        Rectangle locate = this.frame.getBounds();
        int[] point = new int[2];
        point[0] = locate.x;
        point[1] = locate.y;
        return point;
    }

}
