<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Student</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="admin.css">
</head>
    <body>
            <jsp:include page='../../fragments/nav.jsp'>
                <jsp:param name="title" value="Register"/>
            </jsp:include>

             <div class="bar">
              <a class="bar-button studentsButton selected" href="/register?type=student">New Student</a>
              <a class="bar-button teachersButton" href="/register?type=teacher">New Teacher</a>
              <a class="bar-button newCourseButton" href="/register?type=course">New Course</a>
              <a class="bar-button studentCourseRelationButton" href="/register?type=studentcourserelation">Student Course Relation</a>
                 <a class="bar-button teacherCourseRelationButton" href="/register?type=teachercourserelation">Teacher Course Relation</a>
            </div>
        <main class="p-1">
            <h3 class="text-center">Register Student</h3>
            <c:if test="${error_message != null}">
                 <p class="error">${error_message}<p/>
            </c:if>
            <c:if test="${success_message != null}">
                <p class="success">${success_message}<p/>
            </c:if>
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
        </main>
    </body>
</html>