package mi.logic;


import mi.domain.Hangok;
import mi.domain.Szo;
import mi.domain.enumok.FonevRag;
import mi.domain.enumok.Maganhangzo;
import mi.domain.enumok.Rend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static mi.domain.Hangok.*;
import static mi.domain.enumok.FonevRag.*;
import static mi.domain.enumok.Maganhangzo.HOSSZU;

public class FonevRagozo {

    public static String ragoz(Szo szo, FonevRag... ragok) {
        List<FonevRag> ragokLista = new ArrayList<>();
        Collections.addAll(ragokLista, ragok);

        String ret = szo.getSzo();

        if (ragokLista.contains(FonevRag.TOBBES_SZAM)) {
            ret = addTobbesSzamRag(szo);
        } else if (
                        ragokLista.contains(BIRTOKOS_EGYES_SZAM_ELSO_SZEMELY) ||
                        ragokLista.contains(BIRTOKOS_EGYES_SZAM_MASODIK_SZEMELY) ||
                        ragokLista.contains(BIRTOKOS_EGYES_SZAM_HARMADIK_SZEMELY) ||
                        ragokLista.contains(BIRTOKOS_TOBBES_SZAM_ELSO_SZEMELY) ||
                        ragokLista.contains(BIRTOKOS_TOBBES_SZAM_MASODIK_SZEMELY) ||
                        ragokLista.contains(BIRTOKOS_TOBBES_SZAM_HARMADIK_SZEMELY)
                ) {
            ret = addBirtokosSzemelyRag(szo, ragokLista);
        }

        //TODO irany ragok... http://www.magyarora.com/grammar/irany3.pdf

        if(ragokLista.contains(FonevRag.TARGY)){
            ret = addTargyRag(ret);
        }

        return ret;
    }

    private static String addTargyRag(String eredeti) {
        Szo szo = new Szo();
        szo.setSzo(eredeti);
        Rend rend = getHangrend(szo);

        if(mindOU(szo)){
            return eredeti + "öt";
        }

        if(isUtolsoHangMaganhangzo(szo)){
            String utolso = rovidetHosszura(szo.getSzo().substring(szo.getSzo().length() - 1));
            return eredeti.substring(0, eredeti.length() - 1) + utolso + "t";
        } else {
            if(!eredeti.endsWith("cs") && !eredeti.endsWith("t") && !eredeti.endsWith("ty") && !eredeti.endsWith("gy") && !eredeti.endsWith("k") && !eredeti.endsWith("m")){
                return eredeti + "t";
            } else {
                if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                    return eredeti + "ot";
                } else {
                    //TODO ezt megegyszer at kell gondolni
                    String utolsoMaganhangzo = getUtolsoMaganhangzo(szo);
                    if("eéi".contains(utolsoMaganhangzo)) {
                        return eredeti + "et";
                    } else if("aá".contains(utolsoMaganhangzo)){
                        return eredeti + "at";
                    } else {
                        return eredeti + "ot";
                    }
                }
            }
        }

