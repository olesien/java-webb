<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.StudentBean" %>
<jsp:useBean id="total_courses" scope="request" type="java.lang.Integer"/>
<html>
<head>
    <title>Student Course Average</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="admin.css">
</head>
    <body>
            <jsp:include page='../../fragments/nav.jsp'>
                <jsp:param name="title" value="Student Course Average"/>
            </jsp:include>

             <div class="bar">
              <a class="bar-button selected" href="/stats?type=student_course_average">Student Course Average</a>
              <a class="bar-button" href="/stats?type=course_popularity">Course Popularity</a>
            </div>
        <div>
            <c:choose>
                <c:when test="${not empty students}">
                    <table>
                        <tr>
                            <th>Id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Town</th>
                            <th>Course Count</th>
                            <th>Participation</th>
                        </tr>
                        <c:forEach var="student" items="${students}">

                            <tr>
                                <td>#${student.id}</td>
                                <td>${student.fname}</td>
                                <td>${student.lname}</td>
                                <td>${student.town}</td>
                                <td>${student.count}</td>
                                <td>${student.average}%</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>No students found.</p>
                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>