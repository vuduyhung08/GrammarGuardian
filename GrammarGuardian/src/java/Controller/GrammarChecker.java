/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GrammarCheckerDAO;
import Model.Post;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.SpellingCheckRule;

public class GrammarChecker extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
        List<Post> listPost = grammarCheckerDAO.getAllPostAvailable();
        request.setAttribute("LIST_POST", listPost);
        request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        if (session != null && session.getAttribute("USER") != null) {
            switch (action) {
                case "get-result":
                    getResult(request, response);
                    break;
                case "save-post":
                    savePost(request, response);
                    break;
            }
        } else {
            response.sendRedirect("views/common/sign-in.jsp");
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

    private void getResult(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Lấy session hiện tại, nếu không tồn tại session thì trả về null
            HttpSession session = request.getSession(false);
            // Lấy tham số "text" từ request
            String text = request.getParameter("text");
            // Khởi tạo JLanguageTool với ngôn ngữ tiếng Anh Mỹ
            JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
            for (Rule rule : langTool.getAllRules()) {
                if (rule.isDictionaryBasedSpellingRule()) {
                    langTool.disableRule(rule.getId());
                }
            }
            // Kiểm tra lỗi trong văn bản "text" và lưu kết quả vào danh sách "matches"
            List<RuleMatch> matches = langTool.check(text);

            // Create segments with error highlighting
            List<Map<String, Object>> segments = new ArrayList<>();
            int lastPos = 0;
            for (RuleMatch match : matches) {
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

            // Đặt các danh sách lỗi vào request
            request.setAttribute("segments", segments);
            request.setAttribute("matches", matches);
            request.setAttribute("text", text);
            // Lưu vào session nếu cần
            session.setAttribute("ESSAY_INPUT", text);
            session.setAttribute("CHECK_RESULT", matches);

            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
            List<Post> listPost = grammarCheckerDAO.getAllPostAvailable();
            request.setAttribute("LIST_POST", listPost);

            // Chuyển tiếp đến trang index.jsp
            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void getResultImprove(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            HttpSession session = request.getSession(false);
//            String text = request.getParameter("text");
//            JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
//
//            // Khai báo các biến để xác định loại lỗi cần kiểm tra
//            boolean checkSpelling = Boolean.parseBoolean(request.getParameter("checkSpelling"));
//            boolean checkPunctuation = Boolean.parseBoolean(request.getParameter("checkPunctuation"));
//            boolean checkGrammar = Boolean.parseBoolean(request.getParameter("checkGrammar"));
//
//            List<RuleMatch> matches = new ArrayList<>();
//
//            // Kiểm tra lỗi chính tả nếu được chọn
//            if (checkSpelling) {
//                matches.addAll(langTool.check(text));
//            }
//
//            // Kiểm tra lỗi dấu câu nếu được chọn
//            if (checkPunctuation) {
//                langTool.disableRule("MORFOLOGIK_RULE_EN_US");
//                matches.addAll(langTool.check(text));
//            }
//
//            // Kiểm tra lỗi ngữ pháp nếu được chọn
//            if (checkGrammar) {
//                langTool.enableRule("MORFOLOGIK_RULE_EN_US");
//                matches.addAll(langTool.check(text));
//            }
//
//            // Tạo segments với việc làm nổi bật lỗi
//            List<Map<String, Object>> segments = new ArrayList<>();
//            int lastPos = 0;
//            for (RuleMatch match : matches) {
//                if (match.getFromPos() > lastPos) {
//                    Map<String, Object> segment = new HashMap<>();
//                    segment.put("text", text.substring(lastPos, match.getFromPos()));
//                    segment.put("error", false);
//                    segments.add(segment);
//                }
//                Map<String, Object> errorSegment = new HashMap<>();
//                errorSegment.put("text", text.substring(match.getFromPos(), match.getToPos()));
//                errorSegment.put("error", true);
//                segments.add(errorSegment);
//                lastPos = match.getToPos();
//            }
//            if (lastPos < text.length()) {
//                Map<String, Object> segment = new HashMap<>();
//                segment.put("text", text.substring(lastPos));
//                segment.put("error", false);
//                segments.add(segment);
//            }
//
//            request.setAttribute("segments", segments);
//            request.setAttribute("matches", matches);
//            request.setAttribute("text", text);
//
//            session.setAttribute("ESSAY_INPUT", text);
//            session.setAttribute("CHECK_RESULT", matches);
//
//            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
//            List<Post> listPost = grammarCheckerDAO.getAllPostAvailable();
//            request.setAttribute("LIST_POST", listPost);
//            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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

}
