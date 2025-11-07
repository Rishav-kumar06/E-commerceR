<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login | My E-Commerce</title>
        <link rel="stylesheet" href="styles.css">
    </head>

    <body>

        <header>
            <h1>Login</h1>
            <nav>
                <a href="index.jsp">Home</a>
                <a href="register.jsp">Register</a>
                <a href="products.jsp">Products</a>
            </nav>
        </header>

        <section class="form-container">
            <form action="LoginServlet" method="post">
                <h2>User Login</h2>

                <label>Email:</label>
                <input type="email" name="email" required>

                <label>Password:</label>
                <input type="password" name="password" required>

                <button type="submit" class="btn">Login</button>

                <p>New user? <a href="register.jsp">Create an account</a></p>
            </form>

            <!-- Error message display if login fails -->
            <%
                String error = request.getParameter("error");
                if (error != null) {
            %>
            <p style="color:red; margin-top:10px;">Invalid email or password!</p>
            <%
                }
            %>
        </section>

        <footer>
            <p>© 2025 My E-Commerce Website</p>
        </footer>

    </body>
</html>
