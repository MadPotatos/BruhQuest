package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    private int volumeScale;
    public int getVolumeScale() {
		return volumeScale;
	}

	public void setVolumeScale(int volumeScale) {
		this.volumeScale = volumeScale;
	}

	float volume;

    public Sound() {
    	volumeScale = 3;
        soundURL[0] = getClass().getResource("/Sound/menu.wav");
        soundURL[1] = getClass().getResource("/Sound/coin.wav");
        soundURL[2] = getClass().getResource("/Sound/powerup.wav");
        soundURL[3] = getClass().getResource("/Sound/unlock.wav");
        soundURL[4] = getClass().getResource("/Sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/Sound/Hit.wav");
        soundURL[6] = getClass().getResource("/Sound/sword.wav");
        soundURL[7] = getClass().getResource("/Sound/Kill.wav");
        soundURL[8] = getClass().getResource("/Sound/levelup.wav");
        soundURL[9] = getClass().getResource("/Sound/cursor.wav");
        soundURL[10] = getClass().getResource("/Sound/EnergyBall.wav");
        soundURL[11] = getClass().getResource("/Sound/stonebreak.wav");
        soundURL[12] = getClass().getResource("/Sound/GameOver.wav");
        soundURL[13] = getClass().getResource("/Sound/begin.wav");
        soundURL[14] = getClass().getResource("/Sound/click.wav");
        soundURL[15] = getClass().getResource("/Sound/alert.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void play() {
        clip.start();

    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop() {
        clip.stop();

    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;

        }
        fc.setValue(volume);
    }

}
