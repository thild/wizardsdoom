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

    /**
     * Constructor for objects of class SpeechBubble
     */
    public SpeechBubble(String text)  
    {  
        GreenfootImage gi = new greenfoot.GreenfootImage("speech-bubble.png");  
        gi.drawString(text, 20, 30);  
        setImage(gi);  
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {    
            getWorld().removeObject(this);  
        }  
    }
}
