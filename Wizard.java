import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wizard extends NonPlayerCharacter
{

    public Wizard() {
        setSprite(6); //initialSprite
        setMoveRadius(0); //radius of movement
        setSouthSprites(6, 8);
        setWestSprites(18, 20);
        setEastSprites(30, 32);
        setNorthSprites(42, 44);
    }
    
    /**
     * Act - do whatever the Wizard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environ4ment.
     */
    public void act() 
    {
        if(isFrozen()) return;
        super.act();
        move();
    }    
    
    public void interact(Character c) {
    
    }
    
}
