package DAO;

import DAL.DBContext;
import Model.UserSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class UserSessionDAO extends DBContext {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserSessionDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createUserSession(UserSession userSession) {
        try {
            String sql = "INSERT INTO UserSession (UserId, LoginTime, LogoutTime) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userSession.getUserId());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(2, currentDate);
            ps.setTimestamp(3, null); // Logout time is initially null
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserSessionLogoutTime(int userId) {
        try {
            String sql = "UPDATE UserSession SET LogoutTime = ? WHERE UserId = ? AND LogoutTime IS NULL";
            ps = con.prepareStatement(sql);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(1, currentDate);
            ps.setInt(2, userId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTotalActiveUsers() {
        try {
            String sql = "SELECT COUNT(*) FROM UserSession WHERE LogoutTime IS NULL";
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
}
