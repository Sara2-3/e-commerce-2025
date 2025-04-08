<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
  <head>
    <title>Log in</title>
    <link rel="stylesheet" href="/css/register.css" />
  </head>
  <body>

  <form:form action="/login" method="post" modelAttribute="newLogin">
    <h1>Log in</h1>
    <div>
      <form:label path="email">Email:</form:label>
      <form:errors path="email"/>
      <form:input path="email"/>
    </div>
    <div>
      <form:label path="password">Password:</form:label>
      <form:errors path="password"/>
      <form:input type="password" path="password"/>
    </div>
    <input type="submit" value="Log in"/>
    <p>Don't have an account?<a href="/register">Register</a></p>
  </form:form>

  </div>
  </body>
</html>
