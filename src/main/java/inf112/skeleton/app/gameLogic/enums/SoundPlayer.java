package inf112.skeleton.app.gameLogic.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Flag;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {

    public enum SoundOrMusic{
        MUSIC("music"),
        SOUND("sounds");
        String path;
        SoundOrMusic(String path) {
            this.path = path;
        }
    }

    public enum GameSound{

        FLAG_PICKUP("flag_pickup", SoundOrMusic.SOUND),
        MOVE("move",SoundOrMusic.SOUND, 0.5f);


        private SoundOrMusic som;
        private String namePath;
        //private HashMap<String, Float> hashMap;
        private float volume;
        private float pitch;
        private float pan;

        GameSound(String soundPath,SoundOrMusic som, float pitch) {
            this.namePath = soundPath;
            this.som = som;

            this.volume = 0.6f;
            this.pitch = pitch;
            this.pan = 1f;
        }

        GameSound(String soundPath,SoundOrMusic som) {
            this.namePath = soundPath;
            this.som = som;
            this.volume = 1f;
            this.pitch = 1f;
            this.pan = 1f;
        }

        public void playSound(){

            String audioPath =
                    "audio" +
                    File.separator +
                    som.path +
                    File.separator +
                    namePath+
                    ".mp3";

            Sound playSound = Gdx.audio.newSound(Gdx.files.internal(audioPath));
            playSound.play(volume,pitch,pan);
        }

    }



}