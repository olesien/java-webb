<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
    <body>

            <jsp:include page='fragments/nav.jsp'>
                <jsp:param name="title" value="Welcome"/>
            </jsp:include>
            <main class="p-2">
                <h3>Welcome to Grit Academy!</h3>
                <p>On this page you can do all you could ever need, either as a student or a teacher! Please note that students are created by
                the teachers. If you are missing login information please message your teacher.</p>
            </main>
            <jsp:include page='./fragments/footer.jsp' />


    </body>
</html>