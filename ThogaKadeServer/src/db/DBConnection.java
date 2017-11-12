package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author chamodshehanka on 11/10/2017
 * @project ThogaKadeFX
 **/
public class DBConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade","root","wampwamp");
    }
}
