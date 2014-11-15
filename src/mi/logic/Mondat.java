package mi.logic;

import static mi.logic.MondatFajta.*;

public class Mondat {
    private String mondat;
    private String alany;
    private String allitmany;
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

    public String getAlany() {
        return alany;
    }

    public String getAllitmany() {
        return allitmany;
    }

    public MondatFajta getFajta() {
        return fajta;
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
