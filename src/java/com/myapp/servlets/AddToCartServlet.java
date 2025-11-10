package com.myapp.servlets;

import com.myapp.dao.ProductDAO;
import com.myapp.model.Product;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productIdStr = request.getParameter("productId");
        if (productIdStr != null) {
            int productId = Integer.parseInt(productIdStr);

            HttpSession session = request.getSession();
            // Map<ProductID, Quantity>
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cartItems");

            if (cart == null) {
                cart = new HashMap<>();
            }

            // Increase quantity if already in cart
            cart.put(productId, cart.getOrDefault(productId, 0) + 1);

            session.setAttribute("cartItems", cart);
        }

        response.sendRedirect("ProductsServlet"); // redirect back to products
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to add products to cart with quantities";
    }
}
