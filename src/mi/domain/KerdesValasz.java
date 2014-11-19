package mi.domain;

import java.util.ArrayList;
import java.util.List;

public class KerdesValasz {
    public String kerdes;
    public List<String> valaszok;

    public KerdesValasz(String kerdes, List<String> valaszok) {
        this.kerdes = kerdes;
        this.valaszok = valaszok;
    }
    public KerdesValasz(){
        valaszok = new ArrayList<>();
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

    public void addValasz(String valasz){
        valaszok.add(valasz);
    }

    public String getValaszKiir(int i){
        return "V:" + " " + valaszok.get(i);

    }

    public static List<KerdesValasz> createListFromSorok(List<Sor> sorok){
        List<KerdesValasz> ret = new ArrayList<>();
        int i = 0;
        while(i < sorok.size()){
            if(sorok.get(i).tipus.equals("Kérdés")){
                String kerdes = sorok.get(i).sor;
                List<String> valaszok = new ArrayList<>();
                i++;
                while(i < sorok.size() && sorok.get(i).tipus.equals("Válasz")){
                    valaszok.add(sorok.get(i).sor);
                    i++;
                }
                ret.add(new KerdesValasz(kerdes,valaszok));
            }
        }
        return ret;
    }
}
