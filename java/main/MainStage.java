package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.*;
import javafx.stage.Stage;
import savestate.SaveSoundSingleton;
import savestate.SaveStageSingleton;

import java.io.IOException;
import java.util.Objects;


public class MainStage extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomeScreen.fxml")));
        Scene scene = new Scene(root);

        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/images/logo.png")).toExternalForm());

        stage.getIcons().add(icon);
        stage.setTitle("Razboi in 8");
        stage.setWidth(800);
        stage.setHeight(800);

        SaveStageSingleton saveStage = SaveStageSingleton.getInstance();
        saveStage.setStage(stage);

        //stage.setFullScreen(true);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

        AudioClip backgroundMusic = new AudioClip(Objects.requireNonNull(getClass().getResource("/music/sound1.mp3")).toExternalForm());
        backgroundMusic.play();

        SaveSoundSingleton saveTheAudio = SaveSoundSingleton.getInstance();
        saveTheAudio.setAudio(backgroundMusic);
        saveTheAudio.setStateOfPlaying(true);

    }

    public static void main(String[] args) {
        launch();
    }

}