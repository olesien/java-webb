<%@ page import="models.UserBean" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="enums.UserType" %>
<%@ page import="enums.PrivType" %>
<div class="header">

      <h2>${title}</h2>
       <nav>
          <a class="disabled" href="/students">Students</a>
          <a href="/courses">Courses</a>
          <a href="/student_courses">Student Courses</a>
           <% if (session.getAttribute("user") != null) { %>
               <% UserBean user = (UserBean) session.getAttribute("user"); %>
               <% if (user.getUserType() == UserType.teacher && user.getPrivType() == PrivType.superadmin) { %>
                    <a href="/register?type=student">Register</a>
               <% } %>
           <form action="/logout" method="POST">
               <input type="submit" value="Logout">
           </form>
           <a href="/user">${user.getUsername()}</a>
           <% } else { %>
                <a href="/login">Login</a>
           <% } %>
       </nav>
 </div>