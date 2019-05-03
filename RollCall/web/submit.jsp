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
                    <option value="100" selected disabled hidden>Pick a Housemaster:</option>
                    <option value="Nash">Nash</option>
                    <option value="Hill">Hill</option>
                    <option value="Clayton">Clayton</option>
                    <option value="Thomson">Thomson</option>
                    <option value="Alston">Alston</option>
                    <option value="Clarke">Clarke</option>
                    <option value="Hodgson">Hodgson</option>
                    <option value="Fleming">Fleming</option>
                    <option value="Runge">Runge</option>
                </select>
            </div>
            <div class="center" style="text-align: center;height:20px">
                - or -
            </div>
            <div class="center" style="text-align: center;height:70px">
                <input type="text" name="rcemail" placeholder="Enter receipient email">
            </div>
            <div class="center" style="text-align: center;height:70px">
                <form action="send">
                    <input class="btn1 select-new" type="submit">
            </div>
        </form>
        <div id="bottom-right"><button type="button" class="btn btn-primary" onclick="location.href='help.jsp'">Help</button></div>
    </body>
</html>