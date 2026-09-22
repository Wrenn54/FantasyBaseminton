
import enums.GameType;
import enums.Third;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class InterfaceBase implements ActionListener{

    private final Main main;

    private Game game;

    private final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private final int screenWidth = gd.getDisplayMode().getWidth();
    public final int screenHeight = gd.getDisplayMode().getHeight();

    private final int screenCenterX = (int)(screenWidth/2);
    private final int screenCenterY = (int)(screenHeight/2);

    private int teamOneIndex = 0;
    private int teamTwoIndex = 1;

    private final JFrame gameCreationScreen = new JFrame();
    public final JFrame gameplayScreen = new JFrame();

    private final GameButton playoffChoiceButton = new GameButton();
    private final GameButton regularSeasonChoiceButton = new GameButton();

    private final GameLabel teamOneName = new GameLabel();
    private final GameLabel teamTwoName = new GameLabel();

    private GameLabel pitchersMoundName;
    private GameLabel batterName;
    private GameLabel firstBaseName;
    private GameLabel rightFielderName;
    private GameLabel secondBaseName;
    private GameLabel centerFielderName;
    private GameLabel thirdBaseName;
    private GameLabel leftFielderName;

    private GameLabel teamOneStatsName;
    private GameLabel teamOneStatsPosition;
    private GameLabel teamOneStatsScore;
    private GameLabel teamTwoStatsName;
    private GameLabel teamTwoStatsPosition;
    private GameLabel teamTwoStatsScore;
    private GameLabel inning;
    private GameLabel outs;
    private GameLabel strikes;
    private GameLabel balls;

    public ArrayList<GameLabel> playReadoutLabels = new ArrayList<>();

    private final GameButton rightArrowOne = new GameButton();
    private final GameButton leftArrowOne = new GameButton();
    private final GameButton rightArrowTwo = new GameButton();
    private final GameButton leftArrowTwo = new GameButton();

    private final GameButton nextPlayButton = new GameButton();
    private final GameButton nextInningButton = new GameButton();


    public InterfaceBase(Main main){
        this.main = main;
        generateGameCreationScreen();
    }

    private void generateGameCreationScreen(){

        gameCreationScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameCreationScreen.setSize(new Dimension(screenWidth, screenHeight));
        gameCreationScreen.setLayout(null);
        gameCreationScreen.setResizable(false);

        GameLabel title = new GameLabel();
        title.setText("TEAM SELECTION");
        title.setFont(new Font("Monospaced", Font.BOLD, 175));
        title.setDimension();
        title.setLocationCenteredOn(screenCenterX, 75);

        GameLabel teamOne = new GameLabel();
        teamOne.setText("TEAM ONE:");
        teamOne.setFont(new Font("Monospaced", Font.BOLD, 50));
        teamOne.setDimension();
        teamOne.setLocationCenteredOn((int)(screenCenterX*0.5), screenCenterY-300);

        teamOneName.setText(main.allTeams.get(teamOneIndex).fullName);
        teamOneName.setFont(new Font("Monospaced", Font.PLAIN, 45));
        teamOneName.setSize(new Dimension(750, 75));
        teamOneName.setLocationCenteredOn((int)(screenCenterX*0.5), screenCenterY-250);

        GameLabel teamTwo = new GameLabel();
        teamTwo.setText("TEAM TWO:");
        teamTwo.setFont(new Font("Monospaced", Font.BOLD, 50));
        teamTwo.setDimension();
        teamTwo.setLocationCenteredOn((int)(screenCenterX*1.5), screenCenterY-300);

        teamTwoName.setText(main.allTeams.get(teamTwoIndex).fullName);
        teamTwoName.setFont(new Font("Monospaced", Font.PLAIN, 45));
        teamTwoName.setSize(new Dimension(750, 75));
        teamTwoName.setLocationCenteredOn((int)(screenCenterX*1.5), screenCenterY-250);

        rightArrowOne.addActionListener(this);
        rightArrowOne.setText("\u02C3");
        rightArrowOne.setFont(new Font("Monospaced", Font.PLAIN, 50));
        rightArrowOne.setDimension();
        rightArrowOne.setLocationCenteredOn((int)(screenCenterX*0.5)+300, screenCenterY-50);

        leftArrowOne.addActionListener(this);
        leftArrowOne.setText("\u02C2");
        leftArrowOne.setFont(new Font("Monospaced", Font.PLAIN, 50));
        leftArrowOne.setDimension();
        leftArrowOne.setLocationCenteredOn((int)(screenCenterX*0.5)-300, screenCenterY-50);

        rightArrowTwo.addActionListener(this);
        rightArrowTwo.setText("\u02C3");
        rightArrowTwo.setFont(new Font("Monospaced", Font.PLAIN, 50));
        rightArrowTwo.setDimension();
        rightArrowTwo.setLocationCenteredOn((int)(screenCenterX*1.5)+300, screenCenterY-50);

        leftArrowTwo.addActionListener(this);
        leftArrowTwo.setText("\u02C2");
        leftArrowTwo.setFont(new Font("Monospaced", Font.PLAIN, 50));
        leftArrowTwo.setDimension();
        leftArrowTwo.setLocationCenteredOn((int)(screenCenterX*1.5)-300, screenCenterY-50);

        playoffChoiceButton.addActionListener(this);
        playoffChoiceButton.setText("Playoff game");
        playoffChoiceButton.setFont(new Font("Monospaced", Font.PLAIN, 50));
        playoffChoiceButton.setSize(new Dimension(500,(int)(playoffChoiceButton.getFont().getSize()*1.5)));
        playoffChoiceButton.setLocationCenteredOn((int)(screenCenterX*0.5), screenCenterY+225);

        regularSeasonChoiceButton.addActionListener(this);
        regularSeasonChoiceButton.setText("Regular Season");
        regularSeasonChoiceButton.setFont(new Font("Monospaced", Font.PLAIN, 50));
        regularSeasonChoiceButton.setSize(new Dimension(500,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        regularSeasonChoiceButton.setLocationCenteredOn((int)(screenCenterX*1.5), screenCenterY+225);

        gameCreationScreen.add(title);

        gameCreationScreen.add(teamOne);
        gameCreationScreen.add(teamOneName);
        gameCreationScreen.add(rightArrowOne);
        gameCreationScreen.add(leftArrowOne);

        gameCreationScreen.add(teamTwo);
        gameCreationScreen.add(teamTwoName);
        gameCreationScreen.add(rightArrowTwo);
        gameCreationScreen.add(leftArrowTwo);

        gameCreationScreen.add(playoffChoiceButton);
        gameCreationScreen.add(regularSeasonChoiceButton);

        gameCreationScreen.setVisible(true);
    }

    public void generateGameplayScreen(){

        gameCreationScreen.setVisible(false);

        gameplayScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameplayScreen.setSize(new Dimension(screenWidth, screenHeight));
        gameplayScreen.setLayout(null);
        gameplayScreen.setResizable(false);

        JLabel basesBorder = new JLabel();
        basesBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        basesBorder.setSize((int)(screenWidth * 0.6), (int)(screenHeight * 0.6));
        basesBorder.setLocation((int)(screenWidth * 0.5), -10);

        GameLabel pitchersMound = new GameLabel();
        pitchersMound.setText("Pitcher:");
        pitchersMound.setFont(new Font("Monospaced", Font.BOLD, 25));
        pitchersMound.setDimension();
        pitchersMound.setLocationCenteredOn((int)(screenCenterX*1.5), (int)(screenCenterY*0.5)+75);

        pitchersMoundName = new GameLabel();
        pitchersMoundName.setText(game.fieldingTeam.fieldingPositions.currentPitcher.name.replaceAll(" Manolakakis", ""));
        pitchersMoundName.setFont(new Font("Monospaced", Font.BOLD, 25));
        pitchersMoundName.setSize(750, 25);
        pitchersMoundName.setLocationCenteredOn((int)(screenCenterX*1.5), (int)(screenCenterY*0.5)+125);

        GameLabel batter = new GameLabel();
        batter.setText("Batter:");
        batter.setFont(new Font("Monospaced", Font.BOLD, 25));
        batter.setDimension();
        batter.setLocationCenteredOn((int)(screenCenterX*1.5), (int)(screenCenterY*0.75)+75);

        batterName = new GameLabel();
        batterName.setText(game.battingTeam.battingPositions.currentBatter.name.replaceAll(" Manolakakis", ""));
        batterName.setFont(new Font("Monospaced", Font.BOLD, 25));
        batterName.setSize(750, 25);
        batterName.setLocationCenteredOn((int)(screenCenterX*1.5), (int)(screenCenterY*0.75)+125);

        GameLabel firstBase = new GameLabel();
        firstBase.setText("First Base:");
        firstBase.setFont(new Font("Monospaced", Font.BOLD, 25));
        firstBase.setDimension();
        firstBase.setLocationCenteredOn((int)(screenCenterX*1.75), (int)(screenCenterY*0.5)+75);

        firstBaseName = new GameLabel();
        firstBaseName.setText(game.field.findPlayerOnBase(Third.LEFT).name.replaceAll(" Manolakakis", ""));
        firstBaseName.setFont(new Font("Monospaced", Font.BOLD, 25));
        firstBaseName.setSize(750, 25);
        firstBaseName.setLocationCenteredOn((int)(screenCenterX*1.75), (int)(screenCenterY*0.5)+125);

        GameLabel rightFielder = new GameLabel();
        rightFielder.setText("Right field:");
        rightFielder.setFont(new Font("Monospaced", Font.PLAIN, 25));
        rightFielder.setDimension();
        rightFielder.setLocationCenteredOn((int)(screenCenterX*1.75)+75, (int)(screenCenterY*0.5)-75);

        rightFielderName = new GameLabel();
        rightFielderName.setText(game.fieldingTeam.fieldingPositions.rightFielder.name.replaceAll(" Manolakakis", ""));
        rightFielderName.setFont(new Font("Monospaced", Font.PLAIN, 25));
        rightFielderName.setSize(750, 25);
        rightFielderName.setLocationCenteredOn((int)(screenCenterX*1.75)+75, (int)(screenCenterY*0.5)-25);

        GameLabel secondBase = new GameLabel();
        secondBase.setText("Second Base:");
        secondBase.setFont(new Font("Monospaced", Font.BOLD, 25));
        secondBase.setDimension();
        secondBase.setLocationCenteredOn((int)(screenCenterX*1.5), (int)(screenCenterY*0.25)+75);

        secondBaseName = new GameLabel();
        secondBaseName.setText(game.field.findPlayerOnBase(Third.MIDDLE).name.replaceAll(" Manolakakis", ""));
        secondBaseName.setFont(new Font("Monospaced", Font.BOLD, 25));
        secondBaseName.setSize(750, 25);
        secondBaseName.setLocationCenteredOn((int)(screenCenterX*1.5), (int)(screenCenterY*0.25)+125);

        GameLabel centerFielder = new GameLabel();
        centerFielder.setText("Right field:");
        centerFielder.setFont(new Font("Monospaced", Font.PLAIN, 25));
        centerFielder.setDimension();
        centerFielder.setLocationCenteredOn((int)(screenCenterX*1.5), (int)(screenCenterY*0.25)-75);

        centerFielderName = new GameLabel();
        centerFielderName.setText(game.fieldingTeam.fieldingPositions.centerFielder.name.replaceAll(" Manolakakis", ""));
        centerFielderName.setFont(new Font("Monospaced", Font.PLAIN, 25));
        centerFielderName.setSize(750, 25);
        centerFielderName.setLocationCenteredOn((int)(screenCenterX*1.5), (int)(screenCenterY*0.25)-25);

        GameLabel thirdBase = new GameLabel();
        thirdBase.setText("Third Base:");
        thirdBase.setFont(new Font("Monospaced", Font.BOLD, 25));
        thirdBase.setDimension();
        thirdBase.setLocationCenteredOn((int)(screenCenterX*1.25), (int)(screenCenterY*0.5)+75);

        thirdBaseName = new GameLabel();
        thirdBaseName.setText(game.field.findPlayerOnBase(Third.RIGHT).name.replaceAll(" Manolakakis", ""));
        thirdBaseName.setFont(new Font("Monospaced", Font.BOLD, 25));
        thirdBaseName.setSize(750, 25);
        thirdBaseName.setLocationCenteredOn((int)(screenCenterX*1.25), (int)(screenCenterY*0.5)+125);

        GameLabel leftFielder = new GameLabel();
        leftFielder.setText("Left field:");
        leftFielder.setFont(new Font("Monospaced", Font.PLAIN, 25));
        leftFielder.setDimension();
        leftFielder.setLocationCenteredOn((int)(screenCenterX*1.25)-75, (int)(screenCenterY*0.5)-75);

        leftFielderName = new GameLabel();
        leftFielderName.setText(game.fieldingTeam.fieldingPositions.leftFielder.name.replaceAll(" Manolakakis", ""));
        leftFielderName.setFont(new Font("Monospaced", Font.PLAIN, 25));
        leftFielderName.setSize(750, 25);
        leftFielderName.setLocationCenteredOn((int)(screenCenterX*1.25)-75, (int)(screenCenterY*0.5)-25);

        nextPlayButton.addActionListener(this);
        nextPlayButton.setText("Next play");
        nextPlayButton.setFont(new Font("Monospaced", Font.PLAIN, 35));
        nextPlayButton.setSize(new Dimension(350,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        nextPlayButton.setLocationCenteredOn((int)(screenCenterX*0.5)-200, screenCenterY+400);

        nextInningButton.addActionListener(this);
        nextInningButton.setText("Next inning");
        nextInningButton.setFont(new Font("Monospaced", Font.PLAIN, 35));
        nextInningButton.setSize(new Dimension(350,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        nextInningButton.setLocationCenteredOn((int)(screenCenterX*0.5)+200, screenCenterY+400);

        teamOneStatsName = new GameLabel();
        teamOneStatsName.setText(game.teamOne.fullName);
        teamOneStatsName.setFont(new Font("Monospaced", Font.PLAIN, 25));
        teamOneStatsName.setSize(new Dimension(400,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        teamOneStatsName.setLocationCenteredOn((int)(screenCenterX*1.5)-200, screenCenterY+150);

        teamOneStatsPosition = new GameLabel();
        teamOneStatsPosition.setText(game.teamOne.equals(game.battingTeam) ? "Batting" : "Fielding");
        teamOneStatsPosition.setFont(new Font("Monospaced", Font.PLAIN, 35));
        teamOneStatsPosition.setSize(new Dimension(450,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        teamOneStatsPosition.setLocationCenteredOn((int)(screenCenterX*1.5)-200, screenCenterY+185);

        teamOneStatsScore = new GameLabel();
        teamOneStatsScore.setText("Points: " + game.teamOne.score);
        teamOneStatsScore.setFont(new Font("Monospaced", Font.PLAIN, 35));
        teamOneStatsScore.setSize(new Dimension(350,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        teamOneStatsScore.setLocationCenteredOn((int)(screenCenterX*1.5)-200, screenCenterY+220);

        JLabel teamOneStatsBorder = new JLabel();
        teamOneStatsBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        teamOneStatsBorder.setSize((int)(screenWidth * 0.25)+5, (int)(screenHeight * 0.2));
        teamOneStatsBorder.setLocation((int)(screenWidth * 0.5), (int)(screenHeight * 0.6)-20);

        teamTwoStatsName = new GameLabel();
        teamTwoStatsName.setText(game.teamTwo.fullName);
        teamTwoStatsName.setFont(new Font("Monospaced", Font.PLAIN, 30));
        teamTwoStatsName.setSize(new Dimension(400,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        teamTwoStatsName.setLocationCenteredOn((int)(screenCenterX*1.5)+200, screenCenterY+150);

        teamTwoStatsPosition = new GameLabel();
        teamTwoStatsPosition.setText(game.teamTwo.equals(game.battingTeam) ? "Batting" : "Fielding");
        teamTwoStatsPosition.setFont(new Font("Monospaced", Font.PLAIN, 35));
        teamTwoStatsPosition.setSize(new Dimension(350,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        teamTwoStatsPosition.setLocationCenteredOn((int)(screenCenterX*1.5)+200, screenCenterY+185);

        teamTwoStatsScore = new GameLabel();
        teamTwoStatsScore.setText("Points: " + game.teamTwo.score);
        teamTwoStatsScore.setFont(new Font("Monospaced", Font.PLAIN, 35));
        teamTwoStatsScore.setSize(new Dimension(350,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        teamTwoStatsScore.setLocationCenteredOn((int)(screenCenterX*1.5)+200, screenCenterY+220);

        JLabel teamTwoStatsBorder = new JLabel();
        teamTwoStatsBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        teamTwoStatsBorder.setSize((int)(screenWidth * 0.25)-5, (int)(screenHeight * 0.2));
        teamTwoStatsBorder.setLocation((int)(screenWidth * 0.75), (int)(screenHeight * 0.6)-20);

        inning = new GameLabel();
        inning.setText(game.activeInning.getName().substring(0,1).toUpperCase() + game.activeInning.getName().substring(1));
        inning.setFont(new Font("Monospaced", Font.PLAIN, 35));
        inning.setSize(new Dimension(750,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        inning.setLocationCenteredOn((int)(screenCenterX*1.5), screenCenterY+335);

        outs = new GameLabel();
        outs.setText("Outs: " + game.outs);
        outs.setFont(new Font("Monospaced", Font.PLAIN, 35));
        outs.setSize(new Dimension(350,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        outs.setLocationCenteredOn((int)(screenCenterX*1.5), screenCenterY+370);
        
        strikes = new GameLabel();
        strikes.setText("Strikes: " + game.strikes);
        strikes.setFont(new Font("Monospaced", Font.PLAIN, 35));
        strikes.setSize(new Dimension(350,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        strikes.setLocationCenteredOn((int)(screenCenterX*1.5), screenCenterY+405);

        balls = new GameLabel();
        balls.setText("Balls: " + game.balls);
        balls.setFont(new Font("Monospaced", Font.PLAIN, 35));
        balls.setSize(new Dimension(350,(int)(regularSeasonChoiceButton.getFont().getSize()*1.5)));
        balls.setLocationCenteredOn((int)(screenCenterX*1.5), screenCenterY+440);

        JLabel overallStatsBorder = new JLabel();
        overallStatsBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        overallStatsBorder.setSize((int)(screenWidth * 0.5), (int)(screenHeight * 0.3));
        overallStatsBorder.setLocation((int)(screenWidth * 0.5), (int)(screenHeight * 0.8)-25);

        System.out.println((int)((main.gameInterface.screenHeight-150) / 50));

        for(int x=0; x<=(int)((screenHeight) / 50) -5; x++){
            playReadoutLabels.add(new GameLabel());
            playReadoutLabels.get(x).setFont(new Font("Monospaced", Font.PLAIN, 25));
            playReadoutLabels.get(x).setSize((int)(screenWidth*0.5), 50);
            playReadoutLabels.get(x).setLocation(0, 50*x);
            playReadoutLabels.get(x).setText("");
            gameplayScreen.add(playReadoutLabels.get(x));
        }

        gameplayScreen.add(basesBorder);
        gameplayScreen.add(teamOneStatsBorder);
        gameplayScreen.add(teamTwoStatsBorder);
        gameplayScreen.add(overallStatsBorder);

        gameplayScreen.add(pitchersMound);
        gameplayScreen.add(pitchersMoundName);

        gameplayScreen.add(batter);
        gameplayScreen.add(batterName);

        gameplayScreen.add(firstBase);
        gameplayScreen.add(firstBaseName);
        gameplayScreen.add(rightFielder);
        gameplayScreen.add(rightFielderName);

        gameplayScreen.add(secondBase);
        gameplayScreen.add(secondBaseName);
        gameplayScreen.add(centerFielder);
        gameplayScreen.add(centerFielderName);

        gameplayScreen.add(thirdBase);
        gameplayScreen.add(thirdBaseName);
        gameplayScreen.add(leftFielder);
        gameplayScreen.add(leftFielderName);

        gameplayScreen.add(nextPlayButton);
        gameplayScreen.add(nextInningButton);

        gameplayScreen.add(teamOneStatsName);
        gameplayScreen.add(teamOneStatsPosition);
        gameplayScreen.add(teamOneStatsScore);
        gameplayScreen.add(teamTwoStatsName);
        gameplayScreen.add(teamTwoStatsPosition);
        gameplayScreen.add(teamTwoStatsScore);

        gameplayScreen.add(inning);
        gameplayScreen.add(outs);
        gameplayScreen.add(strikes);
        gameplayScreen.add(balls);

        gameplayScreen.setVisible(true);
    }

    public void refreshGameplayScreen(){
        pitchersMoundName.setText(game.fieldingTeam.fieldingPositions.currentPitcher.name.replaceAll(" Manolakakis", ""));
        batterName.setText(game.battingTeam.battingPositions.currentBatter.name.replaceAll(" Manolakakis", ""));
        firstBaseName.setText(game.field.findPlayerOnBase(Third.LEFT).name.replaceAll(" Manolakakis", ""));
        rightFielderName.setText(game.fieldingTeam.fieldingPositions.rightFielder.name.replaceAll(" Manolakakis", ""));
        secondBaseName.setText(game.field.findPlayerOnBase(Third.MIDDLE).name.replaceAll(" Manolakakis", ""));
        centerFielderName.setText(game.fieldingTeam.fieldingPositions.centerFielder.name.replaceAll(" Manolakakis", ""));
        thirdBaseName.setText(game.field.findPlayerOnBase(Third.RIGHT).name.replaceAll(" Manolakakis", ""));
        leftFielderName.setText(game.fieldingTeam.fieldingPositions.leftFielder.name.replaceAll(" Manolakakis", ""));
        
        teamOneStatsName.setText(game.teamOne.fullName);
        teamOneStatsPosition.setText(game.teamOne.equals(game.battingTeam) ? "Batting" : "Fielding");
        teamOneStatsScore.setText("Points: " + game.teamOne.score);
        
        inning.setText(game.activeInning.getName().substring(0,1).toUpperCase() + game.activeInning.getName().substring(1));
        outs.setText("Outs: " + game.outs);
        strikes.setText("Strikes: " + game.strikes);
        balls.setText("Balls: " + game.balls);
    }

    @Override
    public void actionPerformed(ActionEvent buttonPressed) {
        if (buttonPressed.getSource().equals(playoffChoiceButton)) {
            game = new Game(main.allTeams.get(teamOneIndex), main.allTeams.get(teamTwoIndex), GameType.PLAYOFF, main);
            game.runGame();
        } else if(buttonPressed.getSource().equals(regularSeasonChoiceButton)){
            game = new Game(main.allTeams.get(teamOneIndex), main.allTeams.get(teamTwoIndex), GameType.REGULAR, main);
            game.runGame();
        } else if(buttonPressed.getSource().equals(rightArrowOne)){
            teamOneIndex++;
        } else if (buttonPressed.getSource().equals(leftArrowOne)){
            teamOneIndex--;
        } else if (buttonPressed.getSource().equals(rightArrowTwo)){
            teamTwoIndex++;
        } else if (buttonPressed.getSource().equals(leftArrowTwo)){
            teamTwoIndex--;
        } else if (buttonPressed.getSource().equals(nextPlayButton)){
            game.nextAction();
            main.gameInterface.refreshGameplayScreen();
        } else if (buttonPressed.getSource().equals(nextInningButton)){
            while(game.outs<3){
                game.nextAction();
                main.gameInterface.refreshGameplayScreen();
            }
            game.nextAction();
            main.gameInterface.refreshGameplayScreen();
        }

        teamOneIndex = (teamOneIndex==-1 ? 9 : (teamOneIndex==10 ? 0 : teamOneIndex));

        teamTwoIndex = (teamTwoIndex==-1 ? 9 : (teamTwoIndex==10 ? 0 : teamTwoIndex));

        teamOneName.setText(main.allTeams.get(teamOneIndex).fullName);
        teamTwoName.setText(main.allTeams.get(teamTwoIndex).fullName);
    }
}