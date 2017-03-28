package salary;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//@author javanshir
public class DBO {

    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static void connection() {
        try {
            DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salary?characterEncoding=UTF-8", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close() {
        try {
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Pojo> salary() {
        try {
            List<Pojo> list = new ArrayList<>();
            String sql = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                    + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                    + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary";
            connection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pojo t = new Pojo();
                t.setId(rs.getInt(1));
                t.setAd(rs.getString(2));
                t.setCins(rs.getInt(3));
                t.setBank(rs.getDouble(4));
                t.setIlk_maas(rs.getDouble(5));
                t.setSon_maas(rs.getDouble(6));
                t.setArtim(rs.getDouble(7));
                t.setCem(rs.getDouble(8));
                t.setElave_is(rs.getInt(9));
                t.setMukafatlandirma(rs.getDouble(10));
                t.setQayib_gunu(rs.getInt(11));
                t.setDetal_xetasi(rs.getDouble(12));
                t.setSatin_alma(rs.getDouble(13));
                t.setBolme(rs.getString(14));
                t.setAy(rs.getString(15));
                t.setIl(rs.getInt(16));
                t.setIsleyir(rs.getInt(17));
                t.setNe_qeder_isleyib(rs.getString(18));
                t.setBaslama_tarixi(rs.getString(19));
                t.setBorc(rs.getDouble(20));
                t.setYekun_maas(rs.getDouble(21));
                t.setQeyd(rs.getString(22));
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

    public static void salary_Insert(Pojo ft) {
        try {
            String sqlInsert = "INSERT INTO salary (ad, cins, bank, ilk_maas, son_maas, artim, cem, "
                    + "elave_is_gunu, mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                    + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd)"
                    + " VALUES ('" + ft.getAd() + "', " + ft.getCins() + ", " + ft.getBank()
                    + ", " + ft.getIlk_maas() + ", " + ft.getSon_maas() + ", " + ft.getArtim()
                    + ", " + ft.getCem() + ", " + ft.getElave_is() + ", " + ft.getMukafatlandirma()
                    + ", " + ft.getQayib_gunu() + ", " + ft.getDetal_xetasi() + ", " + ft.getSatin_alma()
                    + ", '" + ft.getBolme() + "', '" + ft.getAy() + "', " + ft.getIl() + ", '"
                    + ft.getIsleyir() + "', '" + ft.getNe_qeder_isleyib() + "', '" + ft.getBaslama_tarixi()
                    + "', '" + ft.getBorc() + "', " + ft.getYekun_maas() + ", '" + ft.getQeyd() + "')";
            connection();
            ps = con.prepareStatement(sqlInsert);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void cixarilma_qayitma_Insert(CixanQayidanPojo ft) {
        try {
            String sqlInsert = "INSERT INTO cixarilma_qayitma (user_id, tarix, tip)"
                    + " VALUES ('" + ft.getUser_id() + "', '" + ft.getTarix() + "', " + ft.getTip() + ")";
            connection();
            ps = con.prepareStatement(sqlInsert);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void salary_Delete(int x) {
        try {
            String sqlDelete = "DELETE FROM salary WHERE id = " + x;
            connection();
            ps = con.prepareStatement(sqlDelete);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close();
        }
    }

    public static void salary_Update(Pojo ft, int x) {
        try {
            String sqlUpdate = "UPDATE salary "
                    + "SET ad = '" + ft.getAd() + "', cins = " + ft.getCins() + ", bank = " + ft.getBank()
                    + ", ilk_maas = " + ft.getIlk_maas() + ", son_maas = " + ft.getSon_maas()
                    + ", artim = " + ft.getArtim() + ", cem = " + ft.getCem() + ", elave_is_gunu = "
                    + ft.getElave_is() + ", mukafatlandirma = " + ft.getMukafatlandirma()
                    + ", qayib_gunu = " + ft.getQayib_gunu() + ", detal_xetasi = " + ft.getDetal_xetasi()
                    + ", satin_alma = " + ft.getSatin_alma() + ", bolme = '" + ft.getBolme() + "', ay = '"
                    + ft.getAy() + "', il = " + ft.getIl() + ", isleyir = " + ft.getIsleyir()
                    + ", ne_qeder_isleyib = '" + ft.getNe_qeder_isleyib() + "', baslama_tarixi = '"
                    + ft.getBaslama_tarixi() + "', borc = " + ft.getBorc() + ", yekun_maas = "
                    + ft.getYekun_maas() + ", qeyd = '" + ft.getQeyd() + "' " + "WHERE id = " + x;
            connection();
            ps = con.prepareStatement(sqlUpdate);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close();
        }
    }

    public static List<Pojo> findBolme() {
        try {
            List<Pojo> list = new ArrayList<>();
            String sqlFind = "SELECT bolme FROM salary";
            connection();
            ps = con.prepareStatement(sqlFind);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pojo t = new Pojo();
                t.setBolme(rs.getString(1));
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

    public static List<Pojo> findIl() {
        try {
            List<Pojo> list = new ArrayList<>();
            String sqlFind = "SELECT il FROM salary";
            connection();
            ps = con.prepareStatement(sqlFind);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pojo t = new Pojo();
                t.setIl(rs.getInt(1));
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

    public static List<Pojo> salary_Find_Workers(String x) {
        try {
            List<Pojo> list = new ArrayList<>();
            String sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                    + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                    + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary "
                    + "WHERE ad = '" + x + "'";
            connection();
            ps = con.prepareStatement(sqlFind);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pojo t = new Pojo();
                t.setId(rs.getInt(1));
                t.setAd(rs.getString(2));
                t.setCins(rs.getInt(3));
                t.setBank(rs.getDouble(4));
                t.setIlk_maas(rs.getDouble(5));
                t.setSon_maas(rs.getDouble(6));
                t.setArtim(rs.getDouble(7));
                t.setCem(rs.getDouble(8));
                t.setElave_is(rs.getInt(9));
                t.setMukafatlandirma(rs.getDouble(10));
                t.setQayib_gunu(rs.getInt(11));
                t.setDetal_xetasi(rs.getDouble(12));
                t.setSatin_alma(rs.getDouble(13));
                t.setBolme(rs.getString(14));
                t.setAy(rs.getString(15));
                t.setIl(rs.getInt(16));
                t.setIsleyir(rs.getInt(17));
                t.setNe_qeder_isleyib(rs.getString(18));
                t.setBaslama_tarixi(rs.getString(19));
                t.setBorc(rs.getDouble(20));
                t.setYekun_maas(rs.getDouble(21));
                t.setQeyd(rs.getString(22));
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

    public static CixanQayidanPojo cixarilma_qayitma_Find_Tarix(String user_id, int tip) {
        try {
            CixanQayidanPojo t = new CixanQayidanPojo();
            String sqlFind = "SELECT user_id, tarix, tip FROM cixarilma_qayitma "
                    + "WHERE user_id = '" + user_id + "' AND tip = " + tip + "";
            connection();
            ps = con.prepareStatement(sqlFind);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setUser_id(rs.getString(1));
                t.setTarix(rs.getString(2));
                t.setTip(rs.getInt(3));
            }
            return t;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        } finally {
            close();
        }
    }

    public static Pojo salary_Find_Id(int x) {
        try {
            Pojo t = new Pojo();
            String sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                    + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                    + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary "
                    + "WHERE id = '" + x + "'";
            connection();
            ps = con.prepareStatement(sqlFind);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setAd(rs.getString(2));
                t.setCins(rs.getInt(3));
                t.setBank(rs.getDouble(4));
                t.setIlk_maas(rs.getDouble(5));
                t.setSon_maas(rs.getDouble(6));
                t.setArtim(rs.getDouble(7));
                t.setCem(rs.getDouble(8));
                t.setElave_is(rs.getInt(9));
                t.setMukafatlandirma(rs.getDouble(10));
                t.setQayib_gunu(rs.getInt(11));
                t.setDetal_xetasi(rs.getDouble(12));
                t.setSatin_alma(rs.getDouble(13));
                t.setBolme(rs.getString(14));
                t.setAy(rs.getString(15));
                t.setIl(rs.getInt(16));
                t.setIsleyir(rs.getInt(17));
                t.setNe_qeder_isleyib(rs.getString(18));
                t.setBaslama_tarixi(rs.getString(19));
                t.setBorc(rs.getDouble(20));
                t.setYekun_maas(rs.getDouble(21));
                t.setQeyd(rs.getString(22));
            }
            return t;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        } finally {
            close();
        }
    }

    public static List<Pojo> salary_Find(String x, String y, int z) {
        try {
            List<Pojo> list = new ArrayList<>();
            String sqlFind;
            if (x.equals("Butun Bolmeler")) {
                if (y.equals("Ay")) {
                    if (z == 0000) {
                        sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                                + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                                + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary";
                        connection();
                        ps = con.prepareStatement(sqlFind);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            Pojo t = new Pojo();
                            t.setId(rs.getInt(1));
                            t.setAd(rs.getString(2));
                            t.setCins(rs.getInt(3));
                            t.setBank(rs.getDouble(4));
                            t.setIlk_maas(rs.getDouble(5));
                            t.setSon_maas(rs.getDouble(6));
                            t.setArtim(rs.getDouble(7));
                            t.setCem(rs.getDouble(8));
                            t.setElave_is(rs.getInt(9));
                            t.setMukafatlandirma(rs.getDouble(10));
                            t.setQayib_gunu(rs.getInt(11));
                            t.setDetal_xetasi(rs.getDouble(12));
                            t.setSatin_alma(rs.getDouble(13));
                            t.setBolme(rs.getString(14));
                            t.setAy(rs.getString(15));
                            t.setIl(rs.getInt(16));
                            t.setIsleyir(rs.getInt(17));
                            t.setNe_qeder_isleyib(rs.getString(18));
                            t.setBaslama_tarixi(rs.getString(19));
                            t.setBorc(rs.getDouble(20));
                            t.setYekun_maas(rs.getDouble(21));
                            t.setQeyd(rs.getString(22));
                            list.add(t);
                        }
                        return list;
                    } else {
                        sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                                + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                                + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd "
                                + "FROM salary WHERE il = " + z + "";
                        connection();
                        ps = con.prepareStatement(sqlFind);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            Pojo t = new Pojo();
                            t.setId(rs.getInt(1));
                            t.setAd(rs.getString(2));
                            t.setCins(rs.getInt(3));
                            t.setBank(rs.getDouble(4));
                            t.setIlk_maas(rs.getDouble(5));
                            t.setSon_maas(rs.getDouble(6));
                            t.setArtim(rs.getDouble(7));
                            t.setCem(rs.getDouble(8));
                            t.setElave_is(rs.getInt(9));
                            t.setMukafatlandirma(rs.getDouble(10));
                            t.setQayib_gunu(rs.getInt(11));
                            t.setDetal_xetasi(rs.getDouble(12));
                            t.setSatin_alma(rs.getDouble(13));
                            t.setBolme(rs.getString(14));
                            t.setAy(rs.getString(15));
                            t.setIl(rs.getInt(16));
                            t.setIsleyir(rs.getInt(17));
                            t.setNe_qeder_isleyib(rs.getString(18));
                            t.setBaslama_tarixi(rs.getString(19));
                            t.setBorc(rs.getDouble(20));
                            t.setYekun_maas(rs.getDouble(21));
                            t.setQeyd(rs.getString(22));
                            list.add(t);
                        }
                        return list;
                    }
                } else {
                    sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                            + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                            + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary "
                            + "WHERE ay = '" + y + "' AND il = " + z + "";
                    connection();
                    ps = con.prepareStatement(sqlFind);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Pojo t = new Pojo();
                        t.setId(rs.getInt(1));
                        t.setAd(rs.getString(2));
                        t.setCins(rs.getInt(3));
                        t.setBank(rs.getDouble(4));
                        t.setIlk_maas(rs.getDouble(5));
                        t.setSon_maas(rs.getDouble(6));
                        t.setArtim(rs.getDouble(7));
                        t.setCem(rs.getDouble(8));
                        t.setElave_is(rs.getInt(9));
                        t.setMukafatlandirma(rs.getDouble(10));
                        t.setQayib_gunu(rs.getInt(11));
                        t.setDetal_xetasi(rs.getDouble(12));
                        t.setSatin_alma(rs.getDouble(13));
                        t.setBolme(rs.getString(14));
                        t.setAy(rs.getString(15));
                        t.setIl(rs.getInt(16));
                        t.setIsleyir(rs.getInt(17));
                        t.setNe_qeder_isleyib(rs.getString(18));
                        t.setBaslama_tarixi(rs.getString(19));
                        t.setBorc(rs.getDouble(20));
                        t.setYekun_maas(rs.getDouble(21));
                        t.setQeyd(rs.getString(22));
                        list.add(t);
                    }
                    return list;
                }
            } else if (y.equals("Ay")) {
                if (x.equals("Bütün Bölmələr")) {
                    if (z == 0000) {
                        sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                                + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                                + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary";
                        connection();
                        ps = con.prepareStatement(sqlFind);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            Pojo t = new Pojo();
                            t.setId(rs.getInt(1));
                            t.setAd(rs.getString(2));
                            t.setCins(rs.getInt(3));
                            t.setBank(rs.getDouble(4));
                            t.setIlk_maas(rs.getDouble(5));
                            t.setSon_maas(rs.getDouble(6));
                            t.setArtim(rs.getDouble(7));
                            t.setCem(rs.getDouble(8));
                            t.setElave_is(rs.getInt(9));
                            t.setMukafatlandirma(rs.getDouble(10));
                            t.setQayib_gunu(rs.getInt(11));
                            t.setDetal_xetasi(rs.getDouble(12));
                            t.setSatin_alma(rs.getDouble(13));
                            t.setBolme(rs.getString(14));
                            t.setAy(rs.getString(15));
                            t.setIl(rs.getInt(16));
                            t.setIsleyir(rs.getInt(17));
                            t.setNe_qeder_isleyib(rs.getString(18));
                            t.setBaslama_tarixi(rs.getString(19));
                            t.setBorc(rs.getDouble(20));
                            t.setYekun_maas(rs.getDouble(21));
                            t.setQeyd(rs.getString(22));
                            list.add(t);
                        }
                        return list;
                    } else {
                        sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                                + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                                + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary "
                                + "WHERE il = " + z + "";
                        connection();
                        ps = con.prepareStatement(sqlFind);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            Pojo t = new Pojo();
                            t.setId(rs.getInt(1));
                            t.setAd(rs.getString(2));
                            t.setCins(rs.getInt(3));
                            t.setBank(rs.getDouble(4));
                            t.setIlk_maas(rs.getDouble(5));
                            t.setSon_maas(rs.getDouble(6));
                            t.setArtim(rs.getDouble(7));
                            t.setCem(rs.getDouble(8));
                            t.setElave_is(rs.getInt(9));
                            t.setMukafatlandirma(rs.getDouble(10));
                            t.setQayib_gunu(rs.getInt(11));
                            t.setDetal_xetasi(rs.getDouble(12));
                            t.setSatin_alma(rs.getDouble(13));
                            t.setBolme(rs.getString(14));
                            t.setAy(rs.getString(15));
                            t.setIl(rs.getInt(16));
                            t.setIsleyir(rs.getInt(17));
                            t.setNe_qeder_isleyib(rs.getString(18));
                            t.setBaslama_tarixi(rs.getString(19));
                            t.setBorc(rs.getDouble(20));
                            t.setYekun_maas(rs.getDouble(21));
                            t.setQeyd(rs.getString(22));
                            list.add(t);
                        }
                        return list;
                    }
                } else {
                    sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                            + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                            + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary "
                            + "WHERE bolme = '" + x + "' AND il = " + z + "";
                    connection();
                    ps = con.prepareStatement(sqlFind);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Pojo t = new Pojo();
                        t.setId(rs.getInt(1));
                        t.setAd(rs.getString(2));
                        t.setCins(rs.getInt(3));
                        t.setBank(rs.getDouble(4));
                        t.setIlk_maas(rs.getDouble(5));
                        t.setSon_maas(rs.getDouble(6));
                        t.setArtim(rs.getDouble(7));
                        t.setCem(rs.getDouble(8));
                        t.setElave_is(rs.getInt(9));
                        t.setMukafatlandirma(rs.getDouble(10));
                        t.setQayib_gunu(rs.getInt(11));
                        t.setDetal_xetasi(rs.getDouble(12));
                        t.setSatin_alma(rs.getDouble(13));
                        t.setBolme(rs.getString(14));
                        t.setAy(rs.getString(15));
                        t.setIl(rs.getInt(16));
                        t.setIsleyir(rs.getInt(17));
                        t.setNe_qeder_isleyib(rs.getString(18));
                        t.setBaslama_tarixi(rs.getString(19));
                        t.setBorc(rs.getDouble(20));
                        t.setYekun_maas(rs.getDouble(21));
                        t.setQeyd(rs.getString(22));
                        list.add(t);
                    }
                    return list;
                }
            } else if (z == 0000) {
                if (x.equals("Bütün Bölmələr")) {
                    if (y.equals("Ay")) {
                        sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                                + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                                + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary";
                        connection();
                        ps = con.prepareStatement(sqlFind);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            Pojo t = new Pojo();
                            t.setId(rs.getInt(1));
                            t.setAd(rs.getString(2));
                            t.setCins(rs.getInt(3));
                            t.setBank(rs.getDouble(4));
                            t.setIlk_maas(rs.getDouble(5));
                            t.setSon_maas(rs.getDouble(6));
                            t.setArtim(rs.getDouble(7));
                            t.setCem(rs.getDouble(8));
                            t.setElave_is(rs.getInt(9));
                            t.setMukafatlandirma(rs.getDouble(10));
                            t.setQayib_gunu(rs.getInt(11));
                            t.setDetal_xetasi(rs.getDouble(12));
                            t.setSatin_alma(rs.getDouble(13));
                            t.setBolme(rs.getString(14));
                            t.setAy(rs.getString(15));
                            t.setIl(rs.getInt(16));
                            t.setIsleyir(rs.getInt(17));
                            t.setNe_qeder_isleyib(rs.getString(18));
                            t.setBaslama_tarixi(rs.getString(19));
                            t.setBorc(rs.getDouble(20));
                            t.setYekun_maas(rs.getDouble(21));
                            t.setQeyd(rs.getString(22));
                            list.add(t);
                        }
                        return list;
                    } else {
                        sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                                + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                                + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary "
                                + "WHERE ay = '" + y + "'";
                        connection();
                        ps = con.prepareStatement(sqlFind);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            Pojo t = new Pojo();
                            t.setId(rs.getInt(1));
                            t.setAd(rs.getString(2));
                            t.setCins(rs.getInt(3));
                            t.setBank(rs.getDouble(4));
                            t.setIlk_maas(rs.getDouble(5));
                            t.setSon_maas(rs.getDouble(6));
                            t.setArtim(rs.getDouble(7));
                            t.setCem(rs.getDouble(8));
                            t.setElave_is(rs.getInt(9));
                            t.setMukafatlandirma(rs.getDouble(10));
                            t.setQayib_gunu(rs.getInt(11));
                            t.setDetal_xetasi(rs.getDouble(12));
                            t.setSatin_alma(rs.getDouble(13));
                            t.setBolme(rs.getString(14));
                            t.setAy(rs.getString(15));
                            t.setIl(rs.getInt(16));
                            t.setIsleyir(rs.getInt(17));
                            t.setNe_qeder_isleyib(rs.getString(18));
                            t.setBaslama_tarixi(rs.getString(19));
                            t.setBorc(rs.getDouble(20));
                            t.setYekun_maas(rs.getDouble(21));
                            t.setQeyd(rs.getString(22));
                            list.add(t);
                        }
                        return list;
                    }
                } else {
                    sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                            + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                            + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary "
                            + "WHERE bolme = '" + x + "' AND ay = '" + y + "'";
                    connection();
                    ps = con.prepareStatement(sqlFind);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Pojo t = new Pojo();
                        t.setId(rs.getInt(1));
                        t.setAd(rs.getString(2));
                        t.setCins(rs.getInt(3));
                        t.setBank(rs.getDouble(4));
                        t.setIlk_maas(rs.getDouble(5));
                        t.setSon_maas(rs.getDouble(6));
                        t.setArtim(rs.getDouble(7));
                        t.setCem(rs.getDouble(8));
                        t.setElave_is(rs.getInt(9));
                        t.setMukafatlandirma(rs.getDouble(10));
                        t.setQayib_gunu(rs.getInt(11));
                        t.setDetal_xetasi(rs.getDouble(12));
                        t.setSatin_alma(rs.getDouble(13));
                        t.setBolme(rs.getString(14));
                        t.setAy(rs.getString(15));
                        t.setIl(rs.getInt(16));
                        t.setIsleyir(rs.getInt(17));
                        t.setNe_qeder_isleyib(rs.getString(18));
                        t.setBaslama_tarixi(rs.getString(19));
                        t.setBorc(rs.getDouble(20));
                        t.setYekun_maas(rs.getDouble(21));
                        t.setQeyd(rs.getString(22));
                        list.add(t);
                    }
                    return list;
                }
            } else {
                sqlFind = "SELECT id, ad, cins, bank, ilk_maas, son_maas, artim, cem, elave_is_gunu, "
                        + "mukafatlandirma, qayib_gunu, detal_xetasi, satin_alma, bolme, ay, il, "
                        + "isleyir, ne_qeder_isleyib, baslama_tarixi, borc, yekun_maas, qeyd FROM salary "
                        + "WHERE bolme = '" + x + "' AND ay = '" + y + "' AND il = " + z + "";
                connection();
                ps = con.prepareStatement(sqlFind);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Pojo t = new Pojo();
                    t.setId(rs.getInt(1));
                    t.setAd(rs.getString(2));
                    t.setCins(rs.getInt(3));
                    t.setBank(rs.getDouble(4));
                    t.setIlk_maas(rs.getDouble(5));
                    t.setSon_maas(rs.getDouble(6));
                    t.setArtim(rs.getDouble(7));
                    t.setCem(rs.getDouble(8));
                    t.setElave_is(rs.getInt(9));
                    t.setMukafatlandirma(rs.getDouble(10));
                    t.setQayib_gunu(rs.getInt(11));
                    t.setDetal_xetasi(rs.getDouble(12));
                    t.setSatin_alma(rs.getDouble(13));
                    t.setBolme(rs.getString(14));
                    t.setAy(rs.getString(15));
                    t.setIl(rs.getInt(16));
                    t.setIsleyir(rs.getInt(17));
                    t.setNe_qeder_isleyib(rs.getString(18));
                    t.setBaslama_tarixi(rs.getString(19));
                    t.setBorc(rs.getDouble(20));
                    t.setYekun_maas(rs.getDouble(21));
                    t.setQeyd(rs.getString(22));
                    list.add(t);
                }
                return list;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        } finally {
            close();
        }
    }

    public static boolean pass(String pass) {
        try {
            connection();
            String sql = "Select password from pass where password='" + pass + "' ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close();
        }

    }

    public static void update_pass(String pass) {
        try {
            connection();
            String sql = "update pass set password='" + pass + "' ";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

    }

    public static void main(String[] args) {
    }
}
