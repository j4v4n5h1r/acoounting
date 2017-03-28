package aslan.db;
//@author javanshir

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AslanDB {

    private static Connection con = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/salary?characterEncoding=UTF-8";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public static void connected() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(AslanDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close() {
        try {
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AslanDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<ExpensePojo> expense() {
        try {
            List<ExpensePojo> list = new ArrayList<>();
            String sql = "SELECT Id, Tarix, Malin_adi, Miqdar, Olcu_vahidi, Qiymet_dol, Qiymet_man, Kurs, Yekun_dol, Yekun_man, Qeyd, Qeyd_2, Qeyd_3 FROM expense";
            connected();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ExpensePojo t = new ExpensePojo();
                t.setId(rs.getInt(1));
                t.setTarix(rs.getString(2));
                t.setMalin_adi(rs.getString(3));
                t.setMiqdar(rs.getDouble(4));
                t.setOlcu_vahidi(rs.getString(5));
                t.setQiymet_dol(rs.getDouble(6));
                t.setQiymet_man(rs.getDouble(7));
                t.setKurs(rs.getDouble(8));
                t.setYekun_dol(rs.getDouble(9));
                t.setYekun_man(rs.getDouble(10));
                t.setQeyd(rs.getString(11));
                t.setQeyd_2(rs.getString(12));
                t.setQeyd_3(rs.getString(13));
                list.add(t);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        } finally {
            close();
        }
    }

    public static void expense_Insert(ExpensePojo ft) {
        try {
            String sqlInsert = "INSERT INTO expense (Tarix, Malin_adi, Miqdar, Olcu_vahidi, Qiymet_dol, Qiymet_man, Kurs, Yekun_dol, Yekun_man, Qeyd, Qeyd_2, Qeyd_3)"
                    + " VALUES ('" + ft.getTarix() + "', '" + ft.getMalin_adi() + "', " + ft.getMiqdar() + ", " + ft.getOlcu_vahidi() + ", " + ft.getQiymet_dol() + ", " + ft.getQiymet_man() + ", " + ft.getKurs() + ", " + ft.getYekun_dol() + ", " + ft.getYekun_man() + ", '" + ft.getQeyd() + "', '" + ft.getQeyd_2() + "', '" + ft.getQeyd_3() + "')";
            connected();
            ps = con.prepareStatement(sqlInsert);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AslanDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void expense_Delete(int x) {
        try {
            String sqlDelete = "DELETE FROM expense WHERE Id = " + x;
            connected();
            ps = con.prepareStatement(sqlDelete);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close();
        }
    }

    public static void expense_Update(ExpensePojo ft, int x) {
        try {
            String sqlUpdate = "UPDATE expense "
                    + "SET Tarix = '" + ft.getTarix() + "', Malin_adi = '" + ft.getMalin_adi() + "', "
                    + "Miqdar = " + ft.getMiqdar() + ", Olcu_vahidi = '" + ft.getOlcu_vahidi() + "', "
                    + "Qiymet_dol = " + ft.getQiymet_dol() + ", Qiymet_man = " + ft.getQiymet_man() + ", "
                    + "Kurs = " + ft.getKurs() + ", Yekun_dol = " + ft.getYekun_dol() + ", "
                    + "Yekun_man = " + ft.getYekun_man() + ", Qeyd = '" + ft.getQeyd() + "', "
                    + "Qeyd_2 = '" + ft.getQeyd_2() + "', Qeyd_3 = '" + ft.getQeyd_3() + "' "
                    + "WHERE Id = " + x;
            connected();
            ps = con.prepareStatement(sqlUpdate);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close();
        }
    }

    public static List<FollowTruckPojo> followTruck() {
        try {
            List<FollowTruckPojo> list = new ArrayList<>();
            String sql = "SELECT Id, Tarix, IlkinKM, YekunKM, Ferq, Miqdar, Qiymet, Kurs, Yekun_dol, Yekun_man, Ad FROM FollowTruck";
            connected();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                FollowTruckPojo t = new FollowTruckPojo();
                t.setId(rs.getInt(1));
                t.setTarix(rs.getString(2));
                t.setIlkinKM(rs.getDouble(3));
                t.setYekunKM(rs.getDouble(4));
                t.setFerq(rs.getDouble(5));
                t.setMiqdar(rs.getDouble(6));
                t.setQiymet_man(rs.getDouble(7));
                t.setKurs(rs.getDouble(8));
                t.setYekun_dol(rs.getDouble(9));
                t.setYekun_man(rs.getDouble(10));
                t.setAd(rs.getString(11));
                list.add(t);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        } finally {
            close();
        }
    }

    public static void followTruck_Insert(FollowTruckPojo ft) {
        try {
            String sqlInsert = "INSERT INTO FollowTruck (Tarix, IlkinKM, YekunKM, Ferq, Miqdar, Qiymet, Kurs, Yekun_dol, Yekun_man, Ad)"
                    + " VALUES ('" + ft.getTarix() + "', " + ft.getIlkinKM() + ", " + ft.getYekunKM() + ", " + ft.getFerq() + ", " + ft.getMiqdar() + ", " + ft.getQiymet_man() + ", " + ft.getKurs() + ", " + ft.getYekun_dol() + ", " + ft.getYekun_man() + ", '" + ft.getAd() + "')";
            connected();
            ps = con.prepareStatement(sqlInsert);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AslanDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public static int followTruck_GetId() {
//        try {
//            FollowTruckPojo t = new FollowTruckPojo();
//            String sql = "SELECT Id FROM FollowTruck ORDER BY Id DESC LIMIT 1";
//            connected();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                t.setId(rs.getInt(1) + 1);
//            }
//            return t.getId();
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//            return -1;
//        } finally {
//            close();
//        }
//    }
    public static void followTruck_Delete(int x) {
        try {
            String sqlDelete = "DELETE FROM FollowTruck WHERE Id = " + x;
            connected();
            ps = con.prepareStatement(sqlDelete);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close();
        }
    }

    public static void followTruck_Update(FollowTruckPojo ft, int x) {
        try {
            String sqlUpdate = "UPDATE FollowTruck "
                    + "SET Tarix = '" + ft.getTarix() + "', IlkinKM = " + ft.getIlkinKM() + ", "
                    + "YekunKM = " + ft.getYekunKM() + ", Ferq = " + ft.getFerq() + ", Miqdar = " + ft.getMiqdar() + ", "
                    + "Qiymet = " + ft.getQiymet_man() + ", Kurs = " + ft.getKurs() + ", "
                    + "Yekun_dol = " + ft.getYekun_dol() + ", Yekun_man = " + ft.getYekun_man()
                    + "WHERE Id = " + x;
            connected();
            ps = con.prepareStatement(sqlUpdate);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close();
        }
    }

    public static List<FollowTruckPojo> followTruck_Find(String x) {
        try {
            List<FollowTruckPojo> list = new ArrayList<>();
            String sqlFind = "SELECT Id, Tarix, IlkinKM, YekunKM, Ferq, Miqdar, Qiymet, Kurs, Yekun_dol, Yekun_man FROM FollowTruck WHERE Ad = '" + x + "'";
            connected();
            ps = con.prepareStatement(sqlFind);
            rs = ps.executeQuery();
            while (rs.next()) {
                FollowTruckPojo t = new FollowTruckPojo();
                t.setId(rs.getInt(1));
                t.setTarix(rs.getString(2));
                t.setIlkinKM(rs.getDouble(3));
                t.setYekunKM(rs.getDouble(4));
                t.setFerq(rs.getDouble(5));
                t.setMiqdar(rs.getDouble(6));
                t.setQiymet_man(rs.getDouble(7));
                t.setKurs(rs.getDouble(8));
                t.setYekun_dol(rs.getDouble(9));
                t.setYekun_man(rs.getDouble(10));
                list.add(t);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        } finally {
            close();
        }
    }

    public static List<FollowTruckPojo> findAd() {
        try {
            List<FollowTruckPojo> list = new ArrayList<>();
            String sqlFind = "SELECT Ad FROM FollowTruck";
            connected();
            ps = con.prepareStatement(sqlFind);
            rs = ps.executeQuery();
            while (rs.next()) {
                FollowTruckPojo t = new FollowTruckPojo();
                t.setAd(rs.getString(1));
                list.add(t);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        } finally {
            close();
        }
    }

    public static void main(String[] args) {

    }
}
