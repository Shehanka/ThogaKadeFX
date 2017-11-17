package com.chamodshehanka.thogaKadeCommon.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author chamodshehanka on 11/17/2017
 * @project ThogaKadeFX
 **/
public interface ThogaKadeFactory extends Remote{

    public CustomerController getCustomerController()throws RemoteException;

    public ItemController getItemController()throws RemoteException;

}
