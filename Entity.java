import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Entity extends Actor
{

    private Location initialLocation;
    private String spriteSheet = "sprites.png";

    public void act() {
        if(getInitialLocation() == null) {
            setInitialLocation(getLocation());
        }    
    }
    
    
    /**
     * This will return an object intersecting with the coloured part of your image.
     */
    public Actor getAnIntersectingObject(Class clss) {
        GreenfootImage img = getImage();
        Actor actor = null;
        for(int x = 0; x<img.getWidth(); x++) {
            for(int y = 0; y<img.getHeight(); y++) {
                if(img.getColorAt(x, y).getAlpha()>0) {
                    if(getOneObjectAtOffset(x-img.getWidth()/2, y-img.getHeight()/2, clss)!=null) {
                        actor = getOneObjectAtOffset(x-img.getWidth()/2, y-img.getHeight()/2, clss);
                    }
                }
            }
        }
        return actor;
    }    
    
    protected void setSpriteSheet(String spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
    
    protected String getSpriteSheet() {
        return this.spriteSheet;
    }
    
    protected void setSprite(int id) {
        setImage(SpriteSheetManager.getSprite(spriteSheet, id, 32));
    }
    
    protected void setSprite(String spriteSheet, int id) {
        setImage(SpriteSheetManager.getSprite(spriteSheet, id, 32));
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
