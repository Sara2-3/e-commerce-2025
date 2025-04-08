<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <link rel="stylesheet" href="/css/product_details.css" />

    <title>Product Details</title>
    <link rel="stylesheet" href="/css/style.css" />
    <style>
        .error-message {
            color: red;
            margin: 10px 0;
        }
        .sold-out {
            color: red;
            font-weight: bold;
        }
        .stock-available {
            color: green;
        }
    </style>
</head>
<body>
<div class="nav-container">
        <h1 class="logo"><a href="/home">OSSI</a></h1>
        <div class="categories">
            
            <ul class="category-list">
                <li><a href="/dresses">Dresses</a></li>
                <li><a href="/tops">Tops</a></li>
                <li><a href="/pants">Pants</a></li>
            </ul>
        </div>
        <h2 class="shopping-cart"><a href="/cart">Shopping Cart</a></h2>
    </div>

<div class="container">
    <div class="product-details">
        <img src="${product.imageUrl}" alt="${product.name}" />
        <div class="product-info">
            <h2>${product.name}</h2>
            <p>${product.description}</p>
            <p class="price">Price: $<fmt:formatNumber value="${product.price}" pattern="#" /></p>
            <c:if test="${param.error eq 'outofstock'}">
                <p class="error-message">Sorry, the selected size ${param.size} is out of stock for the requested quantity.</p>
            </c:if>
            <form action="/cart/add" method="POST">
                <input type="hidden" name="productId" value="${product.id}" />
                
                <div class="form-group">
                    <label for="size">Size:</label>
                    <select name="size" id="size" required>
                        <c:forEach items="${stockLevels}" var="stock">
                            <option value="${stock.size}" ${stock.quantity == 0 ? 'disabled' : ''}>
                                ${stock.size} 
                                <c:choose>
                                    <c:when test="${stock.quantity == 0}">
                                        <span class="sold-out">(Sold Out)</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="stock-available">(${stock.quantity} available)</span>
                                    </c:otherwise>
                                </c:choose>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="number" name="quantity" id="quantity" min="1" max="5" value="1" required />
                    <small>(Maximum 5 items per order)</small>
                </div>

                <button type="submit" class="btn btn-primary">Add to Cart</button>
                <a href="/home" class="btn btn-secondary">Back to Home</a>
            </form>
        </div>
    </div>
</div>
<div class="related-products">
    <h2>You May Also Like</h2>
    <div class="product-grid">
        <c:forEach items="${relatedProducts}" var="relatedProduct">
            <div class="product-item">
                <a href="/products/details/${relatedProduct.id}">
                    <img src="${relatedProduct.imageUrl}" alt="${relatedProduct.name}" class="product-image" />
                    <div class="product-info">
                        <h3>${relatedProduct.name}</h3>
                        <p class="price">$<fmt:formatNumber value="${relatedProduct.price}" pattern="#" /></p>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
    <div class="pagination">
        <c:if test="${totalPages > 1}">
            <div class="pagination-links">
                <c:if test="${currentPage > 1}">
                    <a href="/products/details/${product.id}?page=${currentPage - 1}" class="page-link">Previous</a>
                </c:if>
                
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="/products/details/${product.id}?page=${i}" 
                       class="page-link ${currentPage == i ? 'active' : ''}">${i}</a>
                </c:forEach>
                
                <c:if test="${currentPage < totalPages}">
                    <a href="/products/details/${product.id}?page=${currentPage + 1}" class="page-link">Next</a>
                </c:if>
            </div>
        </c:if>
    </div>
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