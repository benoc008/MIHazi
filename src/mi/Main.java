package mi;

import mi.gui.Ablak;

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

        Ablak ablak = new Ablak();
        Thread t = new Thread(ablak);
        t.start();

    }
}
