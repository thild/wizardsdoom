import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wizard extends NonPlayerCharacter
{

    public Wizard(String name) {
        super(name);
        setSprite(6); //initialSprite
        setMoveRadius(0); //radius of movement
        setSouthSprites(6, 8);
        setWestSprites(18, 20);
        setEastSprites(30, 32);
        setNorthSprites(42, 44);
        setCharacterImage("wizard.jpg");
    }
}
