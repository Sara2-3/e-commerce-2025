<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="sale-banner">
        <p id="message1" class="sale-message active">For a short time only – Flash Sale</p>
        <p id="message2" class="sale-message">Enjoy 20% off all items</p>
    </div>
    <div class="nav-container">
        <h1 class="logo">OSSI</h1>
        <div class="categories">
            
            <ul class="category-list">
                <li><a href="/dresses">Dresses</a></li>
                <li><a href="/tops">Tops</a></li>
                <li><a href="/pants">Pants</a></li>
            </ul>
        </div>
        <h2 class="shopping-cart"><a href="/cart">Shopping Cart</a></h2>
    </div>
    <c:if test="${isAdmin}">
        <div>
            <a href="/products/add" class="btn btn-primary">Add a New Product</a>
        </div>
    </c:if>

    <div class="product-list">
        <c:forEach items="${products}" var="product">
            <div class="product-item">
                <c:choose>
                    <c:when test="${isAdmin}">
                        <a href="/products/details/${product.id}">
                            <img src="${product.imageUrl}" alt="${product.name}" class="product-image" />
                        </a>
                        <div class="product-details">
                            <h3>${product.name}</h3>
                            <p>${product.description}</p>
                            <p class="price">$<fmt:formatNumber value="${product.price}" pattern="#" /></p>
                            <p>Category: <strong>${product.category}</strong></p>
                            <div class="admin-controls">
                                <a href="/products/edit/${product.id}" class="btn btn-primary">Edit</a>
                                <a href="/products/delete/${product.id}" class="btn btn-danger">Delete</a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="/products/details/${product.id}">
                            <img src="${product.imageUrl}" alt="${product.name}" class="product-image" />
                        </a>
                        <div class="product-details">
                            <h3>${product.name}</h3>
                            <p>${product.description}</p>
                            <p class="price">$<fmt:formatNumber value="${product.price}" pattern="#" /></p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>
    <div class="pagination">
        <c:if test="${totalPages > 1}">
            <div class="pagination-links">
                <c:if test="${currentPage > 1}">
                    <a href="/home?page=${currentPage - 1}" class="page-link">&laquo; Previous</a>
                </c:if>
                
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="/home?page=${i}" 
                       class="page-link ${currentPage == i ? 'active' : ''}">${i}</a>
                </c:forEach>
                
                <c:if test="${currentPage < totalPages}">
                    <a href="/home?page=${currentPage + 1}" class="page-link">Next &raquo;</a>
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
    </div>
    <script src="/js/script.js"></script>
</body>
</html>