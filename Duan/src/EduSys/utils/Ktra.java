/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Ktra {
    public boolean kiemTraMa(String input) {
        if (input.trim().length() != 5) {
            return false;
        }
        return true;
    }

    public boolean kiemTraTen(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean kiemTraGmail(String input) {
        if (input.trim().length() - 10 < 3 || !input.trim().endsWith("@gmail.com")) {
            return false;
        }
        return true;
    }

    public boolean kiemTraNgaySinh(String dateString) {
        // Định dạng ngày/tháng/năm (dd/MM/yyyy)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            // Parse chuỗi thành Date
            Date date = dateFormat.parse(dateString);

            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean kiemTraCoTren16(String input) {
        String[] parts = input.trim().split("/");
        if (parts.length < 3) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int nam = Integer.parseInt(parts[2].toString());
        int namht = calendar.get(Calendar.YEAR);

        if ((parts[2].length() < 4) || (namht - nam) < 15) {
            return false;
        }
        return true;
    }

    public boolean kiemTraSDT(String input) {
        // Kiểm tra từng ký tự, xem có phải là số không
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        if (input.length() < 9) {
            return false;
        }

        return true;
    }

    public boolean MaCD(String input) {
        if (input.trim().length() != 5) {
            return false;
        }
        return true;
    }
}
