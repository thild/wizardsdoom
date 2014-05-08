import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends PlayerCharacter
{
    public Knight() {
        setSprite(0);
        setSouthSprites(0, 2);
        setWestSprites(12, 14);
        setEastSprites(24, 26);
        setNorthSprites(36, 38);
        setSpeed(20);
        setName("knight");
        SceneManager.getInstance().addEntity(this);
    }
    
    /**
     * Act - do whatever the Wizard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environ4ment.
     */
    public void act() 
    {
        if(isFrozen()) return;
        super.act();
        // Add your action code here.
        move();
        interact();
    }    
    
    private boolean tDown = false;
    
    public void interact() {
        if(Greenfoot.isKeyDown("space")) {
            NonPlayerCharacter wizard = (NonPlayerCharacter)getOneIntersectingObject(Wizard.class);
            if(wizard != null) {
                interact(wizard);
            }        
        }
        WizardWorld w = (WizardWorld)getWorld();
        if(!tDown && Greenfoot.isKeyDown("t") && !isSpeaking()) {
            speak("teste");
            tDown = true;        }
        else if(!tDown && Greenfoot.isKeyDown("t") && isSpeaking()) {
            stopSpeak();
            tDown = true;
        }
        else if (tDown && !Greenfoot.isKeyDown("t")) {
            tDown = false;
        }
        if(Greenfoot.isKeyDown("j")) { 
            ScriptManager.invokeFunction("knight.js", "saySomething", "teste");
        }
    }
    
    public void interact(Character npc) {
        Dialogue dialogue = DialogueManager.getInstance().readDialogue("0002");
        dialogue.setPc(this);
        dialogue.setNpc(npc);
        Greenfoot.setWorld(new DialogueWorld((WizardWorld)getWorld(), dialogue));
    }
    
}
