<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Students" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Course Relation</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="register.css">
</head>
    <body>
            <jsp:include page='../../fragments/nav.jsp'>
                <jsp:param name="title" value="Register Course Relation"/>
            </jsp:include>

             <div class="bar">
              <a class="bar-button studentsButton" href="/register?type=student">New Student</a>
              <a class="bar-button teachersButton" href="/register?type=teacher">New Teacher</a>
              <a class="bar-button newCourseButton" href="/register?type=course">New Course</a>
              <a class="bar-button courseRelationButton selected" href="/register?type=courserelation">Course Relation</a>
            </div>
        <div>
            <div class="addCourseRelation">
                <form action="register?type=courserelation" method="POST">
                    <label for="courserelation_student">Student:</label><br>
                    <select name="courserelation_student" id="courserelation_student" required>
                        <option value="" selected>Select Student</option>
                        <c:forEach var="student" items="${students}">
                            <option value="<c:out value="${student.id}" />"><c:out value="${student.id}. ${student.fname} ${student.lname}" /></option>
                        </c:forEach>
                    </select><br>
                    <label for="courserelation_course">Course:</label><br>
                    <select name="courserelation_course" id="courserelation_course" required>
                        <option value="" selected>Select Course</option>
                        <c:forEach var="course" items="${courses}">
                        <option value="<c:out value="${course.id}" />"><c:out value="${course.name}" /></option>
                        </c:forEach>
                    </select><br>

                    <input type="submit" value="Submit">
                </form>
            </div>

        </div>
    </body>
</html>