package mi.logic;

import static mi.logic.MondatFajta.*;

public class Mondat {
    private String mondat;
    private Szo alany;
    private Szo allitmany;
    private Szo targy;
    private MondatFajta fajta;

    public Mondat(String mondat){
        this.mondat = mondat;
        fajta = KIJELENTO;
    }

    public Mondat(String mondat, String fajta) {
        this.mondat = mondat;
        switch (fajta) {
            case "?":
                this.fajta = KERDO;
                break;
            case ".":
                this.fajta = KIJELENTO;
                break;
            case "!":
                this.fajta = FELKIALTO;
                //TODO ...
                break;
            default:
                this.fajta = KIJELENTO;
                //TODO exceptiont kellene dobni...
                System.out.println("Hibas mondatfajta, kijelentonek allitva");
        }
    }

    public String getMondat() {
        return mondat;
    }

    public Szo getAlany() {
        return alany;
    }

    public void setAlany(Szo alany) {
        this.alany = alany;
    }

    public Szo getAllitmany() {
        return allitmany;
    }

    public void setAllitmany(Szo allitmany) {
        this.allitmany = allitmany;
    }

    public MondatFajta getFajta() {
        return fajta;
    }

    public Szo getTargy() {
        return targy;
    }

    public void setTargy(Szo targy) {
        this.targy = targy;
    }

    public String getFajtaString(){
        switch(fajta){
            case KIJELENTO:
                return ".";
            case KERDO:
                return "?";
            case FELKIALTO:
            case FELSZOLITO:
            case OHAJTO:
                return "!";
            default:
                return ".";
        }
    }
}
