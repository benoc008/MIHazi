package mi.logic;

import mi.domain.Mondat;

import java.util.Random;

public class KerdesGeneralo {

    private static String[] TARGYRA_KERDEZ = {"Miért +", "Milyen", "Mekkora", "Mennyi", "Hány"};
    private static String[] ALLITMANYRA_KERDEZ = {"Hogy", "Hogyan", "Mivel", "Mennyire", "Miért"};

    private Mondat mondat;
    private Random random;

    public String general(Mondat mondat){
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

    private String allitmanybolTargybol() {
        String ret = "";
        ret += getKerdoszoTargyra() + " ";
        ret += mondat.getSzoSzotobol(mondat.getTargy()) + " ";
        ret += mondat.getSzoSzotobol(mondat.getAllitmany()) + "?";
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
