/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AuthenticationDAO;
import DAO.GrammarCheckerDAO;
import DAO.PostDAO;
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
        loadHomePage(request, response);
        switch (action) {
//            case "":
////                LoadHomePage(request, response);
//                break;
            case "login":
                url = "views/common/sign-in.jsp";
                break;
            case "confirm-email":
                ConfirmEmail(request, response);
                url = "views/common/sign-in.jsp";
                break;
            case "logout":
                session.removeAttribute("USER");
                break;
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
                        url = "admin/GetAllUserController";
                        response.sendRedirect(url);
                        return;
                    }
                } else if (!userLogedIn.isIsActive()) {
                    request.setAttribute("ERRORMESSAGE", "Your account not active please contact administration");
                } else if (!userLogedIn.isIsCofirm()) {
                    request.setAttribute("ERRORMESSAGE", "Your account not confirm! Please check your email");
                }
            } else {
                request.setAttribute("ERRORMESSAGE", "Wrong usename or password");
            }
            loadHomePage(request, response);
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
<<<<<<< HEAD

=======
            // B1 gui mail truoc
>>>>>>> hainvt
            String link = "http://localhost:9999/GrammarGuardian/auth?action=confirm-email";
            AuthenticationDAO authDAO = new AuthenticationDAO();
            // tao tai khoan.
            int result = authDAO.Register(userSignUp);

            if (result == 1) {
                url = "views/common/sign-in.jsp";
                request.setAttribute("SUCCESSMESSAGE", "Sign up sucessfully! Please check your email");
                request.setAttribute("EMAIL_URL", "https://mail.google.com/");
                session.setAttribute("USERNAME", userName);
                // mail service
                MailService mailService = new MailService();
                mailService.sendMailWithConfirmLink(email, link);
            } else if (result == 2) {
                request.setAttribute("ERRORMESSAGE", "UserName already exsit please try others. ");
            } else if (result == 3) {
                request.setAttribute("ERRORMESSAGE", "Email already exsit please try others.");
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

    private void loadHomePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
            String indexS = request.getParameter("index");
            String searchS = request.getParameter("search");

            if (indexS == null) {
                indexS = "1";
            }
            if (searchS == null) {
                searchS = "";
            } else {
                searchS = searchS.trim();
            }

            int index = Integer.parseInt(indexS);

            int total = grammarCheckerDAO.getAllPostAvailableTotal();
            List<Post> listPost = grammarCheckerDAO.getAllPostAvailable(index);

            if (!searchS.isEmpty()) {
                total = grammarCheckerDAO.searchPostHomePageByTitleTotal(searchS);
                listPost = grammarCheckerDAO.searchPostHomePageByTitle(searchS, index);
                request.setAttribute("search", searchS);
            }

            int lastPage = total / 12;
            if (total % 12 != 0) {
                lastPage++;
            }

            request.setAttribute("LIST_POST", listPost);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
