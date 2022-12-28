package src.test;

import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;
import org.junit.Test;
import src.app.MainApp;
import src.controller.Client;
import src.controller.Login_Register_Page;
import src.dao.Alter;
import src.dao.Check;
import src.dao.Delete;
import src.dao.Init;
import src.pojo.FrontPlayer;
import src.pojo.Gameround;
import src.pojo.MusicPlay;
import src.tools.Reflect;
import src.view.GameWindow;

import java.io.IOException;
import java.sql.SQLException;

public class JunitTest {
//    @Test
//    public void ClientTest() {
//        new Client().run();
//    }

    @Test
    public void gameWindowTest() {
        new GameWindow().showGame();
    }

    @Test
    public void PageTest() {
        new Login_Register_Page().mainPage();
    }

    @Test
    public void ReflectTest() throws SQLException {
        Reflect.inspect();
    }

   @Test
    public void GameroundTest() {
        new Gameround().init();
   }


    @Test
    public void daoTest() throws SQLException {
        new Check().printTableData();
        new Init().initRecord();
    }

}
