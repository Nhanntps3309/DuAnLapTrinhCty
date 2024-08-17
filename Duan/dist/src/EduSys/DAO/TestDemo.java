/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.DAO;

import EduSys.Entity.NhanVien;


public class TestDemo {
    public static void main(String[] args) {
        NhanVienDAO nvDAO = new NhanVienDAO();
        NhanVien nv = new NhanVien();
        nv.setMaNV("nv01");
        nv.setHoTen("nhan");
        nv.setMatKhau("123");
        nv.setVaiTro(true);
        nvDAO.insert(nv);
        System.out.println("chon nv1 thành công!");
    }
    
}
