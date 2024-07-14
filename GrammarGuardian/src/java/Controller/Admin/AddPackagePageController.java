package Controller.Admin;

import DAO.PackageDAO;
import Model.PostPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddPackagePageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("USER") != null) {
            request.getRequestDispatcher("/views/manage/add-package.jsp").forward(request, response);
        } else {
            response.sendRedirect("auth?action=login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("USER") != null) {
            String description = request.getParameter("description");
            String title = request.getParameter("title");
            float price = Float.parseFloat(request.getParameter("price"));
            int limitText = Integer.parseInt(request.getParameter("limitText"));
            int checkTime = Integer.parseInt(request.getParameter("checkTime"));

            PostPackage p = new PostPackage();
            p.setDescription(description);
            p.setTitle(title);
            p.setPrice(price);
            p.setLimitText(limitText);
            p.setCheckTime(checkTime);

            PackageDAO packageDAO = new PackageDAO();
            boolean result = packageDAO.addPackage(p);
            if (result) {
                request.setAttribute("MESSAGE", "Package created successfully.");
            } else {
                request.setAttribute("ERROR", "Error creating package.");
            }
            response.sendRedirect("GetAllPackageController");
        } else {
            response.sendRedirect("auth?action=login");
        }
    }
    
 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
