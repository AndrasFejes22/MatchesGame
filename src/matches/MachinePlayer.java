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
        if((context.getNumberOfMatches() + context.getPreviousPick() - 1) % 4 == 0){ //a context.getPreviousPick()-t az ember h�zta
            //Winning strategy:
            System.out.println("Nyer�");
            pick = 4 - context.getPreviousPick();
        } else {
            //Aim for winning strategy:
            pick = (context.getPreviousPick() - 1) % 4; // gyufa = 4-el oszthat� sz�m fel� kell terelnie a j�t�kot az AI-nak
            if(pick == 0){
                //No strategy:
                pick = random.nextInt(context.getMaxPick()) + 1;
                System.out.println("Random");
            } else {
                System.out.println("terel�");
            }
        }

        System.out.println(context.getEcho(getName()) + pick); //mell�khat�s!
        return pick;
    }
}
