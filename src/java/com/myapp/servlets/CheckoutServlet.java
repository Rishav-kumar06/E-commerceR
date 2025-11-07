package com.myapp.servlets;

import com.myapp.dao.ProductDAO;
import com.myapp.models.Product;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Integer> cart = (List<Integer>) session.getAttribute("cartItems");
        List<Product> orderedProducts = new ArrayList<>();
        double total = 0;

        if (cart != null) {
            ProductDAO dao = new ProductDAO();
            for (int id : cart) {
                Product p = dao.getProductById(id);
                if (p != null) {
                    orderedProducts.add(p);
                    total += p.getPrice();
                }
            }
            session.removeAttribute("cartItems");
        }

        request.setAttribute("orderedProducts", orderedProducts);
        request.setAttribute("total", total);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { processRequest(request, response); }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { processRequest(request, response); }
}
