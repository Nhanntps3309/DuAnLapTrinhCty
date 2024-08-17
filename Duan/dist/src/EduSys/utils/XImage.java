/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.utils;

import EduSys.Entity.NhanVien;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImage {

//    /**
//     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
//     */
//    public static final Image APP_ICON;
//
//    static {
//// Tải biểu tượng ứng dụng
//        String file = "/src/EduSys/Image/fpt.ico";
//        APP_ICON = new ImageIcon(file).getImage();
//    }
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/EduSys/Image/fpt.png");
        return new ImageIcon(url).getImage();
    }

//    /**
//     * Sao chép file logo chuyên đề vào thư mục logo
//     *
//     * @param file là đối tượng file ảnh
//     * @return chép được hay khô ng
//     */
    public static boolean saveLogo(File file) {
        File dir = new File("logos");
// Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
// Copy vào thư mục logos (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
//    /**
//     * Sao chép file excel danh sach nguoi học vào folder storeFiles
//     * @param src là đối tượng file excel
//     */   
    public static File saveExel(File src){
        File dst = new File("storeFiles", src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return dst;
        } 
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//    /**
//     * Đọc hình ảnh logo chuyên đề
//     *
//     * @param fileName là tên file logo
//     * @return ảnh đọc được
//     */
    public static ImageIcon readLogo(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
//    /**
//     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
//     */
    public static NhanVien USER = null;

//    /**
//     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
//     */
    public static void logoff() {
        XImage.USER = null;
    }

//    /**
//     * Kiểm tra xem đăng nhập hay chưa
//     *
//     * @return đăng nhập hay chưa
//     */
    public static boolean authenticated() {
        return XImage.USER != null;
    }

   
}
