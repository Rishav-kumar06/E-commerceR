/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.myapp.servlets;

import com.myapp.dao.ProductDAO;
import com.myapp.models.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author risha
 */
public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Integer> cart = (List<Integer>) session.getAttribute("cartItems");

        if (cart != null && !cart.isEmpty()) {

            // Get billing/payment info from form
            String fullname = request.getParameter("fullname");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String payment = request.getParameter("payment");

            // Calculate total
            ProductDAO dao = new ProductDAO();
            double total = 0;
            List<Product> orderedProducts = new ArrayList<>();

            for (Integer id : cart) {
                Product p = dao.getProductById(id);
                if (p != null) {
                    total += p.getPrice();
                    orderedProducts.add(p);
                }
            }

            // Clear the cart after checkout
            session.removeAttribute("cartItems");

            // Set attributes to show on checkout.jsp (confirmation)
            request.setAttribute("status", "success");
            request.setAttribute("fullname", fullname);
            request.setAttribute("address", address);
            request.setAttribute("phone", phone);
            request.setAttribute("payment", payment);
            request.setAttribute("total", total);
            request.setAttribute("orderedProducts", orderedProducts);

        } else {
            request.setAttribute("status", "empty");
        }

        // forward to checkout.jsp
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        return "Short description";
    }// </editor-fold>

}