import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * NPC class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class NonPlayerCharacter extends Character
{
    /**
     * For ramdom move
     */
    private int moveRadius;
    
    protected static final int EAST = 0;
    protected static final int WEST = 1;
    protected static final int NORTH = 2;
    protected static final int SOUTH = 3;    
    
    public void act() {
        super.act();
    }
    
    protected int getMoveRadius() {
        return this.moveRadius;
    }
    
    protected void setMoveRadius(int moveRadius) {
        this.moveRadius = moveRadius;
    }    
    
    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    protected boolean canMove(int direction)
    {
        Location iLocation = getInitialLocation();
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
        
        int minX = moveRadius == 0 || iLocation.getX() - moveRadius < 0 ? 0 : iLocation.getX() - moveRadius;
        int minY = moveRadius == 0 || iLocation.getY() - moveRadius < 0 ? 0 : iLocation.getY() - moveRadius;
        int maxX = moveRadius == 0 || iLocation.getX() + moveRadius > (myWorld.getWidth() - 1) ? myWorld.getWidth() - 1 : iLocation.getX() + moveRadius;
        int maxY = moveRadius == 0 || iLocation.getY() + moveRadius > (myWorld.getHeight() - 1) ? myWorld.getHeight() - 1 : iLocation.getY() + moveRadius;
        
                                          
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
        while(!canMove()) {
            turn();
            return;
        }          
        if(Greenfoot.getRandomNumber(1000) < 10) {
            int newDirection = Greenfoot.getRandomNumber(4);
            while(!canMove(newDirection)) {
                newDirection = Greenfoot.getRandomNumber(4);
            }
            setDirection(newDirection);
        }        
        else {
            animate();
            switch(getDirection()) {
                case SOUTH :
                    setLocation(getX(), getY() + (getSpeed() / 10));
                    break;
                case EAST :
                    setLocation(getX() + (getSpeed() / 10), getY());
                    break;
                case NORTH :
                    setLocation(getX(), getY() - (getSpeed() / 10));
                    break;
                case WEST :
                    setLocation(getX() - (getSpeed() / 10), getY());
                    break;
            }        
        }
    }
    
    public void turn()
    {
        switch(getDirection()) {
            case SOUTH :
                setDirection(EAST);
                break;
            case EAST :
                setDirection(NORTH);
                break;
            case NORTH :
                setDirection(WEST);
                break;
            case WEST :
                setDirection(SOUTH);
                break;
        }
    }    
    
}
