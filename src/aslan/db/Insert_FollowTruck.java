package aslan.db;

//@author javanshir, rufan
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Insert_FollowTruck extends javax.swing.JFrame {

    public Insert_FollowTruck() {
        initComponents();
        setLocationRelativeTo(null);
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/db.png"));
        List<FollowTruckPojo> model = AslanDB.findAd();
        HashSet hs = new HashSet();
        ArrayList al = new ArrayList();
        model.stream().forEach((model1) -> {
            hs.add(model1.getAd());
        });
        al.addAll(hs);
        al.stream().forEach((al1) -> {
            comboAd.addItem(al1);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblAd = new javax.swing.JLabel();
        txtAd = new javax.swing.JTextField();
        lblTarix = new javax.swing.JLabel();
        txtTarix = new javax.swing.JTextField();
        lblIlkinKM = new javax.swing.JLabel();
        txtIlkinKM = new javax.swing.JTextField();
        txtYekunKM = new javax.swing.JTextField();
        lblYekunKM = new javax.swing.JLabel();
        txtMiqdar = new javax.swing.JTextField();
        txtQiymat = new javax.swing.JTextField();
        txtKurs = new javax.swing.JTextField();
        lblMiqdar = new javax.swing.JLabel();
        lblQiymat = new javax.swing.JLabel();
        lblKurs = new javax.swing.JLabel();
        btnInsert = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        comboAd = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAd.setText("Sürücü adı");

        lblTarix.setText("Tarix");

        lblIlkinKM.setText("İlkin KM");

        lblYekunKM.setText("Yekun KM");

        lblMiqdar.setText("Miqdar");

        lblQiymat.setText("Qiymət");

        lblKurs.setText("Kurs");

        btnInsert.setText("Daxil et");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnCancel.setText("Bağla");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAd)
                                    .addComponent(lblTarix))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(lblIlkinKM))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtAd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboAd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTarix, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIlkinKM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblYekunKM)
                                    .addComponent(txtYekunKM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMiqdar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMiqdar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQiymat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQiymat))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKurs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKurs)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnInsert)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAd)
                    .addComponent(txtAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTarix)
                    .addComponent(lblIlkinKM)
                    .addComponent(lblYekunKM)
                    .addComponent(lblMiqdar)
                    .addComponent(lblQiymat)
                    .addComponent(lblKurs))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTarix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIlkinKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYekunKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMiqdar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQiymat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKurs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        FollowTruckPojo t = new FollowTruckPojo();
        t.setId(0);
        if ("".equals(txtTarix.getText())) {
            t.setTarix(null);
        } else {
            t.setTarix(txtTarix.getText());
        }
        if ("".equals(txtIlkinKM.getText())) {
            t.setIlkinKM(0.0);
        } else {
            t.setIlkinKM(Double.parseDouble(txtIlkinKM.getText()));
        }
        if ("".equals(txtYekunKM.getText())) {
            t.setYekunKM(0.0);
        } else {
            t.setYekunKM(Double.parseDouble(txtYekunKM.getText()));
        }
        t.setFerq(t.getYekunKM() - t.getIlkinKM());
        if ("".equals(txtMiqdar.getText())) {
            t.setMiqdar(0.0);
        } else {
            t.setMiqdar(Double.parseDouble(txtMiqdar.getText()));
        }
        if ("".equals(txtQiymat.getText())) {
            t.setQiymet_man(0.0);
        } else {
            t.setQiymet_man(Double.parseDouble(txtQiymat.getText()));
        }
        if ("".equals(txtKurs.getText())) {
            t.setKurs(0.0);
        } else {
            t.setKurs(Double.parseDouble(txtKurs.getText()));
        }
        if (t.getKurs() != 0.0) {
            t.setYekun_dol((t.getMiqdar() * t.getQiymet_man()) / t.getKurs());
        } else {
            t.setYekun_dol((t.getMiqdar() * t.getQiymet_man()) / 1);
        }
        t.setYekun_man(t.getMiqdar() * t.getQiymet_man());
        if (!("".equals(txtAd.getText()))) {
            t.setAd(txtAd.getText().toUpperCase());
        } else if (!("".equals(comboAd.getSelectedItem().toString()))) {
            t.setAd(comboAd.getSelectedItem().toString());
        } else {
            t.setAd(null);
        }
        AslanDB.followTruck_Insert(t);
        MainWindow_FollowTruck.visible(t.getAd());
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        new MainWindow_FollowTruck().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insert_FollowTruck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Insert_FollowTruck().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnInsert;
    private javax.swing.JComboBox comboAd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAd;
    private javax.swing.JLabel lblIlkinKM;
    private javax.swing.JLabel lblKurs;
    private javax.swing.JLabel lblMiqdar;
    private javax.swing.JLabel lblQiymat;
    private javax.swing.JLabel lblTarix;
    private javax.swing.JLabel lblYekunKM;
    private javax.swing.JTextField txtAd;
    private javax.swing.JTextField txtIlkinKM;
    private javax.swing.JTextField txtKurs;
    private javax.swing.JTextField txtMiqdar;
    private javax.swing.JTextField txtQiymat;
    private javax.swing.JTextField txtTarix;
    private javax.swing.JTextField txtYekunKM;
    // End of variables declaration//GEN-END:variables
}
