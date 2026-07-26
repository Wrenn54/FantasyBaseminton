
import java.util.ArrayList;

public class BattingPositions {
    
    private int currentBatterIndex = 4;

    public Player currentBatter;
    private ArrayList<Player> battingOrder;

    public BattingPositions(ArrayList<Player> battingOrder){
        this.battingOrder = battingOrder;
        nextBatter();
    }

    public void nextBatter(){
        currentBatterIndex++;
        if (currentBatterIndex == 5){
            currentBatterIndex = 0;
        }
        
        currentBatter = battingOrder.get(currentBatterIndex);
        
        this.currentBatter.totalAtBats++;
    }
}
