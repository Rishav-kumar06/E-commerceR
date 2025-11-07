package com.myapp.servlets;

import com.myapp.dao.ProductDAO;
import com.myapp.models.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cartItems");
        List<Product> cartProducts = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        if (cartMap != null && !cartMap.isEmpty()) {
            ProductDAO dao = new ProductDAO();
            for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
                Product p = dao.getProductById(entry.getKey());
                if (p != null) {
                    cartProducts.add(p);
                    quantities.add(entry.getValue());
                }
            }
        }

        request.setAttribute("cartProducts", cartProducts);
        request.setAttribute("quantities", quantities);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
        return "Servlet to display shopping cart with quantities";
    }
}
