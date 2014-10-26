package mi.logic;

import java.util.List;

public class KerdesValasz {
    public String kerdes;
    public List<String> valaszok;

    public KerdesValasz(String kerdes, List<String> valaszok) {
        this.kerdes = kerdes;
        this.valaszok = valaszok;
    }

    public String getKerdes() {
        return kerdes;
    }

    public void setKerdes(String kerdes) {
        this.kerdes = kerdes;
    }

    public List<String> getValaszok() {
        return valaszok;
    }

    public void setValaszok(List<String> valaszok) {
        this.valaszok = valaszok;
    }
}