        //TODO kepzett szavak
    }

    private static String addBirtokosSzemelyRag(Szo szo, List<FonevRag> ragokLista) {
        String szoto = szo.getSzo();
        boolean tobb = false;
        if(ragokLista.contains(FonevRag.TOBBES_SZAM)){
            tobb = true;
        }
        boolean utolsoMaganhangzo = isUtolsoHangMaganhangzo(szo);
        Rend rend = getHangrend(szo);
        for(FonevRag rag : ragokLista){
            switch (rag){
                case BIRTOKOS_EGYES_SZAM_ELSO_SZEMELY:
                    if(tobb){
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaim";
                                } else {
                                    return szoto + "im";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeim";
                                } else {
                                    return szoto + "im";
                                }
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaim";
                                } else {
                                    return szoto + "aim";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeim";
                                } else {
                                    return szoto + "eim";
                                }
                            }
                        }
                    } else {
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "m";
                            } else {
                                return szoto + "m";
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "om";
                                //TODO pl tollAM http://hu.wikipedia.org/wiki/Birtokos_szem%C3%A9lyjel
                            } else {
                                //TODO öm http://hu.wikipedia.org/wiki/Birtokos_szem%C3%A9lyjel
                                return szoto + "em";
                            }
                        }
                    }

                case BIRTOKOS_EGYES_SZAM_MASODIK_SZEMELY:
                    if(tobb){
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaid";
                                } else {
                                    return szoto + "id";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeid";
                                } else {
                                    return szoto + "id";
                                }
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaid";
                                } else {
                                    return szoto + "aid";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeid";
                                } else {
                                    return szoto + "eid";
                                }
                            }
                        }
                    } else {
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "d";
                            } else {
                                return szoto + "d";
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "od";
                                //TODO pl tollAM http://hu.wikipedia.org/wiki/Birtokos_szem%C3%A9lyjel
                            } else {
                                //TODO öm http://hu.wikipedia.org/wiki/Birtokos_szem%C3%A9lyjel
                                return szoto + "ed";
                            }
                        }
                    }

                case BIRTOKOS_EGYES_SZAM_HARMADIK_SZEMELY:
                    if(tobb){
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jai";
                                } else {
                                    return szoto + "i";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jei";
                                } else {
                                    return szoto + "i";
                                }
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jai";
                                } else {
                                    return szoto + "ai";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jei";
                                } else {
                                    return szoto + "ei";
                                }
                            }
                        }
                    } else {
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "ja";
                            } else {
                                return szoto + "je";
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")) {
                                    return szoto + "ja";
                                } else {
                                    return szoto + "a";
                                }
                            } else {
                                if(szoto.endsWith("i")) {
                                    return szoto + "je";
                                } else {
                                    return szoto + "e";
                                }
                            }
                        }
                    }

                case BIRTOKOS_TOBBES_SZAM_ELSO_SZEMELY:
                    if(tobb){
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaink";
                                } else {
                                    return szoto + "ink";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeink";
                                } else {
                                    return szoto + "ink";
                                }
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaink";
                                } else {
                                    return szoto + "aink";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeink";
                                } else {
                                    return szoto + "eink";
                                }
                            }
                        }
                    } else {
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "nk";
                            } else {
                                return szoto + "nk";
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "unk";
                            } else {
                                return szoto + "ünk";
                            }
                        }
                    }

                case BIRTOKOS_TOBBES_SZAM_MASODIK_SZEMELY:
                    if(tobb){
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaitok";
                                } else {
                                    return szoto + "itok";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeitek";
                                } else {
                                    return szoto + "itek";
                                }
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaitok";
                                } else {
                                    return szoto + "aitok";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeitek";
                                } else {
                                    return szoto + "eitek";
                                }
                            }
                        }
                    } else {
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "tok";
                            } else {
                                // TODO tök
                                return szoto + "tek";
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                // TODO atok
                                return szoto + "otok";
                            } else {
                                // TODO etek
                                return szoto + "ötök";
                            }
                        }
                    }

                case BIRTOKOS_TOBBES_SZAM_HARMADIK_SZEMELY:

                    if(tobb){
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaik";
                                } else {
                                    return szoto + "ik";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeik";
                                } else {
                                    return szoto + "ik";
                                }
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "jaik";
                                } else {
                                    return szoto + "aik";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jeik";
                                } else {
                                    return szoto + "eik";
                                }
                            }
                        }
                    } else {
                        if(utolsoMaganhangzo){
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                return szoto + "juk";
                            } else {
                                return szoto + "jük";
                            }
                        } else {
                            if(rend.equals(Rend.MELY) || rend.equals(Rend.VEGYES)){
                                if(szoto.endsWith("i")){
                                    return szoto + "juk";
                                } else {
                                    return szoto + "uk";
                                }
                            } else {
                                if(szoto.endsWith("i")){
                                    return szoto + "jük";
                                } else {
                                    return szoto + "ük";
                                }
                            }
                        }
                    }

                default:

                    break;
            }
        }
        throw new RuntimeException("nem iskerult a birtokos ragot ratenni a fonevre.");
    }

    private static String addTobbesSzamRag(Szo szo) {
        String szoto = szo.getSzo();
        Rend rend = Hangok.getHangrend(szo);
        Maganhangzo utolso = getUtolsoMaganhangzoFajtaja(szo);
        if (utolso != null) {
            if (utolso.equals(HOSSZU)) {
                return szoto + "k";
            } else {
                String utolsoHosszan = Hangok.rovidetHosszura(szoto.substring(szoto.length() - 1));
                return szoto.substring(0, szoto.length() - 1) + utolsoHosszan + "k";
            }
        }
        if (maganhangzokSzama(szo) == 1) {
            String utolsoMaganhangzo = getUtolsoMaganhangzo(szo);
            if ("eéi".contains(utolsoMaganhangzo)) {
                return szoto + "ek";
            } else if ("aá".contains(utolsoMaganhangzo)) {
                return szoto + "ak";
            } else if ("öő".contains(utolsoMaganhangzo)) {
                return szoto + "ök";
            } else { //TODO csak hotfix a hianyzo egytaguakra, pl ín, ól...
                return szoto + "ak";
            }
        } else {
            if (mindOU(szo)) {
                return szoto + "ök";
            }
            //TODO utolso maganhangzo rovid lesz (levél -> levelek)
            if (rend.equals(Rend.MAGAS)) {
                return szoto + "ek";
            } else {
                return szoto + "ok";
            }
        }

        //TODO kepzett szavak: http://www.magyarora.com/grammar/tobbesszam_tablazat.pdf

    }



}
