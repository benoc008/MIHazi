package mi.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import static mi.helperek.FileMuveletHelper.logol;

public class InputProcessor {

    private static String KERDESEK = "kerdesek.txt";

    private List<KerdesValasz> eloreDefinialtKerdesekValaszok;
    private List<Szo> szokincs;
    private List<String> history;
    private String valasz;


    Random random = new Random();

    public InputProcessor() {
        history = new ArrayList<>();
        kerdeseketBeolvas();
        szokincsetBeolvas();
    }

    private void szokincsetBeolvas() {
        szokincs = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(KERDESEK))) {
            String line = br.readLine();
            while(line != null){
                szokincs.add(Szo.createFromCsv(line));
                line = br.readLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void kerdeseketBeolvas() {
        eloreDefinialtKerdesekValaszok = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(KERDESEK))){
            String line = br.readLine();
            String kerdes = null;
            List<String> valaszok = new ArrayList<>();
            while(line != null){
                if(line.startsWith("K:")){
                    if(kerdes != null){
                        eloreDefinialtKerdesekValaszok.add(new KerdesValasz(kerdes, valaszok));
                    }
                    kerdes = line.substring(3);
                    valaszok = new ArrayList<>();
                } else if(line.startsWith("V:")){
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
        s = s.replaceAll("\\n","");
        history.add(s);
//        logol(s);
        feldolgoz(s);
        history.add(valasz);

        return valasz;
    }

    private void feldolgoz(String s) {
        valasz = s;
        List<Mondat> mondatok = mondatokraBont(s);
        for(Mondat m : mondatok){
            ertelmez(m);
        }


    }

    private List<Mondat> mondatokraBont(String s) {
        List<Mondat> mondatok = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(s, ".!?", true);
        while (stringTokenizer.hasMoreTokens()) {
            String mondat = stringTokenizer.nextToken();
            String irasjel = "";
            if(stringTokenizer.hasMoreTokens()) {
                irasjel = stringTokenizer.nextToken();
            }
            mondatok.add(new Mondat(mondat, irasjel));
        }
        if(mondatok.isEmpty()){
            mondatok.add(new Mondat(s));
        }
        return mondatok;
    }

    private void ertelmez(Mondat mondat){
        eloreDefinialtAdatokAlapjanEllenoriz(mondat);
        keresTargyat(mondat);

    }

    private void keresTargyat(Mondat mondat) {
        String[] szavak = mondat.getMondat().split(" ");
        List<String> tVeguSzavak = tVeguSzavakatKeres(szavak);
        if(tVeguSzavak.size() == 0){
            return;
        } else if(tVeguSzavak.size() == 1){
            mondat.setTargy(tVeguSzavak.get(0));
        }
    }

    private List<String> tVeguSzavakatKeres(String[] szavak) {
        List<String> ret = new ArrayList<>();
        for(String s : szavak){
            if(s.charAt(s.length() - 1) == 't'){
                ret.add(s);
            }
        }
        return ret;
    }

    private void eloreDefinialtAdatokAlapjanEllenoriz(Mondat mondat) {
        String keresett = mondat.getMondat().toLowerCase();
        for(KerdesValasz kv : eloreDefinialtKerdesekValaszok){
            String megadott = kv.getKerdes().toLowerCase();
            megadott = megadott.replaceAll("[?!.]", "");
            if(megadott.equals(keresett)){
                valasz = kv.getValaszok().get(random.nextInt(kv.getValaszok().size()));
            }
        }
    }
}
