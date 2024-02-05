<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="school.models.StudentsWithCourses" %>
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
                        <a href="/courses">Courses</a>
                        <a class="disabled" href="/student_courses">Student Courses</a>
                    </nav>
               </div>
               <%
                   ArrayList<StudentsWithCourses> studentsList = (ArrayList<StudentsWithCourses>) request.getAttribute("students");
                   if (studentsList != null && !studentsList.isEmpty()) {
               %>
               <table>
                   <tr>
                       <th>Name</th>
                       <th>Town</th>
                       <th>Hobby</th>
                       <th>Courses</th>
                   </tr>
                   <% for (StudentsWithCourses student : studentsList) { %>
                   <tr>
                       <td><%= student.getName() %></td>
                       <td><%= student.getTown() %></td>
                       <td><%= student.getHobby() %></td>
                       <td><%= student.getCourses() %></td>
                   </tr>
                   <% } %>
               </table>
               <% } else { %>
               No students found.
               <% } %>
    </body>
</html>