/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserWalletDAO;
import Model.TransitionHistory;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class TransitionHistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String indexS = request.getParameter("index");
            String searchS = request.getParameter("search");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            String url = "views/user/wallet-history.jsp";
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("USER");
            session.setAttribute("USER", user);
            UserWalletDAO userWalletDAO = new UserWalletDAO();
            int walletId = userWalletDAO.getUserWalletByUserId(user.getId()).getWalletId();
            int total = userWalletDAO.getWalletHistoryTotal(walletId);
            List<TransitionHistory> listWalletHistory = userWalletDAO.getWalletHistory(walletId, index);
            int lastPage = total / 8;
            if (total % 8 != 0) {
                lastPage++;
            }
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            if (listWalletHistory.size() > 0) {
                request.setAttribute("WALLET_HISTORY", listWalletHistory);
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
