package com.chamodshehanka.thogaKadeClient.view.controller;

import com.jfoenix.controls.JFXTextField;
import com.chamodshehanka.thogaKadeClient.connector.ServerConnector;
import com.chamodshehanka.thogaKadeCommon.controller.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.chamodshehanka.thogaKadeCommon.model.Item;
import com.chamodshehanka.thogaKadeClient.tableModel.ItemTableModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author chamodshehanka on 11/11/2017
 * @project ThogaKadeFX
 **/

public class ItemFormController implements Initializable{

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private TableView<ItemTableModel> tblItem;

    @FXML
    private TableColumn<ItemTableModel, String> colCode;

    @FXML
    private TableColumn<ItemTableModel, String> colDescription;

    @FXML
    private TableColumn<ItemTableModel, Double> colUnitPrice;

    @FXML
    private TableColumn<ItemTableModel, Integer> colQtyOnHand;

    private ObservableList<ItemTableModel> itemTableModelObservableList = FXCollections.observableArrayList();

    private String previousItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableView();
    }

    @FXML
    private void addItem(ActionEvent actionEvent){
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                Double.valueOf(txtUnitPrice.getText()),
                Integer.valueOf(txtQtyOnHand.getText())
        );

        try {
            ItemController itemController = ServerConnector.getServerConnector().getItemController();
            boolean isAdded = itemController.addItem(item);
            if (isAdded){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Items");
                alert.setHeaderText("Look, a information Dialog");
                alert.setContentText("Item has been successfully added");
                alert.showAndWait();
                tblItem.refresh();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Items");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("Item couldn't add");
                alert.showAndWait();
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchItem(ActionEvent actionEvent){
        try {
            ItemController itemController = ServerConnector.getServerConnector().getItemController();
            Item item = itemController.searchItem(txtItemCode.getText());
            //
            if (null!=previousItem){
                previousItem = txtItemCode.getText();
            }
            //
            if (item != null){
                txtDescription.setText(item.getDescription());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Items");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("Couldn't find item");
                alert.showAndWait();
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateItem(ActionEvent actionEvent){
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                Double.valueOf(txtUnitPrice.getText()),
                Integer.valueOf(txtQtyOnHand.getText())
        );

        try {
            ItemController itemController = ServerConnector.getServerConnector().getItemController();
            itemController.reserveItem(txtItemCode.getText());
            boolean isUpdated = itemController.updateItem(item);
            if (isUpdated){
                itemController.releaseItem(txtItemCode.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Items");
                alert.setHeaderText("Look, a information Dialog");
                alert.setContentText("Item has been successfully updated");
                alert.showAndWait();
                tblItem.refresh();
            }else {
                itemController.releaseItem(txtItemCode.getText());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Items");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("Item couldn't update");
                alert.showAndWait();
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteItem(ActionEvent actionEvent){
        Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
        alertC.setTitle("Items");
        alertC.setHeaderText("Look, a confirmation Dialog");
        alertC.setContentText("Are you sure?");
        Optional<ButtonType> result =alertC.showAndWait();

        if (result.get() == ButtonType.OK){
            try {
                ItemController itemController = ServerConnector.getServerConnector().getItemController();
                boolean isDeleted = itemController.deleteItem(txtItemCode.getText());
                if (isDeleted){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Items");
                    alert.setHeaderText("Look, a information Dialog");
                    alert.setContentText("Item has been successfully deleted");
                    alert.showAndWait();
                    tblItem.refresh();
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Items");
                    alert.setHeaderText("Look, a Warning Dialog");
                    alert.setContentText("Item couldn't delete");
                    alert.showAndWait();
                }
            } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadTableView(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItem.setItems(itemTableModelObservableList);

        ArrayList<Item> itemArrayList;
        try {
            ItemController itemController = ServerConnector.getServerConnector().getItemController();
            itemArrayList = itemController.getAllItems();
            for (Item item:
                 itemArrayList) {
                ItemTableModel model = new ItemTableModel();
                model.setCode(item.getCode());
                model.setDescription(item.getDescription());
                model.setUnitPrice(item.getUnitPrice());
                model.setQtyOnHand(item.getQtyOnHand());

                itemTableModelObservableList.add(model);
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
