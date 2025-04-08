
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Welcome,<c:out value="${loggedInUser.name}"/></h1>
<form action="logout" method="post">
    <input type="submit" value="Log out"/>
</form>



</body>
</html>
