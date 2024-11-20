package com.view;

import com.controller.AppController;
import com.view.viewfunctions.BasisDataView;
import com.view.viewfunctions.MainMenu;

public class AppView {
    public void mainMenu(AppController controller) {
        MainMenu.show(controller);
    }

    public void basisData(int capital, int product, int totalSellers) {
        BasisDataView.show(capital, product, totalSellers);
    }
}