package gamemodel;

import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameTimer{
    private int seconds;
    private int minutes;
    private Text secondsTimer;
    private Text minutesTimer;
    private Text winnerText;
    private Player player1;
    private Player player2;


    public GameTimer(Text secondsTimer, Text minutesTimer, Text winnerText, Player player1, Player player2, int seconds, int minutes) {
        this.secondsTimer=secondsTimer;
        this.minutesTimer=minutesTimer;
        this.seconds=seconds;
        this.minutes=minutes;
        this.winnerText=winnerText;
        this.player1=player1;
        this.player2=player2;
    }

    public void drawTimer(){
        minutesTimer.setText(Integer.toString(minutes)+" min");
        secondsTimer.setText(" & "+Integer.toString(seconds)+" s") ;
        int oneTimeExecute=0;
        while((seconds>0 || minutes>0) && !isChangedScreen()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("eroare de timer");
                e.printStackTrace();
            }
            seconds--;
            System.out.println("SECUNDE: "+seconds);
            secondsTimer.setText("& "+Integer.toString(seconds)+" s");
            System.out.println("nr de minuteee"+minutes);
            if(minutes>0 && seconds==0){
                System.out.println("am intratttt");
                seconds=60;
                System.out.println("secunde a primit: "+seconds);
                minutes--;
                minutesTimer.setText(Integer.toString(minutes)+" min ");
            }
        }
        gameOver();
    }

    public void gameOver(){
        try {
            FileWriter myWriter = new FileWriter("gameOver.txt");
            myWriter.write("1");
            myWriter.close();
            System.out.println("Successfully wrote to the GameOverFile: ");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        GameResult gameResult=new GameResult(player1, player2, winnerText);
        gameResult.drawWinner();
    }

    public boolean isChangedScreen(){
        String data="0";
        try {
            File myObj = new File("screenChanged.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println("Data read from screenChanged.txt: "+data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File not found");
            e.printStackTrace();
        }
        return Integer.parseInt(data)==1;
    }
}