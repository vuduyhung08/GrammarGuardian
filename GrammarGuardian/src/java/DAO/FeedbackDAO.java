/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Feedback;
import Model.User;
import Model.ViewModel.FeedbackVM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class FeedbackDAO {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public FeedbackDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean saveFeedback(Feedback feedback) {
        try {
            String sql = "INSERT INTO Feedback (Content, CreateAt, UserId, Status) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, feedback.getContent());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(2, currentDate);
            ps.setInt(3, feedback.getUserId());
            ps.setInt(4, 0); // Default status is 0 (pending)          
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getAllFeedbackTotal(int status) {
        try {
            if (status == 4) {
                String sql = "SELECT COUNT(*) FROM Feedback";
                ps = con.prepareStatement(sql);

            } else {
                String sql = "SELECT COUNT(*) FROM Feedback WHERE Status = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, status);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<FeedbackVM> getAllFeedback(int index, int status) {
        List<FeedbackVM> feedbackList = new ArrayList<>();

        try {
            String sql = "";
            if (status == 4) {
                sql = "SELECT fd.*, u.UserName, u.Image FROM Feedback fd JOIN [User] u ON fd.UserId = u.UserId ORDER BY fd.CreateAt DESC  OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, (index - 1) * 10);
            } else {
                sql = "SELECT fd.*, u.UserName, u.Image FROM Feedback fd JOIN [User] u ON fd.UserId = u.UserId  WHERE fd.Status = ? ORDER BY fd.CreateAt DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setInt(2, (index - 1) * 10);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                FeedbackVM feedback = new FeedbackVM();
                User user = new User();
                user.setUserName(rs.getString("UserName"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = null;
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                user.setImage(base64Image);
                feedback.setUser(user);
                feedback.setId(rs.getInt("Id"));
                feedback.setContent(rs.getString("Content"));
                feedback.setCreateAt(rs.getString("CreateAt"));
                feedback.setUserId(rs.getInt("UserId"));
                feedback.setStatus(rs.getInt("Status"));

                feedbackList.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public boolean approveFeedback(int feedbackId) {
        try {
            String sql = "UPDATE Feedback SET Status = 1 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rejectFeedback(int feedbackId) {
        try {
            String sql = "";
            ps = con.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Feedback> getApprovedFeedback() {
        List<Feedback> approvedFeedback = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Feedback WHERE Status = 1 ORDER BY CreateAt DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("Id"));
                feedback.setContent(rs.getString("Content"));
                feedback.setCreateAt(rs.getString("CreateAt"));
                feedback.setUserId(rs.getInt("UserId"));
                feedback.setStatus(rs.getInt("Status"));
                approvedFeedback.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvedFeedback;
    }

    public static void main(String[] args) {
        FeedbackDAO fedDAO = new FeedbackDAO();
        List<FeedbackVM> list = fedDAO.getAllFeedback(1, 1);
        for (FeedbackVM feedbackVM : list) {
            System.out.println(feedbackVM.getUser().getUserName());
        }
    }
}
