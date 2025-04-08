<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>${category} Products</title>
   <link rel="stylesheet" href="/css/category_lists.css">
</head>
<body>
<div class="container">
  <div class="categories">
            
            <ul class="category-list">
                <li><a href="/home">OSSI</a></li>
                <li><a href="/home">Dresses</a></li>
                <li><a href="/tops">Tops</a></li>
                <li><a href="/pants">Pants</a></li>
                <li><a href="/cart">Shopping Cart</a></li>
            </ul>
        </div>
<h1>Products in ${category}</h1>

<div class="product-list">
    <c:forEach items="${products}" var="product">
        <div class="product-item">
            <a href="/products/details/${product.id}">
                <img src="${product.imageUrl}" alt="${product.name}" />
            </a>
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>Price: $<fmt:formatNumber value="${product.price}" pattern="#" /></p>
        </div>
    </c:forEach>
</div>


<div class="pagination">
    <c:if test="${totalPages > 1}">
        <div class="pagination-links">
            <c:if test="${currentPage > 1}">
                <a href="/${category}?page=${currentPage - 1}" class="page-link">Previous</a>
            </c:if>
            
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="/${category}?page=${i}" 
                   class="page-link ${currentPage == i ? 'active' : ''}">${i}</a>
            </c:forEach>
            
            <c:if test="${currentPage < totalPages}">
                <a href="/${category}?page=${currentPage + 1}" class="page-link">Next</a>
            </c:if>
        </div>
    </c:if>
</div>

<div class="footer">
<div class="footer-content">
    <div class="footer-section">
    <ul>
    <li><a href="#">About</a></li>
    <li><a href="#">Contact</a></li>
    <li><a href="#">Careers</a></li>
    <li><a href="#">Shipping</a></li>
    </ul>
    </div>
    <p>© 2025 OSSI. All rights reserved.</p>
</div>
</body>
</html>
