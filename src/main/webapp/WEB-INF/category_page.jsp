<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>${category}</title>
</head>
<body>
<h1>Products in Category: ${category}</h1>
<div class="product-grid">
    <c:forEach items="${products}" var="product">
        <div class="product-item">
            <a href="/products/details/${product.id}">
                <img src="${product.imageUrl}" alt="${product.name}" />
            </a>
            <div>${product.name}</div>
            <div>$${product.price}</div>
        </div>
    </c:forEach>
</div>
<a href="/home">Back to Home</a>
</body>
</html>