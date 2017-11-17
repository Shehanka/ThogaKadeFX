package com.chamodshehanka.thogaKadeClient.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author chamodshehanka on 11/17/2017
 * @project ThogaKadeFX
 **/

public class MainMenuFormController implements Initializable{

    @FXML
    private AnchorPane rootMainMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void showCustomerForm(ActionEvent event) {
        try {
            AnchorPane customerFormPane = FXMLLoader.load(getClass().getResource(
                    "/com/chamodshehanka/thogaKadeClient/view/fxml/CustomerForm.fxml"
            ));
            rootMainMenu.getChildren().setAll(customerFormPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showItemForm(ActionEvent event) {
        try {
            AnchorPane itemAnchorPane = FXMLLoader.load(getClass().getResource(
                    "/com/chamodshehanka/thogaKadeClient/view/fxml/ItemForm.fxml"
            ));
            rootMainMenu.getChildren().setAll(itemAnchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
