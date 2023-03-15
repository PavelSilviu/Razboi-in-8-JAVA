package gamemodel;

import javafx.scene.text.Text;

public class GameResult {
    private Player player1;
    private Player player2;
    private Text winnerText;

    public GameResult(Player player1, Player player2, Text winnerText){
        this.player1=player1;
        this.player2=player2;
        this.winnerText=winnerText;
    }

    public int returnWinner(){
        int result = -1;
        if(player1.getScore()>player2.getScore()){
            result=1;
        }
        else if(player1.getScore()<player2.getScore())
            result=2;
        else if(player1.getScore()==player2.getScore())
            result=0;
        return result;
    }

    public void drawWinner(){
        if(returnWinner()!=0)
            winnerText.setText("Winner is: "+returnWinner());
        else if(returnWinner()==0)
            winnerText.setText("No winner. Equality!");
    }

}
