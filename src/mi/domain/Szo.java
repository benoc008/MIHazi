package mi.domain;

import mi.domain.enumok.FonevRag;
import mi.logic.FonevRagozo;
import mi.logic.IgeRagozo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Szo implements Serializable{
    private String szo;
    private List<String> szofajok = new ArrayList<>();
    private IgeRagok igeRagok;
    private List<FonevRag> fonevRagok = new ArrayList<>();

    public static Szo createFromCsv(String s){
        Szo ret = new Szo();
        String[] split = s.split(";");
        ret.setSzo(split[0].toLowerCase());
        for(int i = 1; i < split.length; i++){
            ret.addSzofaj(split[i]);
        }
        return ret;
    }

    public String getVegeNelkul(){
        return szo.substring(0, szo.length() - 2);
    }

    public String getSzo() {
        return szo;
    }

    public void setSzo(String szo) {
        this.szo = szo;
    }

    public List<String> getSzofajok() {
        return szofajok;
    }

    public void addSzofaj(String szofaj) {
        this.szofajok.add(szofaj);
    }

    public void setSzofajok(List<String> szofajok) {
        this.szofajok = szofajok;
    }

    public IgeRagok getIgeRagok() {
        return igeRagok;
    }

    public void setIgeRagok(IgeRagok igeRagok) {
        this.igeRagok = igeRagok;
    }

    public List<FonevRag> getFonevRagok() {
        return fonevRagok;
    }

    public void addFonevRag(FonevRag fonevRag) {
        if(fonevRag != null) {
            this.fonevRagok.add(fonevRag);
        }
    }

    public String getSzoRagozva(){
        if(igeRagok != null){
            return IgeRagozo.ragoz(this, igeRagok.getSzamSzemely(), igeRagok.getMod(), igeRagok.getIdo(), igeRagok.getIgeragozasiRendszer());
        } else if(!fonevRagok.isEmpty()) {
            return FonevRagozo.ragoz(this, fonevRagok.toArray(new FonevRag[fonevRagok.size()]));
        } else {
            return szo;
        }
    }

    @Override
    public String toString(){
        String ret = szo + ";";
        for(int i = 0; i < szofajok.size(); i++){
            ret += szofajok.get(i);
            if(i < szofajok.size() - 1){
                ret += ";";
            }
        }
        return ret;
    }
}
