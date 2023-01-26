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
    public int chooseMatchesToPick(int maxPicks, String echoString) { //validálni kell
       int userPick = 0; // a do-while miatt muszáj inicializálni
       do {
           try {
               System.out.print(echoString); //String echoString = String.format("%s: Mennyit szeretnél elvenni? %s",name, possiblePicks);
               userPick = Integer.parseInt(scanner.nextLine());
               if(userPick < 1 || userPick > maxPicks){
                   System.out.printf("1 és %d közötti számot kell beírnod!%n", maxPicks);
               }
           } catch (NumberFormatException e) {
               System.out.println("Nem számot írtál be!");
           }
       } while (userPick < 1 || userPick > maxPicks);
       return userPick;
    }
}
