/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.PackageDAO;
import Model.PostPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class GetAllPackageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                PackageDAO packageDAO = new PackageDAO();
                List<PostPackage> packages = packageDAO.getAllPackages();
                request.setAttribute("PACKAGES", packages);
                request.getRequestDispatcher("/views/manage/package-management.jsp").forward(request, response);
            } else {
                response.sendRedirect("auth?action=login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("auth?action=login");
        }
    }
}
