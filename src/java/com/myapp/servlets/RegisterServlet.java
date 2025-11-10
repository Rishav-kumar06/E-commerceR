package com.myapp.servlets;

import com.myapp.dao.UserDAO;
import com.myapp.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author risha
 */
public class RegisterServlet extends HttpServlet {

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

        // Getting form values
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        // Validate input
        if (name == null || name.trim().isEmpty() || 
            email == null || email.trim().isEmpty() || 
            pass == null || pass.trim().isEmpty()) {
            String ctx = request.getContextPath();
            response.sendRedirect(ctx + "/register.jsp?error=empty");
            return;
        }

        try {
            UserDAO dao = new UserDAO();

            // Check if email already exists
            if (dao.emailExists(email)) {
                String ctx = request.getContextPath();
                response.sendRedirect(ctx + "/register.jsp?error=exists");
                return;
            }

            // Creating user object
            User user = new User(name.trim(), email.trim(), pass.trim());

            // Saving to DB using Hibernate DAO
            boolean result = dao.registerUser(user);

            // Redirect based on result
            String ctx = request.getContextPath();
            if (result) {
                response.sendRedirect(ctx + "/login.jsp?success=1");
            } else {
                response.sendRedirect(ctx + "/register.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String ctx = request.getContextPath();
            response.sendRedirect(ctx + "/register.jsp?error=1");
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
        return "Servlet to handle user registration";
    }// </editor-fold>

}
