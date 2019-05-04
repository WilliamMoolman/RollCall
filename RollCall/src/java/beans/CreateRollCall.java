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
public class CreateRollCall extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

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
        String house = req.getParameter("house");
        Cookie ck = new Cookie("house", house);
        res.addCookie(ck);
        String grade = req.getParameter("grade");
        Cookie ck2 = new Cookie("grade", grade);
        res.addCookie(ck2);
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        if (house != null && grade != null) {
            try {
                html += "<form action=\"made\">";
                SQLiteJDBC sqlJDBC = new SQLiteJDBC();
                Connection conn = sqlJDBC.SQLconnect();
                String sql = "select name,surname,StuID from Users where House = '" + house + "' and Grade = '" + grade + "'";
                Statement s = conn.createStatement();
                s.executeUpdate(sql);
                ResultSet rs = s.executeQuery(sql);
                
                if(rs.next()){
                    int i = 0;
                    i++;
                        String name = rs.getString("Name");
                        String surname = rs.getString("Surname");
                        String ID = rs.getString("StuID");
                        html += "<div class=\"row\">\n"
                                + "                <div class=\"col border\">\n"
                                + "                    <h5>" + name + "</h5>\n"
                                + "                </div>\n"
                                + "                <div class=\"col border\">\n"
                                + "                    <h5>" + surname + "</h5>\n"
                                + "                </div>\n"
                                + "                <div class=\"col border\">\n"
                                + "                    <input value=\"" + ID + "\" name =\"" + i + "\"type =\"checkbox\">\n"
                                + "                </div>\n"
                                + "            </div>";
                    while (rs.next()) {
                        i++;
                        name = rs.getString("Name");
                        surname = rs.getString("Surname");
                        ID = rs.getString("StuID");
                        html += "<div class=\"row\">\n"
                                + "                <div class=\"col border\">\n"
                                + "                    <h5>" + name + "</h5>\n"
                                + "                </div>\n"
                                + "                <div class=\"col border\">\n"
                                + "                    <h5>" + surname + "</h5>\n"
                                + "                </div>\n"
                                + "                <div class=\"col border\">\n"
                                + "                    <input value=\"" + ID + "\" name =\"" + i + "\"type =\"checkbox\">\n"
                                + "                </div>\n"
                                + "            </div>";
                    }
                    html += "</div>\n"
                            + "        <div class=\"center\">\n"
                            + "            <input class=\"btn1\" type=\"submit\" style=\"display: block;margin: 1rem auto;\"></form>\n"
                            + "        </div>\n"
                            + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                            + "    </body>\n"
                            + "</html>";
                    out.print(html);
                    
                } else {
                    html += "</div>\n"
                    + "        <div class=\"center\">\n"
                    + "            <h3 style=\"text-align: center\">No students in set</h3>\n"
                    + "        </div>\n"
                    + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                    + "    </body>\n"
                    + "</html>";
            out.print(html);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateRollCall.class.getName()).log(Level.SEVERE, null, ex);
            }     
        } else {
            html += "</div>\n"
                    + "        <div class=\"center\">\n"
                    + "            <h3 style=\"text-align: center\">Please choose both a house and a grade</h3>\n"
                    + "        </div>\n"
                    + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                    + "    </body>\n"
                    + "</html>";
            out.print(html);
        }

    }
}
