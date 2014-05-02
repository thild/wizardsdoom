import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)  
import java.awt.Color;

/**
 * Simple speech bubble notification.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpeechBubble extends Actor
{

    private Character character;
    
    /**
     * Constructor for objects of class SpeechBubble
     */
    public SpeechBubble(Character character, String text)  
    {  
        this.character = character;
        GreenfootImage gi = new greenfoot.GreenfootImage("speech-bubble.png");  
        gi.drawString(text, 20, 30);  
        setImage(gi);  
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {    
            character.stopSpeak();  
        }  
        setLocation(character.getX() + 50, character.getY() - 50);
    }
}
