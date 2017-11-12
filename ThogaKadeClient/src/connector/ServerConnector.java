package connector;

import controller.CustomerController;
import controller.ItemController;

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
    private CustomerController customerController;
    private ItemController itemController;

    private ServerConnector()throws NotBoundException,MalformedURLException,RemoteException{
//        customerController = (CustomerController) Naming.lookup("rmi://localhost:5050/CustomerServer");
        itemController = (ItemController) Naming.lookup("rmi://localhost:5050/ItemServer");
    }

    public static ServerConnector getServerConnector() throws NotBoundException,MalformedURLException,RemoteException{
        if (serverConnector ==null){
            serverConnector = new ServerConnector();
        }
        return serverConnector;
    }

    public ItemController getItemController(){
        return itemController;
    }

    public CustomerController getCustomerController(){
        return customerController;
    }
}
