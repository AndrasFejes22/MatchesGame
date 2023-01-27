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
        //int numberOfMatches = 13; // a 13 az 4 + 4 + 4 + 1 azaz lehet azt csin�li hogy az ember �ltal v�lasztot picket mindig kieg�sz�tj�k 4re, �gy neki a v�g�n 1 marad = "buta" AI

        try (Scanner input = new Scanner(System.in)){
            System.out.print("Mi a j�t�kos neve?");
            String playerName = input.nextLine();

            System.out.print("Mennyi gyuf�val j�tsszunk?");
            int numberOfMatches = Integer.parseInt(input.nextLine()); //TODO validation
            Player[] players = new Player[]{new HumanPlayer(playerName, input), new MachinePlayer()};
            GameContext context = new GameContext(numberOfMatches);

            while (context.isNextRoundPossible()){
                int round = context.getRound(); //**
                Player currentPlayer = players[round % players.length]; // b�rmilyen sz�m 2-vel val� marad�kos oszt�sa az 0, vagy 1 v�ltakozva*
                // *ITT v�ltakozik minden k�rben a Player: [0] = human , [1] = AI

                System.out.printf("""
                        %d. k�r
                        Az asztalon van %d gyufa
                       
                        """, round + 1, context.getNumberOfMatches());

                int playerPick = currentPlayer.chooseMatchesToPick(context);
                //round update:
                context.pick(playerPick);
                System.out.println();
            }
            //winner:
            Player winner = players[context.getRound() % players.length]; //* itt a context.getRound()-ot nem alak�tjuk �t a **-al jelzett sorban lek�rdezett round-�, mert a
            //context.pick(playerPick); megv�ltoztatja
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
