import greenfoot.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class manages all sounds in the game.
 * See http://gamedevelopment.tutsplus.com/articles/how-to-build-a-jrpg-a-primer-for-game-developers--gamedev-6676 
 * @author Tony Alexander Hild
 * @version 0.1a
 */
public final class SoundManager  
{

    /**
     * Sounds buffer.
     */
    private static Map<String, GreenfootSound> sounds = new HashMap<String, GreenfootSound>();
    
    /**
     * Singleton object
     */
    private final static SoundManager instance = new SoundManager();
    
    private SoundManager(){}
    
    public static SoundManager getInstance() {
        return instance;
    }
    
    /**
     * Play a sound. Sound files must be of type mp3 and must be located in "sounds" folder.
     * @param sound is the name of mp3 file without extension.
     */
    public void playSound(String sound) {
        playSoundInternal(sound, false);
    }
    
    /**
     * Play a sound with or without loop. Sound files must be of type mp3 and must be located in "sounds" folder.
     * @param sound is the name of mp3 file without extension.
     * @param loop indicates if sound must loop.
     */
    public void playSound(String sound, boolean loop) {
        playSoundInternal(sound, loop);
    }

    /**
     * Play a sound with or without loop. Sound files must be of type mp3 and must be located in "sounds" folder.
     * @param sound is the name of mp3 file without extension.
     * @param loop indicates if sound must loop.
     */
    private void playSoundInternal(String sound, boolean loop) {
        GreenfootSound gfsound = sounds.get(sound); // try get sound from buffer
        if(gfsound == null) { // if sound is null create new sound and add to buffer
            gfsound = new GreenfootSound(sound + ".mp3");
            sounds.put(sound, gfsound);
        }
        if (loop) {
            gfsound.playLoop(); // play with loop
        }
        else {
            gfsound.play(); // play the sound
        }
    }

    /**
     * Stop playing a sound.
     * @param sound is the name of mp3 file without extension.
     */
    public void stopSound(String sound) {
        GreenfootSound gfsound = sounds.get(sound); // try get sound from buffer
        if(gfsound != null) { //if sound is not null stop the sound
            gfsound.stop();
        }
    }
}
