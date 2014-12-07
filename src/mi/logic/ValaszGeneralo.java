package mi.logic;

import mi.domain.Mondat;

import java.util.Random;

public class ValaszGeneralo {
    private static String[] KERDOSZAVAK = {"Mikor", "Hány", "Mennyi", "Miért", "Mivel", "Hogy", "Hogyan", "Hány", "Hányan"};

    private static String[] MULT_IDOHATAROZOK = {"tegnap", "az előbb", "este", "reggel", "egy órája"};

    private Mondat mondat;
    private Random random;

    public String general(Mondat mondat) {
        this.mondat = mondat;
        random = new Random();
        String kerdoszo = kerdoszotKeres();
        switch (kerdoszo){
            case "Mikor":
                return valaszMikorra();


            default:
                return "asd";
        }
    }

    private String valaszMikorra() {
        return MULT_IDOHATAROZOK[random.nextInt(MULT_IDOHATAROZOK.length - 1)];

    }

    private String kerdoszotKeres() {
        for(String szo : mondat.getMondat().split(" ")){
            for(String kerdoszo : KERDOSZAVAK) {
                if (szo.toLowerCase().equals(kerdoszo.toLowerCase())){
                    return kerdoszo;
                }
            }
        }
        return "";
    }
}
