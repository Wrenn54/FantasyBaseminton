import enums.HitType;
import enums.Third;

public class Scenario {
    
    public Game game;
    public Field field;
    public Player pitcher;
    public Player batter;
    public Player rightFielder;
    public Player centerFielder;
    public Player leftFielder;

    public Scenario(Game game, Field field, Player pitcher, Player batter, Player rightFielder, Player centerFielder, Player leftFielder) {
        this.game = game;
        this.field = field;
        this.pitcher = pitcher;
        this.batter = batter;
        this.rightFielder = rightFielder;
        this.centerFielder = centerFielder;
        this.leftFielder = leftFielder;
    }

    public boolean calculateHit(){
        int goal = pitcher.vel - batter.con + 60;
        if (goal < 10) {
            goal = 10;
        } else if (goal > 90) {
            goal = 90;
        }
        return (int)(Math.random()*100)+1 >= goal;
    }

    public boolean calculateStrike() {
        int goal = 20 + (100-pitcher.acc);
        return (int)(Math.random()*100)+1 > goal;
    }

    public Third calculateThird(){
        int roll = (int)(Math.random()*100)+1;
        return roll <= 33 ? Third.LEFT : (roll <= 78 ? Third.MIDDLE : Third.RIGHT);
    }

    public HitType calculateHitType(){
        int goal = batter.awr-20;
        return (int)(Math.random()*100)+1 < goal ? HitType.LINEDRIVE : HitType.FLOATER;
    }

    public boolean calculateCatch(HitType hitType, Third third) {
        int roll = (int)(Math.random()*100)+1;
        switch (hitType){

            case LINEDRIVE -> {
                switch (third) {
                    case LEFT -> {
                        if (leftFielder.spd<batter.pwr) {
                            return false;
                        } else {
                            return (leftFielder.fld - 30) > roll;
                        }
                    }
                    case MIDDLE -> {
                        if (centerFielder.spd<batter.pwr) {
                            return false;
                        } else {
                            return (centerFielder.fld - 30) > roll;
                        }
                    }
                    case RIGHT -> {
                        if (rightFielder.spd<batter.pwr) {
                            return false;
                        } else {
                            return (rightFielder.fld - 30) > roll;
                        }
                    }
                    default -> {
                        return false;
                    }
                }
            }

            case FLOATER -> {
                return third == Third.LEFT ? leftFielder.fld > roll :
                        third == Third.MIDDLE ? centerFielder.fld > roll :
                        rightFielder.fld > roll;
            }
                        
            default -> {
                return false;
            }
        }
    }
    
    public void calculateTagOut() {

        Third third = calculateThird();
        boolean caught = calculateCatch(calculateHitType(), third);

        Player fielder = third == Third.LEFT ? leftFielder:
            third == Third.MIDDLE ? centerFielder:
            rightFielder;
        Player runner = field.findPlayerOnBase(third);

        if (caught) {
            fielder.catches++;
            batter.totalHitsCaught++;
            System.out.println("Birdie caught\n");
            game.outs++;

            if (game.outs == 3){
                return;
            }

            if ((int)(Math.random()*100) + 1 < fielder.awr - runner.awr + 50){ //Tag check
                if (!game.field.findPlayerOnBase(third).equals(game.field.emptyPlayer)){
                    System.out.println(game.field.findPlayerOnBase(third).name + " got tagged out!\n");
                    game.outs++;
                    fielder.tagouts++;
                    game.field.removePlayerOnBase(third);
                }
            }
        } else {
            if (third == Third.RIGHT ? (int)(Math.random()*100) + 1 < 30 + fielder.spd - batter.spd : (int)(Math.random()*100) + 1 < 10 + fielder.fld - batter.spd){ 
                game.field.calculateRuns(batter);
                game.field.removePlayerOnBase(Third.RIGHT);
                fielder.tagouts++;
                game.outs++;
                System.out.println(batter.name + " gets tagged out while running to 1st base!\n");
            } else { //No tagout on the way to first
                game.field.calculateRuns(batter);
            }
        }
    }

    public void runPlay(){
        if(calculateHit()){ //Yes hit
            System.out.println("Birdie is hit by " + batter.name);
            batter.totalHits++;
            calculateTagOut();
            if (game.outs == 3){
                return;
            }
            game.battingTeam.battingPositions.nextBatter();
        } else { //No hit
            if(calculateStrike()){ //Yes strike
                game.strikes++;
                pitcher.strikesThrown++;
            } else { //No strike
                game.balls++;
                pitcher.ballsThrown++;
            }
        }
    }
}