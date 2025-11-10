package com.myapp.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String requestURI = req.getRequestURI();

        // Paths that don't require authentication
        boolean isResourceFile = requestURI.matches(".*(css|jpg|jpeg|png|gif|js)");
        boolean isPublicPath = requestURI.matches(".*(index\\.jsp|ProductsServlet)");
        boolean isLoginPage = requestURI.endsWith("login.jsp") || requestURI.endsWith("LoginServlet");
        boolean isRegisterPage = requestURI.endsWith("register.jsp") || requestURI.endsWith("RegisterServlet");
        
        boolean isLoggedIn = session != null && session.getAttribute("loggedUser") != null;
        
        // If user is logged in and tries to access login/register pages, redirect to products
        if (isLoggedIn && (isLoginPage || isRegisterPage)) {
            res.sendRedirect(req.getContextPath() + "/ProductsServlet");
            return;
        }
        
        // If user is not logged in and tries to access protected pages
        if (!isLoggedIn && !isResourceFile && !isPublicPath && !isLoginPage && !isRegisterPage) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}