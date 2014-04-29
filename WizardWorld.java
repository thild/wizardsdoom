import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WizardWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WizardWorld extends World
{

    /**
     * Constructor for objects of class WizardWorld.
     * 
     */
    public WizardWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setBackground("cell.jpg");
    }
    
    public void act() {
         if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("enter")) {
             unfreeze();
             removeObjects(getObjects(SpeechBubble.class));
         }
    }
    
    public void freeze() {
        for(Object character : getObjects(Character.class)) {
            ((Character)character).freeze();
        }
    }
    
   public void unfreeze() {
        for(Object character : getObjects(Character.class)) {
            ((Character)character).unfreeze();
        }
    }    
}
