package mi.logic;

public class Mondat {
    private String mondat;
    private String alany;
    private String allitmany;
    private MondatFajta fajta;

    public Mondat(String mondat, String fajta) {
        this.mondat = mondat;
        switch (fajta) {
            case "?":
                this.fajta = MondatFajta.KERDO;
                break;
            case ".":
                this.fajta = MondatFajta.KIJELENTO;
                break;
            case "!":
                this.fajta = MondatFajta.FELKIALTO;
                //TODO ...
                break;
            default:
                this.fajta = MondatFajta.KIJELENTO;
                //TODO exceptiont kellene dobni...
                System.out.println("Hibas mondatfajta, kijelentonek allitva");
        }
    }
}
