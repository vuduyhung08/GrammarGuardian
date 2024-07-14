/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.PostDAO;
import Model.Post;
import Model.Post_Update;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

/**
 *
 * @author Datnt
 */
public class UpdateAllSugestionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String postId = request.getParameter("postId");
            PostDAO postDAO = new PostDAO();
            Post post = postDAO.getPostById(Integer.parseInt(postId));
            String content = post.getDescription();
            JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
            List<RuleMatch> matches = langTool.check(content);

            // this is store for result
            StringBuilder correctedText = new StringBuilder(content);

            for (int i = matches.size() - 1; i >= 0; i--) {
                RuleMatch match = matches.get(i);
                if (!match.getSuggestedReplacements().isEmpty()) {
                    correctedText.replace(match.getFromPos(), match.getToPos(), match.getSuggestedReplacements().get(0));
                }
            }

            post.setDescription(correctedText.toString());
            post.setTitle(post.getTitle());
            int updatePostId = postDAO.UpdatePostWithSuggestion(post);
            if (updatePostId > 0) {
                Post_Update postUpdate = postDAO.getPostUpdateById(updatePostId);
                request.setAttribute("POST_UPDATE", postUpdate);
                request.setAttribute("MESSAGE", "UPDATE POST SUCCESSFULLY");
            } else {
                request.setAttribute("ERROR", "Failed");
            }
            request.getRequestDispatcher("UpdatePostController?postId=" + postId).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

}
