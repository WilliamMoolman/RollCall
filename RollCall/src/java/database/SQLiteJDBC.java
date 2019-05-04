/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteDataSource;

/**
 *
 * @author moolm
 */
public class SQLiteJDBC {
    
    public Connection SQLconnect(){
        try {
            Connection c;
            Class.forName("org.sqlite.JDBC");
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:C:\\Users\\moolm\\Desktop\\Coding\\Github\\RollCall\\RollCall\\src\\java\\database\\Users.db");
            c = ds.getConnection();
            
            return c;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection to Users failure.");
            return null;
        }
    }
    
}
