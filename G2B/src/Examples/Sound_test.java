package Examples;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Christoph on 12.05.2017.
 */
public class Sound_test {

    public static Mixer mixer;
    public static Clip clip;

    public static void main(String[] args) {

        /*
        Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();
        for(Mixer.Info info : mixInfo) {

            System.out.println(info.getName() + "---" + info.getDescription());

        }
        mixer = AudioSystem.getMixer(mixInfo[0]);

        DataLine.Info datainfo = new DataLine.Info(Clip.class, null);
        try {

            clip = (Clip) mixer.getLine(datainfo);

        } catch (LineUnavailableException lue) {

            System.out.println(lue.getMessage());

        }

        try {

            URL soundURL = Main.class.getResource("GUI/Error.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip.open(audioStream);

        } catch (LineUnavailableException lue){

            System.out.println(lue.getMessage());

        } catch (UnsupportedAudioFileException uafe) {

            System.out.println(uafe.getMessage());

        } catch (IOException ioe) {

            System.out.println(ioe.getMessage());

        }

        clip.start();

        do {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (clip.isActive());
        */

        File sound = new File("C:\\Users\\Christoph\\IdeaProjects\\G2B\\src\\GUI\\Error.wav");
        PlaySound(sound);

    }

    public static void PlaySound(File sound) {

        try {

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

            Thread.sleep(50);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
