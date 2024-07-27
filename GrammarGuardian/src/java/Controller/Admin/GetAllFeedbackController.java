/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.FeedbackDAO;
import DAO.UserWalletDAO;
import Model.User;
import Model.UserWallet;
import Model.ViewModel.FeedbackVM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAllFeedbackController", urlPatterns = {"/admin/GetAllFeedbackController"})
public class GetAllFeedbackController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String indexS = request.getParameter("index");
            String statusS = request.getParameter("status");
            if (statusS == null || statusS == "") {
                statusS = "4";
            }
            if (indexS == null) {
                indexS = "1";
            }
            int status = Integer.parseInt(statusS);
            int index = Integer.parseInt(indexS);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            List<FeedbackVM> listFeedBack = feedbackDAO.getAllFeedback(index, status);
            int total = feedbackDAO.getAllFeedbackTotal(status);

            int lastPage = total / 10;
            if (total % 10 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_FEEDBACK", listFeedBack);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.setAttribute("status", status);
            request.getRequestDispatcher("/views/manage/feedback-manage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("auth?action=login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session != null && session.getAttribute("USER") != null) {
                User user = (User) session.getAttribute("USER");
                String action = request.getParameter("action");
                FeedbackDAO feedbackDAO = new FeedbackDAO();
                if (action.equals("approve")) {
                    int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
                    boolean result = feedbackDAO.approveFeedback(feedbackId);
                    if (result) {
                        response.sendRedirect("GetAllFeedbackController");
                    } else {
                        request.setAttribute("ERROR_MESSAGE", "Failed to approve wallet order.");
                    }
                } else if (action.equals("reject")) {
                    int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
                    boolean result = feedbackDAO.rejectFeedback(feedbackId);
                    if (result) {
                        response.sendRedirect("GetAllFeedbackController");
                    } else {
                        request.setAttribute("ERROR_MESSAGE", "Failed to reject wallet order.");
                    }
                }
            } else {
                response.sendRedirect("LoginController");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("LoginController");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
