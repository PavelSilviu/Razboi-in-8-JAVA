package controllers;

import databasemanipulation.Database;
import gamemodel.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PvpScreenController implements Runnable {
    private Text secondsTimerSave;
    private Text minutesTimerSave;
    private Text gameResultSave;
    private Player player1;
    private Player player2;

    @FXML
    Pane gameBoard;
    @FXML
    Text scorePlayer1;
    @FXML
    Text scorePlayer2;
    @FXML
    Text playerTurn;
    @FXML
    Text secondsTimer;
    @FXML
    Text minutesTimer;
    @FXML
    Text gameResult;

    public void setTexts(Text secondsTimer, Text minutesTimer, Text gameResult){
        this.secondsTimer=secondsTimer;
        this.minutesTimer=minutesTimer;
        this.gameResult=gameResult;
    }

    public void setPlayers(Player player1, Player player2){
        this.player1=player1;
        this.player2=player2;
    }


    public void initialize() {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        player1.setScore(0);
        player2.setScore(0);
        createUserTurnFile();
        initUserTurnFile(1);
        createGameOverFile();
        initGameOverFile(0);
        createScreenChanged();
        initScreenChangedFile(0);

        GameScore scoreInstance = new GameScore(player1, player2, scorePlayer1, scorePlayer2);
        scoreInstance.drawScore();

        GamePlayerTurn gamePlayerTurn = new GamePlayerTurn(player1, player2, playerTurn);
        gamePlayerTurn.drawPlayerTurn();

        PvpScreenController obj = new PvpScreenController();
        obj.setTexts(secondsTimer, minutesTimer, gameResult);
        obj.setPlayers(player1, player2);
        Thread thread = new Thread(obj);
        thread.start();

        GameBoard gameBoardInstance = new GameBoard(gameBoard, 400, 8, player1, player2, scoreInstance, gamePlayerTurn);
        gameBoardInstance.drawGameBoard();
        gameBoardInstance.initGrid();
        gameBoardInstance.printGrid();
        gameBoardInstance.drawGamePieces();
        //gameBoardInstance.printGrid();
    }

    public void run() {
        GameTimer gameTimer=new GameTimer(secondsTimer, minutesTimer, gameResult, player1, player2,40,0);
        gameTimer.drawTimer();
        System.out.println("This code is running in a thread");
    }

    public void switchToPlayMenuController(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomeScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
        initScreenChangedFile(1);
    }

    public void createUserTurnFile() {
        try {
            File myObj = new File("userTurn.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void initUserTurnFile(int playerNumber) {
        try {
            FileWriter myWriter = new FileWriter("userTurn.txt");
            myWriter.write(String.valueOf(playerNumber));
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + playerNumber);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void initGameOverFile(int number) {
        try {
            FileWriter myWriter = new FileWriter("gameOver.txt");
            myWriter.write(Integer.toString(number));
            myWriter.close();
            System.out.println("Successfully wrote to GameOver file: "+number);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void createGameOverFile() {
        try {
            File myObj = new File("gameOver.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void initScreenChangedFile(int number) {
        try {
            FileWriter myWriter = new FileWriter("screenChanged.txt");
            myWriter.write(Integer.toString(number));
            myWriter.close();
            System.out.println("Successfully wrote to screenChanged file: "+ number);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void createScreenChanged() {
        try {
            File myObj = new File("screenChanged.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File  screenChanged already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
