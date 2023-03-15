package gamemodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Player {
    int playerNumber;
    int score;

    public Player(int playerNumber){
        this.playerNumber=playerNumber;
    }

    public void makePlayerTurn(){
        try {
            FileWriter myWriter = new FileWriter("userTurn.txt");
            myWriter.write(String.valueOf(playerNumber));
            myWriter.close();
            System.out.println("Successfully wrote to the file: "+playerNumber);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean checkIfPlayerTurn(){
        String data="0";
        try {
            File myObj = new File("userTurn.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println("Data read from userTurn.txt: "+data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File not found");
            e.printStackTrace();
        }
        return Integer.parseInt(data)==playerNumber;
    }

    public void increaseScore(){
        score+=200;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
