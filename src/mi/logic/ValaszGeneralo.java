package mi.logic;

import mi.domain.Mondat;
import mi.domain.Tudas;

import java.util.List;
import java.util.Random;

public class ValaszGeneralo {
    private static String[] KERDOSZAVAK = {"Mikor", "Hány", "Mennyi", "Miért", "Mivel", "Mire", "Hogy", "Hogyan", "Hányan", "Mit", "Milyen"};

    private static String[] VALASZOK_MIKORRA = {"tegnap", "az előbb", "este", "reggel", "egy órája", "holnap"};
    private static String[] VALASZOK_HANYRA = {"sok", "kevés", "öt"};
    private static String[] VALASZOK_MENNYIRE = {"nagyon", "kicsit"};
    private static String[] VALASZOK_MIERTRE = {"csak", "mert csak"};
    private static String[] VALASZOK_MIVELRE = {"ceruzával", "dömperrel"};
    private static String[] VALASZOK_MIRERE = {"mire hármat számolok :)"};
    private static String[] VALASZOK_HOGYRA = {"nagyon", "kicsit", "remekül", "ügyesen", "jól"};
    private static String[] VALASZOK_HOGYANRA = {"jól", "remekül", "ügyesen"};
    private static String[] VALASZOK_HANYANRA = {"sokan", "kevesen", "százan", "öten"};
    private static String[] VALASZOK_MITRE = {"semmit", "közöd?"};
    private static String[] VALASZOK_MILYENRE ={"nem tudom", "szép"};

    private Mondat mondat;
    private Random random;
    private List<Tudas> tudastar;

    public ValaszGeneralo(List<Tudas> tudastar) {
        this.tudastar = tudastar;
    }

    public String general(Mondat mondat) {
        this.mondat = mondat;
        random = new Random();
        String kerdoszo = kerdoszotKeres().toLowerCase();
        for(Tudas tudas : tudastar){
            String tudasKerdoszo = tudas.getKerdoszo().toLowerCase();
            String tudasKerdezettSzo = tudas.getKerdezettSzo().getSzo().toLowerCase();
            String allitmany = mondat.getAllitmany().getSzo().toLowerCase();
            String targy = mondat.getTargy().getSzo().toLowerCase();
            if(tudasKerdoszo.equals(kerdoszo) && (tudasKerdezettSzo.equals(allitmany) || tudasKerdezettSzo.equals(targy))){
                return tudas.getValasz().getMondat();
            }
        }
        switch (kerdoszo) {
            case "mikor":
                return valaszKerdoszora(VALASZOK_MIKORRA);
            case "hány":
                return valaszKerdoszora(VALASZOK_HANYRA);
            case "mennyi":
                return valaszKerdoszora(VALASZOK_MENNYIRE);
            case "miért":
                return valaszKerdoszora(VALASZOK_MIERTRE);
            case "mivel":
                return valaszKerdoszora(VALASZOK_MIVELRE);
            case "mire":
                return valaszKerdoszora(VALASZOK_MIRERE);
            case "hogy":
                return valaszKerdoszora(VALASZOK_HOGYRA);
            case "hogyan":
                return valaszKerdoszora(VALASZOK_HOGYANRA);
            case "hányan":
                return valaszKerdoszora(VALASZOK_HANYANRA);
            case "mit":
                return valaszKerdoszora(VALASZOK_MITRE);
            case "milyen":
                return valaszKerdoszora(VALASZOK_MILYENRE);
            case "nem":
                if (random.nextInt(2) == 0) {
                    return "De.";
                } else {
                    return "Nem.";
                }

            default:
                if (random.nextInt(2) == 0) {
                    return "Igen.";
                } else {
                    return "Nem.";
                }
        }
    }

    private String valaszKerdoszora(String[] valaszTomb) {
        return valaszTomb[random.nextInt(valaszTomb.length - 1)];

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
