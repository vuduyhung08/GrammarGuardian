/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author dangnqhe181760
 */
public class EncryptString {

    final protected static char[] hexArray = "0123456789ABCDEF"
            .toCharArray();

    private static String bytesToHex(byte[] bytes) {
        // Tạo một mảng char để lưu trữ chuỗi hex, độ dài gấp đôi độ dài của mảng byte.
        char[] hexChars = new char[bytes.length * 2];
        int v;
        // Duyệt qua từng byte trong mảng byte đầu vào.
        for (int j = 0; j < bytes.length; j++) {
           // Lấy giá trị byte thứ j và thực hiện AND với 0xFF để đảm bảo giá trị dương.
            v = bytes[j] & 0xFF;
           // Lấy phần cao 4 bit của v và lấy index tương ứng trong mảng hexArray để lưu vào hexChars.
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

// Change this to something else.
    private static String SALT = "123456";

// A password hashing method.
    public static String hashPassword(String in) {
        try {
            //khởi tạo một đối tượng MessageDigest với thuật toán băm SHA-256.
            MessageDigest md = MessageDigest
                    .getInstance("SHA-256");
            md.update(SALT.getBytes());        // <-- Prepend SALT.
            md.update(in.getBytes());
            // md.update(SALT.getBytes());     // <-- Or, append SALT.

            byte[] out = md.digest();
            return bytesToHex(out);            // <-- Return the Hex Hash.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
