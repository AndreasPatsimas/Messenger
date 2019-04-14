/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
public class Database {
    public  Connection getConnected() throws SQLException{
        
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/messenger?serverTimezone=UCT"
                , "root", "19141918");
    }
}
