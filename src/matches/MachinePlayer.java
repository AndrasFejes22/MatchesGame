package matches;

import java.util.Random;

public class MachinePlayer implements Player{

    private final Random random = new Random();

    @Override
    public String getName() {
        return "AI";
    }

    @Override
    public int chooseMatchesToPick(GameContext context) {

        int pick = 0;
        if((context.getNumberOfMatches() + context.getPreviousPick() - 1) % 4 == 0){ //a context.getPreviousPick()-t az ember húzta
            //Winning strategy:
            System.out.println("AI strategy: winning");
            pick = 4 - context.getPreviousPick();
        } else {
            //Aim for winning strategy:
            pick = (context.getPreviousPick() - 1) % 4; // gyufa = 4-el osztható szám felé kell terelnie a játékot az AI-nak
            if(pick == 0){
                //No strategy:
                pick = random.nextInt(context.getMaxPick()) + 1;
                System.out.println("AI strategy: Random");
            } else {
                System.out.println("AI strategy: aim for winning strategy");
            }
        }

        System.out.println(context.getEcho(getName()) + pick); //mellékhatás!
        return pick;
    }
}
