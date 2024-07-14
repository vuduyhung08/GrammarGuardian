/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.AuthenticationDAO;
import Model.CreateModel.UserSignUp;
import Service.MailService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class RegisterController extends HttpServlet {
   
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
            out.println("<title>Servlet RegisterController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("views/common/sign-in.jsp").forward(request, response);
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
            String link = "http://localhost:9999/GrammarGuardian/auth";
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

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
