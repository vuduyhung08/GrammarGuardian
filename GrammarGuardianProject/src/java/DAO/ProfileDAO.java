package DAO;

import DAL.DBContext;
import Model.User;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProfileDAO extends DBContext{
     private Connection con;
    private List<User> user;
    PreparedStatement ps;
    ResultSet rs;
    
    
    
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
}
