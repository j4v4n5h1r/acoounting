package salary;

//@author javanshir
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class SalaryTableModel extends AbstractTableModel {

    private final List<Pojo> list;
    private final String[] columnNames = new String[]{
        "Id", "Ad/Soyad/Ata adı", "Cins", "Bank", "Ayın 20si", "Ayın 5i", "Artım", "Cəm", "Əlavə iş günü",
        "Mukafatlandırma", "Qayıb günü", "Detal xətası", "Satın alma", "Bölmə", "Ay", "İl", "Işləyir",
        "Nə qədər işləyib", "İşə başlama tarixi", "Borc", "Yekun maaş", "Qeyd"
    };
    private final Class[] types = new Class[]{
        java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class,
        java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
        java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class,
        java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class,
        java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class,
        java.lang.Double.class, java.lang.String.class
    };

    public SalaryTableModel(List<Pojo> list) {
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
        Pojo data = list.get(row);

        switch (col) {
            case 0:
                data.setId((int) value);
                break;
            case 1:
                data.setAd((String) value);
                break;
            case 2:
                data.setCins((int) value);
                break;
            case 3:
                data.setBank((Double) value);
                data.setCem(data.getBank() + data.getIlk_maas() + data.getSon_maas() + data.getArtim());
                break;
            case 4:
                data.setIlk_maas((Double) value);
                data.setCem(data.getBank() + data.getIlk_maas() + data.getSon_maas() + data.getArtim());
                break;
            case 5:
                data.setSon_maas((Double) value);
                data.setCem(data.getBank() + data.getIlk_maas() + data.getSon_maas() + data.getArtim());
                break;
            case 6:
                data.setArtim((Double) value);
                data.setCem(data.getBank() + data.getIlk_maas() + data.getSon_maas() + data.getArtim());
                break;
            case 7:
                data.setCem((Double) value);
                break;
            case 8:
                data.setElave_is((int) value);
                break;
            case 9:
                data.setMukafatlandirma((Double) value);
                break;
            case 10:
                data.setQayib_gunu((int) value);
                break;
            case 11:
                data.setDetal_xetasi((Double) value);
                break;
            case 12:
                data.setSatin_alma((Double) value);
                break;
            case 13:
                data.setBolme(value.toString().toUpperCase());
                break;
            case 14:
                data.setAy((String) value);
                break;
            case 15:
                data.setIl((Integer) value);
                break;
            case 16:
                data.setIsleyir((int) value);
                break;
            case 17:
                data.setNe_qeder_isleyib((String) value);
                break;
            case 18:
                try {
                    String time = value.toString();
                    Date date3 = new Date();
                    SimpleDateFormat format2 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                    Date date4 = format2.parse(time);
                    SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM");
                    data.setBaslama_tarixi(format3.format(date4));
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date3);
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(date4);
                    int diffYear = cal.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
                    int diffMonth = cal.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
                    data.setNe_qeder_isleyib(diffYear + " il " + diffMonth + " ay");
                } catch (NumberFormatException | IndexOutOfBoundsException | ParseException ex) {
                    Logger.getLogger(SalaryTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 19:
                data.setBorc((Double) value);
                break;
            case 20:
                data.setYekun_maas((Double) value);
                data.setCem(data.getBank() + data.getIlk_maas() + data.getSon_maas() + data.getArtim());
                data.setYekun_maas(data.getCem() - data.getBorc()
                        - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                        + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                break;
            case 21:
                data.setQeyd((String) value);
                break;
        }
        fireTableCellUpdated(row, col);
    }

    @Override
    public Object getValueAt(int row, int col) {
        Pojo data = list.get(row);

        switch (col) {
            case 0:
                return /*(salary.Salary.bolmeId - 1) * 1000 + */ data.getId();
            case 1:
                return data.getAd();
            case 2:
                return data.getCins();
            case 3:
                return data.getBank();
            case 4:
                return data.getIlk_maas();
            case 5:
                return data.getSon_maas();
            case 6:
                return data.getArtim();
            case 7:
                return data.getCem();
            case 8:
                return data.getElave_is();
            case 9:
                return data.getMukafatlandirma();
            case 10:
                return data.getQayib_gunu();
            case 11:
                return data.getDetal_xetasi();
            case 12:
                return data.getSatin_alma();
            case 13:
                return data.getBolme();
            case 14:
                return data.getAy();
            case 15:
                return data.getIl();
            case 16:
                return data.getIsleyir();
            case 17:
                return data.getNe_qeder_isleyib();
            case 18:
                return data.getBaslama_tarixi();
            case 19:
                return data.getBorc();
            case 20:
                return data.getYekun_maas();
            case 21:
                return data.getQeyd();
        }
        return new String();
    }

    public Pojo getTopic(int row) {
        if (row < 0) {
            return null;
        } else {
            return list.get(row);
        }
    }
}
