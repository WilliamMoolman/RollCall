<%-- 
    Document   : createrollcall
    Created on : 03 May 2019, 11:16:19 AM
    Author     : moolm
--%>

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
    </head>
    <body>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col" >
                    <img src="images/tick.png" alt="St John's Logo" height="150" width="150" >
                    
                </div>
                <div class="col-8">
                    <p class="header">St John's Roll Call</p>
                </div>
            </div>
            <div class="row">
                <img src="images/backsmol.png" alt="Go Back" id="back-button" onclick="goBack()">
            </div>
            <div class="row">
                <div class="col-8"></div>
                
            </div>
        </div>
        <form action="admin">
                <div class="center">
                    <select name="action">
                        <option value="100" selected disabled hidden>Action:</option>
                        <option value="+u">Add User</option>
                        <option value="-u">Remove User</option>
                        <option value="+a">Add Admin</option>
                        <option value="-a">Remove Admin</option>
                    </select>
                </div>

                <div class="center">
                    <input class="btn1" type="submit" style="display: block;margin: 1rem auto;">
                </div>
            
        </form>

        <div id="bottom-right"><button type="button" class="btn btn-primary" onclick="location.href='help.jsp'">Help</button></div>
        
        </body>
</html>
