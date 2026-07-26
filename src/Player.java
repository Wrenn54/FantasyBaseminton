
public class Player {

    public String name;
    public String nickname;
    public Team team;

    public int peopleStruckOut;
    public int peopleWalked;

    public int timesStruckOut;
    public int timesWalked;

    //Batting average
    public int totalHits;
    public int totalAtBats;

    public int totalHitsCaught;

    public int catches;

    public int tagouts;

    //Pitcher stat (ERA)
    public int runsAllowed;

    public int ballsThrown;
    public int strikesThrown;

    //RBI
    public int runAssists;

    public boolean playedAsPitcher;

    public int pwr = 0;
    public int con = 0;
    public int spd = 0;
    public int awr = 0;
    public int fld = 0;
    public int vel = 0;
    public int acc = 0;
    public int avg = 0;

    public int[] record = new int[0];

    public Player(String name, String nickname, Team team) {
        this.name = name;
        this.team = team;
        this.nickname = nickname;
        team.roster.add(this);
    }

    public Player() {
        this.name = "EMPTY";
        this.team = null;
        this.nickname = "";
    }

    public void setStats(int pwr, int con, int spd, int awr, int fld, int vel, int acc) {
        this.pwr=pwr;
        this.con=con;
        this.spd=spd;
        this.awr=awr;
        this.fld=fld;
        this.vel=vel;
        this.acc=acc;
        calculateAverage();
    }

    public void setPWR(int pwr){
        this.pwr = pwr;
        calculateAverage();
    }

    public void setCON(int con){
        this.con = con;
        calculateAverage();
    }

    public void setSPD(int spd){
        this.spd = spd;
        calculateAverage();
    }

    public void setAWR(int awr){
        this.awr = awr;
        calculateAverage();
    }

    public void setFLD(int fld){
        this.fld = fld;
        calculateAverage();
    }

    public void setVEL(int vel){
        this.vel = vel;
        calculateAverage();
    }

    public void calculateAverage(){
        this.avg = (int)Math.round((pwr + con + spd + awr + fld + vel + acc)/7.0);
    }
}