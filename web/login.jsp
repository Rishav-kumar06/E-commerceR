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

    <% 
        String error = request.getParameter("error");
        String success = request.getParameter("success");
        if ("empty".equals(error)) { 
    %>
        <p style="color:red; margin-top:10px;">Email and password are required.</p>
    <% } else if ("1".equals(error)) { %>
        <p style="color:red; margin-top:10px;">Invalid email or password! Please check your credentials and try again.</p>
    <% } else if ("1".equals(success)) { %>
        <p style="color:green; margin-top:10px;">Registration successful! Please login.</p>
    <% } %>
</section>

<footer>
    <p>© 2025 My E-Commerce Website</p>
</footer>
</body>
</html>
