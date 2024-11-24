package com.view;

import java.util.List;

import com.controller.AppController;
import com.view.viewfunctions.*;

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

    public void packagesView() {
        PackagesView.show();
    }

    public void sellersListView(List<String> sellerNames) {
        SellersListView.show(sellerNames);
    }

    public void dbFileNamesView(List<String> fileNames) {
        DbFileNamesView.show(fileNames);
    }
}