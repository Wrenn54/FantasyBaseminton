
import java.util.ArrayList;
import java.util.List;

public class Team {

    public ArrayList<Player> roster;
    public BattingPositions battingPositions;
    public FieldingPositions fieldingPositions = new FieldingPositions();

    public int score;

    public String fullName;

    public Team(String fullName) {
        this.fullName = fullName;
        this.roster = new ArrayList<>();
    }

    public void addPlayerToRoster(Player player) {
        roster.add(player);
    }

    public void addPlayersToRoster(ArrayList<Player> players) {
        roster.addAll(players);
    }
    
    public void addPlayersToRoster(List<Player> players) {
        roster.addAll(players);
    }

    public void removePlayerFromRoster(Player player){
        roster.remove(player);
    }
}
