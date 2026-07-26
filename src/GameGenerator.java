import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameGenerator {

    public GameGenerator(Team teamOne, Team teamTwo) {
        generateBattingOrder(teamOne);
        generateFieldingPositions(teamOne);

        generateBattingOrder(teamTwo);
        generateFieldingPositions(teamTwo);
    }

    private void generateFieldingPositions(Team team){

        ArrayList<Player> playerOptions = new ArrayList<>(team.roster);

        Collections.sort(playerOptions, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.vel, o1.vel);
            }
        });

        team.fieldingPositions.pitcherOne = playerOptions.get(0);
        playerOptions.get(0).playedAsPitcher = true;
        playerOptions.remove(0);
        team.fieldingPositions.pitcherTwo = playerOptions.get(0);
        playerOptions.get(0).playedAsPitcher = true;
        playerOptions.remove(0);

        Collections.shuffle(playerOptions);

        team.fieldingPositions.leftFielder = playerOptions.get(0);
        team.fieldingPositions.centerFielder = playerOptions.get(1);
        team.fieldingPositions.rightFielder = playerOptions.get(2);
    }

    private void generateBattingOrder(Team team){
        ArrayList<Player> battingOrder = new ArrayList<>();
        battingOrder.addAll(team.roster);
        Collections.shuffle(battingOrder);
        team.battingPositions = new BattingPositions(battingOrder);
    }
}
