package controllerImpl;

import controller.ItemController;
import dbAccess.ItemDBAccess;
import model.Item;

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
        return itemDBAccess.updateItem(item);
    }

    @Override
    public boolean deleteItem(String code) throws ClassNotFoundException, SQLException, RemoteException {
        return itemDBAccess.deleteItem(code);
    }

    @Override
    public ArrayList<Item> getAllItems() throws ClassNotFoundException, SQLException, RemoteException {
        return itemDBAccess.getAllItems();
    }
}
