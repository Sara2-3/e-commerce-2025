<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/admin.css">
</head>
<body>
    <div class="container">
        <h2>Edit Product</h2>
        <form:form action="/admin/edit/${product.id}" method="POST" modelAttribute="product">
            <div class="form-group">
                <label>Name:</label>
                <form:input path="name" class="form-control"/>
            </div>
            
            <div class="form-group">
                <label>Description:</label>
                <form:textarea path="description" class="form-control"/>
            </div>
            
            <div class="form-group">
                <label>Price:</label>
                <form:input path="price" type="number" step="0.01" class="form-control"/>
            </div>
            
            <div class="form-group">
                <label>Image URL:</label>
                <form:input path="imageUrl" class="form-control"/>
            </div>
            
            <div class="form-group">
                <label>Category:</label>
                <form:input path="category" class="form-control"/>
            </div>
            
            <button type="submit" class="btn btn-primary">Update Product</button>
            <a href="/home" class="btn btn-secondary">Cancel</a>
        </form:form>
    </div>
</body>
</html> 