package matches;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * TODO: imlement "real" AI
 */

public class MatchesGame {

    //public static final int MAX_TAKEN_MATCH = 3;

    public void play(){
        System.out.println("Gyufás játék");
        //int numberOfMatches = 13; // a 13 az 4 + 4 + 4 + 1 azaz lehet azt csináli hogy az ember által választot picket mindig kiegészítjük 4re, így neki a végén 1 marad = "buta" AI

        try (Scanner input = new Scanner(System.in)){
            System.out.print("Mi a játékos neve?");
            String playerName = input.nextLine();

            System.out.print("Mennyi gyufával játsszunk?");
            int numberOfMatches = Integer.parseInt(input.nextLine()); //TODO validation
            Player[] players = new Player[]{new HumanPlayer(playerName, input), new MachinePlayer()};
            GameContext context = new GameContext(numberOfMatches);

            while (context.isNextRoundPossible()){
                int round = context.getRound(); //**
                Player currentPlayer = players[round % players.length]; // bármilyen szám 2-vel való maradékos osztása az 0, vagy 1 váltakozva*
                // *ITT váltakozik minden körben a Player: [0] = human , [1] = AI

                System.out.printf("""
                        %d. kör
                        Az asztalon van %d gyufa
                       
                        """, round + 1, context.getNumberOfMatches());

                int playerPick = currentPlayer.chooseMatchesToPick(context);
                //round update:
                context.pick(playerPick);
                System.out.println();
            }
            //winner:
            Player winner = players[context.getRound() % players.length]; //* itt a context.getRound()-ot nem alakítjuk át a **-al jelzett sorban lekérdezett round-á, mert a
            //context.pick(playerPick); megváltoztatja
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
