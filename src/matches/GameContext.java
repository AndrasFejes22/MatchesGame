package matches;

import java.util.StringJoiner;

/**
 * Ha a játékos választhat a meccs elején a gyufák számát a gépnek akkor is mindig a gyufa = 4-el osztható szám felé kell terelnie a játékot
 * mert pl.: a 13 az 4 + 4 + 4 + 1, azaz lehet azt csináli hogy az ember által választot picket mindig kiegészítjük 4re, így neki a végén 1 marad, és mindig veszít
 * ha sehogy sem tudja húzzon random számú gyufát
 * ehhez a gépnek kell tudnia a játékos által megadott gyufák számát (a játék elején)
 * így viszont az "int chooseMatchesToPick(int maxPicks, String echoString, int previousPick);" 4 paraméteres lenne
 * így inkább legyen egy objektum ami leírja a játék állapotát = GameContext
 */


public class GameContext {

    private int numberOfMatches;
    private int previousPick;
    private int round;

    public GameContext(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public int getPreviousPick() {
        return previousPick;
    }

    public void setPreviousPick(int previousPick) {
        this.previousPick = previousPick;
    }

    public int getMaxPick(){
        return Math.min(3, numberOfMatches);
    }

    public String getEcho(String name){
        String possiblePicks = calculatePossiblePicks(getMaxPick());
        return String.format("%s: Mennyit szeretnél elvenni? %s",name, possiblePicks);
    }

    public boolean isNextRoundPossible(){
        return numberOfMatches > 0;
    }

    public void pick(int pick){
        //validálni kell
        previousPick = pick;
        numberOfMatches -= pick;
        round++;
    }

    private String calculatePossiblePicks(int maxPicks) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 1; i <= maxPicks; i++) {
            joiner.add(Integer.toString(i));
        }
        return joiner.toString();
    }
}
