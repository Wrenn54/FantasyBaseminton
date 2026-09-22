
import enums.Inning;

public class FieldingPositions {
    public Player pitcherOne;
    public Player pitcherTwo;
    public Player rightFielder;
    public Player leftFielder;
    public Player centerFielder;

    public Player currentPitcher;

    public Player choosePitcher(Inning inning){
        currentPitcher = (inning.getString().contains("1") || inning.getString().contains("2")) ? pitcherOne : pitcherTwo;
        return currentPitcher;
    }
}
