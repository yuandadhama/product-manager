package com;

import com.controller.AppController;
import com.view.AppView;

public class Main {
    public static void main(String[] args) {
        // App app = new App();
        // Menu menu = new Menu();

        // menu.main(app);

        AppView view = new AppView();
        AppController controller = new AppController();
        view.mainMenu(controller);
        // System.out.println("hello world");
    }
}