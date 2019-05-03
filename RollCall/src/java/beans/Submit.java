/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.SQLiteJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moolm
 */
public class Submit extends HttpServlet{
    @Override
    
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        Cookie[] cookies = req.getCookies();
        String house = null,grade = null,present = "",absent = "",receiver="",submitter="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("house")) {
                    house = cookie.getValue();
                }
                if (cookie.getName().equals("grade")) {
                    grade = cookie.getValue();
                }
                if (cookie.getName().equals("present")) {
                    present = cookie.getValue();
                }
                if (cookie.getName().equals("absent")) {
                    absent = cookie.getValue();
                }
                if (cookie.getName().equals("username")) {
                    submitter = cookie.getValue();
                }
            }
        }
        
        if(req.getParameter("house").equals("100")){
            receiver=req.getParameter("rcemail");
        } else {
            receiver=req.getParameter("house");
        }
       
        PrintWriter out = res.getWriter();
        SQLiteJDBC sqlJDBC = new SQLiteJDBC();
        Connection conn;
        try {
            conn = sqlJDBC.SQLconnect();
            String date ="strftime('%Y-%m-%d %H:%M:%S','now')";
            String sql = "insert into RollCalls(SubmitterID,ReceiverID,Title,Date,House,Grade)\n" +
            "values('"+submitter+"','"+receiver+"','"+req.getParameter("rcname")+"',"+date+",'"+house+"','"+grade+"')";
            System.out.println(sql);
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
            System.out.println("reee");
            sql="select RollCallID from RollCalls where date = "+date;
            s.executeUpdate(sql);
            ResultSet rs = s.executeQuery(sql);
            String rollCallID = rs.getString("RollCallID");
            Scanner scP = new Scanner(present).useDelimiter(",");
            Scanner scA = new Scanner(absent).useDelimiter(",");
            while(scP.hasNext()){
                sql = "insert into Entries(RollCallID,StuID,Present)\n" +
                "values('"+rollCallID+"','"+scP.next()+"','true')";
                s.executeUpdate(sql);
            }
            System.out.println("abxc");
            while(scA.hasNext()){
                sql = "insert into Entries(RollCallID,StuID,Present)\n" +
                "values('"+rollCallID+"','"+scA.next()+"','false')";
                s.executeUpdate(sql);
            }
            
            
            
        } catch (SQLException e) {
            System.err.println("Thread: " + Thread.currentThread());
            System.err.println("ErrorCode: " + e.getErrorCode());
            System.err.println("SQLState:  " + e.getSQLState());
            System.err.println("Message:   " + e.getMessage());
            System.err.println("NextException: " + e.getNextException());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Submit.class.getName()).log(Level.SEVERE, null, ex);
        }
        Cookie ck1 = new Cookie("house","");
        Cookie ck2 = new Cookie("grade","");
        Cookie ck3 = new Cookie("present","");
        Cookie ck4 = new Cookie("absent","");
        res.addCookie(ck1);
        res.addCookie(ck2);
        res.addCookie(ck3);
        res.addCookie(ck4);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        dispatcher.forward(req,res);
    }
}