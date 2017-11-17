package com.chamodshehanka.thogaKadeClient.view.controller;

import com.jfoenix.controls.JFXTextField;
import com.chamodshehanka.thogaKadeClient.connector.ServerConnector;
import com.chamodshehanka.thogaKadeCommon.controller.CustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.chamodshehanka.thogaKadeCommon.model.Customer;
import com.chamodshehanka.thogaKadeClient.tableModel.CustomerTableModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author chamodshehanka on 11/10/2017
 * @project ThogaKadeFX
 **/

public class CustomerFormController implements Initializable{

    @FXML
    public TableColumn<CustomerTableModel, String> colCustomerID;

    @FXML
    public TableColumn<CustomerTableModel,String> colCustomerName;

    @FXML
    public TableColumn<CustomerTableModel,String> colAddress;

    @FXML
    public TableColumn<CustomerTableModel,Double> colSalary;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerSalary;

    @FXML
    public TableView<CustomerTableModel> tblCustomer;

    private ObservableList<CustomerTableModel> customersList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableView();
    }

    @FXML
    private void addCustomer(ActionEvent actionEvent){
        Customer customer = new Customer(
                txtCustomerID.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                Double.valueOf(txtCustomerSalary.getText())
        );

        try {
            CustomerController customerController = ServerConnector.getServerConnector().getCustomerController();
            boolean isAdded = customerController.addCustomer(customer);
            if (isAdded){
                System.out.println("Added Success");

            }else {
                System.out.println("Adding failed");
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchCustomer(ActionEvent actionEvent){
        try {
            CustomerController customerController = ServerConnector.getServerConnector().getCustomerController();
            Customer customer = customerController.searchCustomer(txtCustomerID.getText());
            if (customer !=null){
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerSalary.setText(String.valueOf(customer.getSalary()));
            }else {
                System.out.println("Customer couldn't found");
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateCustomer(ActionEvent actionEvent){
        Customer customer = new Customer(
                txtCustomerID.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                Double.valueOf(txtCustomerSalary.getText())
        );

        try {
            CustomerController customerController = ServerConnector.getServerConnector().getCustomerController();
            boolean isUpdated = customerController.updateCustomer(customer);
            if (isUpdated){
                System.out.println("Customer has been successfully updated");
            }else {
                System.out.println("Customer couldn't update");
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteCustomer(ActionEvent actionEvent){
        try {
            CustomerController customerController = ServerConnector.getServerConnector().getCustomerController();
            boolean isDeleted = customerController.deleteCustomer(txtCustomerID.getText());
            if (isDeleted){
                System.out.println("Customer has been successfully deleted");
            }else {
                System.out.println("Customer couldn't delete");
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadTableView(){
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        CustomerTableModel tableModel = new CustomerTableModel();
        tableModel.setId("C000");
        tableModel.setName("Chamod Shehanka");
        tableModel.setAddress("Horana");
        tableModel.setSalary(100000);
        customersList.add(tableModel);
        tblCustomer.setItems(customersList);

        ArrayList<Customer> customerArrayList;
        try {
            CustomerController customerController = ServerConnector.getServerConnector().getCustomerController();
            customerArrayList = customerController.getAllCustomers();
            for (Customer customer:
                 customerArrayList) {
                CustomerTableModel customerTableModel = new CustomerTableModel();
                customerTableModel.setId(customer.getId());
                customerTableModel.setName(customer.getName());
                customerTableModel.setAddress(customer.getAddress());
                customerTableModel.setSalary(customer.getSalary());

                customersList.add(customerTableModel);
            }
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
