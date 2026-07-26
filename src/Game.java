import enums.Inning;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private ArrayList<Inning> innings = new ArrayList<>();

    public Team teamOne;
    public Team teamTwo;

    public Field field = new Field(this);
    
    public Team fieldingTeam;
    public Team battingTeam;
    public int outs;
    public int strikes;
    public int balls;
    
    public Game (Team teamOne, Team teamTwo){
        innings.addAll(List.of(Inning.TOP1, Inning.BOTTOM1, Inning.TOP2, Inning.BOTTOM2, Inning.TOP3, Inning.BOTTOM3, Inning.TOP4, Inning.BOTTOM4));
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        new GameGenerator(teamOne, teamTwo);
    }

    public Team runGame(){
        for(Inning currentInning : innings){
            
            System.out.println("\nINNING SWAPPED TO " + currentInning.getName() + "\n");

            outs = 0;
            strikes = 0;
            balls = 0;
            field.clearAllBases();
            
            if (currentInning.getName().equals("BOTTOM4")){
                if (teamOne.score > teamTwo.score){
                    return teamOne;
                }
            }

            if (currentInning.getName().contains("TOP")) {
                battingTeam = teamOne;
                fieldingTeam = teamTwo;
            } else {
                battingTeam = teamTwo;
                fieldingTeam = teamOne;
            }

            while (outs < 3){
                Scenario currentAtBat = new Scenario(this, field,
                fieldingTeam.fieldingPositions.choosePitcher(currentInning), 
                battingTeam.battingPositions.currentBatter, 
                fieldingTeam.fieldingPositions.rightFielder, 
                fieldingTeam.fieldingPositions.leftFielder, 
                fieldingTeam.fieldingPositions.centerFielder);

                currentAtBat.runPlay();

                if(outs == 3){
                    break;
                }

                if (strikes > 3){
                    fieldingTeam.fieldingPositions.currentPitcher.peopleStruckOut++; //Stat
                    battingTeam.battingPositions.currentBatter.timesStruckOut++;
                    System.out.println(battingTeam.battingPositions.currentBatter.name + " struck out");
                    battingTeam.battingPositions.nextBatter();
                    outs++;
                } else if (balls > 3){
                    fieldingTeam.fieldingPositions.currentPitcher.peopleWalked++; //Stat
                    battingTeam.battingPositions.currentBatter.timesWalked++;
                    System.out.println(battingTeam.battingPositions.currentBatter.name + " got walked");
                    battingTeam.score += field.calculateRuns(battingTeam.battingPositions.currentBatter);
                    battingTeam.battingPositions.nextBatter();
                }
            }
        }
        return teamOne.score > teamTwo.score ? teamOne : teamTwo; //IMPLIMENT TIEING MECHANIC//
        //IMPLIMENT TIEING MECHANIC////IMPLIMENT TIEING MECHANIC////IMPLIMENT TIEING MECHANIC//
        //IMPLIMENT TIEING MECHANIC////IMPLIMENT TIEING MECHANIC////IMPLIMENT TIEING MECHANIC//
    }
}
