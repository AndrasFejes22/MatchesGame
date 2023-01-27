package matches;

import java.util.StringJoiner;

/**
 * Ha a j�t�kos v�laszthat a meccs elej�n a gyuf�k sz�m�t a g�pnek akkor is mindig a gyufa = 4-el oszthat� sz�m fel� kell terelnie a j�t�kot
 * mert pl.: a 13 az 4 + 4 + 4 + 1, azaz lehet azt csin�li hogy az ember �ltal v�lasztot picket mindig kieg�sz�tj�k 4re, �gy neki a v�g�n 1 marad, �s mindig vesz�t
 * ha sehogy sem tudja h�zzon random sz�m� gyuf�t
 * ehhez a g�pnek kell tudnia a j�t�kos �ltal megadott gyuf�k sz�m�t (a j�t�k elej�n)
 * �gy viszont az "int chooseMatchesToPick(int maxPicks, String echoString, int previousPick);" 4 param�teres lenne
 * �gy ink�bb legyen egy objektum ami le�rja a j�t�k �llapot�t = GameContext
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
        return String.format("%s: Mennyit szeretn�l elvenni? %s ",name, possiblePicks);
    }

    public boolean isNextRoundPossible(){
        return numberOfMatches > 0;
    }

    public void pick(int pick){
        //valid�lni kell
        previousPick = pick;
        numberOfMatches -= pick;
        round++;
    }

    public int getRound() {
        return round;
    }

    private String calculatePossiblePicks(int maxPicks) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 1; i <= maxPicks; i++) {
            joiner.add(Integer.toString(i));
        }
        return joiner.toString();
    }
}
