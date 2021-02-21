package com.oopclass.breadapp.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.oopclass.breadapp.config.StageManager;
import com.oopclass.breadapp.views.FxmlView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author Irish S. Bautista
 */
@Controller
public class HomeController implements Initializable {

    @FXML
    private Button openCustomer;
    @FXML
    private Button openProduct;
    @FXML
    private Button openOrderDetails;

    @Lazy
    @Autowired
    private StageManager stageManager;
    
    @FXML
    void openCustomer(ActionEvent event){
        stageManager.switchScene(FxmlView.CUSTOMER);
    }
    
    @FXML
    void openProduct(ActionEvent event){
        stageManager.switchScene(FxmlView.PRODUCT);
    }
    
    @FXML
    void openOrderDetails(ActionEvent event){
        stageManager.switchScene(FxmlView.CANCEL);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
