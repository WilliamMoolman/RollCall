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
public class Submit extends HttpServlet {

    @Override

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Cookie[] cookies = req.getCookies();
        String house = null, grade = null, present = "", absent = "", receiver = "", submitter = "", emailHtml = "";
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

        if (req.getParameter("house").equals("100") || req.getParameter("rcname").isEmpty()) {
            if (req.getParameter("rcemail").isEmpty() || req.getParameter("rcname").isEmpty()) {
                String html = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "        <title>SJC Roll Call | Take</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" crossorigin=\"anonymous\">\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\">\n"
                        + "        <meta name=\"google-signin-client_id\" content=\"334817504785-g0vqsgqoii5hc17djc5bkefeottshc3c.apps.googleusercontent.com\">\n"
                        + "        <link rel=\"shortcut icon\" type=\"image/png\" href=\"images/logo.png\">\n"
                        + "        <script src=\"https://apis.google.com/js/platform.js\" async defer></script>\n"
                        + "        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" crossorigin=\"anonymous\"></script><script>\n"
                        + "            function goBack() {\n"
                        + "                window.history.back();\n"
                        + "            }\n"
                        + "        </script>\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <div class=\"container-fluid\">\n"
                        + "            <div class=\"row\">\n"
                        + "                <div class=\"col\" >\n"
                        + "                    <img src=\"images/logo.png\" alt=\"St John's Logo\">\n"
                        + "                </div>\n"
                        + "                <div class=\"col-8\">\n"
                        + "                    <p class=\"header\">St John's Roll Call</p>\n"
                        + "                </div>\n"
                        + "            </div><div class=\"row\">\n"
                        + "                <img src=\"images/backsmol.png\" alt=\"Go Back\" id=\"back-button\" onclick=\"goBack()\">\n"
                        + "            </div>";
                html += "</div>\n"
                        + "        <div class=\"center\">\n"
                        + "            <h3 style=\"text-align: center\">Please fill in required fields</h3>\n"
                        + "        </div>\n"
                        + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                        + "    </body>\n"
                        + "</html>";
                PrintWriter out = res.getWriter();
                res.setContentType("text/html");
                out.print(html);
            } else {
                receiver = req.getParameter("rcemail");
                SQLiteJDBC sqlJDBC = new SQLiteJDBC();
                Connection conn;
                try {
                    conn = sqlJDBC.SQLconnect();
                    String date = "strftime('%Y-%m-%d %H:%M:%S','now')";
                    String sql = "insert into RollCalls(SubmitterID,ReceiverID,Title,Date,House,Grade)\n"
                            + "values('" + submitter + "','" + receiver + "','" + req.getParameter("rcname") + "'," + date + ",'" + house + "','" + grade + "')";
                    Statement s = conn.createStatement();
                    s.executeUpdate(sql);
                    sql = "select RollCallID from RollCalls where date = " + date;
                    s.executeUpdate(sql);
                    ResultSet rs = s.executeQuery(sql);
                    String rollCallID = rs.getString("RollCallID");
                    Scanner scP = new Scanner(present).useDelimiter(",");
                    Scanner scA = new Scanner(absent).useDelimiter(",");
                    emailHtml+="Present:\n\n";
                    while (scP.hasNext()) {
                        String id = scP.next();
                        sql = "insert into Entries(RollCallID,StuID,Present)\n"
                                + "values('" + rollCallID + "','" + id + "','true')";
                        s.executeUpdate(sql);
                            
                        rs = s.executeQuery("select name,surname from users where Stuid = '"+id+"'");
                        emailHtml += rs.getString("Name")+ " " +rs.getString("Surname")+"\n";
                    }
                    emailHtml+="\nAbsent:\n\n";
                    while (scA.hasNext()) {
                        String id = scA.next();
                        sql = "insert into Entries(RollCallID,StuID,Present)\n"
                                + "values('" + rollCallID + "','" + id + "','false')";
                        s.executeUpdate(sql);
                        
                        rs = s.executeQuery("select name,surname from users where Stuid = '"+id+"'");
                        emailHtml += rs.getString("Name")+ " " +rs.getString("Surname")+"\n";
                    }

                } catch (SQLException e) {
                    System.err.println("Thread: " + Thread.currentThread());
                    System.err.println("ErrorCode: " + e.getErrorCode());
                    System.err.println("SQLState:  " + e.getSQLState());
                    System.err.println("Message:   " + e.getMessage());
                }
                Cookie ck1 = new Cookie("house", "");
                Cookie ck2 = new Cookie("grade", "");
                Cookie ck3 = new Cookie("present", "");
                Cookie ck4 = new Cookie("absent", "");
                res.addCookie(ck1);
                res.addCookie(ck2);
                res.addCookie(ck3);
                res.addCookie(ck4);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
                dispatcher.forward(req, res);
            }
        } else {
            receiver = req.getParameter("house");
            SQLiteJDBC sqlJDBC = new SQLiteJDBC();
            Connection conn;
            try {
                conn = sqlJDBC.SQLconnect();
                String date = "strftime('%Y-%m-%d %H:%M:%S','now')";
                String sql = "insert into RollCalls(SubmitterID,ReceiverID,Title,Date,House,Grade)\n"
                        + "values('" + submitter + "','" + receiver + "','" + req.getParameter("rcname") + "'," + date + ",'" + house + "','" + grade + "')";
                Statement s = conn.createStatement();
                s.executeUpdate(sql);
                sql = "select RollCallID from RollCalls where date = " + date;
                s.executeUpdate(sql);
                ResultSet rs = s.executeQuery(sql);
                String rollCallID = rs.getString("RollCallID");
                Scanner scP = new Scanner(present).useDelimiter(",");
                Scanner scA = new Scanner(absent).useDelimiter(",");
                while (scP.hasNext()) {
                    sql = "insert into Entries(RollCallID,StuID,Present)\n"
                            + "values('" + rollCallID + "','" + scP.next() + "','true')";
                    s.executeUpdate(sql);
                }
                while (scA.hasNext()) {
                    sql = "insert into Entries(RollCallID,StuID,Present)\n"
                            + "values('" + rollCallID + "','" + scA.next() + "','false')";
                    s.executeUpdate(sql);
                }

            } catch (SQLException e) {
                System.err.println("Thread: " + Thread.currentThread());
                System.err.println("ErrorCode: " + e.getErrorCode());
                System.err.println("SQLState:  " + e.getSQLState());
                System.err.println("Message:   " + e.getMessage());
            }
            Cookie ck1 = new Cookie("house", "");
            Cookie ck2 = new Cookie("grade", "");
            Cookie ck3 = new Cookie("present", "");
            Cookie ck4 = new Cookie("absent", "");
            res.addCookie(ck1);
            res.addCookie(ck2);
            res.addCookie(ck3);
            res.addCookie(ck4);
            PrintWriter out = res.getWriter();
            out.print("<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>Redirect</title>\n"
                    + "        <script>\n"
                    + "            function redirect() {\n"
                    + "                window.location.href='main.jsp';\n"
                    + "            }\n"
                    + "        </script>\n"
                    + "    </head>\n"
                    + "    <body onload=\"redirect()\">\n"
                    + "        <h1>Redirecting...</h1>\n"
                    + "    </body>\n"
                    + "</html>");
        }
        if (req.getParameter("email").equals("true")) {
            emailHtml+="\nRoll Call taken by "+submitter;
            Email.send("sjcrollcall@gmail.com", "SJCrollcall1?", receiver + "@stjohnscollege.co.za", "Roll Call: " + req.getParameter("rcname") + "(" + house + ": " + grade + ")", emailHtml);
            
        }

    }
}
