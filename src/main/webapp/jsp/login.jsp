<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Students" %>
<html>
<head>
    <title>Login </title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
    <body>
            <div class="login-container">
               <form class="login" ="/login" method="POST">
                 <label for="username">Username:</label><br>
                 <input type="text" id="username" name="username" required minlength="2"><br>

                 <label for="password">Password:</label><br>
                 <input type="text" id="password" name="password" required minlength="2"><br>
                 <input type="submit" value="Login">
               </form>
            </div>

    </body>
</html>