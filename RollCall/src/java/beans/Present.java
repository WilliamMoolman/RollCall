/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.SQLiteJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moolm
 */
public class Present extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        try {
            String house = req.getParameter("house");
            String grade = req.getParameter("grade");
            
            SQLiteJDBC sqlJDBC = new SQLiteJDBC();
            Connection conn = sqlJDBC.SQLconnect();
            String sql = "select name,surname,StuID from Users where House = '"+house+"' and Grade = '"+grade+"'";
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
            ResultSet rs = s.executeQuery(sql);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Present.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Present.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
