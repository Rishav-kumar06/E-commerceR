<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.myapp.model.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products | My E-Commerce</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<jsp:include page="/includes/header.jsp" />

<section class="product-container">
    <% 
        String success = request.getParameter("success");
        if ("1".equals(success)) { 
    %>
        <div style="background: rgba(76, 175, 80, 0.8); color: white; padding: 15px; border-radius: 8px; margin-bottom: 20px; text-align: center; width: 100%;">
            Product added successfully!
        </div>
    <% } %>
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
