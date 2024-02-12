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

               <div class="header">
                    <h2><%= request.getAttribute("name") %></h2>
                    <nav>
                        <a href="/students">Students</a>
                        <a href="/courses">Courses</a>
                        <a class="disabled" href="/student_courses">Student Courses</a>
                    </nav>
               </div>
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
                       <td><%= studentWithCourses.getName() %></td>
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

                                              <select name="student" id="student-select">
                                                <option value="">Select Student</option>
                                                 <% for (Students student : students) { %>
                                                     <option value="<%= student.getId() %>"><%= student.getName() %></option>
                                                 <% } %>
                                              </select>
                                              <label for="course-select">Choose a course:</label>

                                              <select name="course" id="course-select">
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