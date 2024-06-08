/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class GrammarCheckerDAO extends DBContext {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public GrammarCheckerDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllUserPost(int userId) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT * FROM [Post] WHERE UserId = ? ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    ;
     public List<Post> getAllPostAvailable() {
        List<Post> listPosts = new ArrayList<>();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT * FROM [Post] WHERE Status = 3 ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    ;

    

    public boolean SavePost(int userId, Post postCM) {
        try {
            String sql = "INSERT INTO [Post] (Title, Description, UserId, CreateAt, Status)"
                    + " VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, postCM.getTitle());
            ps.setString(2, postCM.getDescription());
            ps.setInt(3, userId);
            LocalDateTime now = LocalDateTime.now();
            ps.setString(4, now.toString());
            ps.setInt(5, 0);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean SaveError(Post postCM, String error) {
        try {
            String sql = "INSERT INTO [Error] (PostId, Description, SolutionId)"
                    + " VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postCM.getPostId());
            ps.setString(2, error);
            ps.setString(3, postCM.getDescription());

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
