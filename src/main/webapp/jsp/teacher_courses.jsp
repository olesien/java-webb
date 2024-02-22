<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="teacher" scope="request" type="models.TeacherBean"/>
<%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title><%= request.getAttribute("name") %></title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <jsp:include page="fragments/nav.jsp">
            <jsp:param name="title" value="Courses" />
        </jsp:include>
        <main class="p-1">
            <h3 class="text-center">Showing courses for the teacher ${teacher.fname} ${teacher.lname}</h3>
            <c:choose>
                <c:when test="${not empty courses}">
                    <table>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>YHP</th>
                            <th>Students</th>
                            <th>Teachers</th>
                        </tr>
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td>${course.name}</td>
                                <td>${course.description}</td>
                                <td>${course.yhp}</td>
                                <td>
                                    <a href="/students_by_course?id=${course.id}"
                                        >See Students
                                    </a>
                                </td>
                                <td>
                                    <a href="/teachers_by_course?id=${course.id}"
                                        >See Teachers
                                    </a>
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
        <jsp:include page='./fragments/footer.jsp' />
    </body>
</html>
