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
    private String scriptObject;
    
    /**
     * Set if this entity is visible on the current world.
     */
    private boolean visible;
    
    private int x;
    
    private int y;
    
    public abstract void reset();
    
    public void act() {
        if(getInitialLocation() == null) {
            setInitialLocation(getLocation());
        }    
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return this.visible;
    }

    public void show() {
        this.visible = true;
    }
    
    public void hide() {
        this.visible = false;
    }
    
    public void setScriptObject(String scriptObject) {
        this.scriptObject = scriptObject;
    }
    
    public String getScriptObject() {
        if (scriptObject == null) return name;
        return scriptObject;
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
    
    public int getX() {
        if(getWorld() == null) {
            return x;
        }
        return super.getX();
    }
    
    public int getY() {
        if(getWorld() == null) {
            return y;
        }
        return super.getY();
    }
    
    public void setX(int x) {
        super.setLocation(x, getY());
        this.x = x;
    }
    
    public void setY(int y) {
        super.setLocation(getX(), y);
        this.y = y;
    }
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        super.setLocation(x, y);
    }
    
    public Location getLocation() {
        return new Location(getX(), getY());
    }

    public Location getInitialLocation() {
        return this.initialLocation;
    }
    
    public void setInitialLocation(Location initialLocation) {
        this.initialLocation = initialLocation;
        setX(initialLocation.getX());
        setY(initialLocation.getY());
    }
}
