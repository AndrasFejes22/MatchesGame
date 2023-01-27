package matches;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * TODO: imlement "real" AI
 */

public class MatchesGame {

    //public static final int MAX_TAKEN_MATCH = 3;

    public void play(){
        System.out.println("Gyuf�s j�t�k");
        //int numberOfMatches = 13; // a 13 az 4 + 4 + 4 + 1 azaz lehet azt csin�li hogy az ember �ltal v�lasztot picket mindig kieg�sz�tj�k 4re, �gy neki a v�g�n 1 marad
        int round = 0;
        try (Scanner input = new Scanner(System.in)){
            System.out.print("Mi a j�t�kos neve?");
            String playerName = input.nextLine();

            System.out.print("Mennyi gyuf�val j�tsszunk?");
            int numberOfMatches = Integer.parseInt(input.nextLine()); //TODO validation
            Player[] players = new Player[]{new HumanPlayer(playerName, input), new MachinePlayer()};
            int playerPick = 0;

            while (numberOfMatches > 0){
                int maxPicks = Math.min(3, numberOfMatches); // ez max 3 amig 3-n�l t�bb gyufa van lenn l�nyege hogy figyeli a marad�k gyuf�t �s ha ez <= 3 akkor be�ll�tja
                String possiblePicks = calculatePossiblePicks(maxPicks); //ez csak egy seg�d: legy�rtja a [1, 2, 3] , [1, 2], vagy [1] Stringeket �gy sz�pen
                Player currentPlayer = players[round % players.length]; // b�rmilyen sz�m 2-vel val� marad�kos oszt�sa az 0, vagy 1 v�ltakozva*
                // *ITT v�ltakozik minden k�rben a Player: [0] = human , [1] = AI
                // *b�rmennyi playerre j�, nem tud t�lindexel�dni se
                String name = currentPlayer.getName();
                System.out.printf("""
                        %d. k�r
                        Az asztalon van %d gyufa
                       
                        """, round + 1, numberOfMatches);
                String echoString = String.format("%s: Mennyit szeretn�l elvenni? %s",name, possiblePicks);
                playerPick = currentPlayer.chooseMatchesToPick(maxPicks,echoString, playerPick);
                //round update:
                numberOfMatches -= playerPick;
                round++;
                System.out.println();
            }
            //winner:
            Player winner = players[round % players.length];
            System.out.println("\n" + winner.getName() + " a gy�ztes!");
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
