import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)  
import java.awt.Color;

/**
 * Write a description of class SpeechBubble here.
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
    
    public void hide() {
        World w = getWorld();  
        w.removeObject(this);  
    }
    
    /*
 MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse==null) return;
        if(mouse.getActor()==this && mouse.getButton()==1) getWorld().removeObject(this);       
       */
}
