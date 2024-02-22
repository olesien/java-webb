<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
       <a href="/"><img src="logo.png" class="logo" alt="Grit Logo"/></a>
       <nav>
           <a href="/courses" class="${param.title == 'Courses' ? "activeLink" : ""}">All Courses</a>
           <c:if test="${not empty sessionScope.user}">
               <a href="/your_courses" class="${param.title == 'Your Courses' ? "activeLink" : ""}">Your Courses</a>
               <c:choose>
                   <c:when test="${sessionScope.user.userType eq 'teacher'}">
                       <a href="/students" class="${param.title == 'Students' ? "activeLink" : ""}">Students</a>

                       <c:choose>
                           <c:when test="${sessionScope.user.privType != 'user'}">
                               <a href="/register?type=student" class="${param.title == 'Register' ? "activeLink" : ""}">Register</a>
                           </c:when>
                       </c:choose>
                       <c:choose>
                           <c:when test="${sessionScope.user.privType eq 'superadmin'}">
                               <a href="/stats?type=student_course_average" class="${param.title == 'Stats' ? "activeLink" : ""}">Statistics</a>
                           </c:when>
                       </c:choose>
                   </c:when>
               </c:choose>
               <form action="/logout" method="POST">
                   <input type="submit" value="Logout">
               </form>

           </c:if>
           <c:if test="${empty sessionScope.user}">
               <a href="/login">Login</a>
           </c:if>
       </nav>
 </header>