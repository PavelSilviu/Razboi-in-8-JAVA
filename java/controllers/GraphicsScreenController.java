package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import savestate.SaveStageSingleton;

import java.io.IOException;
import java.util.Objects;

public class GraphicsScreenController {
    public void switchToSettingsScreen(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/SettingsScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void switchToFullScreen(MouseEvent event){
        SaveStageSingleton saveStage = SaveStageSingleton.getInstance();
        Stage stage = saveStage.getStage();
        stage.setFullScreen(true);
    }
    public void switchToEightHundred(MouseEvent event){
        SaveStageSingleton saveStage = SaveStageSingleton.getInstance();
        Stage stage = saveStage.getStage();
        stage.setFullScreen(false);
    }

}
