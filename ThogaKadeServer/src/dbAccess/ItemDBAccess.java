package dbAccess;

import db.DBConnection;
import model.Item;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author chamodshehanka on 11/11/2017
 * @project ThogaKadeFX
 **/
public class ItemDBAccess {

    public boolean addItem(Item item)throws ClassNotFoundException,SQLException,RemoteException{
        Connection connection = DBConnection.getConnection();
        String SQL = "INSERT INTO Item VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setObject(1,item.getCode());
        statement.setObject(2,item.getDescription());
        statement.setObject(3,item.getUnitPrice());
        statement.setObject(4,item.getQtyOnHand());

        return statement.executeUpdate()>0;
    }

    public Item searchItem(String code)throws ClassNotFoundException,SQLException,RemoteException{
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM Item WHERE code='"+code+"'";
        ResultSet resultSet = statement.executeQuery(SQL);

        return resultSet.next() ? new Item(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getDouble(3),
                resultSet.getInt(4)
        ) : null;
    }

    public boolean updateItem(Item item)throws ClassNotFoundException,SQLException,RemoteException{
        Connection connection = DBConnection.getConnection();
        String SQL = "UPDATE item SET code=?,description=?,unitPrice=?,qtyOnHand=?";
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setObject(1,item.getCode());
        statement.setObject(2,item.getDescription());
        statement.setObject(3,item.getUnitPrice());
        statement.setObject(4,item.getQtyOnHand());

        return statement.executeUpdate()>0;
    }

    public boolean deleteItem(String code)throws ClassNotFoundException,SQLException,RemoteException{
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        String SQL = "DELETE FROM item WHERE code='"+code+"'";

        return statement.executeUpdate(SQL)>0;
    }

    public ArrayList<Item> getAllItems()throws ClassNotFoundException,SQLException,RemoteException{
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM item");
        ArrayList<Item> itemArrayList = new ArrayList<>();
        while (resultSet.next()){
            Item item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
            itemArrayList.add(item);
        }
        return itemArrayList;
    }
}
