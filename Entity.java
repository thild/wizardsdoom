import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Base class for all game entities 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Entity extends Actor
{


    /**
     * Initial entity location
     */
    private Location initialLocation;
    
    /**
     * Sprite sheet file
     */
    private String spriteSheet = "sprites.png";
    
    /**
     * Sprite block size.
     */
    private int blockSize = 32;
    
    private String name;
    
    private static int count = 0;
    
    public void act() {
        if(getInitialLocation() == null) {
            setInitialLocation(getLocation());
        }    
    }

    public void setName(String name) {
        this.name = name + ++count;
    }
    
    public String getName() {
        return this.name;
    }

    public void setSpriteSheet(String spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
    
    public String getSpriteSheet() {
        return this.spriteSheet;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
    
    public int getBlockSize() {
        return this.blockSize;
    }
    
    public void setSprite(int id) {
        setImage(SpriteSheetManager.getSprite(spriteSheet, id, blockSize));
    }
    
    public void setSprite(String spriteSheet, int id, int blockSize) {
        setImage(SpriteSheetManager.getSprite(spriteSheet, id, blockSize));
    }
    
    public Location getLocation() {
        return new Location(getX(), getY());
    }

    public Location getInitialLocation() {
        return this.initialLocation;
    }
    
    public void setInitialLocation(Location initialLocation) {
        this.initialLocation = initialLocation;
    }
    
}
