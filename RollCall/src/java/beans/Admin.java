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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moolm
 */
public class Admin extends HttpServlet {

    @Override
    //Receives HTML request from user. 
    //HttpServletRequest is the information entered on the website and sent to the program as the user sends the request
    //HttpServletResponse is the website data that will be sent to the user on their webpage
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        String action = req.getParameter("action");
        String html = "<!DOCTYPE html>\n"//starts a string which will contain the html for the response
                + "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>SJC Roll Call | Create</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" crossorigin=\"anonymous\">\n"
                + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\">\n"
                + "        <meta name=\"google-signin-client_id\" content=\"334817504785-g0vqsgqoii5hc17djc5bkefeottshc3c.apps.googleusercontent.com\">\n"
                + "        <link rel=\"shortcut icon\" type=\"image/png\" href=\"images/logo.png\">\n"
                + "        <script src=\"https://apis.google.com/js/platform.js\" async defer></script>\n"
                + "        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" crossorigin=\"anonymous\"></script>\n"
                + "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" crossorigin=\"anonymous\"></script>\n"
                + "        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" crossorigin=\"anonymous\"></script>\n"
                + "        <script>\n"
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
                + "                    \n"
                + "                </div>\n"
                + "                <div class=\"col-8\">\n"
                + "                    <p class=\"header\">St John's Roll Call</p>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <img src=\"images/backsmol.png\" alt=\"Go Back\" id=\"back-button\" onclick=\"goBack()\">\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"col-8\"></div>\n"
                + "                \n"
                + "            </div>\n"
                + "        </div>";

        Cookie[] cookies = req.getCookies();//puts cookies in an array
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {//gets username cookie
                try {
                    SQLiteJDBC sqlJDBC = new SQLiteJDBC();//connects to the SQL database
                    Connection conn = sqlJDBC.SQLconnect();
                    Statement s = conn.createStatement();
                    ResultSet rs = s.executeQuery("select * from Admin");

                    while (rs.next()) {
                        if (rs.getString("StuID").equals(cookie.getValue())) {//checks if user is an admin
                            switch (action) {//depending on the users choice of admin command, different html pages will appear
                                case "+u"://add user
                                    html += "<form action=\"admin2\">";
                                    html+= "<input name=\"type\" id=\"hidden\" value=\"+u\">";
                                    html += "<div class=\"center\" style=\"text-align: center;height:70px\">\n"
                                            + "        <input type=\"text\" name=\"id\" placeholder=\"Enter username of user you want to add\">\n"
                                            + "    </div>";
                                    html += "<div class=\"center\" style=\"text-align: center;height:70px\">\n"
                                            + "        <input type=\"text\" name=\"name\" placeholder=\"Name\">\n"
                                            + "    </div>\n"
                                            + "    <div class=\"center\" style=\"text-align: center;height:70px\">\n"
                                            + "        <input type=\"text\" name=\"surname\" placeholder=\"Surname\">\n"
                                            + "    </div>\n"
                                            + "    <div class=\"center\" style=\"height: 40px\">\n"
                                            + "        <select name=\"house\" style=\"height: 40px\"> \n"
                                            + "            <option value=\"100\" selected disabled hidden>House:</option>\n"
                                            + "            <option value=\"Nash\">Nash</option>\n"
                                            + "            <option value=\"Hill\">Hill</option>\n"
                                            + "            <option value=\"Clayton\">Clayton</option>\n"
                                            + "            <option value=\"Thomson\">Thomson</option>\n"
                                            + "            <option value=\"Alston\">Alston</option>\n"
                                            + "            <option value=\"Clarke\">Clarke</option>\n"
                                            + "            <option value=\"Hodgson\">Hodgson</option>\n"
                                            + "            <option value=\"Fleming\">Fleming</option>\n"
                                            + "            <option value=\"Runge\">Runge</option>\n"
                                            + "        </select>\n"
                                            + "    </div>\n"
                                            + "\n"
                                            + "    <div class=\"center\" style=\"height: 40px\">\n"
                                            + "        <select name=\"grade\" style=\"height: 40px\">\n"
                                            + "            <option value=\"100\" selected disabled hidden>Grade:</option>\n"
                                            + "            <option value=\"8\">Remove</option>\n"
                                            + "            <option value=\"9\">Lower Four</option>\n"
                                            + "            <option value=\"10\">Upper Four</option>\n"
                                            + "            <option value=\"11\">Lower Five</option>\n"
                                            + "            <option value=\"12\">Matric</option>\n"
                                            + "            <option value=\"13\">Lower Six</option>\n"
                                            + "            <option value=\"14\">Upper Six</option>\n"
                                            + "        </select>\n"
                                            + "    </div>";
                                    break;
                                case "-u"://remove user
                                    html += "<form action=\"admin2\">";
                                    html+= "<input name=\"type\" id=\"hidden\" value=\"-u\">";
                                    html += "<div class=\"center\" style=\"text-align: center;height:70px\">\n"
                                            + "        <input type=\"text\" name=\"id\" placeholder=\"Enter username of user you want to remove\">\n"
                                            + "    </div>";
                                    break;
                                case "+a"://add admin
                                    html += "<form action=\"admin2\">";
                                    html+= "<input name=\"type\" id=\"hidden\" value=\"+a\">";
                                    html += "<div class=\"center\" style=\"text-align: center;height:70px\">\n"
                                            + "        <input type=\"text\" name=\"id\" placeholder=\"Enter username of admin you want to add\">\n"
                                            + "    </div>";
                                    break;
                                case "-a"://remove admin
                                    html += "<form action=\"admin2\">";
                                    html+= "<input name=\"type\" id=\"hidden\" value=\"-a\">";
                                    html += "<div class=\"center\" style=\"text-align: center;height:70px\">\n"
                                            + "        <input type=\"text\" name=\"id\" placeholder=\"Enter username of admin you want to remove\">\n"
                                            + "    </div>";
                                    break;
                                default:
                                
                            }

                            html += "<div class=\"center\" style=\"height: 40px\">\n"
                                    + "                    <input class=\"btn1\" type=\"submit\" style=\"display: block;margin: 1rem auto;height: 40px;text-align: left; padding-left: 10px\">\n"
                                    + "                </div>\n"
                                    + "            </div>\n"
                                    + "        </form>\n"
                                    + "\n"
                                    + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                                    + "    </body>\n"
                                    + "</html>";
                            out.print(html);

                        }
                    }
                    //if they are not admin, it will display a message telling them they don't have access
                    html += "</div>\n"
                            + "        <div class=\"center\">\n"
                            + "            <h3 style=\"text-align: center\">Not an admin, please sign in with admin account to access these features</h3>\n"
                            + "        </div>\n"
                            + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                            + "    </body>\n"
                            + "</html>";
                    html = "";
                    out.print(html);//loads html page for user
                } catch (SQLException ex) {
                    System.out.println("SQL Error");
                }
            }
        }

    }
}
