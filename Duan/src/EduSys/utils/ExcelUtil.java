/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.utils;

import EduSys.DAO.ThongKeDAO;
import EduSys.Entity.KhoaHoc;
import EduSys.Entity.NguoiHoc;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExcelUtil {

    public static Workbook printBangDiemKhoaHocToExcel(javax.swing.JTable tblBangDiem,
            javax.swing.JComboBox<String> cbKhoaHoc, ThongKeDAO tkDAO) throws FileNotFoundException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Academic Transcript");

        KhoaHoc kh = (KhoaHoc) cbKhoaHoc.getSelectedItem();
        List<Object[]> list = tkDAO.getBangDiem(kh.getMaKH());

        int rownum = 0;
        Cell cell;
        Row row;
        // 
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        //MaNH
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Student ID");
        cell.setCellStyle(style);

        //Họ Tên
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Full Name");
        cell.setCellStyle(style);

        //Điểm 
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue("Point");
        cell.setCellStyle(style);

        //Xếp loại
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Classfication");
        cell.setCellStyle(style);

        //DATA 
        for (int i = 0; i < list.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);

            //ID Student
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue((String) tblBangDiem.getValueAt(i, 0));

            //FULL NAME
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue((String) tblBangDiem.getValueAt(i, 1));

            //POINT
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue((Double) tblBangDiem.getValueAt(i, 2));

            //CLASSIFICATION
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue((String) tblBangDiem.getValueAt(i, 3));

        }
        return workbook;

    }

    public static Workbook printBangDiemChuyenDeToExcel(javax.swing.JTable tblBangDiem,
            ThongKeDAO tkDAO) throws FileNotFoundException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Tổng hợp điểm");

        List<Object[]> list = tkDAO.getDiemTheoChuyenDe();

        int rownum = 0;
        Cell cell;
        Row row;
        // 
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        //Chuyên đề
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Chuyên đề");
        cell.setCellStyle(style);

        //Tổng số học viên
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue("Tổng số học viên");
        cell.setCellStyle(style);

        //Cao nhất
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue("Cao nhất");
        cell.setCellStyle(style);

        //Thấp nhất
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue("Thấp nhất");
        cell.setCellStyle(style);

        //Điểm TB
        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue("Điểm TB");
        cell.setCellStyle(style);

        //DATA 
        for (int i = 0; i < list.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);

            //Chuyên đề
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue((String) tblBangDiem.getValueAt(i, 0));

            //Tổng số học sinh
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue((Integer) tblBangDiem.getValueAt(i, 1));

            //Cao nhất
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue((Double) tblBangDiem.getValueAt(i, 2));

            //Thấp nhất
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue((Double) tblBangDiem.getValueAt(i, 3));

            //Điểm TB
            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue((Double) tblBangDiem.getValueAt(i, 4));
        }
        return workbook;

    }

    public static Workbook printDoanhThuToExcel(javax.swing.JTable tblBangDiem,
            javax.swing.JComboBox<String> cboNam, ThongKeDAO tkDAO) throws FileNotFoundException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Xếp học lực");
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> list = tkDAO.getDoanhThu(nam);

//        KhoaHoc kh = (KhoaHoc) cboNam.getSelectedItem();
//        SimpleDateFormat sim = new SimpleDateFormat("yyyy");
//        int year = Integer.parseInt(sim.format(kh.getNgayKG()));
//  
        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        //ChuyenDe
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Chuyên đề");
        cell.setCellStyle(style);

        //SoKhoa
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue("Số khóa");
        cell.setCellStyle(style);

        //Số HV
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue("Số học viên");
        cell.setCellStyle(style);

        //Doanh thu
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Doanh thu");
        cell.setCellStyle(style);

        //HocPhanCaoNhat
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Học phần cao nhất");
        cell.setCellStyle(style);

        //HocPhanThapNhat
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Học phần thấp nhất");
        cell.setCellStyle(style);

        //HocPhanTB
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Học phần trung bình");
        cell.setCellStyle(style);

        //DATA 
        for (int i = 0; i < list.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);

            //ID Student
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue((String) tblBangDiem.getValueAt(i, 0));

            //FULL NAME
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue((Integer) tblBangDiem.getValueAt(i, 1));

            //POINT
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue((Integer) tblBangDiem.getValueAt(i, 2));

            //CLASSIFICATION
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue((Double) tblBangDiem.getValueAt(i, 3));

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue((Double) tblBangDiem.getValueAt(i, 4));

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue((Double) tblBangDiem.getValueAt(i, 5));

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue((Double) tblBangDiem.getValueAt(i, 6));

        }
        return workbook;

    }

    public static Workbook printNguoiHocToExcel(javax.swing.JTable tblBangDiem,
            ThongKeDAO tkDAO) throws FileNotFoundException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Người học");

        List<Object[]> list = tkDAO.getNguoiHoc();
        int rownum = 0;
        Cell cell;
        Row row;
        //  
        HSSFCellStyle style = createStyleForTitle(workbook);
        row = sheet.createRow(rownum);

        //MaNH
        cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue("Năm");
        cell.setCellStyle(style);

        //Họ Tên
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue("Số người học");
        cell.setCellStyle(style);

//        Điểm 
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Đầu tiên");
        cell.setCellStyle(style);

        //Xếp loại
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Sau cùng");
        cell.setCellStyle(style);

        //DATA 
        for (int i = 0; i < list.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);

            //ID Student
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue((Integer) tblBangDiem.getValueAt(i, 0));

            //FULL NAME
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue((Integer) tblBangDiem.getValueAt(i, 1));

            //POINT
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue((String) tblBangDiem.getValueAt(i, 2));

            //CLASSIFICATION
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue((String) tblBangDiem.getValueAt(i, 3));

        }
        return workbook;
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

}
