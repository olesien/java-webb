<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Courses" %>
<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

               <jsp:include page="nav.jsp" />
               <%
                  String status = request.getParameter("status");
                  if (status != null) {
                  %>
                   <p class="status"><%= status %></p>
               <% } %>
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
                 <br>
                    <form action="/courses" method="POST">
                      <label for="name">Name:</label><br>
                      <input type="text" id="name" name="name"><br>

                       <label for="description">Description:</label><br>
                       <input type="text" id="description" name="description"><br>

                       <label for="yhp">YHP:</label><br>
                       <input type="number" id="yhp" name="yhp"><br>
                      <input type="submit" value="Submit">
                    </form>
    </body>
</html>