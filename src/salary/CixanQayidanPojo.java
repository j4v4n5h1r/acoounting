package salary;

//@author javanshir
public class CixanQayidanPojo {

    String user_id;
    String tarix;
    int tip;

    public CixanQayidanPojo() {

    }

    public CixanQayidanPojo(String user_id, String tarix, int tip) {
        this.user_id = user_id;
        this.tarix = tarix;
        this.tip = tip;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTarix() {
        return tarix;
    }

    public int getTip() {
        return tip;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTarix(String tarix) {
        this.tarix = tarix;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
}
