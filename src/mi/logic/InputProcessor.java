package mi.logic;

import mi.domain.KerdesValasz;
import mi.domain.Mondat;
import mi.domain.Szo;
import mi.domain.Tudas;
import mi.domain.enumok.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class InputProcessor {

    private static String KERDESEK_FILE = "kerdesek.txt";
    private static String SZOKINCS_FILE = "szokincs.csv";
    private static String NEVEK_FILE = "nevek.csv";

    private static String[] IGEKOTOK = {"abba", "hátra", "mellé", "agyon", "haza", "mögé", "alá", "helyre", "neki", "által", "hozzá", "oda", "alul", "ide", "össze", "át", "jóvá", "rá", "be", "keresztül", "rajta", "bele", "ketté", "szembe", "belül", "ki", "szerte", "benn", "bent", "kinn", "kint", "szét", "széjjel", "egybe", "kívül", "tele", "el", "kölcsön", "tova", "elé", "körbe", "tönkre", "ellen", "körül", "túl", "elő", "közzé", "újjá", "előre", "külön", "újra", "fel", "föl", "le", "utána", "félbe", "lent", "végbe", "félre", "létre", "végig", "felül", "fölül", "meg", "vissza", "fenn", "fent", "fönn", "fönt"};

    Random random = new Random();
    private List<KerdesValasz> eloreDefinialtKerdesekValaszok;
    private List<Szo> szokincs;
    private List<Tudas> tudastar;
    private List<String> history;
    private String valasz;

    public InputProcessor() {
        history = new ArrayList<>();
        kerdeseketBeolvas();
        szokincs = new ArrayList<>();
        csvOlvaso(SZOKINCS_FILE);
        csvOlvaso(NEVEK_FILE);
        tudastar = new ArrayList<>();
    }

    private void csvOlvaso(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                szokincs.add(Szo.createFromCsv(line));
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kerdeseketBeolvas() {
        eloreDefinialtKerdesekValaszok = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(KERDESEK_FILE))) {
            String line = br.readLine();
            String kerdes = null;
            List<String> valaszok = new ArrayList<>();
            while (line != null) {
                if (line.startsWith("K:")) {
                    if (kerdes != null) {
                        eloreDefinialtKerdesekValaszok.add(new KerdesValasz(kerdes, valaszok));
                    }
                    kerdes = line.substring(3);
                    valaszok = new ArrayList<>();
                } else if (line.startsWith("V:")) {
                    valaszok.add(line.substring(3));
                }
                line = br.readLine();
            }
            eloreDefinialtKerdesekValaszok.add(new KerdesValasz(kerdes, valaszok));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String add(String s) {
        s = s.replaceAll("\\n", "");
        history.add(s);
//        logol(s);
        feldolgoz(s);
        history.add(valasz);

        return valasz;
    }

    private void feldolgoz(String s) {
        valasz = "";
        List<Mondat> mondatok = mondatokraBont(s);
        for (Mondat mondat : mondatok) {
            if (eloreDefinialtAdatokAlapjanEllenoriz(mondat)) {
                continue;
            }
            ertelmez(mondat);
            tanul(mondat);
            valaszol(mondat);
        }

        if (valasz.equals("")) {
            valasz = s;
        }
    }

    private void tanul(Mondat mondat) {
        Tudas tudas = new Tudas();
        if(history.size() < 2){
            return;
        }
        String elozoMondat = history.get(history.size() - 2);
        if(elozoMondat.endsWith("?")){
            String kerdoszo = ValaszGeneralo.kerdoszotKeres(elozoMondat);
            if(!kerdoszo.equals("")){
                tudas.setKerdoszo(kerdoszo);
                for(String s: KerdesGeneralo.ALLITMANYRA_KERDEZ){
                    if(s.startsWith(kerdoszo)){
                        if(mondat.getAllitmany() != null) {
                            tudas.setKerdezettSzo(mondat.getAllitmany());
                        } else {
                            throw new RuntimeException("hianyzo allitmany");
                        }
                    }
                }
                for(String s: KerdesGeneralo.TARGYRA_KERDEZ){
                    if(s.startsWith(kerdoszo)){
                        if(mondat.getTargy() != null) {
                            tudas.setKerdezettSzo(mondat.getTargy());
                        } else {
                            throw new RuntimeException("hianyzo targy");
                        }
                    }
                }
            }
        }
        if(tudas.getKerdoszo() != null) {
            tudas.setValasz(mondat);
            tudastar.add(tudas);
        }
    }


    private void valaszol(Mondat mondat) {

        if (mondat.getFajta().equals(MondatFajta.KIJELENTO)) {
            try {
                valasz = new KerdesGeneralo().general(mondat);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (mondat.getFajta().equals(MondatFajta.KERDO)) {
            valasz = new ValaszGeneralo(tudastar).general(mondat);
            try {
                System.out.println(IgeRagozo.ragoz(mondat.getAllitmany(), SzamSzemely.EGYES_SZAM_ELSO_SZEMELY, Mod.KIJELENTO, Ido.JELEN, mondat.getRendszer()));
                System.out.println(IgeRagozo.ragoz(mondat.getAllitmany(), SzamSzemely.EGYES_SZAM_MASODIK_SZEMELY, Mod.KIJELENTO, Ido.JELEN, mondat.getRendszer()));
                System.out.println(IgeRagozo.ragoz(mondat.getAllitmany(), SzamSzemely.EGYES_SZAM_HARMADIK_SZEMELY, Mod.KIJELENTO, Ido.JELEN, mondat.getRendszer()));
                System.out.println(IgeRagozo.ragoz(mondat.getAllitmany(), SzamSzemely.TOBBES_SZAM_ELSO_SZEMELY, Mod.KIJELENTO, Ido.JELEN, mondat.getRendszer()));
                System.out.println(IgeRagozo.ragoz(mondat.getAllitmany(), SzamSzemely.TOBBES_SZAM_MASODIK_SZEMELY, Mod.KIJELENTO, Ido.JELEN, mondat.getRendszer()));
                System.out.println(IgeRagozo.ragoz(mondat.getAllitmany(), SzamSzemely.TOBBES_SZAM_HARMADIK_SZEMELY, Mod.KIJELENTO, Ido.JELEN, mondat.getRendszer()));

            } catch (Exception e) {
                e.printStackTrace();
            }
            //valasz = new ValaszGeneralo().general(mondat);
        }
    }

    private List<Mondat> mondatokraBont(String s) {
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

    private void ertelmez(Mondat mondat) {
        keresTargyat(mondat);
        keresAllitmanyt(mondat);
        keresAlanyt(mondat);

        mondatotLogol(mondat);

    }

    private void mondatotLogol(Mondat mondat) {
        System.out.println(mondat.getMondat());
        if (mondat.getAlany() != null) {
            System.out.print(mondat.getAlany().getSzo() + ", ");
            for (String szofaj : mondat.getAlany().getSzofajok()) {
                System.out.print(szofaj);
            }
            System.out.println("");
        } else {
            System.out.println("rejtett alany(?)");
        }
        if (mondat.getAllitmany() != null) {
            System.out.print(mondat.getAllitmany().getSzo() + ", ");
            for (String szofaj : mondat.getAllitmany().getSzofajok()) {
                System.out.print(szofaj);
            }
            System.out.println("");
        } else {
            System.out.println("nincs meg az allitmany");
        }
        if (mondat.getTargy() != null) {
            System.out.print(mondat.getTargy().getSzo() + ", ");
            for (String szofaj : mondat.getTargy().getSzofajok()) {
                System.out.print(szofaj);
            }
            System.out.println("");
        } else {
            System.out.println("nincs targy");
        }
    }

    private void keresAlanyt(Mondat mondat) {
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
        mondat.setAlany(getLegtobbEgyezes(mondat, potencialisSzavak));
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
        mondat.setAllitmany(getLegtobbEgyezes(mondat, potencialisSzavak));
        mondat.torolSzo(mondat.getSzoSzotobol(mondat.getAllitmany()));
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
                if (szo.getSzo().length() < 3) {
                    continue;
                }
                if(szo.getSzofajok().contains("főnév") || szo.getSzofajok().contains("névmás")) {
                    if (s.startsWith(szo.getVegeNelkul())) {
                        potencialisSzavak.add(szo);
                    }
                }
            }
        }
        mondat.setTargy(getLegtobbEgyezes(mondat, potencialisSzavak));
        mondat.torolSzo(mondat.getSzoSzotobol(mondat.getTargy()));
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

    private boolean eloreDefinialtAdatokAlapjanEllenoriz(Mondat mondat) {
        String keresett = mondat.getMondat().toLowerCase();
        for (KerdesValasz kv : eloreDefinialtKerdesekValaszok) {
            String megadott = kv.getKerdes().toLowerCase();
            megadott = megadott.replaceAll("[?!.]", "");
            if (megadott.equals(keresett)) {
                valasz += kv.getValaszok().get(random.nextInt(kv.getValaszok().size()));
                return true;
            }
        }
        return false;
    }

    public List<Tudas> getTudastar() {
        return tudastar;
    }

    public void addTudastar(List<Tudas> tudasLista){
        tudastar.addAll(tudasLista);
    }
}
