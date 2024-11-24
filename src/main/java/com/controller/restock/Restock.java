package com.controller.restock;

import com.controller.AppController;
import com.controller.common.ConvertModelToField;
import com.controller.restock.utility.AddStock;
import com.model.DatabaseModel;
import com.util.Util;

public class Restock {
    private Restock() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel) {
        while (true) {
            AddStock.function(databaseModel);
            if (Util.getYesOrNo("Are you sure with the data")) {
                break;
            }
        }

        ConvertModelToField.function(databaseModel);
        new AppController().showExistingField(false);
    }
}
