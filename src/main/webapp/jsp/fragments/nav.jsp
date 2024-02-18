<jsp:useBean id="user" scope="session" type="models.UserBean"/>
<%@ page import="models.UserBean" %>
<%@ page import="enums.UserType" %>
<%@ page import="enums.PrivType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">

      <h2><c:out value="${param.title}"/></h2>
       <nav>
           <a href="/your_courses">Your Courses</a>
           <c:if test="${not empty sessionScope.user}">
               <c:choose>
                   <c:when test="${sessionScope.user.userType eq 'teacher'}">
                       <a href="/students">Students</a>
                       <a href="/courses">Courses</a>
                       <c:choose>
                           <c:when test="${sessionScope.user.privType eq 'superadmin'}">
                               <a href="/register?type=student">Register</a>
                           </c:when>
                       </c:choose>
                   </c:when>
               </c:choose>
               <form action="/logout" method="POST">
                   <input type="submit" value="Logout">
               </form>
               <a href="/user">${sessionScope.user.username}</a>

           </c:if>
           <c:if test="${empty sessionScope.user}">
               <a href="/login">Login</a>
           </c:if>
       </nav>
 </div>