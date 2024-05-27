/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.CreateModel.UserSignUp;
import Model.Role;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

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

   


  

    public boolean CheckEmail(String email) {
        String sql = "SELECT * FROM [User] WHERE [Email] = ? ";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckUserName Cannot find User");
        }
        return false;
    }

    public boolean ConfirmEmail(String userName) {
        String sql = "UPDATE [User] SET IsConfirm = ? WHERE UserName = ?";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setString(2, userName);

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            } else {
                System.out.println("Confirm email Cannot found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ConfirmEmail Cannot found");
        }
        return false;
    }

    public boolean ForgotPassWord(String password, String email) {
        String sql = "UPDATE [User] SET Password = ? WHERE Email = ?";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            } else {
                System.out.println("ForgotPassWord Cannot found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ForgotPassWord Cannot found");
        }
        return false;
    }
}
