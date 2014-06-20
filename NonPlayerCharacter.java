import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * NPC class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NonPlayerCharacter extends Character
{
    /**
     * For ramdom move
     */
    private int moveRadius;
    
    public NonPlayerCharacter(String name) {
        this.setName(name);
    }
    
    public void act() {
        super.act();
        move();
    }
    
    public int getMoveRadius() {
        return this.moveRadius;
    }
    
    public void setMoveRadius(int moveRadius) {
        this.moveRadius = moveRadius;
    }    
    
    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean canMove(Direction direction)
    {
        Location iLocation = getInitialLocation();
        int width = SceneManager.getInstance().getScene().getWidth();
        int height = SceneManager.getInstance().getScene().getHeight();
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
        int maxX = moveRadius == 0 || iLocation.getX() + moveRadius > (width - 1) ? width - 1 : iLocation.getX() + moveRadius;
        int maxY = moveRadius == 0 || iLocation.getY() + moveRadius > (height - 1) ? height - 1 : iLocation.getY() + moveRadius;
        
                                          
        if (x < minX || y < minY || x > maxX || y > maxY) 
        {
            return false;
        }
        
     
        return true;
        

    }    
    
    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean canMove()
    {
        return canMove(this.getDirection());
    }
   
    public void move() {
        if(isFrozen()) return;
        while(!canMove()) {
            turn();
            return;
        }          
        if(Greenfoot.getRandomNumber(1000) < 10) {
            Direction newDirection = Direction.fromInt(Greenfoot.getRandomNumber(4));
            while(!canMove(newDirection)) {
                newDirection = Direction.fromInt(Greenfoot.getRandomNumber(4));
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
                setDirection(Direction.EAST);
                break;
            case EAST :
                setDirection(Direction.NORTH);
                break;
            case NORTH :
                setDirection(Direction.WEST);
                break;
            case WEST :
                setDirection(Direction.SOUTH);
                break;
        }
    }    
    
}
