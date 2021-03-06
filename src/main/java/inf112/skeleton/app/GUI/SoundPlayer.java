package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.io.File;

public class SoundPlayer {

    public enum SoundOrMusic {
        MUSIC("music"),
        SOUND("sounds");
        String path;

        SoundOrMusic(String path) {
            this.path = path;
        }
    }

    public enum GameSound {

        FLAG_PICKUP("flag_pickup", SoundOrMusic.SOUND),
        MOVE("move", SoundOrMusic.SOUND, 0.5f),
        ROTATE("rotate", SoundOrMusic.SOUND),
        PLAY_MUSIC("demoMarbles", SoundOrMusic.MUSIC);

        private SoundOrMusic som;
        private String namePath;
        private float volume;
        private float pitch;
        private float pan;

        GameSound(String soundPath, SoundOrMusic som, float pitch) {
            this.namePath = soundPath;
            this.som = som;

            this.volume = 0.6f;
            this.pitch = pitch;
            this.pan = 1f;
        }

        GameSound(String soundPath, SoundOrMusic som) {
            this.namePath = soundPath;
            this.som = som;
            this.volume = 0.2f;
            this.pitch = 1f;
            this.pan = 1f;
        }

        //
        public void playSound() {
            String audioPath =
                    "audio" +
                            File.separator +
                            som.path +
                            File.separator +
                            namePath +
                            ".mp3";


            if(this.som == SoundOrMusic.MUSIC){
                Music playMusic = Gdx.audio.newMusic(Gdx.files.internal(audioPath));
                playMusic.play();
                playMusic.setVolume(volume);
                playMusic.setLooping(true);
            }
            else{
                Sound playSound = Gdx.audio.newSound(Gdx.files.internal(audioPath));
                playSound.play(volume, pitch, pan);
            }
        }

    }


}
