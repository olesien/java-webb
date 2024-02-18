<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Students" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

            <jsp:include page='../fragments/nav.jsp'>
                <jsp:param name="title" value="Students"/>
            </jsp:include>
                <c:choose>
                    <c:when test="${not empty students}">
                        <table>
                            <tr>
                                <th>Id</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Town</th>
                                <th>Hobby</th>
                                <th>Courses</th>
                            </tr>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>#${student.id}</td>
                                    <td>${student.fname}</td>
                                    <td>${student.lname}</td>
                                    <td>${student.town}</td>
                                    <td>${student.hobby}</td>
                                    <td><a href="/courses_by_students/${student.id}">Active Courses </a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p>No students found.</p>
                    </c:otherwise>
                </c:choose>

    </body>
</html>