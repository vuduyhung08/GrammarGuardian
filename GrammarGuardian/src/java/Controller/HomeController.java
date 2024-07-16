/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GrammarCheckerDAO;
import DAO.UserSessionDAO;
import DAO.UserWalletDAO;
import Model.Post;
import Model.User;
import Model.UserSession;
import Model.UserWallet;
import java.io.IOException;
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
public class HomeController extends HttpServlet {

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
        GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
        UserSessionDAO userSessionDAO = new UserSessionDAO();
        HttpSession session = request.getSession();
        if (session.isNew()) {
            userSessionDAO.updateViews();
        }
        
        String indexS = request.getParameter("index");
        if (indexS == null) {
            indexS = "1";
        }

        int createSessionResult = userSessionDAO.getTotalActiveUsers();
        String formated1 = String.format("%05d", createSessionResult);
        session.setAttribute("VIEWS", formated1);

        int view = userSessionDAO.getViews(); // lay total views
        String formated = String.format("%05d", view);
        request.setAttribute("v", formated);

        int index = Integer.parseInt(indexS);
        int total = grammarCheckerDAO.getAllPostAvailableTotal();
        List<Post> listPost = grammarCheckerDAO.getAllPostAvailable(index);
        int lastPage = total / 8;
        if (total % 8 != 0) {
            lastPage++;
        }
        if (session != null && session.getAttribute("USER") != null) {
            User userLogedIn = (User) session.getAttribute("USER");
            session.setAttribute("EMAIL", userLogedIn.getEmail());
            UserWalletDAO userWalletDAO = new UserWalletDAO();
            UserWallet userWallet = userWalletDAO.getUserWalletByUserId(userLogedIn.getId());
            if (userWallet.getAmmount() > 0) {
                session.setAttribute("WALLET", userWallet.getAmmount());
            } else {
                session.setAttribute("WALLET", 0);
            }
        }

        request.setAttribute("LIST_POST", listPost);
        request.setAttribute("endP", lastPage);
        request.setAttribute("selectedPage", index);
        request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
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
        processRequest(request, response);

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
