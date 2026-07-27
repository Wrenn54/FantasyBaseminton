
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Announcer {

    private ArrayList<String> strikeoutCallQueue = new ArrayList<>();
        
    public Announcer(){
        
    }

    public String strikeoutCall(Player batter, Player pitcher){
        if (strikeoutCallQueue.size() == 0){
            strikeoutCallQueue.addAll(resetStrikeoutCalls());
            Collections.shuffle(strikeoutCallQueue);
        }
        String returnedString = strikeoutCallQueue.get(0);
        strikeoutCallQueue.remove(0);
        return returnedString.replaceAll("batter", batter.name).replaceAll("pitcher", pitcher.name);
    }

    public ArrayList<String> resetStrikeoutCalls(){
        ArrayList<String> strikeoutCalls = new ArrayList<>(List.of(
        "You can see the fear in batter's eyes as pitcher strikes him out!",
        "batter didn't even have a chance! Another strikeout by pitcher!",
        "pitcher is on point today, striking out batter!",
        "pitcher strikes fear in the opponent's dugout, striking out!",
        "pitcher is on fire! Another strikeout!",
        "And the end of this game ticks a little closer with a strikeout by pitcher."));
        return strikeoutCalls;
    }

    public void walkCall(){

    }

    public void lineDriveCatchCall(){

    }

    public void lineDriveNoCatchCall(){

    }

    public void floaterCatchCall(){

    }

    public void floaterNoCatchCall(){

    }

    public void doublePlayCall(){

    }
}