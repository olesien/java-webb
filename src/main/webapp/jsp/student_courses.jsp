<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.StudentsWithCourses" %>
<%@ page import="models.Students" %>
<%@ page import="models.Courses" %>
<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

                <jsp:include page='fragments/nav.jsp'>
                    <jsp:param name="title" value="Student Courses"/>
                </jsp:include>
                   <%
                       String status = request.getParameter("status");
                       if (status != null) {
                   %>
                      <p class="status"><%= status %></p>
                   <% } %>
               <%
                   ArrayList<StudentsWithCourses> studentsWithCoursesList = (ArrayList<StudentsWithCourses>) request.getAttribute("students_with_courses");
                   if (studentsWithCoursesList != null && !studentsWithCoursesList.isEmpty()) {
               %>
               <table>
                   <tr>
                       <th>Name</th>
                       <th>Town</th>
                       <th>Hobby</th>
                       <th>Courses</th>
                   </tr>
                   <% for (StudentsWithCourses studentWithCourses : studentsWithCoursesList) { %>
                   <tr>
                       <td><%= studentWithCourses.getFname() %></td>
                       <td><%= studentWithCourses.getTown() %></td>
                       <td><%= studentWithCourses.getHobby() %></td>
                       <td><%= studentWithCourses.getCourses() %></td>
                   </tr>
                   <% } %>
               </table>
               <% } else { %>
               No students courses found.
               <% } %>

                              <%
                                  ArrayList<Students> students = (ArrayList<Students>) request.getAttribute("students");
                                  ArrayList<Courses> courses = (ArrayList<Courses>) request.getAttribute("courses");
                                  if (students != null && !students.isEmpty() && courses != null && !courses.isEmpty()) {
                                  %>
                                     <br>
                                         <form action="/student_courses" method="POST">
                                              <label for="student-select">Choose a student:</label>

                                              <select name="student" id="student-select" required>
                                                <option value="">Select Student</option>
                                                 <% for (Students student : students) { %>
                                                     <option value="<%= student.getId() %>"><%= student.getId() %>. <%= student.getFname() %></option>
                                                 <% } %>
                                              </select>
                                              <label for="course-select">Choose a course:</label>

                                              <select name="course" id="course-select" required>
                                                <option value="">Select Course</option>
                                                 <% for (Courses course : courses) { %>
                                                     <option value="<%= course.getId() %>"><%= course.getName() %></option>
                                                 <% } %>
                                              </select>
                                           <input type="submit" value="Submit">
                                         </form>
                                  <% } else { %>
                                      No students or courses found.
                                  <% } %>
    </body>
</html>