package aslan.db;

//@author javanshir, rufan
import java.awt.Component;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
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

public final class MainWindow_Expense extends javax.swing.JFrame {

    private final ExpenseTableModel tableModel;
    public int selectedRow;
    public TableColumn date;

    public RXTable autoResizeColWidth(RXTable table, ExpenseTableModel model) {
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

    public MainWindow_Expense() {
        initComponents();
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/db.png"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tableModel = new ExpenseTableModel(AslanDB.expense()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                selectedRow = tableModel.getTopic(row).getId();
                return true;
            }
        };
        table.setModel(tableModel);
        table = autoResizeColWidth(table, tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
        DatePickerCellEditor datePicker = new DatePickerCellEditor(formatter);
        datePicker.setFormats(formatter);
        date = table.getColumnModel().getColumn(1);
        date.setCellEditor(datePicker);
        table = new RXTable(tableModel);
        JScrollPane pane = new JScrollPane(table);
        add(pane);
        setLocationRelativeTo(null);
        table.getModel().addTableModelListener((TableModelEvent e) -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            String columnName = model.getColumnName(column);
            Object value = model.getValueAt(row, column);
            List<ExpensePojo> list = AslanDB.expense();
            ExpensePojo data = list.get(row);
            switch (column) {
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
                    data.setMalin_adi((String) value);
                    break;
                case 3:
                    data.setMiqdar((Double) value);
                    data.setYekun_man(data.getMiqdar() * data.getQiymet_man());
                    if (data.getKurs() != 0.0) {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / data.getKurs());
                    } else {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / 1);
                    }
                    break;
                case 4:
                    data.setOlcu_vahidi((String) value);
                    break;
                case 5:
                    data.setQiymet_dol((Double) value);
                    data.setYekun_dol((data.getMiqdar() * data.getQiymet_dol()));
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
                        data.setQiymet_dol(data.getQiymet_man() / data.getKurs());
                    } else {
                        data.setYekun_dol((data.getMiqdar() * data.getQiymet_man()) / 1);
                        data.setQiymet_dol(data.getQiymet_man());
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
                    data.setQeyd((String) value);
                    break;
                case 11:
                    data.setQeyd_2((String) value);
                    break;
                case 12:
                    data.setQeyd_3((String) value);
                    break;
            }
            AslanDB.expense_Update(data, selectedRow);
            new MainWindow_Expense().setVisible(true);
            setVisible(false);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new RXTable();
        btnDelete = new javax.swing.JButton();
        btnAddRow = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnFoloowTruck = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        jScrollPane1.setViewportView(table);

        btnDelete.setText("Sil");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnAddRow.setText("+");
        btnAddRow.addActionListener(this::btnAddRowActionPerformed);

        btnClose.setText("Bağla");
        btnClose.addActionListener(this::btnCloseActionPerformed);

        jLabel3.setText("İşləyir bölməsinə \"isleyir\" vəya \"islemir\" yazın");

        jLabel2.setText("Boşluqları doldurarkən \",\"-dən yox \".\"-dən istifadə edin");

        btnFoloowTruck.setText("Follow Truck");
        btnFoloowTruck.addActionListener(this::btnFoloowTruckActionPerformed);

        jLabel6.setText("@JavelGroup.org");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAddRow))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnFoloowTruck)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnClose)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                .addComponent(btnFoloowTruck))
                                        .addContainerGap())
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Delete?",
                "Sil",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            AslanDB.expense_Delete(selectedRow);
        }
        new MainWindow_Expense().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRowActionPerformed
        ExpensePojo insert = new ExpensePojo(0, null, null, 0.0, null, 0.0, 0.0, 0.0, 0.0, 0.0, null, null, null);
        AslanDB.expense_Insert(insert);
        new MainWindow_Expense().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnAddRowActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        new salary.Salary().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnFoloowTruckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoloowTruckActionPerformed
        new MainWindow_FollowTruck().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnFoloowTruckActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow_Expense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow_Expense().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRow;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFoloowTruck;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private RXTable table;
    // End of variables declaration//GEN-END:variables
}
