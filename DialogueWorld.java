import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DialogueWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DialogueWorld extends World
{

    private DialoguePanel dialoguePanel;
    
    /**
     * Constructor for objects of class DialogueWorld.
     * 
     */
    public DialogueWorld(Dialogue dialogue)
    {    
        super(672, 525, 1); 
        dialoguePanel = new DialoguePanel(dialogue);
        //SoundManager.stopSound("outside");
        //SoundManager.playSound("dialogue", true);

    }
    
    public DialoguePanel getDialoguePanel() {
        return dialoguePanel;
    }
    
}
