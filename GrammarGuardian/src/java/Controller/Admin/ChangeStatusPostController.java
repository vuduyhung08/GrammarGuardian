/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.PostDAO;
import Model.Post;
import Model.User;
import Model.ViewModel.ViewPostDetailAdmin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ChangeStatusPostController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        if (session != null && session.getAttribute("USER") != null) {
            switch (action) {
                case "accept":
                    approvePost(request, response);
                    break;
                case "reject":
                    rejectPost(request, response);
                    break;
            }
        } else {
            response.sendRedirect("views/common/sign-in.jsp");
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

    private void approvePost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String postIds = request.getParameter("postId");
            int postId = Integer.parseInt(postIds);
            PostDAO postDAO = new PostDAO();
            // regject
            boolean result = postDAO.ConfirmPostByPostId(postId);
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            int total = postDAO.getAllPostAdminTotal();
            List<ViewPostDetailAdmin> listPost = postDAO.getAllPostInAdmin(index);
            int lastPage = total / 8;
            if (total % 8 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_POST", listPost);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("/views/manage/manage-post.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rejectPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String postIds = request.getParameter("postId");
            int postId = Integer.parseInt(postIds);
            PostDAO postDAO = new PostDAO();
            //confirm
            boolean result = postDAO.RejectPostByPostId(postId);
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            int total = postDAO.getAllPostAdminTotal();
            List<ViewPostDetailAdmin> listPost = postDAO.getAllPostInAdmin(index);
            int lastPage = total / 8;
            if (total % 8 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_POST", listPost);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("/views/manage/manage-post.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
