/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.User;
import Model.UserWallet;
import Model.UserWalletOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO extends DBContext {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public DashboardDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalUsers() {
        try {
            String sql = "SELECT COUNT(*) FROM [User] WHERE RoleId != 2";
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

    public int getTotalPendingPosts() {
        try {
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 1";
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

    public int getTotalConfirmedPosts() {
        try {
            String sql = "SELECT COUNT(*) FROM [Post] WHERE Status = 3";
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

    public float getTotalWalletBalance() {
        try {
            String sql = "SELECT SUM(Ammount) AS TotalBalance FROM UserWallet";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getFloat("TotalBalance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    public List<UserWalletOrder> getMonthlyWalletOrders(int month, int year) {
        List<UserWalletOrder> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM UserWalletOrder WHERE MONTH(CreateAt) = ? AND YEAR(CreateAt) = ? AND Status = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserWalletOrder order = new UserWalletOrder();
                order.setId(rs.getInt("Id"));
                order.setUserWalletId(rs.getInt("UserWalletId"));
                order.setAmmount(rs.getFloat("Ammount"));
                order.setCreateAt(rs.getString("CreateAt"));
                order.setStatus(rs.getInt("Status"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}
