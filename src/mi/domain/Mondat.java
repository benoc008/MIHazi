package mi.domain;

import mi.domain.enumok.MondatFajta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mi.domain.enumok.MondatFajta.*;

public class Mondat {
    private String mondat;
    private List<String> vizsgalandoSzavak = new ArrayList<>();
    private Szo alany;
    private Szo allitmany;
    private Szo targy;
    private MondatFajta fajta;

    public Mondat(String mondat){
        this(mondat, ".");
    }

    public Mondat(String mondat, String fajta) {
        this.mondat = mondat;
        Collections.addAll(vizsgalandoSzavak, mondat.split(" "));
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

    public String getSzoSzotobol(Szo szo){
        for(String s : mondat.split(" ")){
            if(szo.getSzo().length() < 3 || s.startsWith(szo.getVegeNelkul())){
                return s;
            }
        }
        return "";
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

    public List<String> getVizsgalandoSzavak() {
        return vizsgalandoSzavak;
    }

    public void torolSzo(String s){
        vizsgalandoSzavak.remove(s);
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
