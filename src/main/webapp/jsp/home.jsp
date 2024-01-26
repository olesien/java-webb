<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Students" %>
<html>
<head>
    <title>Hello <%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>
                <h2>Hello <%= request.getAttribute("name") %> from Java Servlet!!!</h2>
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
    </body>
</html>