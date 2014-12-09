package mi.logic;

import mi.domain.*;
import mi.domain.enumok.*;

import java.util.*;

import static mi.domain.enumok.FonevRag.*;
import static mi.domain.enumok.FonevRag.TOBBES_SZAM;
import static mi.domain.enumok.SzamSzemely.*;
import static mi.domain.enumok.SzamSzemely.TOBBES_SZAM_HARMADIK_SZEMELY;
import static mi.domain.enumok.SzamSzemely.TOBBES_SZAM_MASODIK_SZEMELY;
import static mi.helperek.FileMuveletHelper.kerdeseketBeolvas;
import static mi.helperek.FileMuveletHelper.logol;
import static mi.helperek.FileMuveletHelper.szokincsetOlvasCSVbol;

public class Elemzo {
    private static String KERDESEK_FILE = "kerdesek.txt";
    private static String SZOKINCS_FILE = "szokincs.csv";
    private static String NEVEK_FILE = "nevek.csv";

    private static String[] IGEKOTOK = {"abba", "hátra", "mellé", "agyon", "haza", "mögé", "alá", "helyre", "neki", "által", "hozzá", "oda", "alul", "ide", "össze", "át", "jóvá", "rá", "be", "keresztül", "rajta", "bele", "ketté", "szembe", "belül", "ki", "szerte", "benn", "bent", "kinn", "kint", "szét", "széjjel", "egybe", "kívül", "tele", "el", "kölcsön", "tova", "elé", "körbe", "tönkre", "ellen", "körül", "túl", "elő", "közzé", "újjá", "előre", "külön", "újra", "fel", "föl", "le", "utána", "félbe", "lent", "végbe", "félre", "létre", "végig", "felül", "fölül", "meg", "vissza", "fenn", "fent", "fönn", "fönt"};

    Random random = new Random();
    private List<KerdesValasz> eloreDefinialtKerdesekValaszok;
    private List<Szo> szokincs;
    private List<Tudas> tudastar;
    private List<String> history;

    public Elemzo(){
        history = new ArrayList<>();
        eloreDefinialtKerdesekValaszokatBeolvas();
        szokincs = new ArrayList<>();
        szokincs.addAll(szokincsetOlvasCSVbol(SZOKINCS_FILE));
        szokincs.addAll(szokincsetOlvasCSVbol(NEVEK_FILE));
        tudastar = new ArrayList<>();
    }

    public List<Mondat> mondatokraBont(String s) {
        List<Mondat> mondatok = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(s, ".!?", true);
        while (stringTokenizer.hasMoreTokens()) {
            String mondat = stringTokenizer.nextToken();
            String irasjel = "";
            if (stringTokenizer.hasMoreTokens()) {
                irasjel = stringTokenizer.nextToken();
            }
            mondatok.add(new Mondat(mondat, irasjel));
        }
        if (mondatok.isEmpty()) {
            mondatok.add(new Mondat(s));
        }
        return mondatok;
    }

    public void ertelmez(Mondat mondat) {
        keresAllitmanyt(mondat);
        keresAlanyt(mondat);
        keresTargyat(mondat);

        mondatotLogol(mondat);

    }

    private void mondatotLogol(Mondat mondat) {
        logol("Mondat: " + mondat.getMondat());
        if (mondat.getAlany() != null) {
            String log = "alany: " + mondat.getAlany().getSzo() + ": ";
            for (String szofaj : mondat.getAlany().getSzofajok()) {
                log += szofaj + ", ";
            }
            logol(log.substring(0, log.length() - 2));
        } else {
            logol("nem talaltam alanyt.");
        }
        if (mondat.getAllitmany() != null) {
            String log = "allitmany: " + mondat.getAllitmany().getSzo() + ": ";
            for (String szofaj : mondat.getAllitmany().getSzofajok()) {
                log += szofaj + ", ";
            }
            logol(log.substring(0, log.length() - 2));
        } else {
            logol("nem talaltam allitmanyt");
        }
        if (mondat.getTargy() != null) {
            String log = "targy: " + mondat.getTargy().getSzo() + ": ";
            for (String szofaj : mondat.getTargy().getSzofajok()) {
                log += szofaj + ", ";
            }
            logol(log.substring(0, log.length() - 2));
        } else {
            logol("nem talaltam targyat");
        }
    }

