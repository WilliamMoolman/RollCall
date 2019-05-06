<%-- 
    Document   : template
    Created on : 03 May 2019, 11:16:19 AM
    Author     : moolm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SJC Roll Call | Take</title>
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
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col" >
                    <img src="images/logo.png" alt="St John's Logo">
                </div>
                <div class="col-8">
                    <p class="header">St John's Roll Call</p>
                </div>
            </div>
            <div class="row">
                <img src="images/backsmol.png" alt="Go Back" id="back-button" onclick="goBack()">
            </div>
        </div>
        <form action="send">
            <div class="center" style="height:70px;margin:0 auto;">
                <input type="text" name="rcname" style="width: 200px;" placeholder="Enter a roll call name">
            </div>
            <div class="center" style="height:70px;" >
                <select name="house" class="select-new" >
                    <%
                        Cookie[] cookies = request.getCookies();
                        String house = "";
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("house")) {
                                house = cookie.getValue();
                            }
                        }
                    %>
                    <option value="caldwella"<% if(house.equals("Nash")){ %> selected <% } %>>Nash</option>
                    <option value="libera"<% if(house.equals("Hill")){ %> selected <% } %>>Hill</option>
                    <option value="murison"<% if(house.equals("Clayton")){ %> selected <% } %>>Clayton</option>
                    <option value="stocks"<% if(house.equals("Thomson")){ %> selected <% } %>>Thomson</option>
                    <option value="black"<% if(house.equals("Alston")){ %> selected <% } %>>Alston</option>
                    <option value="dereuckr"<% if(house.equals("Clarke")){ %> selected <% } %>>Clarke</option>
                    <option value="tswatswa"<% if(house.equals("Hodgson")){ %> selected <% } %>>Hodgson</option>
                    <option value="venter"<% if(house.equals("Fleming")){ %> selected <% } %>>Fleming</option>
                    <option value="byrne"<% if(house.equals("Runge")){ %> selected <% } %>>Runge</option>
                </select>
            </div>
            <div class="center" style="text-align: center;height:20px">
                - or -
            </div>
            <div class="center" style="text-align: center;height:70px">
                <input type="text" name="rcemail" placeholder="Enter receipient username">
            </div>
            <div class="center" style="text-align: center" >
                <p style="padding-top: 10px">Email list to recipient?</p><input type="checkbox" name="email" value="true" >
            </div>
            <div class="center" style="text-align: center;height:70px">
                <form action="send">
                    <input class="btn1 select-new" type="submit">
            </div>
        </form>
        <div id="bottom-right"><button type="button" class="btn btn-primary" onclick="location.href='help.jsp'">Help</button></div>
    </body>
</html>