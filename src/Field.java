import enums.Third;

public class Field {
    private Game game;

    public Player firstBaseRunner;
    public Player secondBaseRunner;
    public Player thirdBaseRunner;

    public Player emptyPlayer = new Player ();

    public Field(Game game){
        this.game = game;
        resetBases();
    }

    public void resetBases(){
        firstBaseRunner = emptyPlayer;
        secondBaseRunner = emptyPlayer;
        thirdBaseRunner = emptyPlayer;
    }

    public Player findPlayerOnBase(Third third){
        return third == Third.LEFT ? firstBaseRunner:
                        third == Third.MIDDLE ? secondBaseRunner:
                        thirdBaseRunner;
    }

    public Player findFielderInThird(Third third){
        return third == Third.LEFT ? game.fieldingTeam.fieldingPositions.leftFielder:
                        third == Third.MIDDLE ? game.fieldingTeam.fieldingPositions.centerFielder:
                        game.fieldingTeam.fieldingPositions.rightFielder;
    }

    public void removePlayerOnBase(Third third){
        switch (third){
            case LEFT:
                thirdBaseRunner = emptyPlayer;
            case MIDDLE:
                secondBaseRunner = emptyPlayer;
            case RIGHT:
                firstBaseRunner = emptyPlayer;
        }
    }

    public void clearAllBases(){
        firstBaseRunner = emptyPlayer;
        secondBaseRunner = emptyPlayer;
        thirdBaseRunner = emptyPlayer;
    }

    public int calculateRuns(Player newRunner){
        boolean run = !thirdBaseRunner.equals(emptyPlayer);

        if (run){
            System.out.println("\n" + thirdBaseRunner.name + " just scored.");
            newRunner.runAssists++;
            game.fieldingTeam.fieldingPositions.currentPitcher.runsAllowed++;
            game.battingTeam.score++;
        }

        thirdBaseRunner = secondBaseRunner;
        secondBaseRunner = firstBaseRunner;
        firstBaseRunner = newRunner;

        System.out.println("\nCurrent people on base are\n1st: " + firstBaseRunner.name + "\n2nd: " + secondBaseRunner.name + "\n3rd: " + thirdBaseRunner.name+ "\n");
        
        return run ? 1 : 0;
    }
}
