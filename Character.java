import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Character extends Entity
{
    
    private int speed = 10;
    private int strength = 10;
    private int health = 10;
    private int power = 10;
    
    private boolean frozen = false;
    private boolean dialoguing = false;
    
    public void freeze() {
        this.frozen = true;
    }

    public void unfreeze() {
        this.frozen = false;
    }
    
    public boolean isFrozen() {
        return this.frozen;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void increaseSpeed(int ammount) {
        this.speed += ammount;
    }
    
    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }
    
    public boolean ask(Character c) {
        createSpeechBubble("Hi wizard!");
        if(Greenfoot.isKeyDown("enter")) {
            return c.answer(this);
        }
        return false;
    }
    
    public boolean answer(Character c) {
        createSpeechBubble("Hello!");
        if(Greenfoot.isKeyDown("enter")) {
            return true; //c.ask("yes");
        }
        return false;
    }

    protected void createSpeechBubble(String text) {
        SpeechBubble sb = new SpeechBubble(text);
        getWorld().addObject(sb,this.getX() + 50,this.getY() - 50);
    }
    
    public abstract void interact(Character c);
    
}
