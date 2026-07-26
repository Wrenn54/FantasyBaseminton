
import gui.UserInterfaceBase;
import static java.lang.Integer.parseInt;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public ArrayList<Team> allTeams = new ArrayList<>();
    public ArrayList<Player> allPlayers = new ArrayList<>();
    private UserInterfaceBase userInterfaceBase = new UserInterfaceBase();

    Scanner scanner = new Scanner(System.in);

    private Game testGame;

    public static void main(String[] args) {
        Main main = new Main();
        main.initializeTeams();
        main.initializePlayers();

        for(int x=0; x<10; x++){
            System.out.print(x+1 + " for " + main.allTeams.get(x).fullName + "\n");
        }
        
        System.out.println("\nPlease input your choice for the first team:");
        int teamChoiceOne = parseInt(main.scanner.nextLine())-1;
        
        System.out.println("\nPlease input your choice for the second team:");
        int teamChoiceTwo = parseInt(main.scanner.nextLine())-1;

        Game testGame = new Game(main.allTeams.get(teamChoiceOne), main.allTeams.get(teamChoiceTwo));
        testGame.runGame();

        for(Team team: new ArrayList<Team>(List.of(testGame.teamOne, testGame.teamTwo))){
            System.out.println("\n" + team.fullName + ": " + team.score + "\n");
            for (Player player : team.roster){
                for(int x=0; x<24-player.name.length(); x++){
                    System.out.print(" ");
                }
                System.out.println(player.name
                 + " || Batting average: " + new DecimalFormat("#.##").format((double)player.totalHits / player.totalAtBats)
                  + " || RBI: " + player.runAssists
                   + " || Walks as batter: " + player.timesWalked
                    + " || Strikeouts as batter: " + player.timesStruckOut
                     + (player.playedAsPitcher ? " || ERA: " + (player.runsAllowed * 2) + " || Strikeouts as pitcher: " + player.peopleStruckOut + " || Walks as pitcher: " + player.peopleWalked : " || Catches: " + player.catches + " || Tagouts: " + player.tagouts));
            }
        }
    }

    public void initializeTeams() {
        Team aurora = new Team("Aurora Ave \'Alanche");
        Team bainbridge = new Team("Bainbridge Bats");
        Team ballard = new Team("Ballard Mallards");
        Team fremont = new Team("Fremont Frankfurters");
        Team mapleLeaf = new Team("Maple Leaf Monsters");
        Team ravenna = new Team("Ravenna Reapers");
        Team redmond = new Team ("Redmond Rattlesnakes");
        Team sodo = new Team("SoDo Dodos");
        Team wallaWalla = new Team ("Walla Walla Werewolves");
        Team wallingford = new Team("Wallingford Whippersnappers");

        allTeams.addAll(List.of(aurora, bainbridge, ballard, fremont, mapleLeaf, ravenna, redmond, sodo, wallaWalla, wallingford));
    }

    public void initializePlayers() {
        Player miles = createPlayer("Miles Befano", "Miles \'Beefcake\' Befano", allTeams.get(0));
            miles.setStats(84, 77, 74, 79, 75, 88, 94);
        Player carter = createPlayer("Carter Mackey", "Carter \'The Slip-Up\' Mackey", allTeams.get(0));
            carter.setStats(77, 60, 89, 77, 63, 89, 83);
        Player levi = createPlayer("Levi Smith", "Levi \'Silent Butt Deadly 😏\' Smith", allTeams.get(0));
            levi.setStats(86, 90, 78, 99, 99, 87, 83);
        Player calder = createPlayer("Calder Castro", "Calder \'Bling Bling boy\' Castro", allTeams.get(0));
            calder.setStats(63, 97, 75, 85, 92, 70, 76);
        Player max = createPlayer("Max Denoma", "Max \'The Huzz\' DeNoma", allTeams.get(0));
            max.setStats(85, 68, 82, 65, 78, 80, 65);

        Player alex = createPlayer("Alex Mar", "Alex \'LSCF\' Mar", allTeams.get(1));
            alex.setStats(99, 80, 95, 97, 97, 99, 90);
        Player deklan = createPlayer("Deklan Hoover", "Deklan \'The 'Taining Wall\' Hoover", allTeams.get(1));
            deklan.setStats(72, 84, 88, 82, 85, 90, 97);
        Player konstantinos = createPlayer("Konstantinos Manolakakis", "Konstantinos \'The Walker\' Manolakakis", allTeams.get(1));
            konstantinos.setStats(75, 79, 73, 88, 73, 83, 99);
        Player oscar = createPlayer("Oscar Edobohls", "Oscar \'High Flyer\' Edobohls", allTeams.get(1));
            oscar.setStats(85, 75, 90, 82, 90, 70, 60);
        Player kiran = createPlayer("Kiran Thakkar", "Kiran \'Pie Ran\' Thakkar", allTeams.get(1));
            kiran.setStats(87, 73, 80, 82, 76, 81, 63);

        Player bram = createPlayer("Bram Hayes", "Bram \'Racketman\' Hayes", allTeams.get(2));
            bram.setStats(82, 97, 87, 99, 89, 97, 92);
        Player hiram = createPlayer("Hiram Helliwell", "", allTeams.get(2));
            hiram.setStats(78, 55, 79, 82, 69, 98, 75);
        Player andrei = createPlayer("Andrei Mattice", "Andrei \'The Gooner\' Mattice", allTeams.get(2));
            andrei.setStats(98, 83, 74, 86, 80, 87, 66);
        Player phinney = createPlayer("Phinney Hanson-Tyler", "Phinney \'Dave Grohl\' Hanson-Tyler", allTeams.get(2));
            phinney.setStats(86, 89, 88, 75, 86, 73, 70);
        Player abe = createPlayer("Abe Liermann", "Abe \'Can't Lose If I'm Not There\' Liermann", allTeams.get(2));
            abe.setStats(60, 57, 84, 86, 74, 87, 83);

        Player gui = createPlayer("Gui Mori De Castro", "Gui \'Outside The Box\' Mori de Castro", allTeams.get(3));
            gui.setStats(80, 96, 85, 90, 93, 83, 83);
        Player hank = createPlayer("Hank Warburton", "Hank \'The Virgin Animal\' Warburton", allTeams.get(3));
            hank.setStats(95, 99, 76, 99, 82, 78, 70);
        Player haikal = createPlayer("Haikal Omer", "", allTeams.get(3));
            haikal.setStats(93, 90, 70, 72, 75, 66, 60);
        Player braedon = createPlayer("Braedon B-W", "Braedon \'Spiders Man\' Brasek Wilcox", allTeams.get(3));
            braedon.setStats(83, 72, 79, 60, 87, 65, 72);
        Player joseph = createPlayer("Joseph powers", "", allTeams.get(3));
            joseph.setStats(83, 70, 58, 67, 76, 53, 95);

        Player robert = createPlayer("Robert Sun", "Roger \'Robert\' Sun", allTeams.get(4));
            robert.setStats(76, 78, 77, 66, 71, 63, 61);
        Player greg = createPlayer("Greg Williams", "Greg \'The Peg\' Williams", allTeams.get(4));
            greg.setStats(95, 78, 94, 80, 92, 97, 86);
        Player duncanA = createPlayer("Duncan Arcuri", "Duncan \'Posiedon's [sic] Tide\' Arcuri", allTeams.get(4));
            duncanA.setStats(90, 83, 75, 87, 75, 53, 74);
        Player alton = createPlayer("Alton Chun", "Alton \'The Swimmer\' Chun", allTeams.get(4));
            alton.setStats(90, 69, 78, 71, 75, 52, 54);
        Player damon = createPlayer("Damon Waldo", "Damon \'Where's\' Waldo", allTeams.get(4));
            damon.setStats(77, 92, 67, 60, 74, 59, 57);

        Player dorian = createPlayer("Dorian Roser", "Dorian \'The Amazing Bulk\' Roser", allTeams.get(5));
            dorian.setStats(78, 75, 73, 80, 79, 74, 84);
        Player kingston = createPlayer("Kingston Delola", "", allTeams.get(5));
            kingston.setStats(80, 84, 78, 64, 82, 70, 66);
        Player george = createPlayer("George Schneider", "", allTeams.get(5));
            george.setStats(70, 68, 73, 65, 61, 65, 80);
        Player johnny = createPlayer("Johnny Lauer", "", allTeams.get(5));
            johnny.setStats(69, 50, 75, 50, 85, 62, 85);
        Player vaughn = createPlayer("Vaughn Wilson", "Vaughn \'The Log\' Wilson", allTeams.get(5));
            vaughn.setStats(66, 50, 73, 70, 68, 60, 55);

        Player benS = createPlayer("Ben Silvers", "", allTeams.get(6));
            benS.setStats(67, 65, 66, 84, 57, 83, 72);
        Player nick = createPlayer("Nick Wittmann", "Nick \'The Wildcat\' Wittmann", allTeams.get(6));
            nick.setStats(87, 75, 94, 98, 94, 95, 94);
        Player toby = createPlayer("Toby Hwang", "Toby \'Maguire\' Hwang", allTeams.get(6));
            toby.setStats(94, 79, 80, 90, 86, 64, 58);
        Player duncanM = createPlayer("Duncan Maclean", "Duncan \'The Stroker\' Maclean", allTeams.get(6));
            duncanM.setStats(85, 70, 79, 87, 84, 56, 63);
        Player tommy = createPlayer("Tommy Daniels", "Tommy \'Dommy Mommy\' Daniels", allTeams.get(6));
            tommy.setStats(88, 77, 74, 70, 62, 69, 57);

        Player james = createPlayer("James Pfleiger", "James \'High On Quack\' Pfleiger", allTeams.get(7));
            james.setStats(93, 92, 72, 85, 93, 68, 52);
        Player zane = createPlayer("Zane Parker", "Zane \'Vanilla Ice\' Parker", allTeams.get(7));
            zane.setStats(85, 78, 86, 77, 78, 95, 92);
        Player henry = createPlayer("Henry Simon", "Henry \'Henry Arthur Simon\' Simon", allTeams.get(7));
            henry.setStats(90, 97, 96, 80, 81, 62, 73);
        Player evan = createPlayer("Evan Arora", "", allTeams.get(7));
            evan.setStats(94, 92, 84, 79, 86, 61, 69);
        Player paul = createPlayer("Paul Knutson", "Michael \'Paul Knutson\' Cera", allTeams.get(7));
            paul.setStats(97, 74, 80, 70, 76, 68, 79);

        Player ewen = createPlayer("Ewen Tomson", "", allTeams.get(8));
            ewen.setStats(69, 67, 95, 62, 61, 73, 77);
        Player emery = createPlayer("Emery Santos", "", allTeams.get(8));
            emery.setStats(87, 83, 91, 53, 89, 80, 58);
        Player oliver = createPlayer("Oliver Keum", "", allTeams.get(8));
            oliver.setStats(64, 85, 70, 88, 74, 70, 74);
        Player leo = createPlayer("Leo Johnson", "Leo \'The Sexy Lizard\' Johnson", allTeams.get(8));
            leo.setStats(93, 83, 85, 63, 69, 67, 58);
        Player vance = createPlayer("Vance Vogt", "Vance \'The Impaler\' Vogt", allTeams.get(8));
            vance.setStats(97, 71, 69, 68, 69, 73, 52);

        Player benB = createPlayer("Ben Brown", "Benjamin \'Biceps\' Brown", allTeams.get(9));
            benB.setStats(82, 84, 80, 79, 84, 74, 79);
        Player josh = createPlayer("Josh Holt", "Joshua \'The Ragebai-SWINGBATTABATTA SWINGBATTABATTA\' Holt", allTeams.get(9));
            josh.setStats(82, 90, 89, 81, 86, 78, 72);
        Player mac = createPlayer("Mac Pingree", "Mac \'Beefcake\' Pingree", allTeams.get(9));
            mac.setStats(82, 78, 84, 92, 97, 71, 65);
        Player vishad = createPlayer("Vishad Lamba", "Vishad \'A Toe Above The Rest\' Lamba", allTeams.get(9));
            vishad.setStats(73, 82, 95, 78, 79, 69, 80);
        Player seamus = createPlayer("Seamus Johnson", "Seamus \'Playing Hard\' Johnson", allTeams.get(9));
            seamus.setStats(90, 79, 85, 81, 98, 60, 63);

        allPlayers.addAll(List.of(levi, miles, calder, carter, max, alex, deklan, konstantinos, oscar, kiran, bram, andrei, phinney, hiram, abe, gui, hank, haikal, braedon, joseph, greg, duncanA, robert, alton, damon, dorian, kingston, george, johnny, vaughn, nick, toby, duncanM, benS, tommy, zane, henry, evan, james, paul, emery, oliver, leo, ewen, vance, josh, mac, benB, vishad,seamus));
    }

    public Player createPlayer(String name, String nickname, Team team) {
        Player player = new Player(name, nickname, team);
        return player;
    }
}
