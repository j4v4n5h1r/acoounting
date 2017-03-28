package aslan.db;

//@author javanshir
import java.io.Serializable;

public class ExpensePojo implements Serializable {

    private int Id;
    private String Tarix;
    private String Malin_adi;
    private Double Miqdar;
    private String Olcu_vahidi;
    private Double Qiymet_dol;
    private Double Qiymet_man;
    private Double Kurs;
    private Double Yekun_dol;
    private Double Yekun_man;
    private String Qeyd;
    private String Qeyd_2;
    private String Qeyd_3;

    public ExpensePojo() {
    }

    public ExpensePojo(int Id, String Tarix, String Malin_adi, Double Miqdar, String Olcu_vahidi, Double Qiymet_dol, Double Qiymet_man, Double Kurs, Double Yekun_dol, Double Yekun_man, String Qeyd, String Qeyd_2, String Qeyd_3) {
        this.Id = Id;
        this.Tarix = Tarix;
        this.Malin_adi = Malin_adi;
        this.Miqdar = Miqdar;
        this.Olcu_vahidi = Olcu_vahidi;
        this.Qiymet_dol = Qiymet_dol;
        this.Qiymet_man = Qiymet_man;
        this.Kurs = Kurs;
        this.Yekun_dol = Yekun_dol;
        this.Yekun_man = Yekun_man;
        this.Qeyd = Qeyd;
        this.Qeyd_2 = Qeyd_2;
        this.Qeyd_3 = Qeyd_3;
    }

    public int getId() {
        return Id;
    }

    public String getTarix() {
        return Tarix;
    }

    public String getMalin_adi() {
        return Malin_adi;
    }

    public Double getMiqdar() {
        return Miqdar;
    }

    public String getOlcu_vahidi() {
        return Olcu_vahidi;
    }

    public Double getQiymet_dol() {
        return Qiymet_dol;
    }

    public Double getQiymet_man() {
        return Qiymet_man;
    }

    public Double getKurs() {
        return Kurs;
    }

    public Double getYekun_dol() {
        return Yekun_dol;
    }

    public Double getYekun_man() {
        return Yekun_man;
    }

    public String getQeyd() {
        return Qeyd;
    }

    public String getQeyd_2() {
        return Qeyd_2;
    }

    public String getQeyd_3() {
        return Qeyd_3;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setTarix(String Tarix) {
        this.Tarix = Tarix;
    }

    public void setMalin_adi(String Malin_adi) {
        this.Malin_adi = Malin_adi;
    }

    public void setMiqdar(Double Miqdar) {
        this.Miqdar = Miqdar;
    }

    public void setOlcu_vahidi(String Olcu_vahidi) {
        this.Olcu_vahidi = Olcu_vahidi;
    }

    public void setQiymet_dol(Double Qiymet_dol) {
        this.Qiymet_dol = Qiymet_dol;
    }

    public void setQiymet_man(Double Qiymet_man) {
        this.Qiymet_man = Qiymet_man;
    }

    public void setKurs(Double Kurs) {
        this.Kurs = Kurs;
    }

    public void setYekun_dol(Double Yekun_dol) {
        this.Yekun_dol = Yekun_dol;
    }

    public void setYekun_man(Double Yekun_man) {
        this.Yekun_man = Yekun_man;
    }

    public void setQeyd(String Qeyd) {
        this.Qeyd = Qeyd;
    }

    public void setQeyd_2(String Qeyd_2) {
        this.Qeyd_2 = Qeyd_2;
    }

    public void setQeyd_3(String Qeyd_3) {
        this.Qeyd_3 = Qeyd_3;
    }
}
