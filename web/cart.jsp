<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.myapp.model.Product"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart | My E-Commerce</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<header>
    <h1>Your Shopping Cart</h1>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="products.jsp">Products</a>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
    </nav>
</header>

<section class="cart-container">

<%
    List<Product> cartProducts = (List<Product>) request.getAttribute("cartProducts");
    List<Integer> quantities = (List<Integer>) request.getAttribute("quantities");
    double total = 0;
    if (cartProducts != null && !cartProducts.isEmpty()) {
        for (int i = 0; i < cartProducts.size(); i++) {
            Product p = cartProducts.get(i);
            int qty = quantities.get(i);
            total += p.getPrice() * qty;
%>
    <div class="cart-item">
        <img src="<%=p.getImage()%>" alt="<%=p.getName()%>" width="100">
        <div class="cart-details">
            <h3><%=p.getName()%></h3>
            <p>Price: $<%=p.getPrice()%></p>
            <p>Qty: <%=qty%></p>
        </div>
        <form action="RemoveFromCartServlet" method="post">
            <input type="hidden" name="productId" value="<%=p.getId()%>">
            <button type="submit" class="btn remove">Remove</button>
        </form>
    </div>
    <hr>
<%
        }
%>
    <div class="cart-summary">
        <h2>Total: $<%=total%></h2>
        <a href="checkout.jsp" class="btn">Proceed to Checkout</a>
    </div>
<%
    } else {
%>
    <p>Your cart is empty!</p>
<%
    }
%>

</section>

<footer>
    <p>© 2025 My E-Commerce Website</p>
</footer>
</body>
</html>
