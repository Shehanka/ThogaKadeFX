package main;

import controllerImpl.CustomerControllerImpl;
import controllerImpl.ItemControllerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author chamodshehanka on 11/10/2017
 * @project ThogaKadeFX
 **/

public class ServerStart {
    public static void main(String[] args) {
        /*try {
            Registry customerRegistry = LocateRegistry.createRegistry(5050);
            System.out.println("Server is starting");
            customerRegistry.rebind("CustomerServer",new CustomerControllerImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/

        try {
            Registry itemRegistry = LocateRegistry.createRegistry(5050);
            System.out.println("Server starting.....");
            itemRegistry.rebind("ItemServer",new ItemControllerImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
