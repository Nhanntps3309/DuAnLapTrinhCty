/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package EduSys.ui;

import EduSys.DAO.ChuyenDeDAO;
import java.sql.*;
import EduSys.DAO.HocVienDAO;
import EduSys.DAO.KhoaHocDAO;
import EduSys.DAO.NguoiHocDAO;
import EduSys.Entity.ChuyenDe;
import EduSys.Entity.HocVien;
import EduSys.Entity.KhoaHoc;
import EduSys.Entity.NguoiHoc;
import static EduSys.ui.MainJFrame.logger;
import EduSys.utils.JDBC;
import EduSys.utils.MsgBox;
import EduSys.utils.XImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class HocVienJFrame extends javax.swing.JFrame {
//public Integer MaKH = -99;

//    public HocVienJFrame(Integer MaKH) {
//        initComponents();
//        setLocationRelativeTo(null);
//        this.MaKH = MaKH;
//
//    }
    public Integer MaKH;
    HocVienDAO dao = new HocVienDAO();
    NguoiHocDAO nhdao = new NguoiHocDAO();
    KhoaHocDAO kdao = new KhoaHocDAO();
    ChuyenDeDAO cddao = new ChuyenDeDAO();

    public HocVienJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Quản Lý Học Viên");
        setIconImage(XImage.getAppIcon());
        // this.MaKH = MaKH;

    }

    public HocVienJFrame(Integer id) {
        initComponents();
        setLocationRelativeTo(null);
        this.MaKH = id;
         setTitle("Quản Lý Học Viên");
        setIconImage(XImage.getAppIcon());

    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNguoiHoc.getModel();
        model.removeAllElements();
        try {
            List<NguoiHoc> list = nhdao.selectByCourse(MaKH);
            for (NguoiHoc nh : list) {
                model.addElement(nh);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn học viên!");
        }
    }

//    void fillGridView() {
//        DefaultTableModel model = (DefaultTableModel) tblHocVien.getModel();
//        model.setRowCount(0);
//        try {
//            String sql = "SELECT hv.*, nh.HoTen FROM HocVien hv "
//                    + " JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH WHERE MaKH=?";
//            ResultSet rs = JDBC.executeQuery(sql, MaKH);
//            while (rs.next()) {
//                double diem = rs.getDouble("Diem");
//                Object[] row = {
//                    rs.getInt("MaHV"), rs.getString("MaNH"),
//                    rs.getString("HoTen"), diem,
//                    false
//                };
//                model.addRow(row);
//                if (rdoTatCa.isSelected()) {
//                    model.addRow(row);
//                } else if (rdoDaNhapDiem.isSelected() && diem >= 0) {
//                    model.addRow(row);
//                } else if (rdoChuaNhapDiem.isSelected() && diem < 0) {
//                    model.addRow(row);
//                }
//            }
//        } catch (SQLException e) {
//            MsgBox.alert(this, "Lỗi truy vấn học viên!");
//        }
//    }
//   
    void fillGridView() {
        DefaultTableModel model = (DefaultTableModel) tblHocVien.getModel();
        model.setRowCount(0);
        String sql = "SELECT hv.*, nh.HoTen FROM HocVien hv "
                + " JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH WHERE MaKH=?";
        //   ResultSet rs = JDBC.executeQuery(sql, MaKH);
        List<HocVien> hv = new ArrayList<>();
        hv = dao.select();
        for (HocVien hocVien : hv) {
            if (hocVien.getMaKH() != this.MaKH && this.MaKH != -99) {
                continue;
            }
            NguoiHoc nhh = nhdao.findById(hocVien.getMaNH());
            model.addRow(new Object[]{hocVien.getMaHV(), hocVien.getMaNH(),
                nhh.getHoTen(), hocVien.getDiem()});

        }

    }

    void insert() {
        NguoiHoc nguoiHoc = (NguoiHoc) cboNguoiHoc.getSelectedItem();
        HocVien model = new HocVien();
        model.setMaKH(MaKH);
        model.setMaNH(nguoiHoc.getMaNH());
        model.setDiem(Double.valueOf(txtDiem.getText()));
        try {
            dao.insert(model);
            this.fillComboBox();
            this.fillGridView();
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi thêm học viên vào khóa học!");
        }
    }

    void fillComboBox2() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboChuyenDe.getModel();
        model.removeAllElements();
        KhoaHoc kh = kdao.findById(MaKH);
        ChuyenDe cd = cddao.findById(kh.getMaCD());
        model.addElement((cd.getTenCD() + " " + kh.getNgayTao()));
        //  System.out.println(cd.getTenCD()+" "+kh.getNgayTao());

    }

    void update() {
        for (int i = 0; i < tblHocVien.getRowCount(); i++) {
            Integer mahv = (Integer) tblHocVien.getValueAt(i, 0);
            String manh = (String) tblHocVien.getValueAt(i, 1);
            Double diem = (Double) tblHocVien.getValueAt(i, 3);
            Boolean isDelete = (Boolean) tblHocVien.getValueAt(i, 4);
          //  HocVien hv = dao.findById(mahv);
            if (isDelete == null) {
                HocVien model = new HocVien();
                model.setMaHV(mahv);
                model.setDiem(diem);
                dao.update(model);
                continue;
            }
            dao.delete(mahv);
            //Ghi nhận log khi nhân viên cập nhật điểm học viên
//            if(hv.getDiem() != diem){
//                logger.info("Cập nhật điểm MaChuyenDe: " + KhoaHoc.getMaCD() + " MaKhoaHoc: " + khoaHoc.getMaKH() 
//                        + " MaHocVien: " + hv.getMaHV() + " điểm hiện tại: " + hv.getDiem() 
//                        + " điểm mới: " + diem);
//                hv.setDiem(diem);
//                hvdao.update(hv);
//                
//            }

        }
        this.fillComboBox();
        this.fillGridView();
        MsgBox.alert(this, "Cập nhật thành công!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        cboNguoiHoc = new javax.swing.JComboBox<>();
        txtDiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHocVien = new javax.swing.JTable();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoDaNhapDiem = new javax.swing.JRadioButton();
        rdoChuaNhapDiem = new javax.swing.JRadioButton();
        btnCapNhat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cboChuyenDe = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "HỌC VIÊN KHÁC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        cboNguoiHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtDiem.setBackground(new java.awt.Color(255, 204, 204));
        txtDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(153, 255, 153));
        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EduSys/Image/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(cboNguoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnThem)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNguoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "HỌC VIÊN TRONG KHÓA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ HV", "MÃ NH", "HO VÀ TÊN", "ÐIÊM", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHocVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHocVienMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblHocVien);

        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaNhapDiem);
        rdoDaNhapDiem.setText("Đã nhập điểm");
        rdoDaNhapDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaNhapDiemActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoChuaNhapDiem);
        rdoChuaNhapDiem.setText(" Chưa nhập điểm");
        rdoChuaNhapDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuaNhapDiemActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 153));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EduSys/Image/Edit.png"))); // NOI18N
        btnCapNhat.setText("Câp Nhât");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(rdoTatCa)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDaNhapDiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoChuaNhapDiem)
                        .addGap(267, 267, 267)
                        .addComponent(btnCapNhat))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat)
                    .addComponent(rdoChuaNhapDiem)
                    .addComponent(rdoTatCa)
                    .addComponent(rdoDaNhapDiem))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "KHÓA HOC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        cboChuyenDe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChuyenDeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(cboChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(cboChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblHocVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHocVienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHocVienMouseClicked

    private void rdoTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTatCaActionPerformed
        // TODO add your handling code here:
        this.fillGridView();
    }//GEN-LAST:event_rdoTatCaActionPerformed

    private void rdoDaNhapDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaNhapDiemActionPerformed
        // TODO add your handling code here:
        this.fillGridView();
    }//GEN-LAST:event_rdoDaNhapDiemActionPerformed

    private void rdoChuaNhapDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChuaNhapDiemActionPerformed
        // TODO add your handling code here:
        this.fillGridView();
    }//GEN-LAST:event_rdoChuaNhapDiemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.fillComboBox();
        this.fillGridView();
        this.fillComboBox2();

    }//GEN-LAST:event_formWindowOpened

    private void txtDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemActionPerformed

    private void cboChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChuyenDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChuyenDeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HocVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HocVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HocVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HocVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HocVienJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChuyenDe;
    private javax.swing.JComboBox<String> cboNguoiHoc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JRadioButton rdoChuaNhapDiem;
    private javax.swing.JRadioButton rdoDaNhapDiem;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JTable tblHocVien;
    private javax.swing.JTextField txtDiem;
    // End of variables declaration//GEN-END:variables
}
//    HocVienDAO dao = new HocVienDAO();
//    NguoiHocDAO nhdao = new NguoiHocDAO();
//    KhoaHocDAO khdao = new KhoaHocDAO();
//    ChuyenDeDAO cddao = new ChuyenDeDAO();
//

