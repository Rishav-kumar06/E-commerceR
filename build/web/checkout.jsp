<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.myapp.model.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout | My E-Commerce</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<jsp:include page="/includes/header.jsp" />
<h2 class="page-title">Checkout</h2>

<section class="form-container">
<%
    List<Product> orderedProducts = (List<Product>) request.getAttribute("orderedProducts");
    Double total = request.getAttribute("total") != null ? (Double) request.getAttribute("total") : 0.0;
    if (orderedProducts != null && !orderedProducts.isEmpty()) {
%>
    <h2>Order Summary</h2>
    <ul>
<%
        for (Product p : orderedProducts) {
%>
        <li><%= p.getName() %> - $<%= p.getPrice() %></li>
<%
        }
%>
    </ul>
    <p><strong>Total: $<%= total %></strong></p>
<%
    } else {
%>
    <p>No items to checkout. <a href="products.jsp">Shop Now</a></p>
<%
    }
%>

    <h2>Billing Details</h2>
    <form action="CheckoutServlet" method="post">
        <label>Full Name:</label>
        <input type="text" name="fullname" required>
        <label>Address:</label>
        <input type="text" name="address" required>
        <label>Phone Number:</label>
        <input type="text" name="phone" required>

        <h3>Payment Method:</h3>
        <label><input type="radio" name="payment" value="COD" checked> Cash on Delivery</label><br>
        <label><input type="radio" name="payment" value="UPI"> UPI Payment</label><br>
        <label><input type="radio" name="payment" value="Card"> Credit/Debit Card</label><br><br>

        <button type="submit" class="btn">Place Order</button>
    </form>
</section>

<footer>
    <p>© 2025 My E-Commerce Website</p>
</footer>
</body>
</html>
