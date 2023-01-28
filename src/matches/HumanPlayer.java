package matches;

import java.util.Scanner;

public class HumanPlayer implements Player{

    private final String name;
    public final Scanner scanner;


    public HumanPlayer(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int chooseMatchesToPick(GameContext context) { //validálni kell
       int userPick = 0; // a do-while miatt muszáj inicializálni
        int maxPick = context.getMaxPick();
       do {
           try {
               System.out.print(context.getEcho(name)); //String echoString = String.format("%s: Mennyit szeretnél elvenni? %s",name, possiblePicks);
               userPick = Integer.parseInt(scanner.nextLine());
               if(userPick < 1 || userPick > maxPick){
                   System.out.printf("\u001b[1;31m" + "You must enter a number between 1 and %d!%n" + "\u001b[0m", maxPick);
               }
           } catch (NumberFormatException e) {
               System.out.println("\u001b[1;31m" + "This is not a valid integer!" + "\u001b[0m");
           }
       } while (userPick < 1 || userPick > maxPick);
       return userPick;
    }
}
