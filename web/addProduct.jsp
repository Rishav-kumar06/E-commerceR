<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product | My E-Commerce</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<header>
    <h1>Add New Product</h1>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="ProductsServlet">Products</a>
        <a href="ViewCartServlet">Cart</a>
    </nav>
</header>

<section class="form-container">
    <form action="AddProductServlet" method="post">
        <h2>Add Product</h2>

        <label>Product Name:</label>
        <input type="text" name="name" required placeholder="Enter product name">

        <label>Price ($):</label>
        <input type="number" name="price" step="0.01" min="0" required placeholder="0.00">

        <label>Image URL:</label>
        <input type="text" name="image" required placeholder="Enter image URL or path">

        <button type="submit" class="btn">Add Product</button>
        <p style="margin-top: 15px;"><a href="ProductsServlet" style="color: #fff;">View All Products</a></p>
    </form>

    <% 
        String error = request.getParameter("error");
        if ("1".equals(error)) { 
    %>
        <p style="color:red; margin-top:10px;">Failed to add product. Please check all fields and try again.</p>
    <% } else if ("invalid_price".equals(error)) { %>
        <p style="color:red; margin-top:10px;">Invalid price format. Please enter a valid number.</p>
    <% } %>
</section>

<footer>
    <p>© 2025 My E-Commerce Website</p>
</footer>
</body>
</html>

