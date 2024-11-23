package com.controller.addcustomer.utility;
import com.util.Util;
import com.view.AppView;

public class SelectPackage {
    private SelectPackage() {
        throw new IllegalStateException("Utility class");
    }

    public static int function(AppView view) {
        view.packagesView();
        return Util.getUserInt("Choose package: ");
    }
}
