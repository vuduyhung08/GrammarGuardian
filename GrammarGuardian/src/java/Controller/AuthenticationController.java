/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AuthenticationDAO;
import DAO.GrammarCheckerDAO;
import Model.CreateModel.UserSignUp;
import Model.Post;
import Model.User;
import Service.MailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class AuthenticationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "views/common/index.jsp";
        switch (action) {
            case "":
                home(request, response);
                break;
            case "login":
                url = "views/common/sign-in.jsp";
                break;
            case "confirm-email":
                ConfirmEmail(request, response);
                break;
            case "logout":
                session.removeAttribute("USER");
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
            case "login":
                Login(request, response);
                break;
            case "register":
                Register(request, response);
                break;
            case "verify-otp":
                Register(request, response);
                break;
        }
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

    private void Login(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/common/sign-in.jsp";
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            HttpSession session = request.getSession(false);
            AuthenticationDAO authDAO = new AuthenticationDAO();
            User userLogedIn = authDAO.Login(userName, password);
            if (userLogedIn != null) {
                if (userLogedIn.isIsActive() && userLogedIn.isIsCofirm()) {
                    // Login thanh cong
                    session.setAttribute("USER", userLogedIn);
                    session.setAttribute("EMAIL", userLogedIn.getEmail());
                    // User 
                    if (userLogedIn.getRoleId() == 1) {
//                        home(request, response);
                        url = "views/common/index.jsp";
                        // Admin
                    } else if (userLogedIn.getRoleId() == 2) {
                        url = "admin/dashboard.jsp";
                    }
                } else if (!userLogedIn.isIsActive()) {
                    request.setAttribute("ERRORMESSAGE", "Tài khoản của bạn bị vô hiệu hóa. Vui lòng liên hệ quản trị viên!");
                } else if (!userLogedIn.isIsCofirm()) {
                    request.setAttribute("ERRORMESSAGE", "Tài khoản của bạn chưa được xác thực vui lòng xác thông qua email của bạn!");
                }
            } else {
                request.setAttribute("ERRORMESSAGE", "Sai tên đăng nhập hoặc tài khoản, vui lòng thử lại!");
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Register(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String url = "views/common/sign-in.jsp";
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");

            // create create modal to signup
            UserSignUp userSignUp = new UserSignUp();
            userSignUp.setUserName(userName);
            userSignUp.setPassword(password);
            userSignUp.setEmail(email);
            userSignUp.setFirstName(firstName);
            userSignUp.setLastName(lastName);
            userSignUp.setPhone(phone);
            session.setAttribute("email", email);
            String link = "http://localhost:9999/GrammarGuardian/auth?action=confirm-email";
            AuthenticationDAO authDAO = new AuthenticationDAO();
            int result = authDAO.Register(userSignUp);

            if (result == 1) {
                url = "views/common/sign-in.jsp";
                request.setAttribute("SUCCESSMESSAGE", "Đăng kí thành công vui lòng xác thực email của bạn");
                request.setAttribute("EMAIL_URL", "https://mail.google.com/");
                session.setAttribute("USERNAME", userName);
                // mail service
                MailService mailService = new MailService();
                mailService.sendMailWithConfirmLink(email, link);
            } else if (result == 2) {
                request.setAttribute("ERRORMESSAGE", "UserName đã có người sử dụng hãy thử một tên khác ");
            } else if (result == 3) {
                request.setAttribute("ERRORMESSAGE", "Email này đã được sử dụng trên hệ thống vui lòng lựa chọn một email khác");
            } else {
                request.setAttribute("ERRORMESSAGE", "Cannot regitser");
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            System.out.println("Register" + e.getMessage());
        }
    }

    private void ConfirmEmail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/common/sign-in.jsp";
            HttpSession session = request.getSession();
            String userName = (String) session.getAttribute("USERNAME");
            AuthenticationDAO authDAO = new AuthenticationDAO();
            authDAO.ConfirmEmail(userName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Confirm Email Error");
        }
    }

    private void home(HttpServletRequest request, HttpServletResponse response) {
        try {
            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
            List<Post> listPost = grammarCheckerDAO.getAllPostAvailable();
            request.setAttribute("LIST_POST", listPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
