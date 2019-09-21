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
public class Admin2 extends HttpServlet {

    @Override
    //Receives HTML request from user. 
    //HttpServletRequest is the information entered on the website and sent to the program as the user sends the request
    //HttpServletResponse is the website data that will be sent to the user on their webpage
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String type = req.getParameter("type");//gets info of user entered 
            String id = req.getParameter("id");

            SQLiteJDBC sqlJDBC = new SQLiteJDBC();
            Connection conn = sqlJDBC.SQLconnect();
            Statement s = conn.createStatement();
            ResultSet rs;
            switch (type) {//SQL statements for admin controls
                case "+u":
                    String name = req.getParameter("name");
                    String surname = req.getParameter("surname");
                    String grade = req.getParameter("grade");
                    String house = req.getParameter("house");
                    rs = s.executeQuery("select count() as num from users where stuid='" + id + "'");
                    if (id.isEmpty() || name.isEmpty() || surname.isEmpty() || grade.equals("100") || house.equals("100")) {
                        noInfo(res);
                    } else {
                        if (rs.getString("num").equals("0")) {
                            s.executeUpdate("insert into users(stuid,name,surname,grade,house)\n"
                                    + "values('" + id + "','" + name + "','" + surname + "','" + grade + "','" + house + "')");
                            redirect(res);
                        } else {
                            exists(res);
                        }

                    }
                    break;
                case "-u"://removing a user
                    rs = s.executeQuery("select count() as num from users where stuid='" + id + "'");

                    if (id.isEmpty()) {
                        noInfo(res);
                    } else {
                        if (rs.getString("num").equals("1")) {
                            s.executeUpdate("delete from users where StuID='" + id + "'");
                            redirect(res);

                        } else {
                            noExists(res);
                        }

                    }

                    break;
                case "+a"://adding an admin
                    rs = s.executeQuery("select count() as num from admin where stuid='" + id + "'");

                    if (id.isEmpty()) {
                        noInfo(res);
                    } else {
                        if (rs.getString("num").equals("0")) {
                            s.executeUpdate("insert into admin(StuID) values ('" + id + "')");
                            redirect(res);

                        } else {
                            exists(res);
                        }

                    }

                    break;
                case "-a"://removing an admin
                    rs = s.executeQuery("select count() as num from admin where stuid='" + id + "'");
                    if (id.isEmpty()) {
                        noInfo(res);
                    } else {
                        if (rs.getString("num").equals("1")) {
                            s.executeUpdate("delete from admin where StuID='" + id + "'");
                            redirect(res);
                        } else {
                            noExists(res);
                        }

                    }

                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void noInfo(HttpServletResponse res) throws IOException {//if user does not put in all the required information
        System.out.println("no info");
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
                + "            </div></div>\n"
                + "        <div class=\"center\">\n"
                + "            <h3 style=\"text-align: center\">Please fill in required fields</h3>\n"
                + "        </div>\n"
                + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                + "    </body>\n"
                + "</html>";
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.print(html);
    }

    public void exists(HttpServletResponse res) throws IOException {//if user tries to add a user that already exists
        System.out.println("exists");
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
                + "            </div></div>\n"
                + "        <div class=\"center\">\n"
                + "            <h3 style=\"text-align: center\">User already exists in database</h3>\n"
                + "        </div>\n"
                + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                + "    </body>\n"
                + "</html>";
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.print(html);
    }

    public void noExists(HttpServletResponse res) throws IOException {//if user tries to delete a user not in database
        System.out.println("exists");
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
                + "            </div></div>\n"
                + "        <div class=\"center\">\n"
                + "            <h3 style=\"text-align: center\">User does not exist in database</h3>\n"
                + "        </div>\n"
                + "        <div id=\"bottom-right\"><button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='help.jsp'\">Help</button></div>\n"
                + "    </body>\n"
                + "</html>";
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.print(html);
    }

    public void redirect(HttpServletResponse res) throws IOException {//redirect the user to the main page after completing action
        System.out.println("red");
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

}
