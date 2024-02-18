<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Courses" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

                <jsp:include page='fragments/nav.jsp'>
                    <jsp:param name="title" value="Courses"/>
                </jsp:include>


                <c:choose>
                    <c:when test="${not empty courses}">
                        <table>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>YHP</th>
                            </tr>
                            <c:forEach var="course" items="${courses}">
                                <tr>
                                    <td>${course.name}</td>
                                    <td>${course.description}</td>
                                    <td>${course.yhp}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p>No courses found.</p>
                    </c:otherwise>
                </c:choose>
    </body>
</html>