/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.TransitionHistory;
import Model.User;
import Model.UserWallet;
import Model.ViewModel.UserWalletOrderVM;
import Service.MailService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
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

    public int getWalletOrdersTotal(int status) {
        List<UserWalletOrderVM> orders = new ArrayList<>();
        try {
            if (status == 4) {
                String sql = "SELECT COUNT(*) FROM UserWalletOrder uwo JOIN [User] u ON uwo.UserId = u.UserId";
                ps = con.prepareStatement(sql);

            } else {
                String sql = "SELECT COUNT(*) FROM UserWalletOrder uwo JOIN [User] u ON uwo.UserId = u.UserId WHERE uwo.Status = ?";
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

    public List<UserWalletOrderVM> getWalletOrders(int index, int status) {
        List<UserWalletOrderVM> orders = new ArrayList<>();
        try {
            if (status == 4) {
                String sql = "SELECT uwo.Id, uwo.UserId, uwo.Ammount, uwo.UserId, uwo.UserWalletId, uwo.Status, u.Email, u.Image "
                        + "FROM UserWalletOrder uwo JOIN [User] u ON uwo.UserId = u.UserId "
                        + "ORDER BY uwo.CreateAt DESC OFFSET ? ROW FETCH NEXT 5 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, (index - 1) * 5);
            } else {
                String sql = "SELECT uwo.Id, uwo.UserId, uwo.Ammount, uwo.UserId, uwo.UserWalletId, uwo.Status, u.Email, u.Image "
                        + "FROM UserWalletOrder uwo JOIN [User] u ON uwo.UserId = u.UserId WHERE uwo.Status = ? "
                        + "ORDER BY uwo.CreateAt DESC OFFSET ? ROW FETCH NEXT 5 ROWS ONLY ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setInt(2, (index - 1) * 5);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                UserWalletOrderVM order = new UserWalletOrderVM();
                order.setId(rs.getInt("Id"));
                order.setUserWalletId(rs.getInt("UserWalletId"));
                order.setAmmount(rs.getFloat("Ammount"));
                order.setStatus(rs.getInt("Status"));
                order.setUserId(rs.getInt("UserId"));
                order.setEmail(rs.getString("Email"));

                byte[] imgData = rs.getBytes("Image");
                String base64Image = null;
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                order.setImage(base64Image);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<UserWalletOrderVM> getWalletOrdersPending() {
        List<UserWalletOrderVM> orders = new ArrayList<>();
        try {
            String sql = "SELECT uwo.Id, uwo.UserId, uwo.Ammount, uwo.UserId, uwo.UserWalletId, uwo.Status, u.Email, u.Image FROM UserWalletOrder uwo JOIN [User] u ON uwo.UserId = u.Id WHERE uwo.Status = 0";
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

                byte[] imgData = rs.getBytes("Image");
                String base64Image = null;
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                order.setImage(base64Image);
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
            if (affectedRows > 0) {
                sql = "SELECT UserId, Ammount, UserWalletId FROM UserWalletOrder WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("UserId");
                    float amount = rs.getFloat("Ammount");
                    int walletId = rs.getInt("UserWalletId");
                    String content = "Send request not sucessfully: " + amount + " will refund to your account";
                    boolean result = addTransitionHistory(content, walletId);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean updateUserWalletBalance(int userId, int userWalletId, float amount) {
        try {
            String sql = "UPDATE UserWallet SET Ammount = ? WHERE UserId = ? AND Id = ?";
            ps = con.prepareStatement(sql);
            // đoạn này không cộng 
            ps.setFloat(1, amount);
            ps.setInt(2, userId);
            ps.setInt(3, userWalletId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                String content = "Add " + amount + " sucessfully to your wallet";
                addTransitionHistory(content, userWalletId);

                AuthenticationDAO authDAO = new AuthenticationDAO();
                User user = authDAO.getUserById(userId);
                // send mail
                String contentEmail = "You was added " + amount + " amount by admin. Anything missing please contact with us!";
                MailService.sendMailWithInfo(user.getEmail(), contentEmail);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    public int getWalletHistoryTotal(int walletId) {
        try {
            String sql = "SELECT COUNT(*) FROM TransitionHistory WHERE WalletId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, walletId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<TransitionHistory> getWalletHistory(int walletId, int index) {
        List<TransitionHistory> listHistory = new ArrayList();
        try {
            String sql = "SELECT * FROM TransitionHistory WHERE WalletId = ? ORDER BY CreateAt DESC  OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, walletId);
            ps.setInt(2, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
                TransitionHistory order = new TransitionHistory();
                order.setWalletId(rs.getInt("Id"));
                order.setContent(rs.getString("Content"));
                order.setCreateAt(rs.getString("CreateAt"));
                order.setWalletId(rs.getInt("WalletId"));
                listHistory.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHistory;
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

    public float getTotalWalletBalance() {
        float totalBalance = 0.0f;
        try {
            String sql = "SELECT SUM(Ammount) AS TotalBalance FROM UserWallet";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalBalance = rs.getFloat("TotalBalance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalBalance;
    }

    public List<UserWallet> getUserWallet() {
        List<UserWallet> listUser = new ArrayList<>();
        try {
            String sql = "SELECT uw.*, u.UserName, u.Email FROM UserWallet uw JOIN [User] u ON uw.UserId = u.UserId ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserWallet userWallet = new UserWallet();
                userWallet.setWalletId(rs.getInt("Id"));
                userWallet.setAmmount(rs.getFloat("Ammount"));
                userWallet.setWalletId(rs.getInt("UserId"));
                userWallet.setCreateAt(rs.getString("CreateAt"));
                userWallet.setUserId(rs.getInt("UserId"));

                User user = new User();
                user.setEmail(rs.getString("Email"));
                user.setUserName(rs.getString("UserName"));

                userWallet.setUser(user);
                listUser.add(userWallet);
            }
            return listUser;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }

    public boolean addFundToUserWallet(int userId, double ammount) {
        try {
            UserWallet userWallet = getUserWalletByUserId(userId);
            if (userWallet != null) {

                double newAmmount = userWallet.getAmmount() + ammount;
                String sql = "UPDATE UserWallet SET Ammount = ? WHERE UserId = ?";
                ps = con.prepareStatement(sql);
                ps.setDouble(1, newAmmount);
                ps.setInt(2, userId);
                int affectedRows = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reFundUserWallet(int userId, double ammount) {
        try {
            UserWallet userWallet = getUserWalletByUserId(userId);
            if (userWallet != null) {

                double newAmmount = userWallet.getAmmount() - ammount;
                if (newAmmount >= 0) {
                    String sql = "UPDATE UserWallet SET Ammount = ? WHERE UserId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setDouble(1, newAmmount);
                    ps.setInt(2, userId);
                    int affectedRows = ps.executeUpdate();
                    if(affectedRows > 0) {
                        return true;
                    }
                } else { 
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UserWalletDAO userwallet = new UserWalletDAO();
        userwallet.reFundUserWallet(19, 18000);
//        List<UserWallet> lusi = userwallet.getUserWallet();
//        for (UserWallet userWallet : lusi) {
//            System.out.println(userWallet.toString());
//        }
    }
}
