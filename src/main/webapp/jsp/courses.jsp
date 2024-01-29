<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Courses" %>
<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

               <div class="header">
                    <h2><%= request.getAttribute("name") %></h2>
                    <nav>
                        <a href="/students">Students</a>
                        <a class="disabled" href="/courses">Courses</a>
                        <a href="/student_courses">Student Courses</a>
                    </nav>
               </div>
               <%
                   ArrayList<Courses> courseList = (ArrayList<Courses>) request.getAttribute("courses");
                   if (courseList != null && !courseList.isEmpty()) {
               %>
               <table>
                   <tr>
                       <th>Name</th>
                       <th>Description</th>
                       <th>YHP</th>
                   </tr>
                   <% for (Courses course : courseList) { %>
                   <tr>
                       <td><%= course.getName() %></td>
                       <td><%= course.getDescription() %></td>
                       <td><%= course.getYhp() %></td>
                   </tr>
                   <% } %>
               </table>
               <% } else { %>
                No courses found.
               <% } %>
    </body>
</html>