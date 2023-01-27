package matches;

public class GameContext {

    /**
     * Ha a játékos választhat a meccs elején a gyufák számát a gépnek akkor is mindig a gyufa = 4-el osztható szám felé kell terelnie a játékot
     * mert pl.: a 13 az 4 + 4 + 4 + 1, azaz lehet azt csináli hogy az ember által választot picket mindig kiegészítjük 4re, így neki a végén 1 marad, és mindig veszít
     * ehhez a gépnek kell tudnia a játékos által megadott gyufák számát (a játék elején)
     * így viszont az "int chooseMatchesToPick(int maxPicks, String echoString, int previousPick);" 4 paraméteres lenne
     * így inkább legyen egy objektum ami leírja a játék állapotát = GameContext
     */
}
