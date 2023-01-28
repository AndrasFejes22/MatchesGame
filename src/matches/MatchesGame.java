package matches;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * TODO: 1.: imlement "real" AI
 * 2.: validations human-->negative number: human wins, not number: NumberFormatException, pick < maxPick?
 * 3.: Test cases?
 */

public class MatchesGame {

    //public static final int MAX_TAKEN_MATCH = 3;

    public void play(){
        System.out.println("Match game");
        //int numberOfMatches = 13; // a 13 az 4 + 4 + 4 + 1 azaz lehet azt csináli hogy az ember által választot picket mindig kiegészítjük 4re, így neki a végén 1 marad = "buta" AI

        try (Scanner input = new Scanner(System.in)){
            System.out.print("What is the name of the player? ");
            String playerName = input.nextLine();

            //System.out.print("Mennyi gyufával játsszunk? ");
            //int numberOfMatches = Integer.parseInt(input.nextLine()); //TODO validation
            int numberOfMatches = readInt("How many matches should we play the game with? ", input);
            Player[] players = new Player[]{new HumanPlayer(playerName, input), new MachinePlayer()};
            GameContext context = new GameContext(numberOfMatches);

            while (context.isNextRoundPossible()){
                int round = context.getRound(); //**
                Player currentPlayer = players[round % players.length]; // bármilyen szám 2-vel való maradékos osztása az 0, vagy 1 váltakozva*
                // *ITT váltakozik minden körben a Player: [0] = human , [1] = AI

                System.out.printf("""
                        %d. round
                        There are %d matches on the table
                       
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

    static int readInt(String askMessage, Scanner scanner) {
        boolean inputCorrect;
        int number = 0;
        do {
            inputCorrect = true;
            System.out.println(askMessage);
            try {
                number = scanner.nextInt();
                if(number < 1){
                    System.out.println("\u001b[1;31m" + "You cannot give a number less than 1!" + "\u001b[0m"); //red letters
                    inputCorrect = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("\u001b[1;31m" + "This is not a valid integer!" + "\u001b[0m"); //red letters
                inputCorrect = false;
            } finally {
                scanner.nextLine();
            }
        } while (!inputCorrect);
        return number;
    }

    /*
    private String calculatePossiblePicks(int maxPicks) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 1; i <= maxPicks; i++) {
            joiner.add(Integer.toString(i));
        }
        return joiner.toString();
    }
    */
}
