package com.myapp.servlets;

import com.myapp.dao.UserDAO;
import com.myapp.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author risha
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Receiving login form input
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || email.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            String ctx = request.getContextPath();
            response.sendRedirect(ctx + "/login.jsp?error=empty");
            return;
        }

        try {
            UserDAO dao = new UserDAO();
            User user = dao.loginUser(email.trim(), password.trim());

            if (user != null) {
                // Creating session
                HttpSession session = request.getSession();
                session.setAttribute("loggedUser", user);
                session.setAttribute("username", user.getName());
                session.setAttribute("userId", user.getId());

                // Redirect to ProductsServlet instead of index.jsp (use context path)
                String ctx = request.getContextPath();
                response.sendRedirect(ctx + "/ProductsServlet");
            } else {
                String ctx = request.getContextPath();
                response.sendRedirect(ctx + "/login.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String ctx = request.getContextPath();
            response.sendRedirect(ctx + "/login.jsp?error=1");
        }
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet to handle user login";
    }// </editor-fold>

}
