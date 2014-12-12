package mi.logic;

import mi.domain.Mondat;
import mi.domain.Szo;
import mi.domain.enumok.*;

import java.util.Random;

import static mi.domain.enumok.IgeragozasiRendszer.ALANYI;
import static mi.domain.enumok.IgeragozasiRendszer.TARGYAS;

public class KerdesGeneralo {

    public static String[] TARGYRA_KERDEZ_ALANYI = {"Milyen", "Mekkora", "Mennyi", "Hány"}; // mit
    public static String[] TARGYRA_KERDEZ_TARGYAS = {"Miért +"};
    public static String[] TARGYRA_KERDEZ = {"Miért +", "Milyen", "Mekkora", "Mennyi", "Hány"}; // TODO nem kellene ez..

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
        Szo valasz = allitmanySzamSzemelyBeallito();
        ret += valasz.getSzoRagozva() + "?";
        return ret;
    }

    private Szo allitmanySzamSzemelyBeallito() {
        Szo valasz = mondat.getAllitmany();
        if(mondat.getAllitmany().getIgeRagok() != null){
            if(valasz.getIgeRagok().getSzamSzemely().equals(SzamSzemely.EGYES_SZAM_ELSO_SZEMELY)){
                valasz.getIgeRagok().setSzamSzemely(SzamSzemely.EGYES_SZAM_MASODIK_SZEMELY);
            } else if(valasz.getIgeRagok().getSzamSzemely().equals(SzamSzemely.EGYES_SZAM_MASODIK_SZEMELY)){
                valasz.getIgeRagok().setSzamSzemely(SzamSzemely.EGYES_SZAM_ELSO_SZEMELY);
            } else if(valasz.getIgeRagok().getSzamSzemely().equals(SzamSzemely.TOBBES_SZAM_ELSO_SZEMELY)){
                valasz.getIgeRagok().setSzamSzemely(SzamSzemely.TOBBES_SZAM_MASODIK_SZEMELY);
            } else if(valasz.getIgeRagok().getSzamSzemely().equals(SzamSzemely.TOBBES_SZAM_MASODIK_SZEMELY)){
                valasz.getIgeRagok().setSzamSzemely(SzamSzemely.TOBBES_SZAM_ELSO_SZEMELY);
            }
        }
        return valasz;
    }

    private String targybol() {
        IgeragozasiRendszer ragozas;
        Szo valasz = mondat.getTargy();
        String ret = "";
        //TODO ez a resz nem tetszik
        if(random.nextInt(2) == 0){
            ragozas = ALANYI;
        } else {
            ragozas = TARGYAS;
        }
        ret += getKerdoszoTargyra(ragozas) + " ";

        ret += valasz.getSzoRagozva() + "?";
        return ret;
    }

    private String allitmanybolTargybol() throws Exception {
        String ret = "";
        Szo valaszAllitmany = allitmanySzamSzemelyBeallito();
        Szo valaszTargy = mondat.getTargy();
        IgeragozasiRendszer ragozas;
        if(random.nextInt(2) == 0){
            ragozas = ALANYI;
        } else {
            ragozas = TARGYAS;
        }

        ret += getKerdoszoTargyra(ragozas) + " ";
        ret += valaszTargy.getSzoRagozva() + " ";

        if(valaszAllitmany.getIgeRagok() != null) {
            valaszAllitmany.getIgeRagok().setIgeragozasiRendszer(ragozas);
            ret += valaszAllitmany.getSzoRagozva() + "?";
        } else {
            ret += valaszAllitmany.getSzo() + "?";
        }
        return ret;
    }

    private String getKerdoszoTargyra(IgeragozasiRendszer ragozas) {
        String kerdoszo;
        if(ragozas.equals(TARGYAS)){
            kerdoszo = TARGYRA_KERDEZ_TARGYAS[random.nextInt(TARGYRA_KERDEZ_TARGYAS.length)];
        } else {
            kerdoszo = TARGYRA_KERDEZ_ALANYI[random.nextInt(TARGYRA_KERDEZ_ALANYI.length)];
        }
        if(kerdoszo.contains("+")){
            return kerdoszo.replace("+", Nevelo.get(mondat.getTargy().getSzo()));
        }
        return kerdoszo;
    }

    private String getKerdoszoAllitmanyra(){
        return ALLITMANYRA_KERDEZ[random.nextInt(ALLITMANYRA_KERDEZ.length)];
    }


}
