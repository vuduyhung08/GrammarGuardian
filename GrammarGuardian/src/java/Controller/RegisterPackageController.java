/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.PackageDAO;
import DAO.UserWalletDAO;
import Model.Permission;
import Model.PostPackage;
import Model.User;
import Model.UserWallet;
import Service.MailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterPackageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            if (session != null && session.getAttribute("USER") != null) {
                User userLogined = (User) session.getAttribute("USER");
                String pkIds = request.getParameter("packageId");
                int pkId = Integer.parseInt(pkIds);
                PackageDAO packageDAO = new PackageDAO();
                UserWalletDAO userWalletDAO = new UserWalletDAO();
                UserWallet userWallet = userWalletDAO.getUserWalletById(userLogined.getId());

                Permission pm = packageDAO.getPermissionsByUserId(userLogined.getId());
                // đoạn này check nếu chưa có package hoặc xài còn 0 lần thì mới được mua gói khác.
                if (pm == null || pm.getCheckTime() == 0) {
                    PostPackage postPackage = packageDAO.getPackageById(pkId);
                    if (userWallet.getAmmount() * 1000 >= postPackage.getPrice()) {
                        boolean result = packageDAO.addPermission(userLogined.getId(), postPackage);
                        if (result) {
                            float newAmount = userWallet.getAmmount() * 1000 - postPackage.getPrice();
                            userWalletDAO.updateUserWalletBalance(userLogined.getId(), userWallet.getWalletId(), newAmount);
                            String content = "Buy " + postPackage.getTitle() + " with " + postPackage.getPrice();
                            userWalletDAO.addTransitionHistory(content, userWallet.getWalletId());

                            String contentMail = "You are order an package with price: " + postPackage.getPrice()
                                    + "can use it with " + postPackage.getCheckTime() + " times to check "
                                    + postPackage.getLimitText() + "words each times you can view info in you profile";
                            MailService.sendMailWithInfo(userLogined.getEmail(), contentMail);

                            request.setAttribute("MESSAGE", "Register package sucessfully");
                        } else {
                            request.setAttribute("ERROR", "Cannot register package");
                        }
                    } else {
                        request.setAttribute("ERROR", "You dont have enough money");
                    }
                } else {
                    request.setAttribute("ERROR", "You already by one package! Please use it before you buy another one!");
                }
                request.getRequestDispatcher("HomeController").forward(request, response);
            } else {
                response.sendRedirect("LoginController");
            }

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
