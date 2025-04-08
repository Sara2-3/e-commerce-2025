<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
  <title>Register</title>
  <link rel="stylesheet" href="/css/register.css" />
</head>
<body>


<form:form action="/register" method="POST" modelAttribute="newUser">
  <h1>Welcome to Ossi</h1>
  <div>
    <form:label path="name">Name:</form:label>
    <form:errors path="name" cssClass="error"/>
    <form:input path="name"/>
  </div>
  <div>
    <form:label path="email">Email:</form:label>
    <form:errors path="email" cssClass="error"/>
    <form:input path="email"/>
  </div>
  <div>
    <form:label path="password">Password:</form:label>
    <form:errors path="password" cssClass="error"/>
    <form:input type="password" path="password"/>
  </div>
  <div>
    <form:label path="confirmPassword">Confirm Password:</form:label>
    <form:errors path="confirmPassword" cssClass="error"/>
    <form:password path="confirmPassword"/>
  </div>
  <input type="submit" value="Register"/>
  <p>Have an existing account?<a href="/">Login!</a></p>
</form:form>

</body>
</html>