/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Permission;
import Model.PostPackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackageDAO extends DBContext {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public PackageDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PostPackage> getAllPackagesHomePage() {
        List<PostPackage> packages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Package WHERE Status = 'TRUE'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PostPackage p = new PostPackage();
                p.setId(rs.getInt("Id"));
                p.setDescription(rs.getString("Description"));
                p.setTitle(rs.getString("Title"));
                p.setPrice(rs.getFloat("Price"));
                p.setLimitText(rs.getInt("LimitText"));
                p.setCheckTime(rs.getInt("CheckTime"));
                p.setStatus(rs.getBoolean("Status"));
                p.setCreateAt(rs.getString("CreateAt"));
                p.setUpdateAt(rs.getString("UpdateAt"));
                packages.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packages;
    }

    public List<PostPackage> getAllPackages() {
        List<PostPackage> packages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Package  ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PostPackage p = new PostPackage();
                p.setId(rs.getInt("Id"));
                p.setDescription(rs.getString("Description"));
                p.setTitle(rs.getString("Title"));
                p.setPrice(rs.getFloat("Price"));
                p.setLimitText(rs.getInt("LimitText"));
                p.setCheckTime(rs.getInt("CheckTime"));
                p.setStatus(rs.getBoolean("Status"));
                p.setCreateAt(rs.getString("CreateAt"));
                p.setUpdateAt(rs.getString("UpdateAt"));
                packages.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packages;
    }

    public boolean addPackage(PostPackage p) {
        try {
            String sql = "INSERT INTO Package (Description, Title, Price, LimitText, CheckTime, Status, CreateAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDescription());
            ps.setString(2, p.getTitle());
            ps.setFloat(3, p.getPrice());
            ps.setInt(4, p.getLimitText());
            ps.setInt(5, p.getCheckTime());
            ps.setBoolean(6, true);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(7, currentDate);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePackage(PostPackage p) {
        try {
            String sql = "SELECT * FROM Package WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            rs = ps.executeQuery();
            boolean status = false;
            if (rs.next()) {
                status = rs.getBoolean("Status");
            }
            sql = "UPDATE Package SET Status = ?, UpdateAt = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, !status);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(2, currentDate);
            ps.setInt(3, p.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePackage(int packageId) {
        try {
            String sql = "DELETE FROM Package WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, packageId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addPermission(int userId, PostPackage packge) {
        try {
            String sql = "INSERT INTO Permission (UserId, PackageId, LimitText, CheckTime) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, packge.getId());    
            ps.setInt(3, packge.getLimitText());
            ps.setInt(4, packge.getCheckTime());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Permission getPermissionsByUserId(int userId) {
        try {
            String sql = "SELECT * FROM Permission WHERE UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Permission p = new Permission();
                p.setId(rs.getInt("Id"));
                p.setUserId(rs.getInt("UserId"));
                p.setPackageId(rs.getInt("PackageId"));    
                p.setCheckTime(rs.getInt("CheckTime"));
                p.setLimitText(rs.getInt("LimitText"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
       public boolean updateUserPermission(int userId, int checkTimeRemains) {
        try {
            String sql = "UPDATE Permission SET CheckTime = ?  WHERE UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, checkTimeRemains);
            ps.setInt(2, userId);    
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PostPackage getPackageById(int packageId) {
        try {
            String sql = "SELECT * FROM [Package] WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, packageId);
            rs = ps.executeQuery();
            if (rs.next()) {
                PostPackage postPackage = new PostPackage();
                postPackage.setId(rs.getInt("Id"));
                postPackage.setCheckTime(rs.getInt("CheckTime"));
                postPackage.setTitle(rs.getString("Title"));
                postPackage.setDescription(rs.getString("Description"));
                postPackage.setLimitText(rs.getInt("LimitText"));
                postPackage.setPrice(rs.getFloat("Price"));
                return postPackage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasPackagePermission(int userId, int packageId) {
        try {
            String sql = "SELECT * FROM Permission WHERE UserId = ? AND PackageId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, packageId);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        PackageDAO packgeDAO = new PackageDAO();
        List<PostPackage> list = packgeDAO.getAllPackagesHomePage();
        System.out.println("list" + list.size());

    }
}
