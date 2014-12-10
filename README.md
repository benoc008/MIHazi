MIHazi
======

Egy 5 eves chatbot.

A program a Budapesti Műszaki és Gazdaságtudományi Egyetem Villamosmérnöki és Informatikai Karának Mesterséges Intelligencia című tárgyhoz készült, mint házi feladat.

A feladat egy olyan Chatbot készítése volt, mely egy óvodás gyerek stílusában képes bemutatkozni, beszélgetni, válaszolni, kérdéseket feltenni. A specifikációnk szerint ezt magyar nyelven tudja, és képes a tanulásra is.

A kész program rendelkezik a következőkkel:

  - Grafikus felület, melyen keresztük a beszélgetés történik
  - Grafikus felület a robot "tanítására"
  - Egyszerű input elemző
  - Kérdés generáló
  - Válasz generáló
  - Belső tanuló modell
  
  
- Grafikus felület, melyen keresztük a beszélgetés történik
  Egyszerű Java Swing felület egy szövegdobozzal, ahol az üzenetek látszanak, egy bemeneti mezővel, valamint egy küldés gombbal. 
  A fájl menüből elérhető a mentés és betöltés funkció, mely a beszélgetés közben fejlődött tudástár állapotát tárolja.
  Ebből a menüből érhető el a logolás ki és bekapcsolása, ami egy olyan fájlt generál, ami tartalmazza az aktuális beszélgetés inputjait, válaszait, valamint az elemzés fő lépéseit, azoknak kimenetét vagy sikertelenségét.
  Az eszközök menüből érhető el az oktatás ablak
  
- Grafikus felület a robot "tanítására"
  Itt határozhatóak meg a robot fő tulajdonságai, például szerkeszthetjük a bemutatkozását, és definiálhatunk sajátos válaszokat megadott kérdésekre.
  Egy táblázatba foglalva látjuk az eddig betöltött kérdéseket és válaszokat. 
  Ezeknek a formátuma egyszerű szöveges fájl, mely sorai, ha K:-val kezdődnek kérdés következik, ha pedig V:-vel, akkor a legutóbbi kérdéshez adható válaszok. Mentéskor egy ilyen fájl generálódik.
  A táblázaton keresztül egyszerűbben tudjuk ezeket a kérdéseket felvenni, szerkeszteni, új válaszokat hozzáadni, vagy esetleg törölni, és mentés után azonnal kipróbálhatóak a chat ablakban.
  
- Egyszerű input elemző
  Az inputot a program először mondatokra bonta, és összehasonlítja az előre leírt mondatokkal, hogy van-e pontos egyezés. Ha igen, a hozzá tartozó válaszokból választ egyet, és ez lesz a válasza. Abban az esetben, ha ebben a listában nem találta meg a megfelelő választ, a mondatot megpróbálja elemezni, és egy primitív mondatra bontani, mely csak alanyból, állátmányból és tárgyból áll. Ennek a felbontásnak első lépésben a megfelelő szófajú szavak szótövének megkeresésével, majd megfelelő ragok illesztésével próbálkozik. Amennyiben nem sikerült, az általa ismert szavak közül a legnagyobb egyezőséggel bírót választja ki.
  
- Kérdés generáló
  Abban az esetben, ha az input nem kérdés volt, a robot tesz fel egy kérdést, ezzel továbblendítve a beszélgetést. A kérdést az alapján generálja, hogy az előző mondat elemzése mennyire volt sikeres. Ha sikerült meghatároznia az állítmányt és a tárgyat is, akkor egy viszonylag kerek mondattal fog visszakérdezni egy véletlen kérdőszóval, remélhetőleg helyes ragozással. (abban az esetben, ha a mondatelemzésnél a mondatrészek meghatározása a ragozás keresésével történt, akkor sikerülni fog)
  
- Válasz generáló
  Ha az input kérdés volt, akkor a robot megpróbál válaszolni rá. Ha nincs válasza az előre definiált válaszok között, akkor megpróbál a beszélgetés során tanult válaszokból generálni. Ha nem sikerül, akkor a beégetett semleges válaszai közül választ egyet, ami megfelelő a kérdőszóhoz, és azt adja válaszul. 
  
- Belső tanuló modell
  A program futása során mindig megnézi, hogy a felhasználó milyen választ adott a robot által generált kérdésre. Hogy elég egszerű legyen a működése, csak azt vizsgálja, hogy milyen kérdőszóval, milyen állítmányra, vagy tárgyra jött a válasz. Így például egy: 
  " - Milyen könyvet olvasol?" 
  " - Krimit."
  beszélgetés után a tudásbázisba fog kerülni a "Milyen" kérdőszóval és "könyv" kérdezett szóval a "krimi", mint válasz. Így remélhetőleg legközelebb, ha a beszélgetés során felmerül a "Milyen könyv" kérdés valamilyen kontextusban, a robot "krimi" választ fog adni, a megfelelő ragozással, a helyett, hogy "nem tudom".
  
  
  
