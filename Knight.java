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
    
    public void interact() {
        if(Greenfoot.isKeyDown("space")) {
            NonPlayerCharacter wizard = (NonPlayerCharacter)getOneIntersectingObject(Wizard.class);
            if(wizard != null) {
                interact(wizard);
            }        
        }
        
    }
    
    public void interact(Character c) {
        Greenfoot.setWorld(new DialogueWorld((WizardWorld)getWorld()));
    }
    
}
