package com.chamodshehanka.thogaKadeServer.dbAccess;

import com.chamodshehanka.thogaKadeServer.db.DBConnection;
import com.chamodshehanka.thogaKadeCommon.model.Customer;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chamodshehanka on 11/10/2017
 * @project ThogaKadeFX
 **/
public class CustomerDBAccess {

    private static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    public boolean addCustomer(Customer customer)throws ClassNotFoundException,SQLException,RemoteException{
        try {
            READ_WRITE_LOCK.writeLock().lock();
            Connection connection = DBConnection.getConnection();
            String SQL = "INSERT INTO Customer VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1, customer.getId());
            statement.setObject(2, customer.getName());
            statement.setObject(3, customer.getAddress());
            statement.setObject(4, customer.getSalary());
            return statement.executeUpdate()>0;
        }finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    public Customer searchCustomer(String id)throws ClassNotFoundException,SQLException,RemoteException{
        try {
            READ_WRITE_LOCK.readLock().lock();
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Customer WHERE id='" + id + "'";
            ResultSet rst = statement.executeQuery(SQL);
            return rst.next() ? new Customer(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getDouble("salary")
            ) : null;
        }finally {
            READ_WRITE_LOCK.readLock().unlock();
        }
    }

    public boolean updateCustomer(Customer customer)throws ClassNotFoundException,SQLException,RemoteException{
        try {
            READ_WRITE_LOCK.writeLock().lock();
            Connection connection = DBConnection.getConnection();
            String SQL = "UPDATE Customer SET name=?, address=?, salary=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1, customer.getId());
            statement.setObject(2, customer.getName());
            statement.setObject(3, customer.getAddress());
            statement.setObject(4, customer.getSalary());
            return statement.executeUpdate() > 0;
        }finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    public boolean deleteCustomer(String id)throws ClassNotFoundException,SQLException,RemoteException{
        try {
            READ_WRITE_LOCK.writeLock().lock();
            Connection connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM customer WHERE id='" + id + "'";
            return statement.executeUpdate(SQL) > 0;
        }finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    public ArrayList<Customer> getAllCustomers()throws ClassNotFoundException,SQLException,RemoteException{
        try {
            READ_WRITE_LOCK.readLock().lock();
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            ArrayList<Customer> customerArrayList = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                );
                customerArrayList.add(customer);
            }
            return customerArrayList;
        }finally {
            READ_WRITE_LOCK.readLock().unlock();
        }
    }
}
