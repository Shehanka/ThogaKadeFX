package controller;

import model.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author chamodshehanka on 11/10/2017
 * @project ThogaKadeFX
 **/
public interface CustomerController extends Remote{

    public boolean addCustomer(Customer customer)throws ClassNotFoundException,SQLException,RemoteException;

    public Customer searchCustomer(String id)throws ClassNotFoundException,SQLException,RemoteException;

    public boolean updateCustomer(Customer customer)throws ClassNotFoundException,SQLException,RemoteException;

    public boolean deleteCustomer(String id)throws ClassNotFoundException,SQLException,RemoteException;

    public ArrayList<Customer> getAllCustomers()throws ClassNotFoundException,SQLException,RemoteException;

}
