/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Post;
import Model.User;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author Datnt
 */
public class ProfileDAO extends DBContext {

    private Connection con;
    private List<User> user;
    PreparedStatement ps;
    ResultSet rs;

    public ProfileDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public User updateProfile(User user, Part image) {
        try {
            User userUpdate = new User();
            String sql = "";
            if (image != null) {
                sql = "UPDATE dbo.[User] SET [FirstName] = ?, [LastName] = ?, [Phone] = ?, "
                        + "[Email] = ?, UpdateAt = ?, Image = ? "
                        + "WHERE [UserId] = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getEmail());

                LocalDateTime now = LocalDateTime.now();
                ps.setString(5, now.toString());
                InputStream fileContent = image.getInputStream();
                ps.setBinaryStream(6, fileContent, (int) image.getSize());
                ps.setInt(7, user.getId());
            } else {
                sql = "UPDATE dbo.[User] SET [FirstName] = ?, [LastName] = ?, [Phone] = ?, "
                        + "[Email] = ?, UpdateAt = ? "
                        + "WHERE [UserId] = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getEmail());

                LocalDateTime now = LocalDateTime.now();
                ps.setString(5, now.toString());
                ps.setInt(6, user.getId());
            }

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                sql = "SELECT * FROM dbo.[User] WHERE UserId = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, user.getId());
                rs = ps.executeQuery();
                if (rs.next()) {
                    int UserId = rs.getInt("UserId");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String phone = rs.getString("Phone");
                    String email = rs.getString("Email");
                    byte[] imgData = rs.getBytes("Image");
                    String base64Image = null;
                    if (imgData != null) {
                        base64Image = Base64.getEncoder().encodeToString(imgData);
                    }

                    userUpdate.setFirstName(firstName);
                    userUpdate.setLastName(lastName);
                    userUpdate.setPhone(phone);
                    userUpdate.setEmail(email);
                    userUpdate.setId(UserId);
                    userUpdate.setImage(base64Image);
                    return userUpdate;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean changePassword(User user, String newPassword) {
        String sql = "SELECT * FROM [User] WHERE [UserName] = ? AND [Password] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                sql = "UPDATE dbo.[User] SET [Password] = ? "
                        + "WHERE [UserName] = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, newPassword);
                ps.setString(2, user.getUserName());
                int affectedRow = ps.executeUpdate();
                if (affectedRow > 0) {
                    return true;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
