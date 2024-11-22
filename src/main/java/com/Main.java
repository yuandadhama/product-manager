package com;

import com.controller.AppController;
import com.view.AppView;

public class Main {
    public static void main(String[] args) {
        AppView view = new AppView();
        AppController controller = new AppController();
        view.mainMenu(controller);
    }
}