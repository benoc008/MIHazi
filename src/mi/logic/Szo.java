package mi.logic;

import java.util.ArrayList;
import java.util.List;

public class Szo {
    private String szo;
    private List<String> szofajok = new ArrayList<>();

    public static Szo createFromCsv(String s){
        Szo ret = new Szo();
        String[] split = s.split(";");
        ret.setSzo(split[0].toLowerCase());
        for(int i = 1; i < split.length; i++){
            ret.addSzofaj(split[i]);
        }
        return ret;
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
