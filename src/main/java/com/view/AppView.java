package com.view;

import com.controller.AppController;
import com.view.viewfunctions.BasisDataView;
import com.view.viewfunctions.MainMenu;
import com.view.viewfunctions.ShowDbView;

public class AppView {
    public void mainMenu(AppController controller) {
        MainMenu.show(controller);
    }

    public void basisData(int capital, int product, int totalSellers) {
        BasisDataView.show(capital, product, totalSellers);
    }

    public void showDbView(String dbFileName, int capital, int product, int revenue, int profit) {
        ShowDbView.show(dbFileName, capital, product, revenue, profit);
    }
}