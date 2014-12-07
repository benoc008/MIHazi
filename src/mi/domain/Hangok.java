package mi.domain;

import mi.domain.enumok.Maganhangzo;
import mi.domain.enumok.Rend;

import static mi.domain.enumok.Maganhangzo.HOSSZU;
import static mi.domain.enumok.Maganhangzo.ROVID;

public class Hangok {
    public static String[] MAGANHANGZOK = {"a", "á", "o", "ó", "u", "ú", "e", "é", "i", "í", "ö", "ő", "ü", "ű"};
    public static String[] MELY_MAGANHANGZO = {"a", "á", "o", "ó", "u", "ú"};
    public static String[] MAGAS_MAGANHANGZO = {"e", "é", "i", "í", "ö", "ő", "ü", "ű"};
    public static String[] HOSSZU_MAGANHANGZO = {"é", "í", "ő", "ű", "á", "ó", "ú"};
    public static String[] ROVID_MAGANHANGZO = {"e", "i", "ö", "ü", "a", "o", "u"};


    public static String getUtolsoMaganhangzo(Szo szo) throws Exception {
        for (int i = szo.getSzo().length() - 1; i >= 0; i--) {
            for (String s : MAGANHANGZOK) {
                if (s.equals(szo.getSzo().substring(i, i + 1))) {
                    return s;
                }
            }
        }
        throw new Exception("Nincs maganhangzo a szoban"); //TODO valami normalis hibakiiro
    }

    public static boolean isUtolsoHangMaganhangzo(Szo szo) {
        for (String s : MAGANHANGZOK) {
            if (szo.getSzo().endsWith(s)) {
                return true;
            }
        }
        return false;
    }

    public static Rend getHangrend(Szo szo) {
        boolean mely = false, magas = false;
        for (String s : MELY_MAGANHANGZO) {
            if (szo.getSzo().contains(s)) {
                mely = true;
            }
        }
        for (String s : MAGAS_MAGANHANGZO) {
            if (szo.getSzo().contains(s)) {
                magas = true;
            }
        }
        if (mely && magas) {
            return Rend.VEGYES;
        } else if (mely) {
            return Rend.MELY;
        } else if (magas) {
            return Rend.MAGAS;
        }
        return Rend.VEGYES;
    }

    public static String rovidetHosszura(String s) {
        for (int i = 0; i < ROVID_MAGANHANGZO.length; i++) {
            if (s.equals(ROVID_MAGANHANGZO[i])) {
                return HOSSZU_MAGANHANGZO[i];
            } else if (s.equals(HOSSZU_MAGANHANGZO[i])) {
                return HOSSZU_MAGANHANGZO[i];
            }
        }
        throw new RuntimeException("Nem maganhangzo.");
    }

    public static Maganhangzo getUtolsoMaganhangzoFajtaja(Szo szo) {
        String szoto = szo.getSzo();
        for (String s : Hangok.HOSSZU_MAGANHANGZO) {
            if (s.equals(szoto.substring(szoto.length() - 1, szoto.length()))) {
                return HOSSZU;
            }
        }
        for (String s : Hangok.ROVID_MAGANHANGZO) {
            if (s.equals(szoto.substring(szoto.length() - 1, szoto.length()))) {
                return ROVID;
            }
        }

        return null;
    }

    public static int maganhangzokSzama(Szo szo) {
        int maganhangzokSzama = 0;
        for (int i = 0; i < szo.getSzo().length(); i++) {
            for (String s : MAGANHANGZOK) {
                if (szo.getSzo().substring(i, i + 1).equals(s)) {
                    maganhangzokSzama++;
                }
            }
        }
        return maganhangzokSzama;
    }

    public static boolean mindOU(Szo szo) { // öőüű
        for (int i = 0; i < szo.getSzo().length(); i++) {
            if ("iíoóeéaáuú".contains(szo.getSzo().substring(i, i + 1))) {
                return false;
            }

        }
        return true;
    }
}
