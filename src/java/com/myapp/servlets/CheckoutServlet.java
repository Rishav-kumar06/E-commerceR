package com.myapp.servlets;

import com.myapp.dao.ProductDAO;
import com.myapp.model.Product;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cartItems");
        List<Product> orderedProducts = new ArrayList<>();
        double total = 0;

        if (cart != null) {
            ProductDAO dao = new ProductDAO();
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int id = entry.getKey();
                int quantity = entry.getValue();
                Product p = dao.getProductById(id);
                if (p != null) {
                    for (int i = 0; i < quantity; i++) {
                        orderedProducts.add(p);
                    }
                    total += p.getPrice() * quantity;
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
