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
                    <img src="images/logo.png" alt="St John's Logo">
                    
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
        </div><form action="adduser"><div class="center" style="text-align: center;height:70px">
        <input type="text" name="id" placeholder="Enter username of user you want to add">
    </div><div class="center" style="text-align: center;height:70px">
        <input type="text" name="name" placeholder="Name">
    </div>
    <div class="center" style="text-align: center;height:70px">
        <input type="text" name="surname" placeholder="Surname">
    </div>
    <div class="center" style="height: 40px">
        <select name="house" style="height: 40px">
            <option value="100" selected disabled hidden>House:</option>
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

    <div class="center" style="height: 40px">
        <select name="grade" style="height: 40px">
            <option value="100" selected disabled hidden>Grade:</option>
            <option value="8">Remove</option>
            <option value="9">Lower Four</option>
            <option value="10">Upper Four</option>
            <option value="11">Lower Five</option>
            <option value="12">Matric</option>
            <option value="13">Lower Six</option>
            <option value="14">Upper Six</option>
        </select>
    </div><div id="bottom-right"><button type="button" class="btn btn-primary" onclick="location.href='help.jsp'">Help</button></div>
    </body>
</html>
