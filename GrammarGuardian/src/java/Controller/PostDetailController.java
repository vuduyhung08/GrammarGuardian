/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.PostDAO;
import Model.Post;
import Model.ViewModel.CommentViewModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PostDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String postIdS = request.getParameter("postId");
            int postId = Integer.parseInt(postIdS);
            PostDAO postDAO = new PostDAO();
            Post post = postDAO.getPostById(postId);

            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            
            int index = Integer.parseInt(indexS);
            int total = postDAO.getListUserCommentInPostTotal(postId);
            List<CommentViewModel> listComment = postDAO.getListUserCommentInPost(postId, index);
            int lastPage = total / 12;
            if (total % 12 != 0) {
                lastPage++;
            }
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.setAttribute("POST", post);
            request.setAttribute("COMMENTS", listComment);
            request.getRequestDispatcher("views/common/post-details.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
