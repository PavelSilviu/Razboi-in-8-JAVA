package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import savestate.SaveSoundSingleton;

import java.io.IOException;
import java.util.Objects;

public class SoundScreenController {
    public void switchToSettingsScreen(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/SettingsScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void stopSound(MouseEvent event){
        SaveSoundSingleton saveTheAudio = SaveSoundSingleton.getInstance();
        saveTheAudio.setStateOfPlaying(false);
        AudioClip backgroundMusic = saveTheAudio.getAudio();
        backgroundMusic.stop();
        //System.out.println(((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable());
    }
    public void playSound(MouseEvent event){
        SaveSoundSingleton saveTheAudio = SaveSoundSingleton.getInstance();
        AudioClip backgroundMusic = saveTheAudio.getAudio();
        if(!saveTheAudio.getStateOfPlaying()){
            backgroundMusic.play();
        }
    }
}
