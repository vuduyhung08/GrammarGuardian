/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GrammarCheckerDAO;
import DAO.PostDAO;
import Model.Post_Error;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Datnt
 */
public class EditPostController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String postId = request.getParameter("postId");
            
            request.getRequestDispatcher("edit-post.jsp").forward(request, response);
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
        try {
            HttpSession session = request.getSession(false);
            String postId = request.getParameter("postId");
            PostDAO postDAO = new PostDAO();
            List<Post_Error> errors = postDAO.getErrorsByPostId(Integer.parseInt(postId));
            request.setAttribute("POST_ID", postId);
            request.setAttribute("ERRORS", errors);
            request.getRequestDispatcher("views/common/edit-post.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Error while loading post to edit");
            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
        }

    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String postId = request.getParameter("postId");
            String[] errorIds = request.getParameterValues("errorId");
            String[] errorTexts = request.getParameterValues("errorText");
            String[] suggestions = request.getParameterValues("suggestion");
            PostDAO postDAO = new PostDAO();
            boolean result = postDAO.updatePostErrors(Integer.parseInt(postId), errorIds, errorTexts, suggestions);
            if (result) {
                request.setAttribute("MESSAGE", "Post updated successfully");
            } else {
                request.setAttribute("ERROR", "Error while updating post");
            }
            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Error while updating post");
            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
