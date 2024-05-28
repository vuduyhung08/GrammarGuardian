/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

<<<<<<< HEAD
import DAO.AuthenticationDAO;
import Model.CreateModel.UserSignUp;
import Model.Post;
import Model.User;
import Service.MailService;
=======
import java.io.IOException;
import java.io.PrintWriter;
>>>>>>> a40837fe3337e1e76bb532cef8193d3c4bd035c5
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
import java.io.IOException;
import java.util.List;


public class AuthenticationController extends HttpServlet {

=======

/**
 *
 * @author Admin
 */
public class AuthenticationController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthenticationController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthenticationController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editoWr-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
>>>>>>> a40837fe3337e1e76bb532cef8193d3c4bd035c5
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "views/common/index.jsp";
        switch (action) {
            case "":
<<<<<<< HEAD
                
=======
                home(request, response);
>>>>>>> a40837fe3337e1e76bb532cef8193d3c4bd035c5
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

<<<<<<< HEAD
    
=======

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
>>>>>>> a40837fe3337e1e76bb532cef8193d3c4bd035c5

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

<<<<<<< HEAD
    

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

    
=======
    private void ConfirmEmail(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void home(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

>>>>>>> a40837fe3337e1e76bb532cef8193d3c4bd035c5
}
