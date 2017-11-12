package controller;

import model.Item;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author chamodshehanka on 11/11/2017
 * @project ThogaKadeFX
 **/
public interface ItemController extends Remote{

    public boolean addItem(Item item)throws ClassNotFoundException,SQLException,RemoteException;

    public Item searchItem(String code)throws ClassNotFoundException,SQLException,RemoteException;

    public boolean updateItem(Item item)throws ClassNotFoundException,SQLException,RemoteException;

    public boolean deleteItem(String code)throws ClassNotFoundException,SQLException,RemoteException;

    public ArrayList<Item> getAllItems()throws ClassNotFoundException,SQLException,RemoteException;

}
