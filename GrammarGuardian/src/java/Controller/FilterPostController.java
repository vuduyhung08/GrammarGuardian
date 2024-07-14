/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.PostDAO;
import Model.Post;
import Model.User;
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
public class FilterPostController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            String search = request.getParameter("search");
            String indexS = request.getParameter("index");
            String statusS = request.getParameter("status");
            if (statusS == null || statusS == "") {
                // neu truong hop k truyen gi hoac vao mac dinh se lay het request.
                statusS = "7";
            }
            int status = Integer.parseInt(statusS);
            String url = "views/user/profile.jsp";
            if (indexS == null) {
                indexS = "1";
            }
            if (search == null) {
                search = "";
            }
            PostDAO postDAO = new PostDAO();
            int index = Integer.parseInt(indexS);
            List<Post> listPost = postDAO.getPostFilter(index, userLogin.getId(), status);
            int total = postDAO.getPostFilterTotal(userLogin.getId(), status);

            if (search != "") {
                listPost = postDAO.getPostFilterSearch(index, search, userLogin.getId(), status);
                total = postDAO.getPostFilterSearchTotal(search, userLogin.getId(), status);
            }

            int lastPage = total / 12;
            if (total % 12 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_POST", listPost);
            request.setAttribute("endP", lastPage);
            request.setAttribute("search", search);
            request.setAttribute("selectedPage", index);
            request.setAttribute("status", status);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
