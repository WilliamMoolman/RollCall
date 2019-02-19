<%-- 
    Document   : main
    Created on : 18 Feb 2019, 7:40:33 PM
    Author     : moolm
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/png" href="img/final_logo.png">
        <meta name="google-signin-client_id" content="334817504785-g0vqsgqoii5hc17djc5bkefeottshc3c.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script type="text/javascript">
            gapi.load('auth2',function(){
                gapi.auth2.init();
            });
            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                var id_token = googleUser.getAuthResponse().id_token;
                var profile_ID = profile.getId();
                var profile_name = profile.getName();
                var profile_image = profile.getImageUrl();
                var profile_email = profile.getEmail();
                document.getElementById("profileEmail").innerHTML = profile_email;
            }
        </script>
        <title>1ST PAGE</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div class="g-signin2" data-onsuccess="onSignIn"></div>
        <div>
            <%
            String name = (String)request.getParameter("profileEmail");
            %>
            <%=name %>
        </div>
    </body>
</html>
