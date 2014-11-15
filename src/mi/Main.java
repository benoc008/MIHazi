package mi;

import mi.gui.Ablak;
import mi.logic.InputProcessor;

public class Main {

    public static void main(String[] args) {
//        FileMuveletHelper.log = new File("log" + new Date().getTime() + ".txt");
//        try {
//            FileMuveletHelper.log.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        HtmlParser parser = new HtmlParser();
//        parser.parse();

        InputProcessor inputProcessor = new InputProcessor();

        Ablak ablak = new Ablak(inputProcessor);
        Thread t = new Thread(ablak);
        t.start();

    }
}
