package mi.logic;

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
    private List<String> history;
    private String valasz;

    public InputProcessor() {
        history = new ArrayList<>();
        kerdeseketBeolvas();
        szokincs = new ArrayList<>();
        csvOlvaso(SZOKINCS_FILE);
        csvOlvaso(NEVEK_FILE);
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
        valasz = s;
        List<Mondat> mondatok = mondatokraBont(s);
        for (Mondat m : mondatok) {
            ertelmez(m);
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
        eloreDefinialtAdatokAlapjanEllenoriz(mondat);
        keresTargyat(mondat);
        keresAllitmanyt(mondat);
        keresAlanyt(mondat);

        mondatotLogol(mondat);

    }

    private void mondatotLogol(Mondat mondat) {
        System.out.println(mondat.getMondat());
        if(mondat.getAlany() != null){
            System.out.print(mondat.getAlany().getSzo() + ", ");
            for(String szofaj : mondat.getAlany().getSzofajok()){
                System.out.print(szofaj);
            }
            System.out.println("");
        } else {
            System.out.println("rejtett alany(?)");
        }
        if(mondat.getAllitmany() != null){
            System.out.print(mondat.getAllitmany().getSzo() + ", ");
            for(String szofaj : mondat.getAllitmany().getSzofajok()){
                System.out.print(szofaj);
            }
            System.out.println("");
        } else {
            System.out.println("nincs meg az allitmany");
        }
        if(mondat.getTargy() != null){
            System.out.print(mondat.getTargy().getSzo() + ", ");
            for(String szofaj : mondat.getTargy().getSzofajok()){
                System.out.print(szofaj);
            }
            System.out.println("");
        } else {
            System.out.println("nincs targy");
        }
    }

    private void keresAlanyt(Mondat mondat) {
        String[] szavak = mondat.getMondat().split(" ");
        List<Szo> potencialisSzavak = new ArrayList<>();
        for (String s : szavak) {
            s = s.toLowerCase();
            for (Szo szo : szokincs) {
                if(!(szo.getSzofajok().contains("főnév") || szo.getSzofajok().contains("névmás"))){
                    continue;
                }
                if(szo.getSzo().equals(s)){
                    mondat.setAlany(szo);
                    return;
                }
                if(szo.getSzo().length() < 3){
                    if(s.startsWith(szo.getSzo())){
                        potencialisSzavak.add(szo);
                    }
                } else if(s.startsWith(szo.getSzo().substring(0, szo.getSzo().length() - 2))){
                    potencialisSzavak.add(szo);
                }
            }
        }
        mondat.setAlany(getLegtobbEgyezes(mondat, potencialisSzavak));
    }

    private void keresAllitmanyt(Mondat mondat) {
        // a targyat mar ne nezze
        String[] szavak = mondat.getMondat().split(" ");
        List<Szo> potencialisSzavak = new ArrayList<>();
        for (String s : szavak) {
            s = s.toLowerCase();
            s = igekototLevesz(s);
            for (Szo szo : szokincs) {
                if(!szo.getSzofajok().contains("ige")){
                    continue;
                }
                if(szo.getSzo().equals(s)){
                    mondat.setAllitmany(szo);
                    return;
                }
                if(szo.getSzo().length() < 3){
                    if(s.startsWith(szo.getSzo())){
                        potencialisSzavak.add(szo);
                    }
                } else if(s.startsWith(szo.getSzo().substring(0, szo.getSzo().length() - 2))){
                    potencialisSzavak.add(szo);
                }
            }
        }
        mondat.setAllitmany(getLegtobbEgyezes(mondat, potencialisSzavak));
    }

    public String igekototLevesz(String s) {
        for(String igekoto : IGEKOTOK){
            if(s.startsWith(igekoto)){
                s = s.replaceAll(igekoto, "");
                return s;
            }
        }
        return s;
    }

    private void keresTargyat(Mondat mondat) {
        String[] szavak = mondat.getMondat().split(" ");
        List<String> tVeguSzavak = tVeguSzavakatKeres(szavak);
        List<Szo> potencialisSzavak = new ArrayList<>();
        for (String s : tVeguSzavak) {
            s = s.toLowerCase();
            for (Szo szo : szokincs) {
                if(szo.getSzo().length() < 3){
                    continue;
                }
                if (s.startsWith(szo.getSzo().substring(0, szo.getSzo().length() - 2))) {
                    potencialisSzavak.add(szo);
                }
            }
        }
        mondat.setTargy(getLegtobbEgyezes(mondat, potencialisSzavak));
    }

    private Szo getLegtobbEgyezes(Mondat mondat, List<Szo> potencialisSzavak) {
        Map<Integer, List<Szo>> map = new TreeMap<>();
        int i = 1;
        while(i == 1 || map.get(i - 1) != null) {
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
        if(szo == null){
            Szo ret = new Szo();
            ret.setSzo("hiba");
            return ret;
        }
        Szo min = szo.get(0);
        for(Szo szav : szo){
            if(szav.getSzo().length() < min.getSzo().length()){
                min = szav;
            }
        }
        return min;
    }

    private List<String> tVeguSzavakatKeres(String[] szavak) {
        List<String> ret = new ArrayList<>();
        for (String s : szavak) {
            if (s.charAt(s.length() - 1) == 't') {
                ret.add(s);
            }
        }
        return ret;
    }

    private void eloreDefinialtAdatokAlapjanEllenoriz(Mondat mondat) {
        String keresett = mondat.getMondat().toLowerCase();
        for (KerdesValasz kv : eloreDefinialtKerdesekValaszok) {
            String megadott = kv.getKerdes().toLowerCase();
            megadott = megadott.replaceAll("[?!.]", "");
            if (megadott.equals(keresett)) {
                valasz = kv.getValaszok().get(random.nextInt(kv.getValaszok().size()));
            }
        }
    }
}
