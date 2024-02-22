<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Course Relation</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="admin.css">
</head>
    <body>
            <jsp:include page='../../fragments/nav.jsp'>
                <jsp:param name="title" value="Register"/>
            </jsp:include>

             <div class="bar">
              <a class="bar-button studentsButton" href="/register?type=student">New Student</a>
              <a class="bar-button teachersButton" href="/register?type=teacher">New Teacher</a>
              <a class="bar-button newCourseButton" href="/register?type=course">New Course</a>
              <a class="bar-button studentCourseRelationButton" href="/register?type=studentcourserelation">Student Course Relation</a>
              <a class="bar-button teacherCourseRelationButton selected" href="/register?type=teachercourserelation">Teacher Course Relation</a>
            </div>
        <main class="p-1">
            <h3 class="text-center">Register Student Course Relation</h3>
            <c:if test="${error_message != null}">
                <p class="error">${error_message}<p/>
            </c:if>
            <c:if test="${success_message != null}">
                <p class="success">${success_message}<p/>
            </c:if>
            <div class="addTeacherCourseRelation">
                <form action="register?type=teachercourserelation" method="POST">
                    <label for="courserelation_teacher">Teacher:</label><br>
                    <select name="courserelation_teacher" id="courserelation_teacher" required>
                        <option value="" selected>Select Teacher</option>
                        <c:forEach var="teacher" items="${teachers}">
                            <option value="<c:out value="${teacher.id}" />"><c:out value="${teacher.id}. ${teacher.fname} ${teacher.lname}" /></option>
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

        </main>
    </body>
</html>