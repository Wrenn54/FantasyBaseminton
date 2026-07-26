
import java.util.ArrayList;

public class BattingPositions {
    
    private int currentBatterIndex = 4;

    public Player currentBatter;
    private ArrayList<Player> battingOrder;
    private Game game;

    public BattingPositions(ArrayList<Player> battingOrder, Game game){
        this.battingOrder = battingOrder;
        this.game = game;
        nextBatter();
    }

    public void nextBatter(){
        currentBatterIndex++;
        if (currentBatterIndex == 5){
            currentBatterIndex = 0;
        }
        
        game.strikes = 0;
        game.balls = 0;
        currentBatter = battingOrder.get(currentBatterIndex);
        
        this.currentBatter.totalAtBats++;
    }
}
