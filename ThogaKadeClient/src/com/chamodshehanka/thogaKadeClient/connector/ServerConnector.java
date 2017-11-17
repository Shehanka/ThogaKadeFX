package com.chamodshehanka.thogaKadeClient.connector;

import com.chamodshehanka.thogaKadeCommon.controller.CustomerController;
import com.chamodshehanka.thogaKadeCommon.controller.ItemController;
import com.chamodshehanka.thogaKadeCommon.controller.ThogaKadeFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author chamodshehanka on 11/10/2017
 * @project ThogaKadeFX
 **/

public class ServerConnector {

    private static ServerConnector serverConnector;
    private ItemController itemController;
    private CustomerController customerController;
    private ThogaKadeFactory thogaKadeFactory;

    private ServerConnector()throws NotBoundException,MalformedURLException,RemoteException{
        thogaKadeFactory= (ThogaKadeFactory) Naming.lookup("rmi://localhost:5050/ThogaKadeServer");
    }

    public static ServerConnector getServerConnector() throws NotBoundException,MalformedURLException,RemoteException{
        if (serverConnector == null){
            serverConnector = new ServerConnector();
        }
        return serverConnector;
    }

    public CustomerController getCustomerController() throws RemoteException {
        if (customerController == null){
            customerController = thogaKadeFactory.getCustomerController();
        }
        return customerController;
    }

    public ItemController getItemController() throws RemoteException {
        if (itemController == null){
            itemController = thogaKadeFactory.getItemController();
        }
        return itemController;
    }

}
