<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Students" %>
<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

               <div class="header">
                    <h2><%= request.getAttribute("name") %></h2>
                    <nav>
                        <a class="disabled" href="/students">Students</a>
                        <a href="/courses">Courses</a>
                        <a href="/student_courses">Student Courses</a>
                    </nav>
               </div>
               <%= request.getAttribute("message") %>

    </body>
</html>