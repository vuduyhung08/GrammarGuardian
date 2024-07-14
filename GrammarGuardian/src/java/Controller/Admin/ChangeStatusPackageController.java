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
import java.util.List;

public class ChangeStatusPackageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("USER") != null) {
            String packageId = request.getParameter("packageId");
            int id = Integer.parseInt(packageId);

            PackageDAO packageDAO = new PackageDAO();
            PostPackage p = new PostPackage();
            p.setId(id);
            boolean result = packageDAO.updatePackage(p);
            if (result) {
                request.setAttribute("MESSAGE", "Package status updated successfully.");
            } else {
                request.setAttribute("ERROR", "Error updating package status.");
            }
            request.getRequestDispatcher("GetAllPackageController").forward(request, response);
        } else {
            response.sendRedirect("auth?action=login");
        }
    }
}
