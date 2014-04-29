import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class PlayableCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PlayerCharacter extends Character
{

    
    public void act() {
        super.act();
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
}
