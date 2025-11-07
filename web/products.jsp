<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.myapp.models.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products | My E-Commerce</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<header>
    <h1>Our Products</h1>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
        <a href="ViewCartServlet">Cart</a>
    </nav>
</header>

<section class="product-container">
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
    if (productList != null) {
        for (Product p : productList) {
%>
    <div class="product-card">
        <img src="<%= p.getImage() %>" alt="<%= p.getName() %>">
        <h3><%= p.getName() %></h3>
        <p>$<%= p.getPrice() %></p>
        <form action="AddToCartServlet" method="post">
            <input type="hidden" name="productId" value="<%= p.getId() %>">
            <button type="submit" class="btn">Add to Cart</button>
        </form>
    </div>
<%
        }
    } else {
%>
    <p>No products available.</p>
<%
    }
%>
</section>

<footer>
    <p>© 2025 My E-Commerce Website</p>
</footer>
</body>
</html>
