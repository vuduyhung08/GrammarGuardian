package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import DAO.GrammarCheckerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



public class RestorePostController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
            String postIds = request.getParameter("postId");
            if (postIds != null) {
                int postId = Integer.parseInt(postIds);
                GrammarCheckerDAO grammarCheckDAO = new GrammarCheckerDAO();
                boolean result = grammarCheckDAO.RestorePost(postId);
                if (result) {
                    request.setAttribute("MESSAGE", "Your post was restore successfully!!");
                } else {
                    request.setAttribute("ERROR", "restorePost failed ");
                    System.out.println("restorePost - restorePost failed");
                }
                request.getRequestDispatcher("profile?action=view").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
