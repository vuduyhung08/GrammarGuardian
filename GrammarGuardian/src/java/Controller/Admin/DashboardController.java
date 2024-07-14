/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.DashboardDAO;
import DAO.UserWalletDAO;
import Model.UserWalletOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                DashboardDAO dashboardDAO = new DashboardDAO();
                int totalUsers = dashboardDAO.getTotalUsers();
                int totalPendingPosts = dashboardDAO.getTotalPendingPosts();
                int totalConfirmedPosts = dashboardDAO.getTotalConfirmedPosts();
                float totalWalletBalance = dashboardDAO.getTotalWalletBalance();

                LocalDateTime now = LocalDateTime.now();
                int currentMonth = now.getMonthValue();
                int currentYear = now.getYear();
                
                String months = request.getParameter("month");
                if(months != null) {
                    currentMonth = Integer.parseInt(months);
                }
                float monthlyWalletRevenueCurentMonth = 0.0f;
                List<UserWalletOrder> monthlyWalletOrders = dashboardDAO.getMonthlyWalletOrders(currentMonth, currentYear);
                for (int i = 1; i <= 12; i++) {
                    float revenueMoneth = 0.0f;
                    List<UserWalletOrder> monthlyWalletOrdersMonth = dashboardDAO.getMonthlyWalletOrders(i, currentYear);
                    for (UserWalletOrder order : monthlyWalletOrdersMonth) {
                            System.out.println("Month" + i + " " + order.getAmmount());
                        if (order.getAmmount() == 0) {
                            revenueMoneth += 0;
                        } else {
                            revenueMoneth += order.getAmmount();
                        }
                        if (i == currentMonth) {
                            monthlyWalletRevenueCurentMonth += order.getAmmount();
                        }
                    }
                    request.setAttribute("REVENUE_MOUNTH_" + i, revenueMoneth);
                }
                
                request.setAttribute("currentMonth", currentMonth);
                request.setAttribute("TOTAL_USERS", totalUsers);
                request.setAttribute("TOTAL_PENDING_POSTS", totalPendingPosts);
                request.setAttribute("TOTAL_CONFIRMED_POSTS", totalConfirmedPosts);
                request.setAttribute("TOTAL_WALLET_BALANCE", totalWalletBalance);
                request.setAttribute("MONTHLY_WALLET_REVENUE", monthlyWalletRevenueCurentMonth);
                request.setAttribute("WALLET_ORDERS", monthlyWalletOrders);

                request.getRequestDispatcher("/views/manage/dashboard.jsp").forward(request, response);
            } else {
                response.sendRedirect("auth?action=login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("auth?action=login");
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
