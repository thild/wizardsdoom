import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class PlayableCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PlayerCharacter extends Character
{
    
    private int direction;
    
    private List<Integer> eastSprites = new ArrayList<Integer>();
    private List<Integer> westSprites = new ArrayList<Integer>();
    private List<Integer> northSprites = new ArrayList<Integer>();
    private List<Integer> southSprites = new ArrayList<Integer>();

    private int animationSprite = 0;
    private int animationFrame = 0;
    

    protected static final int EAST = 0;
    protected static final int WEST = 1;
    protected static final int NORTH = 2;
    protected static final int SOUTH = 3;    
    

    
    public void act() {
        super.act();
    }
    
    protected int getDirection() {
        return direction;
    }

    protected void setDirection(int direction) {
        this.direction = direction;
        resetAnimation();
    }

    
    protected void setEastSprites(int begin, int end) {
        eastSprites.clear();
        for(int i = begin; i <= end; ++i) {
            eastSprites.add(i);
        }
    }
    
    protected void setWestSprites(int begin, int end) {
        westSprites.clear();
        for(int i = begin; i <= end; ++i) {
            westSprites.add(i);
        }
    }
    
    protected void setNorthSprites(int begin, int end) {
        northSprites.clear();
        for(int i = begin; i <= end; ++i) {
            northSprites.add(i);
        }
    }
    
    
    protected void setSouthSprites(int begin, int end) {
        southSprites.clear();
        for(int i = begin; i <= end; ++i) {
            southSprites.add(i);
        }
    }    
    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    protected boolean canMove(int direction)
    {
        World myWorld = getWorld();
        int x = getX();
        int y = getY();
        switch(direction) {
            case SOUTH :
                y++;
                break;
            case EAST :
                x++;
                break;
            case NORTH :
                y--;
                break;
            case WEST :
                x--;
                break;
        }
        
        int minX = 0;
        int minY = 0;
        int maxX = myWorld.getWidth() - 1;
        int maxY = myWorld.getHeight() - 1;
        
                                          
        if (x < minX || y < minY || x > maxX || y > maxY) 
        {
            return false;
        }
        
     
        return true;
    }    
    
    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    protected boolean canMove()
    {
        return canMove(this.getDirection());
    }
   
    public void move() {
        if(Greenfoot.isKeyDown("up"))
        {
            if (getDirection() != NORTH) {
                setDirection(NORTH);
            }
            animate();
            setLocation(getX(), getY() - (getSpeed() / 10));
        }
        else if(Greenfoot.isKeyDown("down"))
        {
            if (getDirection() != SOUTH) {
                setDirection(SOUTH);
            }
            animate();
            setLocation(getX(), getY() + (getSpeed() / 10));
        }
        else if(Greenfoot.isKeyDown("left"))
        {
            if (getDirection() != WEST) {
                setDirection(WEST);
            }
            animate();
            setLocation(getX() - (getSpeed() / 10), getY());
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            if (getDirection() != EAST) {
                setDirection(EAST);
            }
            animate();
            setLocation(getX() + (getSpeed() / 10), getY());
        }
    }
    
    protected void resetAnimation() {
        animationFrame = 0;
        animationSprite = 0;
        switch(getDirection()) {
            case SOUTH :
                setSprite(southSprites.get(0));
                break;
            case EAST :
                setSprite(eastSprites.get(0));
                break;
            case NORTH :
                setSprite(northSprites.get(0));
                break;
            case WEST :
                setSprite(westSprites.get(0));
                break;
        }
    }
    
    protected void animate() {
        if((++animationFrame % 6) == 0) 
        {
            switch(getDirection()) {
                case SOUTH :
                    setSprite(southSprites.get(animationSprite++ % southSprites.size()));
                    break;
                case EAST :
                    setSprite(eastSprites.get(animationSprite++ % eastSprites.size()));
                    break;
                case NORTH :
                    setSprite(northSprites.get(animationSprite++ % northSprites.size()));
                    break;
                case WEST :
                    setSprite(westSprites.get(animationSprite++ % westSprites.size()));
                    break;
            }
            animationFrame = 0;
        }
    }
}
