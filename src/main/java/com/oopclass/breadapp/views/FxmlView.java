package com.oopclass.breadapp.views;

import java.util.ResourceBundle;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
public enum FxmlView {

    CUSTOMER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("customer.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Customer.fxml";
        }

    }, HOME {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("home.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Home.fxml";
        }
    }, PRODUCT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("product.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Product.fxml";
        }

    }, CANCEL {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("cancel.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Cancel.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
