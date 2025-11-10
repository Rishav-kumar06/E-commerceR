<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <h1>My E-Commerce Store</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/ProductsServlet">Products</a>
        
        <% if(session.getAttribute("loggedUser") != null) { %>
            <span class="welcome">Welcome, ${loggedUser.name}!</span>
            <a href="${pageContext.request.contextPath}/ViewCartServlet">Cart</a>
            <% 
                com.myapp.model.User currentUser = (com.myapp.model.User)session.getAttribute("loggedUser");
                if(currentUser != null && "ADMIN".equals(currentUser.getRole())) { 
            %>
                <a href="${pageContext.request.contextPath}/addProduct.jsp">Add Product</a>
            <% } %>
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
        <% } else { %>
            <a href="${pageContext.request.contextPath}/register.jsp">Register</a>
            <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
        <% } %>
    </nav>
</header>