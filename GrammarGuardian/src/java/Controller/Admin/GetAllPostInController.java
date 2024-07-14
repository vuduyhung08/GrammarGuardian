/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.PostDAO;
import Model.ViewModel.ViewPostDetailAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class GetAllPostInController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetAllPostInController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetAllPostInController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                PostDAO postDAO = new PostDAO();
                List<ViewPostDetailAdmin> listPost = new ArrayList<>();

                String indexS = request.getParameter("index");
                String searchS = request.getParameter("search");
                String typeSearch = request.getParameter("searchType");
                int total = 0;
                if (indexS == null) {
                    indexS = "1";
                }
                if (searchS == null) {
                    searchS = "";
                } else {
                    searchS = searchS.trim();
                }
                
                if(typeSearch == null || typeSearch.isEmpty()){
                    typeSearch = "all";
                }
                int index = Integer.parseInt(indexS);

                // Điều kiện tìm kiếm hoặc lọc loại bài viết
                if (searchS.isEmpty() && (typeSearch == null || "all".equals(typeSearch))) {
                    total = postDAO.getAllPostAdminTotal();
                    listPost = postDAO.getAllPostInAdmin(index);
                } else {
                    if (!searchS.isEmpty() && (typeSearch == null || "all".equals(typeSearch))) {
                        total = postDAO.searchPostManagePageTitleTotal(searchS);
                        listPost = postDAO.searchPostManagePageByTitle(searchS, index);
                        request.setAttribute("search", searchS);
                    } else if (searchS.isEmpty() && typeSearch != null && !"all".equals(typeSearch)) {
                        int type = Integer.parseInt(typeSearch);
                        total = postDAO.getPostTotalByTypeSearch(type);
                        listPost = postDAO.getPostByTypeSearch(type, index);
                        request.setAttribute("searchType", typeSearch);
                    } else if (!searchS.isEmpty() && typeSearch != null && !"all".equals(typeSearch)) {
                        int type = Integer.parseInt(typeSearch);
                        total = postDAO.getPostTotalByTitleAndType(searchS, type); // Add this method in PostDAO
                        listPost = postDAO.getPostsByTitleAndType(searchS, type, index); // Add this method in PostDAO
                        request.setAttribute("search", searchS);
                        request.setAttribute("searchType", typeSearch);
                    }
                }

                int lastPage = total / 8;
                if (total % 8 != 0) {
                    lastPage++;
                }
                request.setAttribute("LIST_POST", listPost);
                request.setAttribute("endP", lastPage);
                request.setAttribute("selectedPage", index);
            }
            request.getRequestDispatcher("/views/manage/manage-post.jsp").forward(request, response);
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
        processRequest(request, response);
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
