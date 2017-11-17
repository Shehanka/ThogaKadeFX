package com.chamodshehanka.thogaKadeServer.controllerImpl;

import com.chamodshehanka.thogaKadeCommon.controller.CustomerController;
import com.chamodshehanka.thogaKadeServer.dbAccess.CustomerDBAccess;
import com.chamodshehanka.thogaKadeCommon.model.Customer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author chamodshehanka on 11/10/2017
 * @project ThogaKadeFX
 **/
public class CustomerControllerImpl extends UnicastRemoteObject implements CustomerController {

    private CustomerDBAccess customerDBAccess = new CustomerDBAccess();

    public CustomerControllerImpl() throws RemoteException {
    }

    @Override
    public boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException, RemoteException {
        return customerDBAccess.addCustomer(customer);
    }

    @Override
    public Customer searchCustomer(String id) throws ClassNotFoundException, SQLException, RemoteException {
        return customerDBAccess.searchCustomer(id);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException, RemoteException {
        return customerDBAccess.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException, RemoteException {
        return customerDBAccess.deleteCustomer(id);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException, RemoteException {
        return customerDBAccess.getAllCustomers();
    }
}
