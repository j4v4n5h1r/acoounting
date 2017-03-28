package salary;

//@author javanshir
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Print extends JFrame {

    private JTable table;
    JScrollPane pane;
    DefaultTableModel model;
    public Object[][] data;

    public Print() {
        createTable();
    }

    public JTable autoResizeColWidth(JTable table, DefaultTableModel model) {
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

    private void createTable() {
        List<Pojo> list = new ArrayList<>(DBO.salary());
        data = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getAd();
        }
        for (int i = 0; i < list.size(); i++) {
            data[i][1] = list.get(i).getCem();
        }
        model = new DefaultTableModel(
                data,
                new String[]{
                    "Ad / Soyad / Ata adi", "Maaş"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setModel(model);
        pane = new JScrollPane(table);
        add(pane);
        table = autoResizeColWidth(table, model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        print();
    }

    private void print() {
        ExcelExporter exp = new ExcelExporter();
        try {
            exp.exportTable(table, new File("Cap_Etme.xls"));
        } catch (IOException ex) {
            Logger.getLogger(ShowAll.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
