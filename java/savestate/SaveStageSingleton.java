package savestate;

import javafx.stage.Stage;

public class SaveStageSingleton {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private static SaveStageSingleton singletonInstance = null;

    private SaveStageSingleton(){};

    public static SaveStageSingleton getInstance()
    {
        if (singletonInstance == null)
            singletonInstance = new SaveStageSingleton();

        return singletonInstance;
    }
}
