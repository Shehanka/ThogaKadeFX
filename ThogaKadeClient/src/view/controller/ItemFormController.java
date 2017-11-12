package view.controller;

import com.jfoenix.controls.JFXTextField;
import connector.ServerConnector;
import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import tableModel.ItemTableModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
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
                System.out.println("Item has been successfully added");
            }else {
                System.out.println("Item couldn't add");
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
            if (item != null){
                txtDescription.setText(item.getDescription());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            }else {
                System.out.println("Item couldn't find");
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
            boolean isUpdated = itemController.updateItem(item);
            if (isUpdated){
                System.out.println("Item has been successfully updated");
            }else {
                System.out.println("Item couldn't update");
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteItem(ActionEvent actionEvent){
        try {
            ItemController itemController = ServerConnector.getServerConnector().getItemController();
            boolean isDeleted = itemController.deleteItem(txtItemCode.getText());
            if (isDeleted){
                System.out.println("Item has been successfully deleted");
            }else {
                System.out.println("Item couldn't delete");
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadTableView(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        ItemTableModel itemTableModel = new ItemTableModel();
        itemTableModel.setCode("I001");
        itemTableModel.setDescription("Milo");
        itemTableModel.setUnitPrice(50);
        itemTableModel.setQtyOnHand(1000);

        itemTableModelObservableList.add(itemTableModel);
        itemTableModelObservableList.add(itemTableModel);
        itemTableModelObservableList.add(itemTableModel);
        itemTableModelObservableList.add(itemTableModel);
        itemTableModelObservableList.add(itemTableModel);
        itemTableModelObservableList.add(itemTableModel);

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