    private void keresAlanyt(Mondat mondat) {
        if(mondat.getAllitmany() != null && mondat.getAllitmany().getIgeRagok() != null) {
            SzamSzemely szamSzemely = mondat.getAllitmany().getIgeRagok().getSzamSzemely();

            Szo rejtett = new Szo();
            rejtett.addSzofaj("rejtett");
            if (szamSzemely.equals(EGYES_SZAM_ELSO_SZEMELY)) {
                rejtett.setSzo("én");
            } else if (szamSzemely.equals(EGYES_SZAM_MASODIK_SZEMELY)) {
                rejtett.setSzo("te");
            } else if (szamSzemely.equals(EGYES_SZAM_HARMADIK_SZEMELY)) {
                rejtett.setSzo("ő");
            } else if (szamSzemely.equals(TOBBES_SZAM_ELSO_SZEMELY)) {
                rejtett.setSzo("mi");
            } else if (szamSzemely.equals(TOBBES_SZAM_MASODIK_SZEMELY)) {
                rejtett.setSzo("ti");
            } else if (szamSzemely.equals(TOBBES_SZAM_HARMADIK_SZEMELY)) {
                rejtett.setSzo("ők");
            }
            if (rejtett != null) {
                mondat.setAlany(rejtett);
                if (!rejtett.getSzo().equals("ő") && !rejtett.getSzo().equals("ők")) {
                    return;
                }
            }
        }
        List<Szo> potencialisSzavak = new ArrayList<>();
        for (String s : mondat.getVizsgalandoSzavak()) {
            s = s.toLowerCase();
            for (Szo szo : szokincs) {
                if (!(szo.getSzofajok().contains("főnév") || szo.getSzofajok().contains("névmás"))) {
                    continue;
                }
                if (szo.getSzo().equals(s)) {
                    mondat.setAlany(szo);
                    return;
                }
                if (szo.getSzo().length() < 3) {
                    if (s.startsWith(szo.getSzo())) {
                        potencialisSzavak.add(szo);
                    }
                } else if (s.startsWith(szo.getVegeNelkul())) {
                    potencialisSzavak.add(szo);
                }
            }
        }
        Szo alany = fonevRagozastVegignez(mondat, potencialisSzavak, null);
        if(alany != null){
            mondat.setAlany(alany);
        } else {
            mondat.setAlany(getLegtobbEgyezes(mondat, potencialisSzavak));
        }
        mondat.torolSzo(mondat.getSzoSzotobol(mondat.getAlany()));
    }

    private void keresAllitmanyt(Mondat mondat) {
        List<Szo> potencialisSzavak = new ArrayList<>();
        for (String s : mondat.getVizsgalandoSzavak()) {
            s = s.toLowerCase();
            s = igekototLevesz(s);
            for (Szo szo : szokincs) {
                if (!szo.getSzofajok().contains("ige")) {
                    continue;
                }
                if (szo.getSzo().equals(s)) {
                    mondat.setAllitmany(szo);
                    return;
                }
                if (szo.getSzo().length() < 3) {
                    if (s.startsWith(szo.getSzo())) {
                        potencialisSzavak.add(szo);
                    }
                } else if (s.startsWith(szo.getVegeNelkul())) {
                    potencialisSzavak.add(szo);
                }
            }
        }
        Szo allitmany = igeragozastVegignez(mondat, potencialisSzavak);
        if(allitmany != null){
            mondat.setAllitmany(allitmany);
        } else {
            mondat.setAllitmany(getLegtobbEgyezes(mondat, potencialisSzavak));
        }
        mondat.torolSzo(mondat.getSzoSzotobol(mondat.getAllitmany()));
    }

