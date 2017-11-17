package com.chamodshehanka.thogaKadeServer.controllerImpl;

import com.chamodshehanka.thogaKadeCommon.controller.ItemController;
import com.chamodshehanka.thogaKadeServer.dbAccess.ItemDBAccess;
import com.chamodshehanka.thogaKadeCommon.model.Item;
import com.chamodshehanka.thogaKadeServer.reservation.ItemReservation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author chamodshehanka on 11/11/2017
 * @project ThogaKadeFX
 **/
public class ItemControllerImpl extends UnicastRemoteObject implements ItemController{

    private ItemDBAccess itemDBAccess = new ItemDBAccess();
    private final static ItemReservation ITEM_RESERVATION = new ItemReservation();

    public ItemControllerImpl() throws RemoteException {

    }

    @Override
    public boolean addItem(Item item) throws ClassNotFoundException, SQLException, RemoteException {
        return itemDBAccess.addItem(item);
    }

    @Override
    public Item searchItem(String code) throws ClassNotFoundException, SQLException, RemoteException {
        return itemDBAccess.searchItem(code);
    }

    @Override
    public boolean updateItem(Item item) throws ClassNotFoundException, SQLException, RemoteException {
        return ITEM_RESERVATION.reserveItem(item.getCode(), this) && itemDBAccess.updateItem(item);
    }

    @Override
    public boolean deleteItem(String code) throws ClassNotFoundException, SQLException, RemoteException {
        return itemDBAccess.deleteItem(code);
    }

    @Override
    public ArrayList<Item> getAllItems() throws ClassNotFoundException, SQLException, RemoteException {
        return itemDBAccess.getAllItems();
    }

    @Override
    public boolean reserveItem(String code) throws ClassNotFoundException, SQLException, RemoteException {
        return ITEM_RESERVATION.reserveItem(code,this);
    }

    @Override
    public boolean releaseItem(String code) throws ClassNotFoundException, SQLException, RemoteException {
        return ITEM_RESERVATION.releaseItem(code,this);
    }
}
