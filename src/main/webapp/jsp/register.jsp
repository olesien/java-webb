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
              <button class="bar-button studentsButton selected">Students</button>
              <button class="bar-button teachersButton">Teachers</button>
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
            <div class="teachers hidden">
                <form action="/register?type=teacher" method="POST">
                    <label for="teacher_username">Username:</label><br>
                    <input type="text" id="teacher_username" name="teacher_username" required minlength="2"><br>

                    <label for="teacher_password">Password:</label><br>
                    <input type="password" id="teacher_password" name="teacher_password" required minlength="3"><br>

                    <label for="teacher_fname">First Name:</label><br>
                    <input type="text" id="teacher_fname" name="teacher_fname" required minlength="2"><br>

                    <label for="teacher_lname">Last Name:</label><br>
                    <input type="text" id="teacher_lname" name="teacher_lname" required minlength="2"><br>

                    <label for="teacher_town">Town:</label><br>
                    <input type="text" id="teacher_town" name="teacher_town" required minlength="2"><br>

                    <label for="teacher_hobby">Hobby:</label><br>
                    <input type="text" id="teacher_hobby" name="teacher_hobby"><br>

                    <label for="teacher_email">Email:</label><br>
                    <input type="email" id="teacher_email" name="teacher_email" required minlength="2"><br>

                    <label for="teacher_phone">Phone:</label><br>
                    <input type="tel" id="teacher_phone" name="teacher_phone" required minlength="2"><br>

                    <label for="teacher_priv">Privelege Type:</label><br>
                    <select name="teacher_priv" id="teacher_priv">
                        <option value="user" selected>User</option>
                        <option value="admin">Admin</option>
                        <option value="superadmin">Superadmin</option>
                    </select><br>

                    <input type="submit" value="Submit">
                </form>
            </div>

        </div>
            <script src="scripts/register.js"></script>
    </body>
</html>