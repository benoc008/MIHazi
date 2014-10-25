package mi.logic;

public class Szo {
    private String szo;
    private String szofaj;

    public Szo(String szo, String szofaj) {
        this.szo = szo;
        this.szofaj = szofaj;
    }

    public String getSzo() {
        return szo;
    }

    public void setSzo(String szo) {
        this.szo = szo;
    }

    public String getSzofaj() {
        return szofaj;
    }

    public void setSzofaj(String szofaj) {
        this.szofaj = szofaj;
    }
}
