<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Students" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="register.css">
</head>
    <body>
            <jsp:include page='fragments/nav.jsp'>
                <jsp:param name="title" value="Register"/>
            </jsp:include>

             <div class="bar">
              <button class="bar-button selected" onclick="showType('students')">Students</button>
              <button class="bar-button" onclick="showType('teachers')">Teachers</button>
            </div>
        <div>
           <div class="students">
               <form action="/register?type=student" method="POST">
                 <label for="username">Username:</label><br>
                 <input type="text" id="username" name="username" required minlength="2"><br>

                 <label for="password">Password:</label><br>
                 <input type="password" id="password" name="password" required minlength="3"><br>

                 <label for="fname">First Name:</label><br>
                 <input type="text" id="fname" name="fname" required minlength="2"><br>

                 <label for="lname">Last Name:</label><br>
                 <input type="text" id="lname" name="lname" required minlength="2"><br>

                  <label for="town">Town:</label><br>
                  <input type="text" id="town" name="town" required minlength="2"><br>

                  <label for="hobby">Hobby:</label><br>
                  <input type="text" id="hobby" name="hobby"><br>

                  <label for="email">Email:</label><br>
                  <input type="email" id="email" name="email" required minlength="2"><br>

                  <label for="phone">Phone:</label><br>
                  <input type="tel" id="phone" name="phone" required minlength="2"><br>
                  <input type="submit" value="Submit">
               </form>
           </div>

        </div>
    </body>
    <script src="/scripts/register.js" />
</html>