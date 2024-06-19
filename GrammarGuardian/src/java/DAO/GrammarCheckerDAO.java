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

/**
 *
 * @author Datnt
 */
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

    public int searchPostHomePageByTitleTotal(String title) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Title LIKE ? AND Status = 3";
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

    public List<Post> searchPostHomePageByTitle(String title, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT * FROM [Post] WHERE Title LIKE ? AND Status = 3 ORDER BY PostId DESC OFFSET ? ROW FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getString("CreateAt"));
                post.setUserId(rs.getInt("UserId"));
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

    public int getAllUserPostTotal(int userId) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT COUNT(*) FROM [Post] WHERE UserId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Post> getAllUserPost(int userId, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT * FROM [Post] WHERE UserId = ? ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 12);

            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setUserId(rs.getInt("UserId"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

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

    public int getAllPostAvailableTotal() {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 3 ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // list user manager approve
    public List<Post> getAllPostAvailable(int index) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT * FROM [Post] WHERE Status = 3 ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public int getAllPostSavedTotal(int userId) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 0 AND UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // list user manager approve
    public List<Post> getAllPostSaved(int userId, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT * FROM [Post] WHERE Status = 0 AND UserId = ? ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 12);

            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public int getAllPostSendToConfirmTotal(int userId) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 1 AND UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // list user manager approve
    public List<Post> getAllPostSendToConfirm(int userId, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT * FROM [Post] WHERE Status = 1 AND UserId = ? ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public int getAllPostRejectTotal(int userId) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 2 AND UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // list user manager approve
    public List<Post> getAllPostReject(int userId, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT * FROM [Post] WHERE Status = 2 AND UserId = ? ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public int getAllPostConfirmTotal(int userId) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 3 AND UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // list user manager approve
    public List<Post> getAllPostConfirm(int userId, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT * FROM [Post] WHERE Status = 3 AND UserId = ? ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public int getAllPostDeleteTotal(int userId) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 4 AND UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // list user manager approve
    public List<Post> getAllPostDelete(int userId, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            String sql = "SELECT * FROM [Post] WHERE Status = 4 AND UserId = ? ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public boolean SubmitPost(int postId) {
        try {
            String sql = "UPDATE POST SET Status = 1 WHERE PostId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean DeletePost(int postId) {
        try {
            String sql = "UPDATE POST SET Status = 4 WHERE PostId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean RestorePost(int postId) {
        try {
            String sql = "UPDATE POST SET Status = 0 WHERE PostId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExistFavouritePost(int postId, int userId) {
        try {
            String sql = "SELECT * FROM [Post_Favourite] WHERE PostId = ? AND UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean AddToFavouriteList(int postId, int userId) {
        try {
            boolean isExist = isExistFavouritePost(postId, userId);
            if (isExist) {
                return false;
            }
            String sql = "INSERT INTO [Post_Favourite] (PostId, UserId) VALUES (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean RemovePostFavouriteList(int postId, int userId) {
        try {
            String sql = "DELETE FROM [Post_Favourite] WHERE PostId = ? AND UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getAllFavourtePostTotal(int userId) {
        try {
            // status = 3 was post manager approval
            String sql = "SELECT COUNT(*) FROM [Post] p JOIN [Post_Favourite] pf ON p.PostId = pf.PostId AND p.UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // list user manager approve
    public List<Post> getAllFavourtePost(int userId, int index) {
        List<Post> listPosts = new ArrayList();
        try {
            // status = 3 was post manager approval
            String sql = "SELECT p.UserId, p.PostId, p.Title, p.Description, p.CreateAt, p.Status FROM [Post] p JOIN [Post_Favourite] pf ON p.PostId = pf.PostId AND pf.UserId = ?"
                    + " ORDER BY PostId DESC OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostId"));
                post.setTitle(rs.getString("Title"));
                post.setDescription(rs.getString("Description"));
                post.setStatus(rs.getInt("Status"));
                post.setCreateAt(rs.getDate("CreateAt").toString());
                post.setUserId(rs.getInt("UserId"));
                listPosts.add(post);
            }
            return listPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    public static void main(String[] args) {
        GrammarCheckerDAO checkDAO = new GrammarCheckerDAO();
        checkDAO.AddToFavouriteList(18, 17);
        List<Post> listPost = checkDAO.getAllFavourtePost(17, 1);
        for (Post p : listPost) {
            System.out.println(p.toString());
        }
    }
}
