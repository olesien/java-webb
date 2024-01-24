<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello <%= request.getAttribute("name") %> </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Hello <%= request.getAttribute("name") %> from Java Servlet!!!</h2>
</body>
</html>