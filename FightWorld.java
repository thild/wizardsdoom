import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FightWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FightWorld extends World
{

    private Fight fight;
    
    /**
     * Constructor for objects of class FightWorld.
     * 
     */
    private FightWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        this.fight = new Fight();
    }
}
