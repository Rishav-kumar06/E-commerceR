<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Checkout | My E-Commerce</title>
        <link rel="stylesheet" href="styles.css">
    </head>

    <body>

        <header>
            <h1>Checkout</h1>
            <nav>
                <a href="index.jsp">Home</a>
                <a href="products.jsp">Products</a>
                <a href="cart.jsp">Cart</a>
            </nav>
        </header>

        <section class="form-container">
            <form action="CheckoutServlet" method="post">
                <h2>Billing Details</h2>

                <label>Full Name:</label>
                <input type="text" name="fullname" required>

                <label>Address:</label>
                <input type="text" name="address" required>

                <label>Phone Number:</label>
                <input type="text" name="phone" required>

                <h3>Payment Method:</h3>

                <label>
                    <input type="radio" name="payment" value="COD" checked>
                    Cash on Delivery
                </label><br>

                <label>
                    <input type="radio" name="payment" value="UPI">
                    UPI Payment
                </label><br>

                <label>
                    <input type="radio" name="payment" value="Card">
                    Credit/Debit Card
                </label><br><br>

                <button type="submit" class="btn">Place Order</button>
            </form>
        </section>

        <footer>
            <p>© 2025 My E-Commerce Website</p>
        </footer>

    </body>
</html>
