package matches;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * TODO: imlement "real" AI
 */

public class MatchesGame {

    public static final int MAX_TAKEN_MATCH = 3;

    public void play(){
        System.out.println("Gyufás játék");
        int numberOfMatches = 13;
        int round = 0;
        try (Scanner input = new Scanner(System.in)){
            System.out.print("Mi a játékos neve?");
            String playerName = input.nextLine();
            Player[] players = new Player[]{new HumanPlayer(playerName, input), new MachinePlayer()};

            while (numberOfMatches > 0){
                int maxPicks = Math.min(MAX_TAKEN_MATCH, numberOfMatches); // ez max 3 amig 3-nál több gyufa van lenn lényege hogy figyeli a maradék gyufát és ha ez <= 3 akkor beállítja
                String possiblePicks = calculatePossiblePicks(maxPicks); //ez csak egy segéd: legyártja a [1, 2, 3] , [1, 2], vagy [1] Stringeket így szépen
                Player currentPlayer = players[round % players.length]; // bármilyen szám 2-vel való maradékos osztása az 0, vagy 1 váltakozva*
                // *ITT váltakozik minden körben a Player: [0] = human , [1] = AI
                // *bármennyi playerre jó, nem tud túlindexelõdni se
                String name = currentPlayer.getName();
                System.out.printf("""
                        %d. kör
                        Az asztalon van %d gyufa
                       
                        """, round + 1, numberOfMatches);
                String echoString = String.format("%s: Mennyit szeretnél elvenni? %s",name, possiblePicks);
                int playerPick = currentPlayer.chooseMatchesToPick(maxPicks,echoString);
                //round update:
                numberOfMatches -= playerPick;
                round++;
                System.out.println();
            }
            //winner:
            Player winner = players[round % players.length];
            System.out.println("\n" + winner.getName() + " a gyõztes!");
        }


    }

    private String calculatePossiblePicks(int maxPicks) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 1; i <= maxPicks; i++) {
            joiner.add(Integer.toString(i));
        }
        return joiner.toString();
    }
}
