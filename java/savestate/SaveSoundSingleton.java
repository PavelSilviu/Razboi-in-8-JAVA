package savestate;

import javafx.scene.media.AudioClip;

public class SaveSoundSingleton {
    private AudioClip audio;
    private boolean stateOfPlaying;

    public AudioClip getAudio() {
        return audio;
    }

    public void setAudio(AudioClip audio) {
        this.audio = audio;
    }

    public boolean getStateOfPlaying() {
        return stateOfPlaying;
    }

    public void setStateOfPlaying(boolean stateOfPlaying) {
        this.stateOfPlaying = stateOfPlaying;
    }

    private static SaveSoundSingleton singletonInstance = null;

    private SaveSoundSingleton(){};

    public static SaveSoundSingleton getInstance()
    {
        if (singletonInstance == null)
            singletonInstance = new SaveSoundSingleton();

        return singletonInstance;
    }
}
