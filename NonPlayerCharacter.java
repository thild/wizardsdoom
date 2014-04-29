import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class NonPlayerCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class NonPlayerCharacter extends Character
{
    
    private int moveRadius;
    private int direction;
    
    private List<Integer> eastSprites = new ArrayList<Integer>();
    private List<Integer> westSprites = new ArrayList<Integer>();
    private List<Integer> northSprites = new ArrayList<Integer>();
    private List<Integer> southSprites = new ArrayList<Integer>();
    
    
    private int animationSprite = 0;
    
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


    protected int getMoveRadius() {
        return this.moveRadius;
    }
    
    protected void setMoveRadius(int moveRadius) {
        this.moveRadius = moveRadius;
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
    
    private int animationFrame = 0;
    
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
