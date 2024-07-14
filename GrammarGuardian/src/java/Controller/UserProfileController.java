package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import DAO.GrammarCheckerDAO;
import Model.Post;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserProfileController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet UserProfileController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfileController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
            String indexS = request.getParameter("index");
//            String searchS = request.getParameter("search");
            if (indexS == null) {
                indexS = "1";
            }
//            if (searchS == null) {
//                searchS = "";
//            }
            int index = Integer.parseInt(indexS);
            // chua cau hinh kip search theo tung loai bai post.
            int total = grammarCheckerDAO.getAllUserPostTotal(userLogin.getId());
            List<Post> listPost = grammarCheckerDAO.getAllUserPost(userLogin.getId(), index);
            int status = 0;
//            if (searchS != "") {
//                total = grammarCheckerDAO.searchPostHomePageByTitleTotal(searchS);
//                listPost = grammarCheckerDAO.searchPostHomePageByTitle(searchS, index);
//                request.setAttribute("search", searchS);
//            }
            int lastPage = total / 12;
            if (total % 12 != 0) {
                lastPage++;
            }
            request.setAttribute("status", status);
            request.setAttribute("LIST_POST", listPost);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("views/user/profile.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
