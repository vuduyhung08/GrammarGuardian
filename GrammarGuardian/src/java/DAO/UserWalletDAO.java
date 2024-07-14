/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.UserWallet;
import Model.ViewModel.UserWalletOrderVM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserWalletDAO extends DBContext {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public UserWalletDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isExitUserWallet(int userId) {
        try {
            String sql = "SELECT * FROM UserWallet WHERE UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                sql = "INSERT INTO [UserWallet] (Ammount, CreateAt, UserId) VALUES (?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setFloat(1, 0);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                ps.setString(2, currentDate);
                ps.setInt(3, userId);
                int affectedRow = ps.executeUpdate();
                if (affectedRow > 0) {
                    System.out.println("Taoj vi tien thanh cong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserWallet getUserWalletByUserId(int userId) {
        try {
            isExitUserWallet(userId);
            String sql = "SELECT * FROM UserWallet WHERE UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                UserWallet order = new UserWallet();
                order.setWalletId(rs.getInt("Id"));
                order.setAmmount(rs.getInt("Ammount"));
                order.setCreateAt(rs.getString("CreateAt"));
                order.setUserId(rs.getInt("UserId"));
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addTransitionHistory(String Content, int WalletId) {
        try {
            String sql = "INSERT INTO TransitionHistory (Content, CreateAt, WalletId) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, Content);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(2, currentDate);
            ps.setInt(3, WalletId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addWalletOrder(int userWalletId, int userId, float amount) {
        try {
            String sql = "INSERT INTO UserWalletOrder (UserWalletId, Ammount, CreateAt, Status, UserId) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userWalletId);
            ps.setFloat(2, amount);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(3, currentDate);
            ps.setInt(4, 0); // 0 for pending, 1 for approved, 2 for rejected    
            ps.setInt(5, userId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UserWalletOrderVM> getWalletOrders() {
        List<UserWalletOrderVM> orders = new ArrayList<>();
        try {
            String sql = "SELECT uwo.Id, uwo.UserId, uwo.Ammount, uwo.UserId, uwo.UserWalletId, uwo.Status, u.Email, u.Image FROM UserWalletOrder uwo JOIN [User] u ON uwo.UserId = u.UserId";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserWalletOrderVM order = new UserWalletOrderVM();
                order.setId(rs.getInt("Id"));
                order.setUserWalletId(rs.getInt("UserWalletId"));
                order.setAmmount(rs.getFloat("Ammount"));
                order.setStatus(rs.getInt("Status"));
                order.setUserId(rs.getInt("UserId"));
                order.setEmail(rs.getString("Email"));
                
                order.setImage(rs.getString("Image"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean approveWalletOrder(int orderId) {
        try {
            String sql = "UPDATE UserWalletOrder SET Status = 1 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {                // Update user's wallet balance    
                sql = "SELECT UserId, Ammount, UserWalletId FROM UserWalletOrder WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("UserId");
                    float amount = rs.getFloat("Ammount");
                    int walletId = rs.getInt("UserWalletId");
                    updateUserWalletBalance(userId, walletId, amount);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rejectWalletOrder(int orderId) {
        try {
            String sql = "UPDATE UserWalletOrder SET Status = 2 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserWallet getUserWalletById(int userId) {
        try {
            String sql = "SELECT * FROM UserWallet WHERE UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                float ammount = rs.getFloat("Ammount");
                UserWallet wallet = new UserWallet();
                // convert từ điểm sang tiền.
                wallet.setAmmount(ammount / 1000);
                wallet.setCreateAt(rs.getString("CreateAt"));
                wallet.setUserId(rs.getInt("UserId"));
                wallet.setWalletId(rs.getInt("Id"));
                return wallet;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateUserWalletBalance(int userId, int userWalletId, float amount) {
        try {
            String sql = "UPDATE UserWallet SET Ammount = Ammount + ? WHERE UserId = ? AND Id = ?";
            ps = con.prepareStatement(sql);
            ps.setFloat(1, amount);
            ps.setInt(2, userId);
            ps.setInt(3, userWalletId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                String content = "Add to your wallet " + amount;
                addTransitionHistory(content, userWalletId);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
