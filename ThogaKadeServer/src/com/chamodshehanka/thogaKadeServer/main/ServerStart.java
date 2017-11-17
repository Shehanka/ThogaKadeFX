package com.chamodshehanka.thogaKadeServer.main;

import com.chamodshehanka.thogaKadeServer.controllerImpl.ThogaKadeFactoryImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author chamodshehanka on 11/10/2017
 * @project ThogaKadeFX
 **/

public class ServerStart {
    public static void main(String[] args) {
        try {
            Registry thogaKadeRegistry = LocateRegistry.createRegistry(5050);
            System.out.println("Server starting.....");
            thogaKadeRegistry.rebind("ThogaKadeServer",new ThogaKadeFactoryImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
