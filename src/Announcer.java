
import enums.Inning;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Announcer {

    private final Main main;

    private final ArrayList<String> strikeoutCallQueue = new ArrayList<>();
    private final ArrayList<String> walkCallQueue = new ArrayList<>();
    private final ArrayList<String> strikeThrownQueue = new ArrayList<>();
    private final ArrayList<String> ballThrownQueue = new ArrayList<>();
    private final ArrayList<String> birdieHitCallQueue = new ArrayList<>();
    private final ArrayList<String> birdieCaughtQueue = new ArrayList<>();

    public Announcer(Main main) {
        this.main = main;
    }

    public void updatePlayList(String newPlay) {
        for (int x = (int) ((main.gameInterface.screenHeight) / 50) - 5; x >= 1; x--) {
            main.gameInterface.playReadoutLabels.get(x)
                    .setText(main.gameInterface.playReadoutLabels.get(x - 1).getText());
            main.gameInterface.playReadoutLabels.get(x)
                    .setFont(main.gameInterface.playReadoutLabels.get(x - 1).getFont());
        }
        main.gameInterface.playReadoutLabels.get(0)
                .setFont(new Font("Monospaced", Font.PLAIN, 25));
        main.gameInterface.playReadoutLabels.get(0).setText(newPlay);
    }

    public void updatePlayList(String newPlay, int style) {
        for (int x = (int) ((main.gameInterface.screenHeight) / 50) - 5; x >= 1; x--) {
            main.gameInterface.playReadoutLabels.get(x)
                    .setText(main.gameInterface.playReadoutLabels.get(x - 1).getText());
            main.gameInterface.playReadoutLabels.get(x)
                    .setFont(main.gameInterface.playReadoutLabels.get(x - 1).getFont());
        }
        main.gameInterface.playReadoutLabels.get(0)
                .setFont(new Font("Monospaced", style, 25));
        main.gameInterface.playReadoutLabels.get(0).setText(newPlay);
    }

    public void inningSwapAnnouncment(Inning inning) {
        updatePlayList("Inning swapped to " + inning.getName(), Font.BOLD);
    }

    public void tieBreakerAnnouncment() {
        updatePlayList("The points are tied so the game moves to the tiebreaker!", Font.BOLD);
    }

    public void tieBreakerPointsAnnouncment(String teamName, int teamTieBreakerPoints) {
        updatePlayList("The " + teamName + " are at " + teamTieBreakerPoints + " tiebreaker point(s).");
    }

    public void tieBreakerFirstUpAnnouncment(String teamName) {
        updatePlayList("First up are the " + teamName);
    }

    public void tieBreakerNextUpAnnouncment(String teamName) {
        updatePlayList("Next up are the " + teamName);
    }

    public void strikeThrownCall(String batterName, String pitcherName) {
        if (strikeThrownQueue.isEmpty()) {
            strikeThrownQueue.addAll(resetStrikeThrownCalls());
            Collections.shuffle(strikeThrownQueue);
        }
        String returnedString = strikeThrownQueue.get(0);
        strikeThrownQueue.remove(0);
        updatePlayList(returnedString.replaceAll("%batter%", batterName).replaceAll("%pitcher%", pitcherName));
    }

    public void ballThrownCall(String batterName, String pitcherName) {
        if (ballThrownQueue.isEmpty()) {
            ballThrownQueue.addAll(resetBallThrownCalls());
            Collections.shuffle(ballThrownQueue);
        }
        String returnedString = ballThrownQueue.get(0);
        ballThrownQueue.remove(0);
        updatePlayList(returnedString.replaceAll("%batter%", batterName).replaceAll("%pitcher%", pitcherName));
    }

    public void strikeoutCall(String batterName, String pitcherName) {
        if (strikeoutCallQueue.isEmpty()) {
            strikeoutCallQueue.addAll(resetStrikeoutCalls());
            Collections.shuffle(strikeoutCallQueue);
        }
        String returnedString = strikeoutCallQueue.get(0);
        strikeoutCallQueue.remove(0);
        updatePlayList(returnedString.replaceAll("%batter%", batterName).replaceAll("%pitcher%", pitcherName));
    }

    public void walkCall(String batterName, String pitcherName) {
        if (walkCallQueue.isEmpty()) {
            walkCallQueue.addAll(resetWalkCalls());
            Collections.shuffle(walkCallQueue);
        }
        String returnedString = walkCallQueue.get(0);
        walkCallQueue.remove(0);
        updatePlayList(returnedString.replaceAll("%batter%", batterName).replaceAll("%pitcher%", pitcherName));
    }

    public void birdieHitcall(String batterName) {
        if (birdieHitCallQueue.isEmpty()) {
            birdieHitCallQueue.addAll(resetBirdieHitCalls());
            Collections.shuffle(birdieHitCallQueue);
        }
        String returnedString = birdieHitCallQueue.get(0);
        birdieHitCallQueue.remove(0);
        updatePlayList(returnedString.replaceAll("%batter%", batterName));
    }

    public void birdieCaughtCall(String batterName, String fielderName) {
        if (birdieCaughtQueue.isEmpty()) {
            birdieCaughtQueue.addAll(resetBirdieCaughtCalls());
            Collections.shuffle(birdieCaughtQueue);
        }
        String returnedString = birdieCaughtQueue.get(0);
        birdieCaughtQueue.remove(0);
        updatePlayList(returnedString.replaceAll("%batter%", batterName).replaceAll("%fielder%", fielderName));
    }

    public void lineDriveCatchCall() {

    }

    public void lineDriveNoCatchCall() {

    }

    public void floaterCatchCall() {

    }

    public void floaterNoCatchCall() {

    }

    public void doublePlayCall() {

    }

    public ArrayList<String> resetBallThrownCalls() {
        return new ArrayList<>(List.of(
                "%pitcher% threw a ball",
                "%pitcher% threw a ball"));
    }

    public ArrayList<String> resetStrikeThrownCalls() {
        return new ArrayList<>(List.of(
                "%pitcher% threw a strike",
                "%pitcher% threw a strike"));
    }

    public ArrayList<String> resetStrikeoutCalls() {
        return new ArrayList<>(List.of(
                "You can see the fear in %batter%'s eyes as %pitcher% strikes him out!",
                "%batter% didn't even have a chance! Another strikeout by %pitcher%!",
                "%pitcher% is on point today, striking out %batter%!",
                "%pitcher% strikes fear in the opponent's dugout, striking out!",
                "%pitcher% is on fire! Another strikeout!",
                "And the end of this game ticks a little closer with a strikeout by %pitcher%."));
    }

    public ArrayList<String> resetWalkCalls() {
        return new ArrayList<>(List.of(
                "%batter% walks!",
                "%pitcher% seems to have lost his mojo, walking %batter%!",
                "And %batter% takes his base after %pitcher% walks him!",
                "We're entering a dangerous situation for the defense now, as %pitcher% walks %batter%!",
                "The coach must be unhappy with %pitcher% after he walks %batter%.",
                "And that pitch sails over %batter%'s head for ball four!",
                "And %batter% walks! The pitcher is calling for a nothing burger!"));
    }

    public ArrayList<String> resetBirdieHitCalls() {
        return new ArrayList<>(List.of(
                "The birdie is hit by %batter%",
                "The birdie is hit by %batter%"));
    }

    public ArrayList<String> resetBirdieCaughtCalls() {
        return new ArrayList<>(List.of(
                "%batter% is caught out by %fielder%",
                "%batter% is caught out by %fielder%"));
    }
}