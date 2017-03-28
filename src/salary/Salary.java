package salary;

//@author javanshir
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
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

public final class Salary extends javax.swing.JFrame {

    //table button soyad,ad,ata adi(oglu qizi),maas - maasa gore sirala
    private SalaryTableModel tableModel;
    public int selectedRow = -1;
    public static String bolme;
    public static String ay;
    public static int il = 0;
    private final String selectedItem_Bolme;
    private final String selectedItem_Ay;
    private final int selectedItem_Il;
    private List<Pojo> sortedList;
    public TableColumn date;
    public static int bolmeId = 0;
    public static String ad;
    public static int row2;

    public void visible(String bolme2, String ay2, int il2) {
        il = il2;
        ay = ay2;
        bolme = bolme2;
        new Salary().setVisible(true);
    }

    public RXTable autoResizeColWidth(RXTable table, SalaryTableModel model) {
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

    public Salary() {
        initComponents();
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/db.png"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        List<Pojo> model = DBO.findBolme();
        HashSet hs = new HashSet();
        ArrayList al = new ArrayList();
        model.stream().forEach((model1) -> {
            hs.add(model1.getBolme());
        });
        al.addAll(hs);
        comboBolme.addItem("Butun Bolmeler");
        al.stream().forEach((al1) -> {
            comboBolme.addItem(al1);
            bolmeId++;
        });
        comboBolme.removeItem("null");
        if (bolme == null) {
            comboBolme.setSelectedIndex(0);
        } else {
            comboBolme.setSelectedItem(bolme);
        }

        if (ay == null) {
            comboAy.setSelectedIndex(0);
        } else {
            comboAy.setSelectedItem(ay);
        }

        List<Pojo> model2 = DBO.findIl();
        HashSet hs2 = new HashSet();
        ArrayList al2 = new ArrayList();
        model2.stream().forEach((model1) -> {
            hs2.add(model1.getIl());
        });
        al2.addAll(hs2);
        comboIl.addItem(0000);
        al2.stream().forEach((al1) -> {
            comboIl.addItem(al1);
        });
        comboIl.removeItem(0);
        if (il == 0) {
            comboIl.setSelectedIndex(0);
        } else {
            comboIl.setSelectedItem(il);
        }

        selectedItem_Bolme = (String) comboBolme.getSelectedItem();
        selectedItem_Ay = (String) comboAy.getSelectedItem();
        selectedItem_Il = Integer.parseInt(comboIl.getSelectedItem().toString());
        Pojo tempVar;
        sortedList = DBO.salary_Find(selectedItem_Bolme, selectedItem_Ay, selectedItem_Il);
        for (int i = 0; i < sortedList.size(); i++) {
            if (sortedList.get(i).getIsleyir() == 1) {
                sortedList.remove(i);
                i--;
            }
        }
        for (int i = 0; i < sortedList.size(); i++) {
            for (int j = 0; j < sortedList.size(); j++) {
                if (sortedList.get(i).getCem() == sortedList.get(j).getCem()) {
                }
                if (sortedList.get(i).getCem() > sortedList.get(j).getCem()) {
                    tempVar = sortedList.get(j);
                    sortedList.set(j, sortedList.get(i));
                    sortedList.set(i, tempVar);
                }
            }
        }
        tableModel = new SalaryTableModel(sortedList) {
            @Override
            public boolean isCellEditable(int row, int column) {
                ad = tableModel.getTopic(row).getAd();
                selectedRow = tableModel.getTopic(row).getId();
                row2 = row;
                return (column != 0) && (column != 17);
            }
        };
        table.setModel(tableModel);
        table = autoResizeColWidth(table, tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
        DatePickerCellEditor datePicker = new DatePickerCellEditor(formatter);
        datePicker.setFormats(formatter);
        date = table.getColumnModel().getColumn(18);
        date.setCellEditor(datePicker);
        table = new RXTable(tableModel);
        JScrollPane pane = new JScrollPane(table);
        add(pane);
        setLocationRelativeTo(null);
        double x = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            x += tableModel.getTopic(i).getArtim();
        }
        lblArtiminCemi.setText(String.valueOf((x)));
        double y = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            y += tableModel.getTopic(i).getCem();
        }
        lblTotal.setText(String.valueOf((y)));
        comboBolme.addActionListener((ActionEvent e) -> {
            String selectedBolme = (String) comboBolme.getSelectedItem();
            visible(selectedBolme, ay, il);
            setVisible(false);
        });
        comboAy.addActionListener((ActionEvent e) -> {
            String selectedAy = (String) comboAy.getSelectedItem();
            visible(bolme, selectedAy, il);
            setVisible(false);
        });
        comboIl.addActionListener((ActionEvent e) -> {
            int selectedIl = Integer.parseInt(comboIl.getSelectedItem().toString());
            visible(bolme, ay, selectedIl);
            setVisible(false);
        });
        table.getModel().addTableModelListener((TableModelEvent e) -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model1 = (TableModel) e.getSource();
            String columnName = model1.getColumnName(column);
            Object value = model1.getValueAt(row, column);
            Pojo temp;
            sortedList = DBO.salary_Find(selectedItem_Bolme, selectedItem_Ay, selectedItem_Il);
            for (int i = 0; i < sortedList.size(); i++) {
                if (sortedList.get(i).getIsleyir() == 1) {
                    sortedList.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < sortedList.size(); i++) {
                for (int j = 0; j < sortedList.size(); j++) {
                    if (sortedList.get(i).getCem() > sortedList.get(j).getCem()) {
                        temp = sortedList.get(j);
                        sortedList.set(j, sortedList.get(i));
                        sortedList.set(i, temp);
                    }
                }
            }
            List<Pojo> list = sortedList;
            Pojo data = list.get(row);
            switch (column) {
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
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 4:
                    data.setIlk_maas((Double) value);
                    data.setCem(data.getBank() + data.getIlk_maas() + data.getSon_maas() + data.getArtim());
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 5:
                    data.setSon_maas((Double) value);
                    data.setCem(data.getBank() + data.getIlk_maas() + data.getSon_maas() + data.getArtim());
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 6:
                    data.setArtim((Double) value);
                    data.setCem(data.getBank() + data.getIlk_maas() + data.getSon_maas() + data.getArtim());
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 7:
                    data.setCem((Double) value);
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 8:
                    data.setElave_is((int) value);
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 9:
                    data.setMukafatlandirma((Double) value);
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 10:
                    data.setQayib_gunu((int) value);
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 11:
                    data.setDetal_xetasi((Double) value);
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
                    break;
                case 12:
                    data.setSatin_alma((Double) value);
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
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
                        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM");
                        Date date4 = format2.parse(time);
                        SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM");
                        data.setBaslama_tarixi(format3.format(date4));
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date3);
                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(date4);
                        int diffYear = cal.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
                        int diffMonth = cal.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
                        if (diffMonth < 0) {
                            diffMonth += 12;
                            diffYear--;
                        }
                        data.setNe_qeder_isleyib(diffYear + " il " + diffMonth + " ay");
                    } catch (NumberFormatException | IndexOutOfBoundsException | ParseException ex) {
                        Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 19:
                    data.setBorc((Double) value);
                    data.setYekun_maas(data.getCem() - data.getBorc()
                            - ((data.getCem() / 26) * data.getQayib_gunu()) - data.getDetal_xetasi()
                            + data.getSatin_alma() + ((data.getCem() / 26) * data.getElave_is()));
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
            DBO.salary_Update(data, selectedRow);
            visible(bolme, ay, il);
            setVisible(false);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new RXTable();
        lblArtiminCemi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnIsdenCixart = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        comboBolme = new javax.swing.JComboBox();
        btnShowAll = new javax.swing.JButton();
        btnAddRow = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTotal2 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnExport = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        comboAy = new javax.swing.JComboBox();
        comboIl = new javax.swing.JComboBox();
        btnExpense = new javax.swing.JButton();
        btnFollowTruck = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnWorkers = new javax.swing.JButton();
        btnIsdenCixanlar = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnTable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        lblArtiminCemi.setText("jLabel3");

        jLabel4.setText("Artımın Cəmi:");

        btnAdd.setText("Əlavə et");
        btnAdd.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnAddActionPerformed(evt);
        });

        btnIsdenCixart.setText("İşdən Cıxart");
        btnIsdenCixart.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnIsdenCixartActionPerformed(evt);
        });

        btnExit.setText("Çıxış");
        btnExit.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnExitActionPerformed(evt);
        });

        btnShowAll.setText("Show All");
        btnShowAll.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnShowAllActionPerformed(evt);
        });

        btnAddRow.setText("+");
        btnAddRow.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnAddRowActionPerformed(evt);
        });

        jLabel2.setText("Boşluqları doldurarkən \",\"-dən yox \".\"-dən istifadə edin");

        lblTotal2.setText("Total:");

        lblTotal.setText("jLabel3");

        btnExport.setText("Tabel Kanvert");
        btnExport.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnExportActionPerformed(evt);
        });

        jLabel3.setText("İşləyir bölməsinə \"isleyir\" vəya \"islemir\" yazın");

        btnPrint.setText("Çap Etmə");
        btnPrint.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnPrintActionPerformed(evt);
        });

        comboAy.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Ay", "Yanvar", "Fevral", "Mart", "Aprel", "May", "Iyun", "Iyul", "Avgust", "Sentyabr", "Oktyabr", "Noyabr", "Dekabr"}));

        btnExpense.setText("Xərclər");
        btnExpense.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnExpenseActionPerformed(evt);
        });

        btnFollowTruck.setText("Follow Truck");
        btnFollowTruck.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnFollowTruckActionPerformed(evt);
        });

        jLabel6.setText("@JavelGroup.org");

        btnWorkers.setText("İşçi Pəncərəsi");
        btnWorkers.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnWorkersActionPerformed(evt);
        });

        btnIsdenCixanlar.setText("İşdən Çıxanlar");
        btnIsdenCixanlar.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnIsdenCixanlarActionPerformed(evt);
        });

        btnDelete.setText("Sil");
        btnDelete.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnDeleteActionPerformed(evt);
        });

        btnUpdate.setText("Dəyiş");
        btnUpdate.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnUpdateActionPerformed(evt);
        });

        btnTable.setText("Table");
        btnTable.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnTableActionPerformed(evt);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(comboBolme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(comboAy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(comboIl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel2)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(btnExpense)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnFollowTruck)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnWorkers)))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnIsdenCixanlar)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(btnTable)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                                        .addComponent(jLabel4)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblArtiminCemi)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblTotal2)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblTotal)
                                                        .addGap(56, 56, 56))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(btnExport)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(btnPrint)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(18, 18, 18)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnShowAll, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                                .addComponent(btnIsdenCixart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(26, 26, 26)
                                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnAddRow))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboBolme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboAy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboIl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddRow)
                                .addComponent(jLabel4)
                                .addComponent(lblArtiminCemi)
                                .addComponent(lblTotal2)
                                .addComponent(lblTotal)
                                .addComponent(jLabel2)
                                .addComponent(btnDelete)
                                .addComponent(btnTable))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(btnExport))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnIsdenCixart, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPrint)
                                .addComponent(btnExpense)
                                .addComponent(btnFollowTruck)
                                .addComponent(btnWorkers)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnIsdenCixanlar))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

    }//GEN-LAST:event_tableMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Add add = new Add();
        add.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnIsdenCixartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIsdenCixartActionPerformed
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "İlk olarağ işçi adını seçin");
        } else {
            List<Pojo> list = DBO.salary();
            List<Pojo> list2;
            Pojo data = null;
            String adx = null;
            for (Pojo list1 : list) {
                if (selectedRow == list1.getId()) {
                    data = list1;
                    data.setIsleyir(1);
                    adx = data.getAd();
                }
            }
            if (data != null) {
                list2 = DBO.salary_Find_Workers(data.getAd());
                list2.stream().map((list21) -> {
                    list21.setIsleyir(1);
                    return list21;
                }).forEach((list21) -> {
                    DBO.salary_Update(list21, list21.getId());
                });
                DBO.salary_Update(data, selectedRow);
            }
            CixanQayidanPojo cq = new CixanQayidanPojo();
            cq.setUser_id(adx);
            cq.setTip(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date datex = new Date();
            cq.setTarix(dateFormat.format(datex));
            DBO.cixarilma_qayitma_Insert(cq);
            visible(bolme, ay, il);
            setVisible(false);
        }
    }//GEN-LAST:event_btnIsdenCixartActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        new ShowAll().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnShowAllActionPerformed

    private void btnAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRowActionPerformed
        String selected_Bolme;
        String selected_Ay;
        int selected_Il;
        if ("Butun Bolmeler".equals((String) comboBolme.getSelectedItem())) {
            selected_Bolme = (String) comboBolme.getItemAt(1);
        } else {
            selected_Bolme = (String) comboBolme.getSelectedItem();
        }
        if ("Ay".equals((String) comboAy.getSelectedItem())) {
            DateFormat ay2 = new SimpleDateFormat("MM");
            Date dateAy = new Date();
            selected_Ay = comboAy.getItemAt(Integer.parseInt(ay2.format(dateAy))).toString();
        } else {
            selected_Ay = comboAy.getSelectedItem().toString();
        }
        if (Integer.parseInt(comboIl.getSelectedItem().toString()) == 0) {
            selected_Il = 0000;
        } else {
            DateFormat year = new SimpleDateFormat("yyyy");
            Date dateYear = new Date();
            selected_Il = Integer.parseInt(year.format(dateYear));
        }
        Pojo insert = new Pojo(0, null, 1, 0.0, 0.0, 0.0, 0.0, 0.0, 0, 0.0, 0, 0.0, 0.0, selected_Bolme, selected_Ay, selected_Il, 0, null, null, 0.0, 0.0, null);
        DBO.salary_Insert(insert);
        visible(bolme, ay, il);
        setVisible(false);
    }//GEN-LAST:event_btnAddRowActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        ExcelExporter exp = new ExcelExporter();
        try {
            exp.exportTable(table, new File("table.xls"));
        } catch (IOException ex) {
            Logger.getLogger(ShowAll.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Print print = new Print();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpenseActionPerformed
        new aslan.db.MainWindow_Expense().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnExpenseActionPerformed

    private void btnFollowTruckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFollowTruckActionPerformed
        new aslan.db.MainWindow_FollowTruck().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnFollowTruckActionPerformed

    private void btnWorkersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorkersActionPerformed
        if (ad == null) {
            JOptionPane.showMessageDialog(null, "İlk olarağ işçi adını seçin");
        } else {
            new salary.Workers(ad).setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_btnWorkersActionPerformed

    private void btnIsdenCixanlarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIsdenCixanlarActionPerformed
        new IsdenCixanlar().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnIsdenCixanlarActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "İlk olarağ işçi adını seçin");
        } else {
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Delete?",
                    "Sil",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                DBO.salary_Delete(selectedRow);
            }
        }
        visible(bolme, ay, il);
        setVisible(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "İlk olarağ işçi adını seçin");
        } else {
            new salary.Update(selectedRow).setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTableActionPerformed
        new TableButton().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnTableActionPerformed

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
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Salary().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddRow;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExpense;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnFollowTruck;
    private javax.swing.JButton btnIsdenCixanlar;
    private javax.swing.JButton btnIsdenCixart;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JButton btnTable;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnWorkers;
    private javax.swing.JComboBox comboAy;
    private javax.swing.JComboBox comboBolme;
    private javax.swing.JComboBox comboIl;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArtiminCemi;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal2;
    private RXTable table;
    // End of variables declaration//GEN-END:variables
}
