/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.PostDAO;
import Model.Post;
import Model.Post_Error;
import Model.Post_Update;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@MultipartConfig
public class UpdatePostController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String postId = request.getParameter("postId");
            PostDAO postDAO = new PostDAO();
            Post post = postDAO.getPostById(Integer.parseInt(postId));
            List<Post_Error> postError = postDAO.getErrorsByPostId(Integer.parseInt(postId));
            if (post.getUpdatePostId() > 0) {
                Post_Update postUpdate = postDAO.getPostUpdateById(post.getUpdatePostId());
                request.setAttribute("POST_UPDATE", postUpdate);
            }
            request.setAttribute("POST", post);
            if (postError.size() > 0) {
                request.setAttribute("POST_ERROR", postError);
            }
            request.getRequestDispatcher("views/user/edit-post.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Error while loading post to edit");
            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String url = "";
            String postId = request.getParameter("postId");
            String title = request.getParameter("title");
            Part image = request.getPart("image");
            PostDAO postDAO = new PostDAO();
            Post post = new Post();

            if (postId != null) {
                post = postDAO.getPostById(Integer.parseInt(postId));
                post.setTitle(title);
            }
            boolean result = postDAO.updatePostDetail(post, image);
            if (result) {
                request.setAttribute("MESSAGE", "Post updated successfully");
                url = "profile?action=view";
            } else {
                request.setAttribute("ERROR", "Error while updating post");
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Error while updating post");
            request.getRequestDispatcher("views/common/edit-post.jsp").forward(request, response);
        }
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
