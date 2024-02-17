<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Students" %>
<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

            <jsp:include page='fragments/nav.jsp'>
                <jsp:param name="title" value="Students"/>
            </jsp:include>
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
                       <th>First Name</th>
                       <th>Last Name</th>
                       <th>Town</th>
                       <th>Hobby</th>
                   </tr>
                   <% for (Students student : studentsList) { %>
                   <tr>
                       <td>#<%= student.getId() %></td>
                       <td><%= student.getFname() %></td>
                       <td><%= student.getLname() %></td>
                       <td><%= student.getTown() %></td>
                       <td><%= student.getHobby() %></td>
                   </tr>
                   <% } %>
               </table>
               <% } else { %>
               No students found.
               <% } %>
               <br>

    </body>
</html>