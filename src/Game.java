import enums.GameType;
import enums.Inning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Game {

    private Scenario currentScenario;

    public final Main main;

    private final ArrayList<Inning> innings = new ArrayList<>(List.of(Inning.TOP1, Inning.BOTTOM1, Inning.TOP2,
            Inning.BOTTOM2, Inning.TOP3, Inning.BOTTOM3, Inning.TOP4, Inning.BOTTOM4));

    public final Announcer announcer;

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

    public Inning activeInning;

    public Game(Team teamOne, Team teamTwo, GameType gameType, Main main) {
        this.main = main;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.gameType = gameType;
        new GameGenerator(teamOne, teamTwo, this);
        announcer = new Announcer(main);
    }

    public void nextAction() {
        if (outs >= 3) {
            try {
                activeInning = innings.get(innings.indexOf(activeInning) + 1);
            } catch (Exception e) {
                findWinner();
            }

            announcer.inningSwapAnnouncment(activeInning);

            outs = 0;
            strikes = 0;
            balls = 0;
            field.clearAllBases();

            if (activeInning.getString().contains("TOP")) {
                battingTeam = teamOne;
                fieldingTeam = teamTwo;
            } else {
                battingTeam = teamTwo;
                fieldingTeam = teamOne;
            }

            if (activeInning.getString().equals("BOTTOM4")) {
                if (teamOne.score > teamTwo.score) {
                    findWinner();
                }
            }

            if (!(activeInning.getString().equals("TOP1"))) {
                battingTeam.battingPositions.nextBatter();
            } else {
                fieldingTeam.fieldingPositions.choosePitcher(activeInning);
                main.gameInterface.generateGameplayScreen();
            }

            currentScenario = generateScenario(fieldingTeam.fieldingPositions.choosePitcher(activeInning),
                    battingTeam.battingPositions.currentBatter);

        } else {
            currentScenario = generateScenario(fieldingTeam.fieldingPositions.choosePitcher(activeInning),
                    battingTeam.battingPositions.currentBatter);

            runScenario(currentScenario, false);
        }
    }

    public void runGame() {

        teamOne.score = 0;
        teamTwo.score = 0;
        for (int x = 0; x < 5; x++) {
            teamOne.roster.get(0).clearRecord();
            teamTwo.roster.get(0).clearRecord();
        }

        activeInning = innings.get(0);

        outs = 0;
        strikes = 0;
        balls = 0;
        field.clearAllBases();

        battingTeam = teamOne;
        fieldingTeam = teamTwo;

        fieldingTeam.fieldingPositions.choosePitcher(activeInning);
        main.gameInterface.generateGameplayScreen();
    }

    public Scenario generateScenario(Player pitcher, Player batter) {
        return new Scenario(this, field,
                pitcher,
                batter,
                fieldingTeam.fieldingPositions.rightFielder,
                fieldingTeam.fieldingPositions.leftFielder,
                fieldingTeam.fieldingPositions.centerFielder);
    }

    public void runScenario(Scenario currentAtBat, boolean tiebreakerGame) {

        currentAtBat.runPlay();

        if (outs >= (tiebreakerGame ? 1 : 3)) {
            return;
        }

        if (strikes > 3) {
            fieldingTeam.fieldingPositions.currentPitcher.peopleStruckOut++; // Stat
            currentAtBat.batter.timesStruckOut++;
            announcer.strikeoutCall(currentAtBat.batter.name, currentAtBat.pitcher.name);
            if (!tiebreakerGame) {
                battingTeam.battingPositions.nextBatter();
            }
            outs++;
        } else if (balls > 3) {
            fieldingTeam.fieldingPositions.currentPitcher.peopleWalked++; // Stat
            currentAtBat.batter.timesWalked++;
            announcer.walkCall(currentAtBat.batter.name, currentAtBat.pitcher.name);
            battingTeam.score += field.calculateRuns(currentAtBat.batter);
            if (!tiebreakerGame) {
                battingTeam.battingPositions.nextBatter();
            }
        }
    }

    public void findWinner() {

        if (!(teamOne.score == teamTwo.score)) {

            main.printFinalStats(this);

        } else {

            field.clearAllBases();
            announcer.tieBreakerAnnouncment();
            switch (gameType) {

                case PLAYOFF -> {
                    ArrayList<Player> teamOneBatters = new ArrayList<>(teamOne.roster);

                    Collections.sort(teamOneBatters, new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            return Integer.compare(o2.spd + o2.awr + o2.con + o2.pwr,
                                    o1.spd + o1.awr + o1.con + o1.pwr);
                        }
                    });
                    teamOneBatters.remove(3);
                    teamOneBatters.remove(3);

                    ArrayList<Player> teamTwoBatters = new ArrayList<>(teamTwo.roster);

                    Collections.sort(teamTwoBatters, new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            return Integer.compare(o2.spd + o2.awr + o2.con + o2.pwr,
                                    o1.spd + o1.awr + o1.con + o1.pwr);
                        }
                    });
                    teamTwoBatters.remove(3);
                    teamTwoBatters.remove(3);

                    teamOneTieBreakerPoints = 0;

                    battingTeam = teamOne;
                    fieldingTeam = teamTwo;

                    announcer.tieBreakerFirstUpAnnouncment(teamOne.fullName);

                    for (Player batter : teamOneBatters) {

                        batter.totalAtBats++;
                        outs = 0;
                        field.clearAllBases();

                        while (outs < 1) {
                            currentScenario = generateScenario(fieldingTeam.fieldingPositions.pitcherOne, batter);
                            runScenario(currentScenario, true);
                            if (!field.firstBaseRunner.equals(field.emptyPlayer)) {
                                outs++;
                                teamOneTieBreakerPoints++;
                            }
                        }
                    }

                    announcer.tieBreakerPointsAnnouncment(teamOne.fullName, teamOneTieBreakerPoints);

                    announcer.tieBreakerNextUpAnnouncment(teamOne.fullName);

                    teamTwoTieBreakerPoints = 0;

                    battingTeam = teamTwo;
                    fieldingTeam = teamOne;

                    for (Player batter : teamTwoBatters) {

                        batter.totalAtBats++;
                        outs = 0;
                        field.clearAllBases();

                        while (outs < 1) {
                            currentScenario = generateScenario(fieldingTeam.fieldingPositions.pitcherOne, batter);
                            runScenario(currentScenario, true);
                            if (!field.firstBaseRunner.equals(field.emptyPlayer)) {
                                outs++;
                                teamTwoTieBreakerPoints++;
                            }
                        }
                    }

                    announcer.tieBreakerPointsAnnouncment(teamTwo.fullName, teamTwoTieBreakerPoints);

                    if (teamOneTieBreakerPoints > teamTwoTieBreakerPoints) {
                        System.out
                                .println("After the tiebreaker rounds, the " + teamOne.fullName + " come out on top!");
                        main.printFinalStats(this);
                    } else if (teamOneTieBreakerPoints < teamTwoTieBreakerPoints) {
                        System.out
                                .println("After the tiebreaker rounds, the " + teamTwo.fullName + " come out on top!");
                        main.printFinalStats(this);
                    } else {
                        findWinner();
                    }
                }

                case REGULAR -> {

                }

                default -> {

                }
            }
        }
    }
}
