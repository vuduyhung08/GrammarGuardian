/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptString {
    
    // định nghĩa biến hexArray -> chuyển đổi các byte thành dạng chuỗi hexa
    final protected static char[] hexArray = "0123456789ABCDEF"
            .toCharArray();
    
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

// Thêm vào trước password 1 đoạn chuỗi nào đó để nó phức tạp hơn
// Có hacker xây dựng trước bộ database đã mã hóa sẵn nó sẽ tấn cống được
    private static String SALT = "123456";
    
// A password hashing method.
    public static String hashPassword(String in) {
        try {
           // Khởi tạo đối tượng MessageDigest với thuật toán SHA-256.
            MessageDigest md = MessageDigest
                    .getInstance("SHA-256");
            //Cập nhật đối tượng MessageDigest với SALT.
            md.update(SALT.getBytes());        // <-- Đặt trước
            //Cập nhật đối tượng MessageDigest với chuỗi đầu vào.
            md.update(in.getBytes());
            // md.update(SALT.getBytes());     // <-- Hoặc, đặt sau 
            
            // Thực hiện hàm băm để lấy kết quả dưới dạng mảng byte.
            byte[] out = md.digest();
            
            //Chuyển đổi mảng byte thành chuỗi hexa.
            return bytesToHex(out);            // <-- Return the Hex Hash.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void main(String[] args) {
        System.out.println(hexArray);
    }
}
