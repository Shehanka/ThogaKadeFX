package com.chamodshehanka.thogaKadeServer.controllerImpl;

import com.chamodshehanka.thogaKadeCommon.controller.CustomerController;
import com.chamodshehanka.thogaKadeCommon.controller.ItemController;
import com.chamodshehanka.thogaKadeCommon.controller.ThogaKadeFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author chamodshehanka on 11/17/2017
 * @project ThogaKadeFX
 **/
public class ThogaKadeFactoryImpl extends UnicastRemoteObject implements ThogaKadeFactory{

    public ThogaKadeFactoryImpl() throws RemoteException {
    }

    @Override
    public CustomerController getCustomerController() throws RemoteException {
        return new CustomerControllerImpl();
    }

    @Override
    public ItemController getItemController() throws RemoteException {
        return new ItemControllerImpl();
    }
}