//
//    void fillComboBox() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNguoiHoc.getModel();
//        model.removeAllElements();
//        try {
//            List<NguoiHoc> list = nhdao.selectByCourse(MaKH);
//            for (NguoiHoc nh : list) {
//
//                model.addElement(nh);
//            }
//        } catch (Exception e) {
//            MsgBox.alert(this, "Lỗi truy vấn học viên!");
//        }
//    }
////    void fillComboBoxKH(){
////        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhoaHoc.getModel();
////        model.removeAllElements();
////        Ch
////    }
//
//    void fillGridView() {
//        DefaultTableModel model = (DefaultTableModel) tblHocVien.getModel();
//        model.setRowCount(0);
//        String sql = "SELECT hv.*, nh.HoTen FROM HocVien hv "
//                + " JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH WHERE MaKH=?";
//        //   ResultSet rs = JDBC.executeQuery(sql, MaKH);
//        List<HocVien> hv = new ArrayList<>();
//        hv = dao.select();
//        for (HocVien hocVien : hv) {
//            if (hocVien.getMaKH() != this.MaKH && this.MaKH != -99) {
//                continue;
//            }
//            NguoiHoc nhh = nhdao.findById(hocVien.getMaNH());
//            model.addRow(new Object[]{hocVien.getMaHV(), hocVien.getMaNH(),
//                nhh.getHoTen(), hocVien.getDiem()});
//
//        }
//
//    }
//
//    void insert() {
//        NguoiHoc nguoiHoc = (NguoiHoc) cboNguoiHoc.getSelectedItem();
//        HocVien model = new HocVien();
//        model.setMaKH(MaKH);
//        model.setMaNH(nguoiHoc.getMaNH());
//        model.setDiem(Double.valueOf(txtDiem.getText()));
//        try {
//            dao.insert(model);
//            this.fillComboBox();
//            this.fillGridView();
//        } catch (Exception e) {
//            MsgBox.alert(this, "Lỗi thêm học viên vào khóa học!");
//        }
//    }
//
//    void update() {
//        for (int i = 0; i < tblHocVien.getRowCount(); i++) {
//            Integer mahv = (Integer) tblHocVien.getValueAt(i, 0);
//            String manh = (String) tblHocVien.getValueAt(i, 1);
//            Double diem = (Double) tblHocVien.getValueAt(i, 3);
//            Boolean isDelete = (Boolean) tblHocVien.getValueAt(i, 4);
//            if (isDelete) {
//                dao.delete(mahv);
//            } else {
//                HocVien model = new HocVien();
//                model.setMaHV(mahv);
//                model.setMaKH(MaKH);
//                model.setMaNH(manh);
//                model.setDiem(diem);
//                dao.update(model);
//            }
//        }
//        this.fillComboBox();
//        this.fillGridView();
//        MsgBox.alert(this, "Cập nhật thành công!");
    //    }
