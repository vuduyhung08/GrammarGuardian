/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.FeedbackDAO;
import Model.Feedback;
import Model.User;
import Model.ViewModel.FeedbackVM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "FeedbackController", urlPatterns = {"/FeedbackController"})
public class FeedbackController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            List<FeedbackVM> listFeedBack = feedbackDAO.getAllFeedback(index, 1);
            int total = feedbackDAO.getAllFeedbackTotal(1);

            int lastPage = total / 10;
            if (total % 10 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_FEEDBACK", listFeedBack);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("views/common/about-us.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                User user = (User) session.getAttribute("USER");
                String feedback = request.getParameter("feedback");
                FeedbackDAO feedbackDAO = new FeedbackDAO();
                Feedback newFeedback = new Feedback();
                newFeedback.setContent(feedback);
                newFeedback.setUserId(user.getId());
                boolean result = feedbackDAO.saveFeedback(newFeedback);
                if (result) {
                    request.setAttribute("MESSAGE", "Feedback submitted successfully. Please wait for admin approval.");
                } else {
                    request.setAttribute("ERROR", "Failed to submit feedback.");
                }
                request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
            } else {
                response.sendRedirect("LoginController");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
