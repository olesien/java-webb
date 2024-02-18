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
            <jsp:include page='../../fragments/nav.jsp'>
                <jsp:param name="title" value="Register Teacher"/>
            </jsp:include>

             <div class="bar">
              <a class="bar-button studentsButton" href="/register?type=student">New Student</a>
              <a class="bar-button teachersButton" href="/register?type=teacher">New Teacher</a>
              <a class="bar-button newCourseButton selected" href="/register?type=course">New Course</a>
              <a class="bar-button courseRelationButton" href="/register?type=courserelation">Course Relation</a>
            </div>
        <div>
            <div class="addCourse">
                <form action="register?type=course" method="POST">
                    <label for="course_name">Name:</label><br>
                    <input type="text" id="course_name" name="course_name" required minlength="2"><br>

                    <label for="course_description">Description:</label><br>
                    <input type="text" id="course_description" name="course_description" required minlength="3"><br>

                    <label for="course_yhp">Yhp:</label><br>
                    <input type="number" id="course_yhp" name="course_yhp" required min="0"><br>

                    <input type="submit" value="Submit">
                </form>
            </div>

        </div>
    </body>
</html>