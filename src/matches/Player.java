package matches;

public interface Player {


    String getName();

    int chooseMatchesToPick(int maxPicks, String echoString, int previousPick); //3.: int humanPick most
}
