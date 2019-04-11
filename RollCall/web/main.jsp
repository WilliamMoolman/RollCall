<%-- 
    Document   : main
    Created on : 11 Apr 2019, 8:12:18 AM
    Author     : addmin
--%>

<%@page import="beans.SignIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <meta name="google-signin-client_id" content="334817504785-g0vqsgqoii5hc17djc5bkefeottshc3c.apps.googleusercontent.com">
        
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <script type="text/javascript">
            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                //document.getElementById('g-image').src=profile.getImageUrl();
                document.getElementById('g-name').innerHTML =profile.getName();
                //document.getElementById('g-email').innerHTML =profile.getEmail();
                console.log(profile.getName());
                //window.location.href='main.jsp';
            }
        </script>  
        <script type="text/javascript">
            function signIn() {
                <%
                    System.out.println("function onSignIn Activated ");
                    System.out.println((String)request.getParameter("gemail"));
                    SignIn googleID = new SignIn((String)request.getParameter("gemail"),(String)request.getParameter("gname"),(String)request.getParameter("gimage"));
                %>
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
                <div class="col">
                    <p id="g-name">not work</p>
                </div>
                <div class="col">
                    
                </div>
                <div class="col">
                    
                </div>
            </div>
            <div class="row">
                <div class="col-8"></div>
                
            </div>
        </div> 
        <div class="g-signin2" id="hidden" data-onsuccess="onSignIn">Sign In</div>
        <div id="bottom-right"><button type="button" class="btn btn-primary">Help</button></div>
        
        
         
    </body>
</html>
