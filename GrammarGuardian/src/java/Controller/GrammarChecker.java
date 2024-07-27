/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.GrammarCheckerDAO;
import DAO.PackageDAO;
import DAO.ProfileDAO;
import Model.Permission;
import Model.Post;
import Model.Post_Error;
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
import org.languagetool.rules.Rule;
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
            response.sendRedirect("LoginController");
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

    private void getResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Sẽ cho check free 3 lần. 
            HttpSession session = request.getSession();
            User userLogined = (User) session.getAttribute("USER");
            String text = request.getParameter("text");
            String wordCount = request.getParameter("word-count"); // lay word count.
            String checkType = request.getParameter("check-type"); // Lấy giá trị của check-type
            ProfileDAO profileDAO = new ProfileDAO();

            PackageDAO packageDAO = new PackageDAO();
            Permission userPermission = packageDAO.getPermissionsByUserId(userLogined.getId());

            boolean isValidateToChecker = true;
            int wordInput = Integer.parseInt(wordCount);
            if (userPermission == null) {
                // get user ra first
                if (userLogined.getCheckFreeTime() == 0) {
                    isValidateToChecker = false;
                    request.setAttribute("ERROR", "You already using leat 10 times free checking! Please register our package to try more!");
                    request.getRequestDispatcher("HomeController").forward(request, response);
                    return;
                }
            } // validate checkTime
            else if (userPermission.getCheckTime() == 0) {
                isValidateToChecker = false;
                request.setAttribute("ERROR", "You dont have any remains time to check anymore");
                request.getRequestDispatcher("HomeController").forward(request, response);
                return;
            } // validate word cound 
            else if (userPermission.getLimitText() < wordInput) {
                isValidateToChecker = false;
                request.setAttribute("ERROR", "Your package's word limit is not validate");
                request.getRequestDispatcher("HomeController").forward(request, response);
                return;
            }

            if(userLogined.getCheckFreeTime() != 0 && wordInput > 100) {
                isValidateToChecker = false;
                 request.setAttribute("ERROR", "You just able to check lower than 100 words");
            }
            
            if (isValidateToChecker) {
                
                  // tru so lan mien phi neu con
                if (userLogined.getCheckFreeTime() != 0) {
                    int remainsTime = userLogined.getCheckFreeTime() - 1;
                    userLogined.setCheckFreeTime(remainsTime);
                    profileDAO.updateRemainsTime(userLogined);
                }

                
                JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

                // Chặn kiểm tra theo loại được chọn
                if ("spell".equals(checkType)) {
                    // Chỉ kiểm tra chính tả
                    for (Rule rule : langTool.getAllRules()) {
                        if (!rule.isDictionaryBasedSpellingRule()) {
                            langTool.disableRule(rule.getId());
                        }
                    }
                } else if ("grammar".equals(checkType)) {
                    // Chỉ kiểm tra ngữ pháp
                    for (Rule rule : langTool.getAllRules()) {
                        if (rule.isDictionaryBasedSpellingRule()) {
                            langTool.disableRule(rule.getId());
                        }
                    }
                } // Không cần else cho "all" vì mặc định kiểm tra tất cả các lỗi

                List<RuleMatch> matches = langTool.check(text);

                // Tạo các đoạn văn với phần lỗi được đánh dấu
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

                // this is store for result
                StringBuilder correctedText = new StringBuilder(text);

                for (int i = matches.size() - 1; i >= 0; i--) {
                    RuleMatch match = matches.get(i);
                    if (!match.getSuggestedReplacements().isEmpty()) {
                        correctedText.replace(match.getFromPos(), match.getToPos(), match.getSuggestedReplacements().get(0));
                    }
                }

                request.setAttribute("wordCount", wordCount);
                request.setAttribute("segments", segments);
                request.setAttribute("matches", matches);
                request.setAttribute("text", text);
                request.setAttribute("checkType", checkType); // Thiết lập giá trị của checkType trong request
                session.setAttribute("TEXT_SOLUTION", correctedText);
                session.setAttribute("TEXT_RESULT", segments);
                session.setAttribute("ESSAY_INPUT", text);
                session.setAttribute("CHECK_RESULT", matches);

              
                if (userPermission != null) {
                    // get user ra first
                    int checkTimePackageRemains = userPermission.getCheckTime() - 1;
                    packageDAO.updateUserPermission(userPermission.getPackageId(), userPermission.getUserId(), checkTimePackageRemains);
                }
            } else {
                request.setAttribute("ERROR", "ERROR In Check Post");
            }

            request.getRequestDispatcher("HomeController").forward(request, response);
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

            // save check result.
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
