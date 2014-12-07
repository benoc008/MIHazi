package mi.logic;

import mi.domain.Szo;
import mi.domain.enumok.*;

import static mi.domain.Hangok.*;
import static mi.domain.enumok.Ido.*;
import static mi.domain.enumok.IgeragozasiRendszer.ALANYI;
import static mi.domain.enumok.IgeragozasiRendszer.TARGYAS;
import static mi.domain.enumok.Mod.*;

public class IgeRagozo {

    public static String ragoz(Szo szo, SzamSzemely szamSzemely, Mod mod, Ido ido, IgeragozasiRendszer rendszer) throws Exception {
        if (!szo.getSzofajok().contains("ige")) {
            return "ERROR"; //TODO ez nem jo
        }

        if (ido == JELEN && mod == KIJELENTO && rendszer == ALANYI) {
            return jelenKijelentoAlanyi(szo, szamSzemely);
        } else if (ido == JELEN && mod == KIJELENTO && rendszer == TARGYAS) {
            return jelenKijelentoTargyas(szo, szamSzemely);
        } else if (ido == MULT && mod == KIJELENTO && rendszer == ALANYI) {
            return multKijelentoAlanyi(szo, szamSzemely);
        } else if (ido == MULT && mod == KIJELENTO && rendszer == TARGYAS) {
            return multKijelentoTargyas(szo, szamSzemely);
        } else if (ido == JOVO && mod == KIJELENTO && rendszer == ALANYI) {
            return jovoKijelentoAlanyi(szo, szamSzemely);
        } else if (ido == JOVO && mod == KIJELENTO && rendszer == TARGYAS) {
            return jovoKijelentoTargyas(szo, szamSzemely);
        } else if (ido == JELEN && mod == FELTETELES && rendszer == ALANYI) {
            return jelenFeltetelesAlanyi(szo, szamSzemely);
        } else if (ido == JELEN && mod == FELTETELES && rendszer == TARGYAS) {
            return jelenFeltetelesTárgyas(szo, szamSzemely);
        } else if (ido == MULT && mod == FELTETELES && rendszer == ALANYI) {
            return multFeltetelesAlanyi(szo, szamSzemely);
        } else if (ido == MULT && mod == FELTETELES && rendszer == TARGYAS) {
            return multFeltetelesTargyas(szo, szamSzemely);
        } else if (ido == JELEN && mod == FELSZOLITO && rendszer == ALANYI) {

        } else if (ido == JELEN && mod == FELSZOLITO && rendszer == TARGYAS) {

        }

        return "";
    }

    private static String jelenKijelentoAlanyi(Szo szo, SzamSzemely szamSzemely) throws Exception {
        String szoto = szo.getSzo();
        String veg = getVeg(szoto);
        Rend rend = getHangrend(szo);

        String utolsoMaganhangzo = getUtolsoMaganhangzo(szo);

        switch (szamSzemely) {
            case EGYES_SZAM_ELSO_SZEMELY:
                if (veg.equals("ik")) {
                    if ("eé".contains(utolsoMaganhangzo)) {
                        return szoto.substring(0, szoto.length() - 2) + "em";
                    } else if ("üűöő".contains(utolsoMaganhangzo)) {
                        return szoto.substring(0, szoto.length() - 2) + "öm";
                    } else {
                        return szoto.substring(0, szoto.length() - 2) + "om";
                    }
                } else {
                    if ("eé".contains(utolsoMaganhangzo)) {
                        return szoto + "ek";
                    } else if ("üűöő".contains(utolsoMaganhangzo)) {
                        return szoto + "ök";
                    } else {
                        return szoto + "ok";
                    }
                }

            case EGYES_SZAM_MASODIK_SZEMELY:
                if (veg.equals("ik")) {
                    if ("eé".contains(utolsoMaganhangzo)) {
                        return szoto.substring(0, szoto.length() - 2) + "el";
                    } else if ("üűöő".contains(utolsoMaganhangzo)) {
                        return szoto.substring(0, szoto.length() - 2) + "öl";
                    } else {
                        return szoto.substring(0, szoto.length() - 2) + "ol";
                    }
                } else if (veg.equals("sz")) {
                    if ("eé".contains(utolsoMaganhangzo)) {
                        return szoto + "el";
                    } else if ("üűöő".contains(utolsoMaganhangzo)) {
                        return szoto + "öl";
                    } else {
                        return szoto + "ol";
                    }
                } else {
                    return szoto + "sz";
                }

            case EGYES_SZAM_HARMADIK_SZEMELY:
                return szoto;

            case TOBBES_SZAM_ELSO_SZEMELY:
                if (veg.equals("ik")) {
                    szoto = szoto.substring(0, szoto.length() - 2);
                }
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "unk";
                } else {
                    return szoto + "ünk";
                }

            case TOBBES_SZAM_MASODIK_SZEMELY:
                if (veg.equals("ik")) {
                    szoto = szoto.substring(0, szoto.length() - 2);
                }
                if ("eé".contains(utolsoMaganhangzo)) {
                    return szoto + "tek";
                } else if ("üűöő".contains(utolsoMaganhangzo)) {
                    return szoto + "tök";
                } else {
                    return szoto + "tok";
                }
                //TODO tölt(ö)tök ezt hogy?

            case TOBBES_SZAM_HARMADIK_SZEMELY:
                if (veg.equals("ik")) {
                    szoto = szoto.substring(0, szoto.length() - 2);
                }
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nak";
                } else {
                    return szoto + "nek";
                }
                //TODO tölt(e)nek ezt hogy?

