import enums.GameType;
import enums.Inning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Game {

    private final ArrayList<Inning> innings = new ArrayList<>(List.of(Inning.TOP1, Inning.BOTTOM1, Inning.TOP2, Inning.BOTTOM2, Inning.TOP3, Inning.BOTTOM3, Inning.TOP4, Inning.BOTTOM4));

    Announcer announcer = new Announcer();

    public Team teamOne;
    public Team teamTwo;
    private final GameType gameType;

    private int teamOneTieBreakerPoints;
    private int teamTwoTieBreakerPoints;

    public Field field = new Field(this);
    
    public Team fieldingTeam;
    public Team battingTeam;
    public int outs;
    public int strikes;
    public int balls;
    
    public Game (Team teamOne, Team teamTwo, GameType gameType){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.gameType = gameType;
        new GameGenerator(teamOne, teamTwo, this);
    }

    public Team runGame(){

        teamOne.score = 0;
        teamTwo.score = 0;
        for(int x = 0; x < 5; x++){
            teamOne.roster.get(0).clearRecord();
            teamTwo.roster.get(0).clearRecord();
        }

        for(Inning currentInning : innings){
            
            System.out.println("////----------------\\\\\\\\\nINNING SWAPPED TO " + currentInning.getName() + "\n////----------------\\\\\\\\\n");

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
                runScenario(fieldingTeam.fieldingPositions.choosePitcher(currentInning), battingTeam.battingPositions.currentBatter, false);
            }
        }
        return findWinner();
    }

    public void runScenario(Player pitcher, Player batter, boolean tiebreakerGame){
        Scenario currentAtBat = new Scenario(this, field,
            pitcher, 
            batter, 
            fieldingTeam.fieldingPositions.rightFielder, 
            fieldingTeam.fieldingPositions.leftFielder, 
            fieldingTeam.fieldingPositions.centerFielder);

        currentAtBat.runPlay();

        if(outs >= (tiebreakerGame ? 1 : 3)){
            return;
        }

        if (strikes > 3){
            fieldingTeam.fieldingPositions.currentPitcher.peopleStruckOut++; //Stat
            batter.timesStruckOut++;
            System.out.println(announcer.strikeoutCall(batter, pitcher) + "\n");
            if(!tiebreakerGame){
                battingTeam.battingPositions.nextBatter();
            }
            outs++;
        } else if (balls > 3){
            fieldingTeam.fieldingPositions.currentPitcher.peopleWalked++; //Stat
            batter.timesWalked++;
            System.out.println(batter.name + " got walked");
            battingTeam.score += field.calculateRuns(batter);
            if(!tiebreakerGame){
                battingTeam.battingPositions.nextBatter();
            }
        }
    }

    public Team findWinner(){

        if (!(teamOne.score == teamTwo.score)){

            return (teamOne.score > teamTwo.score ? teamOne : teamTwo);

        } else {

            field.clearAllBases();
            System.out.println("The points are tied so the game moves to the tiebreaker!\n");

            switch (gameType) {
                
                case PLAYOFF -> {
                    ArrayList<Player> teamOneBatters = new ArrayList<>(teamOne.roster);

                    Collections.sort(teamOneBatters, new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            return Integer.compare(o2.spd + o2.awr + o2.con + o2.pwr, o1.spd + o1.awr + o1.con + o1.pwr);
                        }
                    });
                    teamOneBatters.remove(3);
                    teamOneBatters.remove(3);

                    ArrayList<Player> teamTwoBatters = new ArrayList<>(teamTwo.roster);

                    Collections.sort(teamTwoBatters, new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            return Integer.compare(o2.spd + o2.awr + o2.con + o2.pwr, o1.spd + o1.awr + o1.con + o1.pwr);
                        }
                    });
                    teamTwoBatters.remove(3);
                    teamTwoBatters.remove(3);

                    teamOneTieBreakerPoints = 0;

                    battingTeam = teamOne;
                    fieldingTeam = teamTwo;

                    System.out.println("First up are the " + teamOne.fullName + "\n");

                    for (Player batter : teamOneBatters){
                        
                        outs = 0;
                        field.clearAllBases();

                        while (outs < 1) {
                            runScenario(fieldingTeam.fieldingPositions.pitcherOne, batter, true);
                            if (!field.firstBaseRunner.equals(field.emptyPlayer)){
                                outs++;
                                teamOneTieBreakerPoints++;
                            }
                        }
                    }

                    System.out.println("The " + teamOne.fullName + " are at " + teamOneTieBreakerPoints + " tiebreaker point(s).\n");

                    System.out.println("Next up are the " + teamOne.fullName + "\n");
                    
                    teamTwoTieBreakerPoints = 0;

                    battingTeam = teamTwo;
                    fieldingTeam = teamOne;

                    for (Player batter : teamTwoBatters){
                        
                        outs = 0;
                        field.clearAllBases();

                        while (outs < 1){
                            runScenario(fieldingTeam.fieldingPositions.pitcherOne, batter, true);
                            if (!field.firstBaseRunner.equals(field.emptyPlayer)){
                                outs++;
                                teamTwoTieBreakerPoints++;
                            }
                        }
                    }

                    System.out.println("The " + teamTwo.fullName + " are at " + teamTwoTieBreakerPoints + " tiebreaker point(s).\n");

                    if(teamOneTieBreakerPoints > teamTwoTieBreakerPoints){
                        System.out.println("After the tiebreaker rounds, the " + teamOne.fullName + " come out on top!");
                        return teamOne;
                    } else if (teamOneTieBreakerPoints < teamTwoTieBreakerPoints) {
                        System.out.println("After the tiebreaker rounds, the " + teamTwo.fullName + " come out on top!");
                        return teamTwo;
                    } else {
                        return findWinner();
                    }
                }
            
                case REGULAR -> {
                    return teamOne;
                }

                default -> {
                    return teamOne;
                }
            }
        }
    }
}
