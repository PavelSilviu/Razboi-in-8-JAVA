package gamemodel;

import javafx.scene.text.Text;

public class GamePlayerTurn {
    private Text playerTurn;
    private Player player1;
    private Player player2;

    public GamePlayerTurn(Player player1, Player player2, Text playerTurn) {
        this.player1 = player1;
        this.player2 = player2;
        this.playerTurn = playerTurn;
    }

    public void drawPlayerTurn() {
        if(player1.checkIfPlayerTurn()){
            playerTurn.setText("1");
        }
        else if(player2.checkIfPlayerTurn())
            playerTurn.setText("2");
    }
}
