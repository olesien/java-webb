<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Students" %>
<html>
<head>
    <title>Add Teacher</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="register.css">
</head>
    <body>
            <jsp:include page='../../fragments/nav.jsp'>
                <jsp:param name="title" value="Register Teacher"/>
            </jsp:include>

             <div class="bar">
              <a class="bar-button studentsButton" href="/register?type=student">New Student</a>
              <a class="bar-button teachersButton selected" href="/register?type=teacher">New Teacher</a>
              <a class="bar-button newCourseButton" href="/register?type=course">New Course</a>
              <a class="bar-button courseRelationButton" href="/register?type=courserelation">Course Relation</a>
            </div>
        <div>
            <div class="teachers">
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

                    <label for="teacher_priv">Privilege Type:</label><br>
                    <select name="teacher_priv" id="teacher_priv">
                        <option value="user" selected>User</option>
                        <option value="admin">Admin</option>
                        <option value="superadmin">Superadmin</option>
                    </select><br>

                    <input type="submit" value="Submit">
                </form>
            </div>

        </div>
    </body>
</html>