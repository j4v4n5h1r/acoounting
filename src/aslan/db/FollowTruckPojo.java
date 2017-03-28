package aslan.db;

//@author javanshirmammadov
import java.io.Serializable;

public class FollowTruckPojo implements Serializable {

    private int Id;
    private String Tarix;
    private Double IlkinKM;
    private Double YekunKM;
    private Double Ferq;
    private Double Miqdar;
    private Double Qiymet_man;
    private Double Kurs;
    private Double Yekun_dol;
    private Double Yekun_man;
    private String Ad;

    public FollowTruckPojo() {
    }

    public FollowTruckPojo(int Id, String Tarix, Double IlkinKM, Double YekunKM, Double Ferq, Double Miqdar, Double Qiymet_man, Double Kurs, Double Yekun_dol, Double Yekun_man, String Ad) {
        this.Id = Id;
        this.Tarix = Tarix;
        this.IlkinKM = IlkinKM;
        this.YekunKM = YekunKM;
        this.Ferq = Ferq;
        this.Miqdar = Miqdar;
        this.Qiymet_man = Qiymet_man;
        this.Kurs = Kurs;
        this.Yekun_dol = Yekun_dol;
        this.Yekun_man = Yekun_man;
        this.Ad = Ad;
    }

    public int getId() {
        return Id;
    }

    public String getTarix() {
        return Tarix;
    }

    public Double getIlkinKM() {
        return IlkinKM;
    }

    public Double getYekunKM() {
        return YekunKM;
    }

    public Double getFerq() {
        return Ferq;
    }

    public Double getMiqdar() {
        return Miqdar;
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

    public String getAd() {
        return Ad;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setTarix(String Tarix) {
        this.Tarix = Tarix;
    }

    public void setIlkinKM(Double IlkinKM) {
        this.IlkinKM = IlkinKM;
    }

    public void setYekunKM(Double YekunKM) {
        this.YekunKM = YekunKM;
    }

    public void setFerq(Double Ferq) {
        this.Ferq = Ferq;
    }

    public void setMiqdar(Double Miqdar) {
        this.Miqdar = Miqdar;
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

    public void setAd(String Ad) {
        this.Ad = Ad;
    }

}
