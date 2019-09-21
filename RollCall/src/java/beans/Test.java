/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import database.SQLiteJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author moolm
 */
public class Test {
    //A class for testing program functionality, does not affect rest of project
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLiteJDBC sqlJDBC = new SQLiteJDBC();
        Connection conn = sqlJDBC.SQLconnect();
        String sql = "select * from Users";
        Statement s = conn.createStatement();
        s.executeUpdate(sql);
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            
            String stuID = rs.getString("StuID");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String house = rs.getString("House");
            int grade = rs.getInt("Grade");
            // print the results
            System.out.format("%s, %s, %s, %s, %s\n", stuID, name, surname, house, grade);
      }
    }
}
