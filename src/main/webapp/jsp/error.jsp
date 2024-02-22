<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.StudentBean" %>
<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    <body>

                 <jsp:include page='fragments/nav.jsp'>
                     <jsp:param name="title" value="Error"/>
                 </jsp:include>
               <div>
                    <p class="error"><%= request.getAttribute("code") %> - <%=request.getAttribute("message") %></p>
               </div>
                 <jsp:include page='./fragments/footer.jsp' />
    </body>
</html>