<%-- 
    Document   : main
    Created on : 03 May 2019, 8:10:37 AM
    Author     : moolm
--%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>SJC Roll Call | Main</title>
        <meta charset="UTF-8">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <meta name="google-signin-client_id" content="334817504785-g0vqsgqoii5hc17djc5bkefeottshc3c.apps.googleusercontent.com">
        <link rel="shortcut icon" type="image/png" href="images/tick.png">
    </head>
    <body>
        <div class="g-signin2" id="hidden" data-onsuccess="onSignIn">Sign In</div>
        <div class="container-fluid">
            <div class="row">
                <div class="col" >
                    <img src="images/tick.png" alt="St John's Logo">
                </div>
                <div class="col-8">
                    <p class="header">St John's Roll Call</p>
                </div>
            </div>
        </div>
        
        <div class="center">
            <button type="button" class="btn1" onclick="location.href='createrollcall.jsp'">Create Roll Call</button>
        </div>
        <div class="center">
            <button type="button" class="btn1" onclick="location.href='view.jsp'">View Roll Calls</button>
        </div>
        <div class="center">
            <button type="button" class="btn1" onclick="signOut()" >Log Out</button>
        </div>
        
        <div id="bottom-right"><button type="button" class="btn btn-primary" onclick="location.href='help.jsp'">Help</button></div>
        <script type="text/javascript">
            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                //document.getElementById('g-email').innerHTML = profile.getEmail();
                var uname= profile.getEmail().substr(0, profile.getEmail().indexOf('@')); 
                document.cookie = "username=".concat(uname);
                console.log('singed in-mmain');
            }
            function signOut() {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    document.cookie = "username=";
                    console.log('User signed out.');
                    window.location.href='index.jsp';
                });
            }
        </script> 
        
    </body>
</html>