    private Szo igeragozastVegignez(Mondat mondat, List<Szo> potencialisSzavak) {
        Szo ret = null;
        for (Szo szo : potencialisSzavak) {
            for (String mondatSzava : mondat.getVizsgalandoSzavak()) {
                for (SzamSzemely szamSzemely : SzamSzemely.values()) {
                    for(Mod mod : Mod.values()) {
                        for (Ido ido : Ido.values()) {
                            for(IgeragozasiRendszer rendszer : IgeragozasiRendszer.values()) {
                                if (mondatSzava.equals(IgeRagozo.ragoz(szo, szamSzemely, mod, ido, rendszer))) {
                                    ret = szo;
                                    ret.setIgeRagok(new IgeRagok(szamSzemely, mod, ido, rendszer));
                                    logol("ige megtalalasa a ragozas alapjan sikeres: " + ret.getSzo());
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    public String igekototLevesz(String s) {
        for (String igekoto : IGEKOTOK) {
            if (s.startsWith(igekoto)) {
                s = s.replaceAll(igekoto, "");
                return s;
            }
        }
        return s;
    }

    private void keresTargyat(Mondat mondat) {
        List<String> tVeguSzavak = egyTVeguSzavakatKeres(mondat.getVizsgalandoSzavak());
        if (tVeguSzavak.isEmpty()) {
            return;
        }
        List<Szo> potencialisSzavak = new ArrayList<>();
        for (String s : tVeguSzavak) {
            s = s.toLowerCase();
            for (Szo szo : szokincs) {
                if (szo.getSzo().length() < 2) {
                    continue;
                }
                if (szo.getSzofajok().contains("főnév") || szo.getSzofajok().contains("névmás")) {
                    if (s.startsWith(szo.getVegeNelkul())) {
                        potencialisSzavak.add(szo);
                    }
                }
            }
        }
        Szo targy = fonevRagozastVegignez(mondat, potencialisSzavak, TARGY);
        if(targy != null){
            mondat.setTargy(targy);
        } else {
            mondat.setTargy(getLegtobbEgyezes(mondat, potencialisSzavak));
        }
        mondat.torolSzo(mondat.getSzoSzotobol(mondat.getTargy()));
    }

    // ha targyRag = FonevRag.Targy, akkor mindig hozzateszi a targyragot, ha null, akkor nem.
    private Szo fonevRagozastVegignez(Mondat mondat, List<Szo> potencialisSzavak, FonevRag targyRag) {
        Szo ret = null;
        //TODO refactor az egeszet, hatalmas ganyolas
        List<FonevRag> birtokosok = new ArrayList<>();
        birtokosok.add(BIRTOKOS_EGYES_SZAM_ELSO_SZEMELY);
        birtokosok.add(BIRTOKOS_EGYES_SZAM_MASODIK_SZEMELY);
        birtokosok.add(BIRTOKOS_EGYES_SZAM_HARMADIK_SZEMELY);
        birtokosok.add(BIRTOKOS_TOBBES_SZAM_ELSO_SZEMELY);
        birtokosok.add(BIRTOKOS_TOBBES_SZAM_MASODIK_SZEMELY);
        birtokosok.add(BIRTOKOS_TOBBES_SZAM_HARMADIK_SZEMELY);
        for (Szo szo : potencialisSzavak) {
            for (String mondatSzava : mondat.getVizsgalandoSzavak()) {
                if(mondatSzava.equals(FonevRagozo.ragoz(szo, targyRag))){
                    ret = szo;
                    ret.addFonevRag(targyRag);
                }
                if(mondatSzava.equals(FonevRagozo.ragoz(szo, TOBBES_SZAM, targyRag))){
                    ret = szo;
                    ret.addFonevRag(TOBBES_SZAM);
                    ret.addFonevRag(targyRag);
                }
                for(FonevRag rag : birtokosok){
                    if(mondatSzava.equals(FonevRagozo.ragoz(szo, rag, targyRag))){
                        ret = szo;
                        ret.addFonevRag(TOBBES_SZAM);
                        ret.addFonevRag(targyRag);
                    }
                }
            }
        }
        if(ret != null){
            logol("fonev megtalalasa a ragozas alapjan sikeres: " + ret.getSzo());
        }
        return ret;
    }

    private Szo getLegtobbEgyezes(Mondat mondat, List<Szo> potencialisSzavak) {
        Map<Integer, List<Szo>> map = new TreeMap<>();
        int i = 1;
        while (i == 1 || map.get(i - 1) != null) {
            for (Szo szo : potencialisSzavak) {
                for (String szavaim : mondat.getMondat().split(" ")) {
                    if (szo.getSzo().length() >= i && szavaim.length() >= i && szo.getSzo().substring(0, i).equals(szavaim.substring(0, i))) {
                        if (map.get(i) == null) {
                            map.put(i, new ArrayList<Szo>());
                        }
                        List<Szo> tmp = map.get(i);
                        tmp.add(szo);
                        map.put(i, tmp);
                    }
                }
            }
            i++;
        }

        return getLegkisebb(map.get(i - 2));
    }

    private Szo getLegkisebb(List<Szo> szo) {
        if (szo == null) {
            Szo ret = new Szo();
            ret.setSzo("");
            return ret;
        }
        Szo min = szo.get(0);
        for (Szo szav : szo) {
            if (szav.getSzo().length() < min.getSzo().length()) {
                min = szav;
            }
        }
        return min;
    }

    private List<String> egyTVeguSzavakatKeres(List<String> szavak) {
        List<String> ret = new ArrayList<>();
        for (String s : szavak) {
            if (s.charAt(s.length() - 1) == 't' && s.charAt(s.length() - 2) != 't') {
                ret.add(s);
            }
        }
        return ret;
    }

    public String eloreDefinialtAdatokAlapjanEllenoriz(Mondat mondat) {
        String keresett = mondat.getMondat().toLowerCase();
        for (KerdesValasz kv : eloreDefinialtKerdesekValaszok) {
            String megadott = kv.getKerdes().toLowerCase();
            megadott = megadott.replaceAll("[?!.]", "");
            if (megadott.equals(keresett)) {
                return kv.getValaszok().get(random.nextInt(kv.getValaszok().size()));
            }
        }
        return "";
    }

    public void eloreDefinialtKerdesekValaszokatBeolvas(){
        eloreDefinialtKerdesekValaszok = kerdeseketBeolvas(KERDESEK_FILE);
    }

    public List<Tudas> getTudastar() {
        return tudastar;
    }

    public void addTudastarLista(List<Tudas> tudasLista) {
        tudastar.addAll(tudasLista);
    }

    public void addTudas(Tudas tudas) {
        tudastar.add(tudas);
    }

    public void addHistory(String s){
        history.add(s);
    }

    public List<String> getHistory() {
        return history;
    }
}
