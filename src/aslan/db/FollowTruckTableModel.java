package aslan.db;

//@author javanshir
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public abstract class FollowTruckTableModel extends AbstractTableModel {

    private final List<FollowTruckPojo> list;
    private final String[] columnNames = new String[]{
        "Id", "Tarix", "Ilkin KM", "Yekun KM", "Fərq", "Miqdar (litr)", "Qiymət(man)", "Kurs", "Yekun ($)",
        "Yekun (man)", "Ad"
    };
    private final Class[] types = new Class[]{
        java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class,
        java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
        java.lang.Double.class, java.lang.Double.class, java.lang.String.class
    };

    public FollowTruckTableModel(List<FollowTruckPojo> list) {
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
        FollowTruckPojo data = list.get(row);

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
        fireTableCellUpdated(row, col);
    }

    @Override
    public Object getValueAt(int row, int col) {
        FollowTruckPojo data = list.get(row);

        switch (col) {
            case 0:
                return data.getId();
            case 1:
                return data.getTarix();
            case 2:
                return data.getIlkinKM();
            case 3:
                return data.getYekunKM();
            case 4:
                return data.getFerq();
            case 5:
                return data.getMiqdar();
            case 6:
                return data.getQiymet_man();
            case 7:
                return data.getKurs();
            case 8:
                return data.getYekun_dol();
            case 9:
                return data.getYekun_man();
            case 10:
                return data.getAd();
        }

        return new String();
    }

    public FollowTruckPojo getTopic(int row) {
        if (row < 0) {
            return null;
        } else {
            return list.get(row);
        }
    }
}
