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
               <%
                   ArrayList<Students> studentsList = (ArrayList<Students>) request.getAttribute("students");
                   if (studentsList != null && !studentsList.isEmpty()) {
               %>
               <table>
                   <tr>
                       <th>Name</th>
                       <th>Town</th>
                       <th>Hobby</th>
                   </tr>
                   <% for (Students student : studentsList) { %>
                   <tr>
                       <td><%= student.getName() %></td>
                       <td><%= student.getTown() %></td>
                       <td><%= student.getHobby() %></td>
                   </tr>
                   <% } %>
               </table>
               <% } else { %>
               No students found.
               <% } %>
               <br>
               <form action="/students" method="POST">
                 <label for="name">Name:</label><br>
                 <input type="text" id="name" name="name"><br>

                  <label for="town">Town:</label><br>
                  <input type="text" id="town" name="town"><br>

                  <label for="hobby">Hobby:</label><br>
                  <input type="text" id="hobby" name="hobby"><br>
                 <input type="submit" value="Submit">
               </form>

    </body>
</html>