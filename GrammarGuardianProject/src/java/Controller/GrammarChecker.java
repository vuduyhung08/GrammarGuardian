/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GrammarCheckerDAO;
import Model.Post;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

public class GrammarChecker extends HttpServlet {

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
            out.println("<title>Servlet GrammarChecker</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GrammarChecker at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
            case "get-results":
                getResult(request, response);
                break;
        }

    }

    private void getResult(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);

//            Lấy giá trị của tham số "text" từ yêu cầu (request).
            String text = request.getParameter("text");
//            Khởi tạo công cụ kiểm tra ngữ pháp cho tiếng Anh Mỹ.
            JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

//            Kiểm tra văn bản và lưu các lỗi phát hiện được vào danh sách matches.
            List<RuleMatch> matches = langTool.check(text);

//            Tạo danh sách để lưu các đoạn văn bản và lỗi.
            // Create segments with error highlighting
            List<Map<String, Object>> segments = new ArrayList<>();

//            Vòng lặp for duyệt qua từng lỗi trong matches
            int lastPos = 0;

            for (RuleMatch match : matches) {
//            
                if (match.getFromPos() > lastPos) {
                    Map<String, Object> segment = new HashMap<>();
                    segment.put("text", text.substring(lastPos, match.getFromPos()));
                    segment.put("error", false);
                    segments.add(segment);
                }

                Map<String, Object> errorSegment = new HashMap<>();
                errorSegment.put("text", text.substring(match.getFromPos(), match.getToPos()));
                errorSegment.put("error", true);
                segments.add(errorSegment);
                lastPos = match.getToPos();
            }
            if (lastPos < text.length()) {
                Map<String, Object> segment = new HashMap<>();
                segment.put("text", text.substring(lastPos));
                segment.put("error", false);
                segments.add(segment);
            }

            request.setAttribute("segments", segments);
            request.setAttribute("matches", matches);
            request.setAttribute("text", text);

            session.setAttribute("ESSAY_INPUT", text);
            session.setAttribute("CHECK_RESULT", matches);

//            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
//            List<Post> listPost = grammarCheckerDAO.getAllPostAvailable();
//            request.setAttribute("LIST_POST", listPost);
            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void savePost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            String title = request.getParameter("title");
            List<RuleMatch> matches = (List<RuleMatch>) session.getAttribute("CHECK_RESULT");
            String textInput = (String) session.getAttribute("ESSAY_INPUT");
            User userLogedIn = (User) session.getAttribute("USER");

            Post post = new Post();
            post.setTitle(title);
            post.setDescription(textInput);
            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
            boolean result = grammarCheckerDAO.SavePost(userLogedIn.getId(), post);

            List<Post> listPost = grammarCheckerDAO.getAllPostAvailable();
            request.setAttribute("LIST_POST", listPost);

            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
