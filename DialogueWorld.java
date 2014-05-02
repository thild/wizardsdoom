import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DialogueWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DialogueWorld extends World
{

    private WizardWorld world;
    private Dialogue dialogue;
    
    /**
     * Constructor for objects of class DialogueWorld.
     * 
     */
    public DialogueWorld(WizardWorld world)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(672, 525, 1); 
        this.world = world;
        this.dialogue = new Dialogue(world);
        SoundManager.stopSound("outside.mp3");
        SoundManager.playSound("dialogue.mp3", true);

    }

    
    public void act() {
        /*
        if(Greenfoot.isKeyDown("escape")) {
            dialogue.dispose();
            SoundManager.stopSound("dialogue.mp3");
            Greenfoot.setWorld(world);
            SoundManager.playSound("outside.mp3", true);
        }
        */
    }
    
        
}
