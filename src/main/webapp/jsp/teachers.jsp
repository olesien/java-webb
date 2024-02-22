<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.StudentBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="course" scope="request" type="models.CourseBean"/>

<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

            <jsp:include page='./fragments/nav.jsp'>
                <jsp:param name="title" value="Teachers"/>
            </jsp:include>
                <main class="p-1">
                    <h3 class="text-center">Teachers that are a part of the course ${course.name}</h3>
                    <c:choose>
                        <c:when test="${not empty teachers}">
                            <table>
                                <tr>
                                    <th>Id</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Town</th>
                                    <th>Hobby</th>
                                    <th>Courses</th>
                                </tr>
                                <c:forEach var="teacher" items="${teachers}">
                                    <tr>
                                        <td>#${teacher.id}</td>
                                        <td>${teacher.fname}</td>
                                        <td>${teacher.lname}</td>
                                        <td>${teacher.town}</td>
                                        <td>${teacher.hobby}</td>
                                        <td><a href="/teacher_courses?id=${teacher.id}">Active Courses </a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>No teachers found.</p>
                        </c:otherwise>
                    </c:choose>
                </main>

    </body>
</html>