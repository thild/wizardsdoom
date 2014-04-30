import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.Map;


/**
 * Write a description of class SoundManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public final class SoundManager  
{
    
    private static Map<String, GreenfootSound> sounds = new HashMap<String, GreenfootSound>();

    /**
     * Sound files must be of type mp3 and must be located in "sounds" folder
     */
    public static void playSound(String soundFile) {
        playSoundInternal(soundFile, false);
    }
    
    public static void playSound(String soundFile, boolean loop) {
        playSoundInternal(soundFile, loop);
    }

    private static void playSoundInternal(String soundFile, boolean loop) {
        GreenfootSound sound = sounds.get(soundFile);
        if(sound == null) {
            sound = new GreenfootSound(soundFile);
            sounds.put(soundFile, sound);
        }
        if (loop) {
            sound.playLoop();
        }
        else {
            sound.play();
        }
    }

    public static void stopSound(String soundFile) {
        GreenfootSound sound = sounds.get(soundFile);
        if(sound != null) {
            sound.stop();
        }
    }
    
}
