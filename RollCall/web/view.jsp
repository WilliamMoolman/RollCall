<%-- 
    Document   : template
    Created on : 03 May 2019, 11:16:19 AM
    Author     : moolm
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.SQLiteJDBC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SJC Roll Call | Create</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <meta name="google-signin-client_id" content="334817504785-g0vqsgqoii5hc17djc5bkefeottshc3c.apps.googleusercontent.com">
        <link rel="shortcut icon" type="image/png" href="images/logo.png">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
        <script>
            function onClick(i){
            var form = document.getElementById("myForm");
            form.action = "deepview";
            form.id.value=i;
            form.submit();
        }
            
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col" >
                    <img src="images/tick.png" alt="St John's Logo" height="150" width="150">
                </div>
                <div class="col-8">
                    <p class="header">St John's Roll Call</p>
                </div>
            </div>
            <div class="row">
                <img src="images/backsmol.png" alt="Go Back" id="back-button" onclick="goBack()">
            </div>
            <% 
                SQLiteJDBC sqlJDBC = new SQLiteJDBC();
                Connection conn;
                conn = sqlJDBC.SQLconnect();
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery("select *, substr(Date,0,12) as goodtime from RollCalls where ReceiverID = '23723' or SubmitterID = '23723' order by Date DESC");
                while(rs.next()){
                    %>
                    <div class="row border" onclick="onClick(<%=rs.getString("RollCallID")%>)">
                        <div class="col">
                            <%=rs.getString("title")%>
                        </div>
                        <div class="col">
                            <%=rs.getString("goodtime")%>
                        </div>
                        <div class="col">
                            <%=rs.getString("House")+" "+rs.getString("Grade")%>
                        </div>
                        <div class="col">
                            <%=rs.getString("SubmitterID")%>
                        </div> 
                    </div>
                    <%
                }
            %>
            
            
            <form id="myForm">
                <input id="hidden" name="id">
            </form>    
        </div>
        <div id="bottom-right"><button type="button" class="btn btn-primary" onclick="location.href='help.jsp'">Help</button></div>
    </body>
</html>
