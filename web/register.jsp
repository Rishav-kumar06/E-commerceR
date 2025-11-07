<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register | My E-Commerce</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<header>
    <h1>Create Account</h1>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="login.jsp">Login</a>
        <a href="products.jsp">Products</a>
    </nav>
</header>

<section class="form-container">
    <form action="RegisterServlet" method="post">
        <h2>Register</h2>

        <label>Name:</label>
        <input type="text" name="name" required>

        <label>Email:</label>
        <input type="email" name="email" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <button type="submit" class="btn">Create Account</button>
        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </form>

    <% 
        String error = request.getParameter("error");
        if ("exists".equals(error)) { 
    %>
        <p style="color:red; margin-top:10px;">Email already exists! Try another.</p>
    <% } else if ("1".equals(error)) { %>
        <p style="color:red; margin-top:10px;">Registration failed. Try again.</p>
    <% } %>
</section>

<footer>
    <p>© 2025 My E-Commerce Website</p>
</footer>
</body>
</html>
