<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="/css/cart.css">
</head>
<body>
<div class="container">
  <div class="categories">
            
            <ul class="category-list">
                <li><a href="/home">OSSI</a></li>
                <li><a href="/tops">Tops</a></li>
                <li><a href="/pants">Pants</a></li>
            </ul>
        </div>
    <h1>Your Shopping Cart</h1>

    <c:if test="${empty cartItems}">
        <p>Your cart is currently empty.</p>
        <a href="/home" class="btn">Continue Shopping</a>
    </c:if>


    <c:if test="${not empty cartItems}">
        <table class="cart-table">
            <thead>
            <tr>
                <th>Product</th>
                <th>Size</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Subtotal</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cartItems}" var="item">
                <tr>
                    <td>
                        <img src="${item.product.imageUrl}" alt="${item.product.name}" width="50" height="50"/>
                        ${item.product.name}
                    </td>
                    <td>${item.size}</td>
                    <td>${item.quantity}</td>
                    <td>$<fmt:formatNumber value="${item.product.price}" pattern="#" /></td>
                    <td>$<fmt:formatNumber value="${item.subtotal}" pattern="#" /></td>
                    <td>
                        <a href="/cart/remove/${item.id}" class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4" align="right"><strong>Total:</strong></td>
                <td colspan="2">$<fmt:formatNumber value="${totalPrice}" pattern="#" /></td>
            </tr>
            </tfoot>
        </table>

        <div class="cart-actions">
            <a href="/cart/clear" class="btn btn-danger">Clear Cart</a>
            <a href="/home" class="btn">Continue Shopping</a>
        </div>
    </c:if>
</div>
<div class="middle">
<div class="luxury">
<img alt="finest-edit-icon" fetchpriority="auto" loading="lazy" src="https://www.mytheresa.com/content/80/80/30/909cd113-be1f-418a-9664-e9387dc767ce.png">
The finest edit in luxury
<p>Finest edit of more than 200 international luxury brands</p></div>
<div class="luxury">
<img alt="designer-collaborations-icon" fetchpriority="auto" loading="lazy" src="https://www.mytheresa.com/content/80/80/30/22cb0ec6-e426-4579-a283-5e4705cce512.png">
Designer collaborations
<p>Designer collaborations with the most coveted names in fashion</p></div>
<div class="luxury">
<img alt="delivery-icon" fetchpriority="auto" loading="lazy" src="https://www.mytheresa.com/content/80/80/30/b579a4c8-dfa7-49d4-b719-f4f8b5453aa3.png">
Fast delivery
<p>Delivery to over 130 countries</p>
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