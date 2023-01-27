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
        int pick = 4 - context.getPreviousPick(); // ez még nem okos csak Random 1, vagy 2, vagy 3-at dob.
        System.out.println(context.getEcho(getName()) + pick); //mellékhatás!
        return pick;
    }
}
