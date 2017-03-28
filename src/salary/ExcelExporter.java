package salary;

//@author javanshir
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ExcelExporter {

    public ExcelExporter() {
    }//ISO-8859-5

    public void exportTable(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                bw.write(model.getColumnName(i) + "\t");
            }
            bw.write("\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bw.write(model.getValueAt(i, j).toString() + "\t");
                }
                bw.write("\n");
            }
        }
    }
}
