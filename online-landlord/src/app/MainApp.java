package src.app;

import src.controller.Login_Register_Page;

public class MainApp {
    public static void main(String[] args) {
            Login_Register_Page loginRegisterPage = new Login_Register_Page();
            Thread thread = new Thread(loginRegisterPage);
            thread.start();
    }

}
