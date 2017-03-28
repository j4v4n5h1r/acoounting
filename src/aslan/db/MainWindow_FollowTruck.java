package aslan.db;

//@author javanshir, rufan
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.table.DatePickerCellEditor;
import salary.RXTable;

public final class MainWindow_FollowTruck extends javax.swing.JFrame {

    public static String surucuAdi;
    private FollowTruckTableModel tableModel;
    public int selectedRow;
    private String selectedItem;
    public TableColumn date;

    public static void visible(String text) {
        surucuAdi = text;
        new MainWindow_FollowTruck().setVisible(true);
    }

    public RXTable autoResizeColWidth(RXTable table, FollowTruckTableModel model) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(model);
        int margin = 5;
        for (int i = 0; i < table.getColumnCount(); i++) {
            int vColIndex = i;
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(vColIndex);
            int width;
            TableCellRenderer renderer = col.getHeaderRenderer();
            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
            width = comp.getPreferredSize().width;
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, vColIndex);
                comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, vColIndex), false, false,
                        r, vColIndex);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            width += 2 * margin;
            col.setPreferredWidth(width);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(
                SwingConstants.LEFT);
        table.getTableHeader().setReorderingAllowed(false);
        return table;
    }

    public void refresh() {
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
        if ((surucuAdi == null) || ("".equals(surucuAdi))) {
            surucuAdi = (String) comboAd.getItemAt(0);
        }
        comboAd.setSelectedItem(surucuAdi);
        selectedItem = (String) comboAd.getSelectedItem();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tableModel = new FollowTruckTableModel(AslanDB.followTruck_Find(selectedItem)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                selectedRow = tableModel.getTopic(row).getId();
                return true;
            }
        };
        table = autoResizeColWidth(table, tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(10).setMinWidth(0);
        table.getColumnModel().getColumn(10).setMaxWidth(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
        DatePickerCellEditor datePicker = new DatePickerCellEditor(formatter);
        datePicker.setFormats(formatter);
        date = table.getColumnModel().getColumn(1);
        date.setCellEditor(datePicker);
        table = new RXTable(tableModel);
        JScrollPane pane = new JScrollPane(table);
        add(pane);
        setLocationRelativeTo(null);
        comboAd.addActionListener((ActionEvent e) -> {
            String selected = (String) comboAd.getSelectedItem();
            visible(selected);
            setVisible(false);
        });
        table.getModel().addTableModelListener((TableModelEvent e) -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model1 = (TableModel) e.getSource();
            String columnName = model1.getColumnName(column);
            Object value = model1.getValueAt(row, column);
            List<FollowTruckPojo> list = AslanDB.followTruck_Find(selectedItem);
            FollowTruckPojo data = list.get(row);
            switch (column) {
                case 0:
                    data.setId((int) value);
                    break;
                case 1:
                    try {
                        String time = value.toString();
                        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM");
                        Date date4 = format2.parse(time);
                        SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM");
                        data.setTarix(format3.format(date4));
                    } catch (NumberFormatException | IndexOutOfBoundsException | ParseException ex) {
                        Logger.getLogger(MainWindow_Expense.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    data.setIlkinKM((Double) value);
                    data.setFerq(data.getYekunKM() - data.getIlkinKM());
                    break;
                case 3:
                    data.setYekunKM((Double) value);
                    data.setFerq(data.getYekunKM() - data.getIlkinKM());
                    break;
                case 4:
                    data.setFerq((Double) value);
                    break;
                case 5:
                    data.setMiqdar((Double) value);
                    data.setYekun_man(data.getMiqdar() * data.getQiymet_man());
                    if (data.getKurs() != 0.0) {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / data.getKurs());
                    } else {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / 1);
                    }
                    break;
                case 6:
                    data.setQiymet_man((Double) value);
                    data.setYekun_man(data.getMiqdar() * data.getQiymet_man());
                    if (data.getKurs() != 0.0) {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / data.getKurs());
                    } else {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / 1);
                    }
                    break;
                case 7:
                    data.setKurs((Double) value);
                    if (data.getKurs() != 0.0) {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / data.getKurs());
                    } else {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / 1);
                    }
                    break;
                case 8:
                    data.setYekun_dol((Double) value);
                    break;
                case 9:
                    data.setYekun_man((Double) value);
                    data.setYekun_dol(data.getYekun_man() / data.getKurs());
                    break;
                case 10:
                    data.setAd((String) value);
                    break;
            }
            AslanDB.followTruck_Update(data, selectedRow);
            visible(surucuAdi);
        });
    }

    public MainWindow_FollowTruck() {
        initComponents();
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/db.png"));
        refresh();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new RXTable();
        btnAddRow = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        comboAd = new javax.swing.JComboBox();
        btnClose = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnExpense = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        jScrollPane1.setViewportView(table);

        btnAddRow.setText("+");
        btnAddRow.addActionListener(this::btnAddRowActionPerformed);

        btnDelete.setText("Sil");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnClose.setText("Bağla");
        btnClose.addActionListener(this::btnCloseActionPerformed);

        jLabel3.setText("İşləyir bölməsinə \"isleyir\" vəya \"islemir\" yazın");

        jLabel2.setText("Boşluqları doldurarkən \",\"-dən yox \".\"-dən istifadə edin");

        btnAdd.setText("Əlavə et");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnExpense.setText("Xərclər");
        btnExpense.addActionListener(this::btnExpenseActionPerformed);

        jLabel6.setText("@JavelGroup.org");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAddRow))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnExpense)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAdd)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnClose))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(comboAd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(comboAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(btnAddRow))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnDelete)
                                                .addComponent(btnClose)
                                                .addComponent(btnAdd)
                                                .addComponent(btnExpense))
                                        .addContainerGap())
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRowActionPerformed
        FollowTruckPojo insert = new FollowTruckPojo(0, null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, surucuAdi);
        AslanDB.followTruck_Insert(insert);
        refresh();
        setVisible(false);
    }//GEN-LAST:event_btnAddRowActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Delete?",
                "Sil",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            AslanDB.followTruck_Delete(selectedRow);
        }
        refresh();
        setVisible(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        new salary.Salary().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        new Insert_FollowTruck().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpenseActionPerformed
        new MainWindow_Expense().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnExpenseActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow_FollowTruck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow_FollowTruck().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddRow;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExpense;
    private javax.swing.JComboBox comboAd;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private RXTable table;
    // End of variables declaration//GEN-END:variables
}
