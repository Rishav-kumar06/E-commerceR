<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

            <!-- Sample Cart Item (later dynamic) -->
            <div class="cart-item">
                <img src="https://via.placeholder.com/100" alt="Product">
                <div class="cart-details">
                    <h3>Sample Product</h3>
                    <p>Price: $99.99</p>
                    <p>Qty: 1</p>
                </div>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="productId" value="1">
                    <button type="submit" class="btn remove">Remove</button>
                </form>
            </div>

            <hr>

            <div class="cart-summary">
                <h2>Total: $99.99</h2>
                <a href="checkout.jsp" class="btn">Proceed to Checkout</a>
            </div>

        </section>

        <footer>
            <p>© 2025 My E-Commerce Website</p>
        </footer>

    </body>
</html>
