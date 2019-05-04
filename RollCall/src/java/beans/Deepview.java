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
public class Deepview extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        try {
            String id = req.getParameter("id");
            String html = "<html>\n" +
                    "    <head>\n" +
                    "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "        <title>SJC Roll Call | Create</title>\n" +
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
                    "                <img src=\"images/backsmol.png\" alt=\"Go Back\" id=\"back-button\" onclick=\"goBack()\">\n" +
                    "            </div>";
            SQLiteJDBC sqlJDBC = new SQLiteJDBC();
            Connection conn;
            conn = sqlJDBC.SQLconnect();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select name,surname,house,grade,present\n" +
"from entries\n" +
"left join users on users.StuID=Entries.StuID\n" +
"where rollcallid='"+id+"'\n" +
"order by surname");
            while(rs.next()){
                String style = "";
                if(rs.getString("Present").equals("false")){
                    style = "style=\"background-color: #ff6666\"";
                }
                html+="<div class=\"row \""+
                       style +">\n" +
"                        <div class=\"col border\">\n" +
"                            \n" +rs.getString("Name")+
"                        </div>\n" +
"                        <div class=\"col border\">\n" +
"                            \n" +rs.getString("Surname")+
"                        </div>\n" +
"                        <div class=\"col border\">\n" +
"                            \n" +rs.getString("Present")+
"                        </div>\n" +
"                    </div>";
            }
            html+="            \n" +
"        </div>\n" +
"        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n" +
"    </body>\n" +
"</html>";
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.print(html);
        
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Deepview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}