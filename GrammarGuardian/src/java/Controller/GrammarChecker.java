/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GrammarCheckerDAO;
import DAO.PostDAO;
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
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;


public class GrammarChecker extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    private void LoadHomePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
            PostDAO postDAO = new PostDAO();
            String indexS = request.getParameter("index");
            String searchS = request.getParameter("search");
            if (indexS == null) {
                indexS = "1";
            }
            if (searchS == null) {
                searchS = "";
            }
            int index = Integer.parseInt(indexS);

            int total = grammarCheckerDAO.getAllPostAvailableTotal();
            List<Post> listPost = grammarCheckerDAO.getAllPostAvailable(index);
            if (searchS != "") {
                total = postDAO.searchPostHomePageByTitleTotal(searchS);
                listPost = postDAO.searchPostHomePageByTitle(searchS, index);
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

    private void getResult(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            String text = request.getParameter("text");
            JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
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

                // Determine the type of error based on the rule category
                String errorType = "unknown";  // Default, in case the category isn't clear
                if (match.getRule().getCategory().getName().equals("Grammar")) {
                    errorType = "grammar";
                } else if (match.getRule().getCategory().getName().equals("Syntax")) {
                    errorType = "syntax";
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
            LoadHomePage(request, response);
            request.getRequestDispatcher("views/common/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     private void savePost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            String title = request.getParameter("title");
            List<Map<String, Object>> segments = (List<Map<String, Object>>) session.getAttribute("CHECK_RESULT");
            List<RuleMatch> matches = (List<RuleMatch>) session.getAttribute("CHECK_RESULT");
            String textInput = (String) session.getAttribute("ESSAY_INPUT");
            User userLogedIn = (User) session.getAttribute("USER");

            Post post = new Post();
            post.setTitle(title);
            post.setDescription(textInput);
            GrammarCheckerDAO grammarCheckerDAO = new GrammarCheckerDAO();
            int postId = grammarCheckerDAO.savePost(userLogedIn.getId(), post);

             for (RuleMatch match : matches) {
                Post_Error error = new Post_Error();
                error.setPostId(postId);
                error.setExplain(match.getMessage());
                error.setErrorText(match.getMessage());
                error.setStart_Position(match.getFromPos());
                String errorText = textInput.substring(match.getFromPos(), match.getToPos());
                error.setErrorText(errorText);
                error.setEnd_Position(match.getToPos());
                for (String suggestion : match.getSuggestedReplacements()) {
                    error.setSuggestion(suggestion);
                    grammarCheckerDAO.saveError(error);

                }
            }
           
            request.getRequestDispatcher("HomeController").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
