package mi.tools;

import mi.domain.Szo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HtmlParser {

    private static String SZOTAR_DIR = "C:\\Users\\Bence\\AppData\\Local\\VirtualStore\\Program Files (x86)\\GnuWin32\\bin\\wikiszotar.hu\\wiki\\magyar_ertelmezo_szotar";
    private static String SZO_ES_SZOFAJ_REGEX = ".*<span class=\"mw-headline\">[a-zA-Z,() áéóöőíúüűÁÉÓÖŐÍÚÜŰ]*</span>.*";
    private File szotarMappa = new File(SZOTAR_DIR);

    private List<Szo> szavak = new ArrayList<>();

    public void parse(){
        fajlokatOlvas();
        szokincsetKiir();
    }


    private void fajlokatOlvas(){
        File[] szotarFajl = szotarMappa.listFiles();
        if(szotarFajl == null){
            return;
        }
        for (int i = 0; i < szotarFajl.length; i++){
            System.out.println(""+i);
            try(BufferedReader br = new BufferedReader(new FileReader(szotarFajl[i]))) {
                String sor = br.readLine();
                while(sor != null){
                    if(sor.matches(SZO_ES_SZOFAJ_REGEX)){
                        sortFeldolgoz(sor);
                        break;
                    }
                    sor = br.readLine();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void sortFeldolgoz(String sor){
        String[] elsoFelezes = sor.split("<span class=\"mw-headline\">");
        String[] masodikFelezes = elsoFelezes[elsoFelezes.length - 1].split("</span>");
        String tartalom = masodikFelezes[0];
        tartalom = tartalom.replaceAll("\\s+", "");
        String[] tartalomDarabok = tartalom.split("[,()]");

        Szo szo = new Szo();
        szo.setSzo(tartalomDarabok[0]);
        for(int i = 1; i < tartalomDarabok.length; i++){
            szo.addSzofaj(tartalomDarabok[i]);
        }
        szavak.add(szo);
    }

    private void szokincsetKiir(){
        try(PrintWriter writer = new PrintWriter("szokincs.csv", "UTF-8")){
            for(Szo szo : szavak){
                writer.println(szo.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
