<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Popularity</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="admin.css">
</head>
    <body>
            <jsp:include page='../../fragments/nav.jsp'>
                <jsp:param name="title" value="Stats"/>
            </jsp:include>

             <div class="bar">
              <a class="bar-button" href="/stats?type=student_course_average">Student Course Average</a>
              <a class="bar-button selected" href="/stats?type=course_popularity">Course Popularity</a>
            </div>
        <main class="p-1">
            <h3 class="text-center">Course Popularity</h3>
            <c:choose>
                <c:when test="${not empty courses}">
                    <table>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>YHP</th>
                            <th>Popularity</th>
                        </tr>
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td>${course.name}</td>
                                <td>${course.description}</td>
                                <td>${course.yhp}</td>
                                <td>
                                    ${course.popularity}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>No courses found.</p>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>