package com.myapp.servlets;

import com.myapp.dao.ProductDAO;
import com.myapp.model.Product;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProductsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try {
                        ProductDAO dao = new ProductDAO();
                        List<Product> products = dao.getAllProducts();
            
                        if (products == null || products.isEmpty()) {
                                System.out.println("No products found in database");
                        } else {
                                System.out.println("Found " + products.size() + " products");
                        }
            
                        request.setAttribute("productList", products);
                        request.getRequestDispatcher("/products.jsp").forward(request, response);
                } catch (Exception e) {
                        System.err.println("Error loading products: " + e.getMessage());
                        e.printStackTrace();
                        response.sendRedirect("error.jsp");
                }
    }

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { processRequest(request, response); }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { processRequest(request, response); }
}
