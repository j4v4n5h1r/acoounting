package aslan.db;

//@author javanshir
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class ExpenseTableModel extends AbstractTableModel {

    private final List<ExpensePojo> list;
    private final String[] columnNames = new String[]{
        "Id", "Tarix", "Malın Adı", "Miqdar", "Ölçü vahidi", "Qiymət (dol)", "Qiymət (man)", "Kurs",
        "Yekun ($)", "Yekun (man)", "Qeyd", "Qeyd 2", "Qeyd 3"
    };
    private final Class[] types = new Class[]{
        java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class,
        java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
        java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class,
        java.lang.String.class
    };

    public ExpenseTableModel(List<ExpensePojo> list) {
        this.list = list;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        ExpensePojo data = list.get(row);

        switch (col) {
            case 0:
                data.setId((int) value);
                break;
            case 1:
                try {
                    String time = value.toString();
                    SimpleDateFormat format2 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
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
        fireTableCellUpdated(row, col);
    }

    @Override
    public Object getValueAt(int row, int col) {
        ExpensePojo data = list.get(row);

        switch (col) {
            case 0:
                return data.getId();
            case 1:
                return data.getTarix();
            case 2:
                return data.getMalin_adi();
            case 3:
                return data.getMiqdar();
            case 4:
                return data.getOlcu_vahidi();
            case 5:
                return data.getQiymet_dol();
            case 6:
                return data.getQiymet_man();
            case 7:
                return data.getKurs();
            case 8:
                return data.getYekun_dol();
            case 9:
                return data.getYekun_man();
            case 10:
                return data.getQeyd();
            case 11:
                return data.getQeyd_2();
            case 12:
                return data.getQeyd_3();
        }

        return new String();
    }

    public ExpensePojo getTopic(int row) {
        if (row < 0) {
            return null;
        } else {
            return list.get(row);
        }
    }
}
