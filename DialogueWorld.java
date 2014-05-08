import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DialogueWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DialogueWorld extends World
{

    
    /**
     * Constructor for objects of class DialogueWorld.
     * 
     */
    public DialogueWorld(WizardWorld world, Dialogue dialogue)
    {    
        super(672, 525, 1); 
        DialoguePanel dialoguePanel = new DialoguePanel(world, dialogue);
        SoundManager.stopSound("outside.mp3");
        SoundManager.playSound("dialogue.mp3", true);

    }
    
    public void act() {
    }
  
        
}
