/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author Datnt
 */
public class OtpService {

    public static String genarateOtp() {
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = (int) (numbers.length() * Math.random());
            sb.append(numbers.charAt(index));
        }
        return sb.toString();
    }
}
