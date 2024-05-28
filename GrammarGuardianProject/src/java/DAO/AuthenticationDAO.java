package DAO;

import DAL.DBContext;
import Model.CreateModel.UserSignUp;
import Model.User;
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
public class AuthenticationDAO extends DBContext {

    private Connection con;
    private List<User> user;
    PreparedStatement ps;
    ResultSet rs;

    public AuthenticationDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String GetUserRole(int RoleId) {
        String sql = "SELECT * FROM [Role] WHERE [RoleId] = ? ";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, RoleId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("RoleName");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("GetUserRole Cannot found");
        }
        return null;
    }

}