            default:
                return "";
        }
    }

    private static String jelenKijelentoTargyas(Szo szo, SzamSzemely szamSzemely) throws Exception {
        String szoto = szo.getSzo();
        String veg = getVeg(szoto);
        Rend rend = getHangrend(szo);

        String utolsoMaganhangzo = getUtolsoMaganhangzo(szo);

        switch (szamSzemely) {
            case EGYES_SZAM_ELSO_SZEMELY:
                if (veg.equals("ik")) {
                    if ("eé".contains(utolsoMaganhangzo)) {
                        return szoto.substring(0, szoto.length() - 2) + "em";
                    } else if ("üűöő".contains(utolsoMaganhangzo)) {
                        return szoto.substring(0, szoto.length() - 2) + "öm";
                    } else {
                        return szoto.substring(0, szoto.length() - 2) + "om";
                    }
                } else {
                    if ("eé".contains(utolsoMaganhangzo)) {
                        return szoto + "em";
                    } else if ("üűöő".contains(utolsoMaganhangzo)) {
                        return szoto + "öm";
                    } else {
                        return szoto + "om";
                    }
                }

            case EGYES_SZAM_MASODIK_SZEMELY:
                if (veg.equals("ik")) {
                    if ("eé".contains(utolsoMaganhangzo)) {
                        return szoto.substring(0, szoto.length() - 2) + "ed";
                    } else if ("üűöő".contains(utolsoMaganhangzo)) {
                        return szoto.substring(0, szoto.length() - 2) + "öd";
                    } else {
                        return szoto.substring(0, szoto.length() - 2) + "od";
                    }
                } else {
                    if ("eé".contains(utolsoMaganhangzo)) {
                        return szoto + "ed";
                    } else if ("üűöő".contains(utolsoMaganhangzo)) {
                        return szoto + "öd";
                    } else {
                        return szoto + "od";
                    }
                }

            case EGYES_SZAM_HARMADIK_SZEMELY:
                if (veg.equals("ik")) {
                    szoto = szoto.substring(0, szoto.length() - 2);
                }
                if (veg.equals("sz")) {
                    if (szoto.endsWith("sz")) {
                        return szoto.substring(0, szoto.length() - 2) + "ssza";
                    } else if (szoto.endsWith("s")) {
                        return szoto + "sa";
                    } else if (szoto.endsWith("z")) {
                        return szoto + "za";
                    }
                } else {
                    if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                        return szoto + "ja";
                    } else {
                        return szoto + "i";
                    }
                }

            case TOBBES_SZAM_ELSO_SZEMELY:
                if (veg.equals("ik")) {
                    szoto = szoto.substring(0, szoto.length() - 2);
                }
                if (veg.equals("sz")) {
                    if (szoto.endsWith("sz")) {
                        if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                            return szoto.substring(0, szoto.length() - 2) + "sszuk";
                        } else {
                            return szoto.substring(0, szoto.length() - 2) + "sszük";
                        }
                    } else if (szoto.endsWith("s")) {
                        if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                            return szoto + "suk";
                        } else {
                            return szoto + "sük";
                        }
                    } else if (szoto.endsWith("z")) {
                        if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                            return szoto + "zuk";
                        } else {
                            return szoto + "zük";
                        }
                    }
                } else {
                    if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                        return szoto + "juk";
                    } else {
                        return szoto + "jük";
                    }
                }

            case TOBBES_SZAM_MASODIK_SZEMELY:
                if (veg.equals("ik")) {
                    szoto = szoto.substring(0, szoto.length() - 2);
                }
                if (veg.equals("sz")) {
                    if (szoto.endsWith("s")) {
                        return szoto + "sátok";
                    } else if (szoto.endsWith("z")) {
                        return szoto + "zátok";
                    }
                } else {
                    if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                        return szoto + "játok";
                    } else {
                        return szoto + "itek";
                    }
                }

            case TOBBES_SZAM_HARMADIK_SZEMELY:
                if (veg.equals("ik")) {
                    szoto = szoto.substring(0, szoto.length() - 2);
                }
                if (veg.equals("sz")) {
                    if (szoto.endsWith("s")) {
                        return szoto + "sák";
                    } else if (szoto.endsWith("z")) {
                        return szoto + "zák";
                    }
                } else {
                    if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                        return szoto + "ják";
                    } else {
                        return szoto + "ik";
                    }
                }

            default:
                return "";
        }
    }

    private static String multKijelentoAlanyi(Szo szo, SzamSzemely szamSzemely) throws Exception {
        String szoto = szo.getSzo();
        String veg = getVeg(szoto);
        if (veg.equals("ik")) {
            szoto = szoto.substring(0, szoto.length() - 2);
        }

        Rend rend = getHangrend(szo);
        String utolsoMaganhangzo = getUtolsoMaganhangzo(szo);


        switch (szamSzemely) {
            case EGYES_SZAM_ELSO_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tam";
                } else {
                    return szoto + "tem";
                }

            case EGYES_SZAM_MASODIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tál";
                } else {
                    return szoto + "tél";
                }

            case EGYES_SZAM_HARMADIK_SZEMELY:
                if ("eé".contains(utolsoMaganhangzo)) {
                    return szoto + "ett";
                } else if ("üűöő".contains(utolsoMaganhangzo)) {
                    return szoto + "ött";
                } else {
                    return szoto + "ott";
                }

            case TOBBES_SZAM_ELSO_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tunk";
                } else {
                    return szoto + "tünk";
                }

            case TOBBES_SZAM_MASODIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tatok";
                } else {
                    return szoto + "tetek";
                }

            case TOBBES_SZAM_HARMADIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tak";
                } else {
                    return szoto + "tek";
                }

            default:
                return "";
        }
    }

    private static String multKijelentoTargyas(Szo szo, SzamSzemely szamSzemely) throws Exception {
        String szoto = szo.getSzo();
        String veg = getVeg(szoto);
        if (veg.equals("ik")) {
            szoto = szoto.substring(0, szoto.length() - 2);
        }

        Rend rend = getHangrend(szo);


        switch (szamSzemely) {
            case EGYES_SZAM_ELSO_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tam";
                } else {
                    return szoto + "tem";
                }

            case EGYES_SZAM_MASODIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tad";
                } else {
                    return szoto + "ted";
                }

            case EGYES_SZAM_HARMADIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "ta";
                } else {
                    return szoto + "te";
                }

            case TOBBES_SZAM_ELSO_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tuk";
                } else {
                    return szoto + "tük";
                }

            case TOBBES_SZAM_MASODIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "tátok";
                } else {
                    return szoto + "tétek";
                }

            case TOBBES_SZAM_HARMADIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "ták";
                } else {
                    return szoto + "ték";
                }

            default:
                return "";
        }
    }

    private static String jovoKijelentoAlanyi(Szo szo, SzamSzemely szamSzemely) throws Exception {
        String szoto = szo.getSzo();
        if (szoto.endsWith("ni")) {
            szoto = szoto.substring(0, szoto.length() - 2);
        }

        switch (szamSzemely) {
            case EGYES_SZAM_ELSO_SZEMELY:
                return szoto + "ni fogok";

            case EGYES_SZAM_MASODIK_SZEMELY:
                return szoto + "ni fogsz";

            case EGYES_SZAM_HARMADIK_SZEMELY:
                return szoto + "ni fog";

            case TOBBES_SZAM_ELSO_SZEMELY:
                return szoto + "ni fogunk";

            case TOBBES_SZAM_MASODIK_SZEMELY:
                return szoto + "ni fogtok";

            case TOBBES_SZAM_HARMADIK_SZEMELY:
                return szoto + "ni fognak";

            default:
                return "";
        }
    }

    private static String jovoKijelentoTargyas(Szo szo, SzamSzemely szamSzemely) throws Exception {
        String szoto = szo.getSzo();
        if (szoto.endsWith("ni")) {
            szoto = szoto.substring(0, szoto.length() - 2);
        }

        switch (szamSzemely) {
            case EGYES_SZAM_ELSO_SZEMELY:
                return szoto + "ni fogom";

            case EGYES_SZAM_MASODIK_SZEMELY:
                return szoto + "ni fogod";

            case EGYES_SZAM_HARMADIK_SZEMELY:
                return szoto + "ni fogja";

            case TOBBES_SZAM_ELSO_SZEMELY:
                return szoto + "ni fogjuk";

            case TOBBES_SZAM_MASODIK_SZEMELY:
                return szoto + "ni fogjátok";

            case TOBBES_SZAM_HARMADIK_SZEMELY:
                return szoto + "ni fogják";

            default:
                return "";
        }
    }

    private static String jelenFeltetelesAlanyi(Szo szo, SzamSzemely szamSzemely) throws Exception {
        String szoto = szo.getSzo();
        String veg = getVeg(szoto);
        if (veg.equals("ik")) {
            szoto = szoto.substring(0, szoto.length() - 2);
        }

        Rend rend = getHangrend(szo);
        //TODO (a)(e)

        switch (szamSzemely) {
            case EGYES_SZAM_ELSO_SZEMELY:
                return szoto + "nék";

            case EGYES_SZAM_MASODIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nál";
                } else {
                    return szoto + "nél";
                }

            case EGYES_SZAM_HARMADIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "na";
                } else {
                    return szoto + "ne";
                }

            case TOBBES_SZAM_ELSO_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nánk";
                } else {
                    return szoto + "nénk";
                }

            case TOBBES_SZAM_MASODIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nátok";
                } else {
                    return szoto + "nétek";
                }

            case TOBBES_SZAM_HARMADIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nának";
                } else {
                    return szoto + "nének";
                }

            default:
                return "";
        }
    }

    private static String jelenFeltetelesTárgyas(Szo szo, SzamSzemely szamSzemely) throws Exception {
        String szoto = szo.getSzo();
        String veg = getVeg(szoto);
        if (veg.equals("ik")) {
            szoto = szoto.substring(0, szoto.length() - 2);
        }

        Rend rend = getHangrend(szo);
        //TODO (a)(e)

        switch (szamSzemely) {
            case EGYES_SZAM_ELSO_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nám";
                } else {
                    return szoto + "ném";
                }

            case EGYES_SZAM_MASODIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nád";
                } else {
                    return szoto + "néd";
                }

            case EGYES_SZAM_HARMADIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "ná";
                } else {
                    return szoto + "né";
                }

            case TOBBES_SZAM_ELSO_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nánk";
                } else {
                    return szoto + "nénk";
                }

            case TOBBES_SZAM_MASODIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nátok";
                } else {
                    return szoto + "nétek";
                }

            case TOBBES_SZAM_HARMADIK_SZEMELY:
                if (rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)) {
                    return szoto + "nák";
                } else {
                    return szoto + "nék";
                }

            default:
                return "";
        }
    }

    private static String multFeltetelesAlanyi(Szo szo, SzamSzemely szamSzemely) throws Exception {
        return multKijelentoAlanyi(szo, szamSzemely) + " volna";
    }

    private static String multFeltetelesTargyas(Szo szo, SzamSzemely szamSzemely) throws Exception {
        return multKijelentoTargyas(szo, szamSzemely) + " volna";
    }

    //TODO felszolito : http://www.magyarora.com/grammar/igeragozas_felszolito_alanyi.pdf
    //TODO felszolito : http://www.magyarora.com/grammar/igeragozas_felszolito_targyas.pdf

    private static String getVeg(String szoto) {
        String veg = "";
        if (szoto.endsWith("ni")) {
            veg = "ni";
        } else if (szoto.endsWith("s") || szoto.endsWith("sz") || szoto.endsWith("z")) {
            veg = "sz";
        } else if (szoto.endsWith("ik")) {
            veg = "ik";
        }
        return veg;
    }

}
