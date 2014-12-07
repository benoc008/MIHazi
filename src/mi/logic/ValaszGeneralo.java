package mi.logic;

import mi.domain.Mondat;
import mi.domain.Tudas;

import java.util.List;
import java.util.Random;

public class ValaszGeneralo {
    private static String[] KERDOSZAVAK = {"Mikor", "Hány", "Mennyi", "Miért", "Mivel", "Hogy", "Hogyan", "Hány", "Hányan"};

    private static String[] MULT_IDOHATAROZOK = {"tegnap", "az előbb", "este", "reggel", "egy órája"};

    private Mondat mondat;
    private Random random;
    private List<Tudas> tudastar;

    public ValaszGeneralo(List<Tudas> tudastar) {
        this.tudastar = tudastar;
    }

    public String general(Mondat mondat) {
        this.mondat = mondat;
        random = new Random();
        String kerdoszo = kerdoszotKeres();
        switch (kerdoszo){
            case "Mikor":
                return valaszMikorra();


            default:
                if(random.nextInt(2) == 0){
                    return "Igen.";
                } else {
                    return "Nem.";
                }
        }
    }

    private String valaszMikorra() {
        return MULT_IDOHATAROZOK[random.nextInt(MULT_IDOHATAROZOK.length - 1)];

    }

    public String kerdoszotKeres() {
        for(String szo : mondat.getMondat().split(" ")){
            for(String kerdoszo : KERDOSZAVAK) {
                if (szo.toLowerCase().equals(kerdoszo.toLowerCase())){
                    return kerdoszo;
                }
            }
        }
        return "";
    }

    //TODO ezt valami helperbe
    public static String kerdoszotKeres(String mondat) {
        for(String szo : mondat.split(" ")){
            for(String kerdoszo : KERDOSZAVAK) {
                if (szo.toLowerCase().equals(kerdoszo.toLowerCase())){
                    return kerdoszo;
                }
            }
        }
        return "";
    }
}
