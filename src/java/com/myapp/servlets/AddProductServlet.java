package com.myapp.servlets;

import com.myapp.dao.ProductDAO;
import com.myapp.model.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author risha
 */
public class AddProductServlet extends HttpServlet {

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
        String priceStr = request.getParameter("price");
        String image = request.getParameter("image");

        // Validate input
        if (name == null || name.trim().isEmpty() || 
            priceStr == null || priceStr.trim().isEmpty() || 
            image == null || image.trim().isEmpty()) {
            response.sendRedirect("addProduct.jsp?error=1");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            
            // Creating product object
            Product product = new Product(name.trim(), price, image.trim());

            // Saving to DB using Hibernate DAO
            ProductDAO dao = new ProductDAO();
            dao.saveOrUpdateProduct(product);

            // Redirect to products page on success
            response.sendRedirect("ProductsServlet?success=1");
        } catch (NumberFormatException e) {
            response.sendRedirect("addProduct.jsp?error=invalid_price");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addProduct.jsp?error=1");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to add product form
        response.sendRedirect("addProduct.jsp");
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
        return "Servlet to handle product addition";
    }// </editor-fold>

}

