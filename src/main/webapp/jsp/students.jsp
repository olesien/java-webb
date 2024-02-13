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
                   String status = request.getParameter("status");
                   if (status != null) {
               %>
                  <p class="status"><%= status %></p>
               <% } %>
               <%
                   ArrayList<Students> studentsList = (ArrayList<Students>) request.getAttribute("students");
                   if (studentsList != null && !studentsList.isEmpty()) {
               %>

               <table>
                   <tr>
                       <th>Id</th>
                       <th>Name</th>
                       <th>Town</th>
                       <th>Hobby</th>
                   </tr>
                   <% for (Students student : studentsList) { %>
                   <tr>
                       <td>#<%= student.getId() %></td>
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
                 <label for="fname">First Name:</label><br>
                 <input type="text" id="fname" name="fname" required minlength="2"><br>

                 <label for="lname">Last Name:</label><br>
                 <input type="text" id="lname" name="lname" required minlength="2"><br>

                  <label for="town">Town:</label><br>
                  <input type="text" id="town" name="town" required minlength="2"><br>

                  <label for="hobby">Hobby:</label><br>
                  <input type="text" id="hobby" name="hobby" required minlength="2"><br>
                 <input type="submit" value="Submit">
               </form>

    </body>
</html>