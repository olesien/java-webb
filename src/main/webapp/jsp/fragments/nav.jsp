<%@ page import="models.UserBean" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<div class="header">

    My id is ${user.getUsername()}
      <h2>${title}</h2>
       <nav>
          <a class="disabled" href="/students">Students</a>
          <a href="/courses">Courses</a>
          <a href="/student_courses">Student Courses</a>
          <a href="/register">Register</a>
          <form action="/logout" method="POST">
              <input type="submit" value="Logout">
          </form>
       </nav>
 </div>