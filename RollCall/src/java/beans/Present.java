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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moolm
 */
public class Present extends HttpServlet{
    @Override
    //Receives HTML request from user. 
    //HttpServletRequest is the information entered on the website and sent to the program as the user sends the request
    //HttpServletResponse is the website data that will be sent to the user on their webpage
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, NullPointerException{
        String house = null;
        String grade = null;
        String html =
"<!DOCTYPE html>\n" +
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
"        <script>\n" +
"            function goBack() {\n" +
"                window.history.back();\n" +
"            }\n" +
"        </script>\n" +
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
"            </div>\n" +
"            <div class=\"row\">\n" +
"                <div class=\"col-3\">\n" +
"                    <img src=\"images/backsmol.png\" alt=\"Go Back\" id=\"back-button\" onclick=\"goBack()\">\n" +
"                </div>\n" +
"                <div class =\"col-9\">\n" +
"                    <p class=\"h5\">These students are absent. Continue?</p>\n" +
"                </div>      \n" +
"            </div>";
        
        PrintWriter out = res.getWriter();
        try {
            Cookie[] cookies = req.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("house")) {
                        house = cookie.getValue();
                    }
                    if (cookie.getName().equals("grade")) {
                        grade = cookie.getValue();
                    }
                }
            }
            
            SQLiteJDBC sqlJDBC = new SQLiteJDBC();
            Connection conn = sqlJDBC.SQLconnect();
            String sql = "select name,surname,StuID from Users where House = '"+house+"' and Grade = '"+grade+"'";
            Statement s = conn.createStatement();//checks who is present
            ResultSet rs = s.executeQuery(sql);
            String[] checkID = new String[30];
            String[] present = new String[30];
            String[] absent = new String[30];
            int numPresent = 0;
            int numAbsent = 0;
            int numTotal = 0;
            while(rs.next()){
                checkID[numTotal] = rs.getString(("StuID"));
                numTotal++;
            }
            outerloop:
            for(int y = 0;y<numTotal;y++){
                boolean inset=false;
                
                
                for(int z = 1;z<=numTotal;z++){
                    String param = req.getParameter(Integer.toString(z));
                    label1:
                    try {
                        if(param.equals(checkID[y])){
                            present[numPresent]=checkID[y];//checks whether they were bresent
                            numPresent++;
                            inset=true;
                            break label1;
                        }
                    } catch(NullPointerException e) {
                    }
                    
                }
                if(!inset){
                        absent[numAbsent]=checkID[y];
                        numAbsent++;
                    }
                
            }
            
            for(int k = 0;k<numAbsent;k++){
                s.executeQuery("select name,surname from users where StuID='"+absent[k]+"'");
                ResultSet rs2 = s.executeQuery("select name,surname from users where StuID='"+absent[k]+"'");
                String name2 = rs2.getString("Name");
                String surname2 = rs2.getString("Surname");
                html+="<div class=\"row\">\n" +
"                <div class=\"col border\">\n" +//prints the names of those present to the html
"                    <h5>"+name2+"</h5>\n" +
"                </div>\n" +
"                <div class=\"col border\">\n" +
"                    <h5>"+surname2+"</h5>\n" +
"                </div>\n" +
"            </div>";
            }
            
            html+="</div>\n" +
"        <div class=\"center\">\n" +
"            \n" +
"                <button class=\"btn1\" onclick=\"location.href='submit.jsp'\" type=\"button\">Continue</button> " +
"            \n" +
"        </div>\n" +
"        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n" +
"    </body>\n" +
"</html>";
        res.setContentType("text/html");
        out.print(html);
        String pres = "";
        for(int l = 0;l<numPresent;l++){
            pres+=present[l]+",";
        }
        String abs = "";
        for(int l = 0;l<numAbsent;l++){
            abs+=absent[l]+",";
        }
        Cookie ck = new Cookie("present",pres);//adds users to cookies for later usage
        Cookie ck2 = new Cookie("absent",abs);
        res.addCookie(ck);
        res.addCookie(ck2);
        } catch (SQLException ex) {
            Logger.getLogger(Present.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}