package gamemodel;

import javafx.scene.text.Text;

public class GameScore {
    private Text scorePlayer1;
    private Text scorePlayer2;
    private Player player1;
    private Player player2;

    public GameScore(Player player1, Player player2, Text scorePlayer1, Text scorePlayer2) {
        this.player1 = player1;
        this.player2 = player2;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public void drawScore() {
        scorePlayer1.setText("Score Player1: "+Integer.toString(player1.getScore()));
        scorePlayer2.setText("Score Player2: "+Integer.toString(player2.getScore()));
    }

    public void setScorePlayer1(String scorePlayer1) {
        this.scorePlayer1.setText("Score Player1: "+scorePlayer1);
    }

    public void setScorePlayer2(String scorePlayer2) {
        this.scorePlayer2.setText("Score Player2: "+scorePlayer2);
    }
}
