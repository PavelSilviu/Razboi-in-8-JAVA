package gamemodel;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {
    private ArrayList<Piece> pieces;
    private Pane gameBoard;
    private int size;
    private int spots;
    private int matrixSize;
    private int[][] grid;
    private int squareSize;
    private Player player1;
    private Player player2;
    private GameScore scoreInstance;
    private GamePlayerTurn gamePlayerTurn;

    public GameBoard(Pane gameBoard, int size, int spots, Player player1, Player player2, GameScore scoreInstance, GamePlayerTurn gamePlayerTurn) {
        this.gameBoard = gameBoard;
        this.size = size;
        this.spots = spots;
        this.matrixSize=spots+2;
        grid=new int[matrixSize][matrixSize];
        if (spots > 0)
            this.squareSize = size / spots;
        else
            this.squareSize = -1;
        this.player1=player1;
        this.player2=player2;
        this.scoreInstance=scoreInstance;
        this.gamePlayerTurn=gamePlayerTurn;
    }

    public void drawGameBoard() {
        for (int i = 0; i < size; i += squareSize)
            for (int j = 0; j < size; j += squareSize) {
                Rectangle r = new Rectangle(i, j, squareSize, squareSize);
                if (((i + j) / 50) % 2 == 0) {
                    r.setFill(Color.WHITE);
                } else {
                    r.setFill(Color.BLACK);
                }
                gameBoard.getChildren().add(r);
            }

    }

    public void initGrid() {
        for (int line = 0; line < matrixSize; line ++)
            for (int column = 0; column < matrixSize; column ++){
                if(line==0 || column==0 || line==matrixSize-1 || column==matrixSize-1)
                    grid[line][column]=3;
                else
                    grid[line][column]=0;
            }
        int line=1;
        for (int  column= 1; column< matrixSize-1; column ++) {
                if((line+column)%2==0)
                    grid[line][column]=1;//player 1
        }
        line=2;
        for (int column = 1; column < matrixSize-1; column ++) {
            if((line+column)%2==0)
                grid[line][column]=1;//player 1
        }
        line=matrixSize-3;
        for (int column = 1; column < matrixSize-1; column ++) {
            if((line+column)%2==0)
                grid[line][column]=2;//player 2
        }
        line=matrixSize-2;
        for (int column = 1; column < matrixSize-1; column ++) {
            if((line+column)%2==0)
                grid[line][column]=2;//player 2
        }
    }

    public void printGrid(){
        for (int line = 0; line < matrixSize; line ++){
            for (int column = 0; column < matrixSize; column ++)
                System.out.print(grid[line][column]+" ");
            System.out.println();
        }

    }

    public void drawGamePieces() {//in functie de grid
        pieces = new ArrayList<Piece>();
        for (int line = 0; line < matrixSize; line ++)
            for (int column = 0; column < matrixSize; column ++){
                if(grid[line][column]==1){
                    //System.out.println("linia "+line+" & coloana "+column);
                    Circle circle = new Circle();
                    circle.setFill(Color.CORAL);
                    circle.setStroke(Color.BLACK);
                    double radius = squareSize / 3.0;
                    Piece piece = new Piece((column-1)*squareSize+squareSize/2,(line-1)*squareSize+squareSize/2, radius, circle);
                    piece.memorateOldValues(piece.getX(),piece.getY());
                    circle.setOnMouseClicked(event->pressedPieceHandler(event,piece));
                    circle.setOnMouseDragged(event->draggedPieceHandler(event,piece));
                    circle.setOnMouseReleased(event->releasedPieceHandler(event,piece,1));

                    gameBoard.getChildren().add(circle);
                    piece.draw();
                }
                else if(grid[line][column]==2){
                    //System.out.println("linia "+line+" & coloana "+column);
                    Circle circle = new Circle();
                    circle.setFill(Color.MAGENTA);
                    circle.setStroke(Color.BLACK);
                    double radius = squareSize / 3.0;
                    Piece piece = new Piece((column-1)*squareSize+squareSize/2,(line-1)*squareSize+squareSize/2, radius, circle);
                    piece.memorateOldValues(piece.getX(),piece.getY());
                    circle.setOnMouseClicked(event->pressedPieceHandler(event,piece));
                    circle.setOnMouseDragged(event->draggedPieceHandler(event,piece));
                    circle.setOnMouseReleased(event->releasedPieceHandler(event,piece, 2));

                    gameBoard.getChildren().add(circle);
                    piece.draw();
                }
            }
    }

    public void pressedPieceHandler(MouseEvent event, Piece p){
       // p.setColor(Color.RED);
    }

    public void draggedPieceHandler(MouseEvent event, Piece p){
        p.setX(p.getX()+event.getX());
        p.setY(p.getY()+event.getY());
        p.draw();
    }

    public void releasedPieceHandler(MouseEvent event, Piece p, int playerNumber){
        int gridX=(int)p.getX()/squareSize;
        int gridY=(int)p.getY()/squareSize;
        boolean playerTurn;
        if(playerNumber==1){
            playerTurn=player1.checkIfPlayerTurn();
        }
        else
            playerTurn=player2.checkIfPlayerTurn();

        System.out.println("vechiul grid: x="+(int)p.getOldX()/squareSize+" & y="+(int)p.getOldY()/squareSize);
        printGrid();
        if(isAdiacentPosition(gridX, gridY, p) && !isOnNeighbour(gridX,gridY) && isValidPosition(gridX,gridY) && playerTurn && !checkIfGameOver()){
            p.setX(squareSize/2+squareSize*gridX);
            p.setY(squareSize/2+squareSize*gridY);
            p.draw();
            grid[(int)p.getOldY()/squareSize+1][(int)p.getOldX()/squareSize+1]=0;
            grid[gridY+1][gridX+1]=playerNumber;
            p.memorateOldValues(p.getX(),p.getY());
            takeOutPiece(gridX,gridY,playerNumber);
            System.out.println("noul grid: x="+gridX+" & y="+gridY);
            printGrid();
            if(playerNumber==1){
                player2.makePlayerTurn();
            }
            else{
                player1.makePlayerTurn();
            }
            gamePlayerTurn.drawPlayerTurn();
        }
        else{
            p.setX(p.getOldX());
            p.setY(p.getOldY());
            p.draw();
        }

    }

    public boolean isAdiacentPosition(int gridX, int gridY, Piece p){//ia noua pozitie si vede daca e adiacenta
        int gridXOldPosition=(int)p.getOldX()/squareSize;
        int gridYOldPosition=(int)p.getOldY()/squareSize;
        boolean returnVar=gridX==gridXOldPosition+1 && gridY==gridYOldPosition+1 || gridX==gridXOldPosition+1 && gridY==gridYOldPosition-1 || gridX==gridXOldPosition-1 && gridY==gridYOldPosition-1 || gridX==gridXOldPosition-1 && gridY==gridYOldPosition+1;
        System.out.println("isAdiacentPosition a returnat :"+returnVar);
        return returnVar;
    }

    public boolean isOnNeighbour(int gridX, int gridY){
        boolean returnVar=grid[gridY+1][gridX+1]==1 || grid[gridY+1][gridX+1]==2;
        System.out.println("isOnNeighbour a returnat:"+returnVar);
        return returnVar;
    }

    public boolean isValidPosition(int gridX, int gridY){
        boolean returnValue=grid[gridY+1][gridX+1]!=3;
        System.out.println("isValidPosition a returnat:"+returnValue);
        return returnValue;
    }

    public void takeOutPiece(int gridX, int gridY, int playerNumber ){
        int tempVar;
        if(playerNumber==1){
            tempVar=2;
        }
        else
            tempVar=1;
        if(grid[gridY+1+1][gridX+1+1]==tempVar){
            System.out.println("i m innnn");
            if(grid[gridY+1][gridX+1]==playerNumber && grid[gridY+1][gridX+3]==playerNumber && grid[gridY+3][gridX+3]==playerNumber && grid[gridY+3][gridX+1]==playerNumber){
                System.out.println("sterg piesa");
                grid[gridY+1+1][gridX+1+1]=0;
                if(playerNumber==1){
                    player1.increaseScore();
                    System.out.println("sunt playerul "+playerNumber+"scorul meu acum:"+player1.getScore());
                    scoreInstance.setScorePlayer1(Integer.toString(player1.getScore()));
                    scoreInstance.drawScore();
                }
                else{
                    player2.increaseScore();
                    System.out.println("sunt playerul "+playerNumber+"scorul meu acum:"+player2.getScore());
                    scoreInstance.setScorePlayer2(Integer.toString(player2.getScore()));
                    scoreInstance.drawScore();
                }

                drawGameBoard();
                drawGamePieces();
            }
        }
        if(grid[gridY+1+1][gridX]==tempVar){
            System.out.println("i m innnn");
            if(grid[gridY+1+1-1][gridX-1]==playerNumber && grid[gridY+1+1-1][gridX+1]==playerNumber && grid[gridY+1+1+1][gridX+1]==playerNumber && grid[gridY+1+1+1][gridX-1]==playerNumber){
                System.out.println("sterg piesa");
                grid[gridY+1+1][gridX]=0;
                if(playerNumber==1){
                    player1.increaseScore();
                    System.out.println("sunt playerul "+playerNumber+"scorul meu acum:"+player1.getScore());
                    scoreInstance.setScorePlayer1(Integer.toString(player1.getScore()));
                    scoreInstance.drawScore();
                }
                else{
                    player2.increaseScore();
                    System.out.println("sunt playerul "+playerNumber+"scorul meu acum:"+player2.getScore());
                    scoreInstance.setScorePlayer2(Integer.toString(player2.getScore()));
                    scoreInstance.drawScore();
                }

                drawGameBoard();
                drawGamePieces();
            }
        }
        if(grid[gridY][gridX]==tempVar){
            System.out.println("i m innnn");
            if(grid[gridY-1][gridX-1]==playerNumber && grid[gridY-1][gridX+1]==playerNumber && grid[gridY+1][gridX+1]==playerNumber && grid[gridY+1][gridX-1]==playerNumber){
                System.out.println("sterg piesa");
                grid[gridY][gridX]=0;
                if(playerNumber==1){
                    player1.increaseScore();
                    System.out.println("sunt playerul "+playerNumber+"scorul meu acum:"+player1.getScore());
                    scoreInstance.setScorePlayer1(Integer.toString(player1.getScore()));
                    scoreInstance.drawScore();
                }
                else{
                    player2.increaseScore();
                    System.out.println("sunt playerul "+playerNumber+"scorul meu acum:"+player2.getScore());
                    scoreInstance.setScorePlayer2(Integer.toString(player2.getScore()));
                    scoreInstance.drawScore();
                }

                drawGameBoard();
                drawGamePieces();
            }
        }
        if(grid[gridY][gridX+1+1]==tempVar){
            System.out.println("i m innnn");
            if(grid[gridY-1][gridX+1+1-1]==playerNumber && grid[gridY-1][gridX+1+1+1]==playerNumber && grid[gridY+1][gridX+1+1+1]==playerNumber && grid[gridY+1][gridX+1+1-1]==playerNumber){
                System.out.println("sterg piesa");
                grid[gridY][gridX+1+1]=0;
                if(playerNumber==1){
                    player1.increaseScore();
                    System.out.println("sunt playerul "+playerNumber+"scorul meu acum:"+player1.getScore());
                    scoreInstance.setScorePlayer1(Integer.toString(player1.getScore()));
                    scoreInstance.drawScore();
                }
                else{
                    player2.increaseScore();
                    System.out.println("sunt playerul "+playerNumber+"scorul meu acum:"+player2.getScore());
                    scoreInstance.setScorePlayer2(Integer.toString(player2.getScore()));
                    scoreInstance.drawScore();
                }

                drawGameBoard();
                drawGamePieces();
            }
        }
    }

    public boolean checkIfGameOver(){
        String data="0";
        try {
            File myObj = new File("gameOver.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println("Data read from gameOver.txt: "+data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File not found");
            e.printStackTrace();
        }
        return Integer.parseInt(data)==1;
    }
}
