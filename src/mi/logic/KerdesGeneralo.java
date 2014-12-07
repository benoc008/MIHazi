package mi.logic;

import mi.domain.Mondat;
import mi.domain.enumok.Ido;
import mi.domain.enumok.IgeragozasiRendszer;
import mi.domain.enumok.Mod;
import mi.domain.enumok.SzamSzemely;

import java.util.Random;

public class KerdesGeneralo {

    public static String[] TARGYRA_KERDEZ = {"Miért +", "Milyen", "Mekkora", "Mennyi", "Hány", "Mit"};
    public static String[] ALLITMANYRA_KERDEZ = {"Hogy", "Hogyan", "Mivel", "Mennyire", "Miért"};

    private Mondat mondat;
    private Random random;

    public String general(Mondat mondat) throws Exception {
        this.mondat = mondat;
        this.random = new Random();
        if(mondat.getTargy() != null && mondat.getAllitmany() != null){
            return allitmanybolTargybol();
        } else if(mondat.getTargy() != null){
            return targybol();
        } else if(mondat.getAllitmany() != null){
            return allitmanybol();
        } else {
            return "";
        }
    }

    private String allitmanybol() {
        String ret = "";
        ret += getKerdoszoAllitmanyra() + " ";
        ret += mondat.getSzoSzotobol(mondat.getAllitmany()) + "?";
        return ret;
    }

    private String targybol() {
        String ret = "";
        ret += getKerdoszoTargyra() + " ";
        ret += mondat.getSzoSzotobol(mondat.getTargy()) + "?";
        return ret;
    }

    private String allitmanybolTargybol() throws Exception {
        String ret = "";
        ret += getKerdoszoTargyra() + " ";
        ret += mondat.getSzoSzotobol(mondat.getTargy()) + " ";
        if(mondat.getAlany() != null) {
            if(ret.contains(" a ") || ret.contains(" az ")) {
                ret += IgeRagozo.ragoz(mondat.getAllitmany(), SzamSzemely.EGYES_SZAM_HARMADIK_SZEMELY, Mod.KIJELENTO, Ido.JELEN, IgeragozasiRendszer.TARGYAS) + "?";
            } else {
                ret += IgeRagozo.ragoz(mondat.getAllitmany(), SzamSzemely.EGYES_SZAM_HARMADIK_SZEMELY, Mod.KIJELENTO, Ido.JELEN, IgeragozasiRendszer.ALANYI) + "?";
            }
        } else {
            ret += mondat.getSzoSzotobol(mondat.getAllitmany()) + "?";
        }
        return ret;
    }

    private String getKerdoszoTargyra() {
        String kerdoszo = TARGYRA_KERDEZ[random.nextInt(TARGYRA_KERDEZ.length)];
        if(kerdoszo.contains("+")){
            return kerdoszo.replace("+", Nevelo.get(mondat.getTargy().getSzo()));
        }
        return kerdoszo;
    }

    private String getKerdoszoAllitmanyra(){
        return ALLITMANYRA_KERDEZ[random.nextInt(ALLITMANYRA_KERDEZ.length)];
    }


}
