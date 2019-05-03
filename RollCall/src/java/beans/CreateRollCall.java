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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moolm
 */
public class CreateRollCall extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        
        String html = "<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <title>SJC Roll Call | Take</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" crossorigin=\"anonymous\">\n" +
"        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\">\n" +
"        <meta name=\"google-signin-client_id\" content=\"334817504785-g0vqsgqoii5hc17djc5bkefeottshc3c.apps.googleusercontent.com\">\n" +
"        <link rel=\"shortcut icon\" type=\"image/png\" href=\"images/logo.png\">\n" +
"        <script src=\"https://apis.google.com/js/platform.js\" async defer></script>\n" +
"        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" crossorigin=\"anonymous\"></script>\n" +
"        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" crossorigin=\"anonymous\"></script>\n" +
"        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" crossorigin=\"anonymous\"></script>\n" +
"    </head>\n" +
"    <body>\n" +
"        <div class=\"container-fluid\">\n" +
"            <div class=\"row\">\n" +
"                <div class=\"col\" >\n" +
"                    <img src=\"images/logo.png\" alt=\"St John's Logo\">\n" +
"                </div>\n" +
"                <div class=\"col-8\">\n" +
"                    <p class=\"header\">St John's Roll Call</p>\n" +
"                </div>\n" +
"            </div>"
                + "<form action=\"made\">";
        String house = req.getParameter("house");
        int grade = Integer.parseInt(req.getParameter("grade"));
        html+="<input name=\"house\" id=\"hidden\" value=\""+house+"\" ><input name=\"grade\" id=\"hidden\" value=\""+grade+"\">";
        res.setContentType("text/html");
        
        try {
            SQLiteJDBC sqlJDBC = new SQLiteJDBC();
            Connection conn = sqlJDBC.SQLconnect();
            String sql = "select name,surname,StuID from Users where House = '"+house+"' and Grade = '"+grade+"'";
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
            ResultSet rs = s.executeQuery(sql);
            PrintWriter out = res.getWriter();
            String result = "";
            while (rs.next()) {
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String ID = rs.getString("StuID");
                html+= "<div class=\"row\">\n" +
"                <div class=\"col border\">\n" +
"                    <h5>"+name+"</h5>\n" +
"                </div>\n" +
"                <div class=\"col border\">\n" +
"                    <h5>"+surname+"</h5>\n" +
"                </div>\n" +
"                <div class=\"col border\">\n" +
"                    <input value=\"present\" name =\""+ID+"\"type =\"checkbox\">\n" +
"                </div>\n" +
"            </div>";
            }
            html+="</div>\n" +
"        <div class=\"center\">\n" +
"            <input class=\"btn1\" type=\"submit\" style=\"display: block;margin: 1rem auto;\"></for>\n" +
"        </div>\n" +
"        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n" +
"    </body>\n" +
"</html>";
        out.print(html);
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection failed lmao");
        } catch (SQLException ex) {
            Logger.getLogger(CreateRollCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
}
