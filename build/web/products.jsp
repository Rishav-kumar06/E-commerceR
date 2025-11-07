<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <a href="cart.jsp">Cart</a>
            </nav>
        </header>

        <section class="product-container">

            <!-- DUMMY PRODUCTS FOR NOW (will replace with dynamic using Servlet later) -->
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product">
                <h3>Sample Product 1</h3>
                <p>$49.99</p>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="productId" value="1">
                    <button type="submit" class="btn">Add to Cart</button>
                </form>
            </div>

            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product">
                <h3>Sample Product 2</h3>
                <p>$79.99</p>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="productId" value="2">
                    <button type="submit" class="btn">Add to Cart</button>
                </form>
            </div>

            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product">
                <h3>Sample Product 3</h3>
                <p>$99.99</p>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="productId" value="3">
                    <button type="submit" class="btn">Add to Cart</button>
                </form>
            </div>

        </section>

        <footer>
            <p>© 2025 My E-Commerce Website</p>
        </footer>

    </body>
</html>
