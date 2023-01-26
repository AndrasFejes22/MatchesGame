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
    public int chooseMatchesToPick(int maxPicks, String echoString) { //valid�lni kell
       int userPick = 0; // a do-while miatt musz�j inicializ�lni
       do {
           try {
               System.out.print(echoString); //String echoString = String.format("%s: Mennyit szeretn�l elvenni? %s",name, possiblePicks);
               userPick = Integer.parseInt(scanner.nextLine());
               if(userPick < 1 || userPick > maxPicks){
                   System.out.printf("1 �s %d k�z�tti sz�mot kell be�rnod!%n", maxPicks);
               }
           } catch (NumberFormatException e) {
               System.out.println("Nem sz�mot �rt�l be!");
           }
       } while (userPick < 1 || userPick > maxPicks);
       return userPick;
    }
}
