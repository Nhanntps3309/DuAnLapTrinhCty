/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.DAO;

import EduSys.Entity.NhanVien;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
       List<NhanVien> ls = new ArrayList<>();
    public boolean checkLogin(String username, String pass) {
        for (NhanVien u : ls) {
            if (u.getMaNV().equals(username) && u.getMatKhau().equals(pass)) {

                return true;
            }
        }

        return false;
    }
}
