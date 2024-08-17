    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package EduSys.ui;

import EduSys.DAO.NhanVienDAO;
import EduSys.utils.Auth;
import EduSys.utils.MsgBox;
import EduSys.utils.XImage;


public class ÐoiMatKhauJDialog extends javax.swing.JDialog {

    
    public ÐoiMatKhauJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        getTaiKhoan();
        setTitle("ĐỔI MẬT KHẨU");
    }
    NhanVienDAO dao = new NhanVienDAO();
    
    private void getTaiKhoan(){
        
        txtTenDN.setEditable(false);
        System.out.println("1");
        if(Auth.user == null){
            return;
        }
        txtTenDN.setText(Auth.user.getMaNV());
        System.out.println("2");
    }
    private void doiMK(){
        String tenÐN = txtTenDN.getText();
        String MKHT = new String(txtMKHienTai.getPassword());
        String MKMoi = new String(txtMKMoi.getPassword());
        String XNMKMoi = new String(this.XNMKMoi.getPassword());
        
        if(!tenÐN.equalsIgnoreCase(Auth.user.getMaNV())){
            MsgBox.alert(this, "Sai ten ÐN!");
        }
        else if(!MKHT.equals(Auth.user.getMatKhau())){
            MsgBox.alert(this, "Sai MK!");
        }
        else if(!MKMoi.equals(XNMKMoi)){
            MsgBox.alert(this, "Xac nhan mat khau khong dung");
        }
        else{
            Auth.user.setMatKhau(MKMoi);
            dao.update(Auth.user);
            MsgBox.alert(this, "Ðôi mat khau thanh công");
        }
    }
    private void huyBo(){
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnÐongY = new javax.swing.JButton();
        btnHuyBo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtMKHienTai = new javax.swing.JPasswordField();
        txtMKMoi = new javax.swing.JPasswordField();
        XNMKMoi = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Đổi Mật Khẩu");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Tên đăng nhập");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Mật khẩu mới");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mật khẩu hiện tại");

        btnÐongY.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnÐongY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EduSys/Image/Refresh.png"))); // NOI18N
        btnÐongY.setText("Đồng ý");
        btnÐongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnÐongYActionPerformed(evt);
            }
        });

        btnHuyBo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHuyBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EduSys/Image/No.png"))); // NOI18N
        btnHuyBo.setText("Hủy bỏ ");
        btnHuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyBoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Xác nhận mật khẩu mới");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(txtTenDN, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtMKMoi))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(txtMKHienTai, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(XNMKMoi)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnÐongY)
                        .addGap(59, 59, 59)
                        .addComponent(btnHuyBo)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMKHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XNMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnÐongY, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnÐongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnÐongYActionPerformed
        // TODO add your handling code here:
        doiMK();
    }//GEN-LAST:event_btnÐongYActionPerformed

    private void btnHuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyBoActionPerformed
        // TODO add your handling code here:
        huyBo();
    }//GEN-LAST:event_btnHuyBoActionPerformed

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
            java.util.logging.Logger.getLogger(ÐoiMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ÐoiMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ÐoiMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ÐoiMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ÐoiMatKhauJDialog dialog = new ÐoiMatKhauJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField XNMKMoi;
    private javax.swing.JButton btnHuyBo;
    private javax.swing.JButton btnÐongY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtMKHienTai;
    private javax.swing.JPasswordField txtMKMoi;
    private javax.swing.JTextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}
