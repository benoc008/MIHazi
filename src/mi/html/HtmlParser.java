package mi.html;

import mi.logic.Szo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HtmlParser {
    //kecske

    //TODO ez igy rossz
    private static String SZOTAR_DIR = "c:\\Users\\Bence\\IdeaProjects\\MIHazi\\resources\\wikiszotar\\";
    private static String SZO_ES_SZOFAJ_REGEX = ".*<span class=\"mw-headline\">[a-zA-Z,() áéóöőíúüűÁÉÓÖŐÍÚÜŰ]*</span>.*";
    private File szotarMappa = new File(SZOTAR_DIR);

    private List<Szo> szavak = new ArrayList<>();


    public void fajlokatOlvas(){
        File[] szotarFajl = szotarMappa.listFiles();
        for (int i = 0; i < szotarFajl.length; i++){
            try(BufferedReader br = new BufferedReader(new FileReader(szotarFajl[i]))) {
                String line = br.readLine();
                while(line != null){
                    if(line.matches(SZO_ES_SZOFAJ_REGEX)){

                    }
                }


            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void fajltFeldolgoz(){

    }

}
