package mi.logic;

public class Nevelo {

    private static String MAGANHANGZOK = "aáeéiíoóöőuúüű";

    public static String get(String s){
        if(MAGANHANGZOK.contains(s.substring(0,1))){
            return "az";
        } else {
            return "a";
        }
    }
}
