package matches;

import java.util.Random;

public class MachinePlayer implements Player{

    private final Random random = new Random();

    @Override
    public String getName() {
        return "AI";
    }

    @Override
    public int chooseMatchesToPick(int maxPicks, String echoString) {
        int pick = random.nextInt(maxPicks) + 1; // ez még nem okos csak Random 1, vagy 2, vagy 3-at dob.
        System.out.println("AI picked: " + pick); //mellékhatás!
        return pick;
    }
}
