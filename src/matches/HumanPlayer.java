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
    public int chooseMatchesToPick(GameContext context) { //valid�lni kell
       int userPick = 0; // a do-while miatt musz�j inicializ�lni
        int maxPick = context.getMaxPick();
       do {
           try {
               System.out.print(context.getEcho(name)); //String echoString = String.format("%s: Mennyit szeretn�l elvenni? %s",name, possiblePicks);
               userPick = Integer.parseInt(scanner.nextLine());
               if(userPick < 1 || userPick > maxPick){
                   System.out.printf("1 �s %d k�z�tti sz�mot kell be�rnod!%n", maxPick);
               }
           } catch (NumberFormatException e) {
               System.out.println("Nem sz�mot �rt�l be!");
           }
       } while (userPick < 1 || userPick > maxPick);
       return userPick;
    }
}
