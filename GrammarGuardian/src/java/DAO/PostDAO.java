/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Post;
import Model.ViewModel.CommentViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;

public class PostDAO extends DBContext {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public PostDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int searchPostByTitleTotal(String title) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Title LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Post> searchPostByTitle(String title, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT * FROM [Post] WHERE Title LIKE ? ORDER BY PostId DESC OFFSET ? ROW FETCH NEXT 4 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getString("CreateAt"));
//                post.setCreateAt(rs.getString("UpdateAt"));
//                post.setCreateAt(rs.getString("DeleteAt"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    // Comment
    public boolean CommentPost(int postId, String Content, int UserId) {
        try {
            String sql = "INSERT INTO [Comment] (PostId, Content, UserId, CreateAt)"
                    + " VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.setString(2, Content);
            ps.setInt(3, UserId);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(4, currentDate);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // get lisst comment
    public List<CommentViewModel> getListUserCommentInPost(int postId) {
        try {
            
             List<CommentViewModel> listComment = new ArrayList();
            String sql = "SELECT * FROM [Comment] WHERE PostId = ? ";
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            while (rs.next()) {
                CommentViewModel commentVM = new CommentViewModel();
                commentVM.setId(rs.getInt("Id"));
                commentVM.setPostId(rs.getInt("PostId"));
                commentVM.setContent(rs.getString("Content"));
                commentVM.setUserId(rs.getInt("UserId"));
                commentVM.setCreateAt(rs.getString("Content"));

                sql = "SELECT * FROM [User] WHERE UserId = ?";
                ps.setInt(1, commentVM.getUserId());
                rs = ps.executeQuery();
                if (rs.next()) {
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String fullName = firstName + " " + lastName;
                    commentVM.setUserName(fullName);
                    String base64Image = null;
                    byte[] imgData = rs.getBytes("Image");
                    if (imgData != null) {
                        base64Image = Base64.getEncoder().encodeToString(imgData);
                    }
                    commentVM.setAvatar(base64Image);
                }
                listComment.add(commentVM);
            }
            return listComment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        PostDAO postDAO = new PostDAO();
        int result = postDAO.searchPostByTitleTotal("Tech");
        System.out.println(result);
    }
}
