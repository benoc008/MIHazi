package mi.domain;

import mi.domain.enumok.IgeragozasiRendszer;
import mi.domain.enumok.MondatFajta;
import mi.logic.KerdesGeneralo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mi.domain.enumok.MondatFajta.*;
import static mi.helperek.FileMuveletHelper.logol;

public class Mondat implements Serializable{
    private String mondat;
    private List<String> vizsgalandoSzavak = new ArrayList<>();
    private Szo alany;
    private Szo allitmany;
    private Szo targy;
    private MondatFajta fajta;
    private IgeragozasiRendszer rendszer = IgeragozasiRendszer.ALANYI;

    public Mondat(String mondat){
        this(mondat, ".");
    }

    public Mondat(String mondat, String fajta) {
        this.mondat = mondat.toLowerCase();
        if(mondat.startsWith(" ")){
            this.mondat = this.mondat.substring(1);
        }
        Collections.addAll(vizsgalandoSzavak, this.mondat.split(" "));
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
                logol("Nincs irasjel, kijelentonek allitva");
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

    public IgeragozasiRendszer getRendszer() {
        return rendszer;
    }

    public void torolSzo(String s){
        int index = vizsgalandoSzavak.indexOf(s);
        if(index > 0 && "az".contains(vizsgalandoSzavak.get(index - 1))){
            rendszer = IgeragozasiRendszer.TARGYAS;
            vizsgalandoSzavak.remove(index - 1);
        }
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
