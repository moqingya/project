package src.controller;

import src.pojo.MusicPlay;
import src.pojo.FrontPlayer;

import java.io.*;

public class Client implements Runnable{
           private FrontPlayer player = new FrontPlayer();
           private MusicPlay mc = new MusicPlay();

    @Override
    public void run() {
            while (true) {
                mc.MusicPlay("res\\music\\roommusic.wav");
                mc.musicMain(3);
                try {
                    player.home();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    player.playGame(mc);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
