import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Base class for all game characters
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Character extends Entity 
{
    
    private Direction direction = Direction.SOUTH;
    
    private List<Integer> eastSprites = new ArrayList<Integer>();
    private List<Integer> westSprites = new ArrayList<Integer>();
    private List<Integer> northSprites = new ArrayList<Integer>();
    private List<Integer> southSprites = new ArrayList<Integer>();

    private int animationSprite = 0;
    private int animationFrame = 0;
    
    private String characterImage;
 
    private int speed = 10;
    private int strength = 10;
    private int health = 10;
    private int power = 10;
    private int money = 10;
    private int inteligence = 10;
    
    private boolean frozen = false;
    private boolean dialoguing = false;
    private boolean speaking = false;
    
    private SpeechBubble speechBubble;
    
    private boolean interacting;
    private boolean touching;
    private boolean clicking;
    
    
    public void act() {
        super.act();
        interact();
        touch();
        click();
    }
    
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        resetAnimation();
    }

    
    public void setEastSprites(int begin, int end) {
        eastSprites.clear();
        for(int i = begin; i <= end; ++i) {
            eastSprites.add(i);
        }
    }
    
    public void setWestSprites(int begin, int end) {
        westSprites.clear();
        for(int i = begin; i <= end; ++i) {
            westSprites.add(i);
        }
    }
    
    /**
     * Gets the character image file name.
     * This image is showed in dialogues and combats.
     * The image must be placed in ./images directory.
     *
     * @return Returns the character image file name.
     */
    public String getCharacterImage() {
        return characterImage;
    }
    
    /**
     * Sets the character image. The image must be placed in ./images directory.
     * This image is showed in dialogues and combats.
     * @param characterImage The character image file name.
     */
    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }
    
    /**
     * Test if we are close to one of the edges of the world. Return true is we are.
     */
    public Direction atWorldEdge()
    {
        if(getX() < SceneManager.EDGE) {return Direction.WEST;}
        if(getX() > SceneManager.getInstance().getScene().getWidth() - SceneManager.EDGE) {return Direction.EAST;}
        if(getY() < SceneManager.EDGE) {return Direction.NORTH;}
        if(getY() > SceneManager.getInstance().getScene().getHeight() - SceneManager.EDGE) {return Direction.SOUTH;}
        return Direction.NONE;
    }    
    
    public void setNorthSprites(int begin, int end) {
        northSprites.clear();
        for(int i = begin; i <= end; ++i) {
            northSprites.add(i);
        }
    }
    
    
    public void setSouthSprites(int begin, int end) {
        southSprites.clear();
        for(int i = begin; i <= end; ++i) {
            southSprites.add(i);
        }
    }    
    
    
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
    
    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param power the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * @return the inteligence
     */
    public int getInteligence() {
        return inteligence;
    }

    /**
     * @param power the money to set
     */
    public void setInteligence(int inteligence) {
        this.inteligence = inteligence;
    }
    
    public void increaseSpeed(int ammount) {
        if(speed + ammount < 0) {
            speed = 0;
        }
        else {
            speed += ammount;
        }
    }
    
    public void increaseHealth(int ammount) {
        if(health + ammount < 0) {
            health = 0;
        }
        else {
            health += ammount;
        }
    }
    
    public void increasePower(int ammount) {
        if(power + ammount < 0) {
            power = 0;
        }
        else {
            power += ammount;
        }
    }
    
    public void increaseStrength(int ammount) {
        if(strength + ammount < 0) {
            strength = 0;
        }
        else {
            strength += ammount;
        }
    }
    
    public void increaseMoney(int ammount) {
        if(money + ammount < 0) {
            money = 0;
        }
        else {
            money += ammount;
        }
    }        
    
    public void increaseInteligence(int ammount) {
        if(inteligence + ammount < 0) {
            inteligence = 0;
        }
        else {
            inteligence += ammount;
        }
    }        
    
    public void reset() {
        stopSpeak();
    }
    
    public void talkWith(Character character, String text) {
    }
    
    public void talkWith(Character character, String... args) {
    }
    
    public void speak(String text) {
        if(getWorld() == null) return;
        if (speechBubble == null) {
            speechBubble = new SpeechBubble(this, text);
            getWorld().addObject(speechBubble, 
                                 this.getX() + 50, 
                                 this.getY() - 50);
            speechBubble.act();
            speechBubble.paint();
        }
        else {
            speechBubble.paint();
        }
        speaking = true;
    }
    
    public boolean isSpeaking() {
        return this.speaking;
    }
    
    public void stopSpeak() {
        if(getWorld() == null) return;
        if (speechBubble != null) {
            getWorld().removeObject(speechBubble);
            speechBubble = null;
        }
        speaking = false;
    }
    
    public void interact() {
        if(getWorld() == null) return;
        if(Greenfoot.isKeyDown("space") && !interacting) { 
            PlayerCharacter player = (PlayerCharacter)getOneIntersectingObject(PlayerCharacter.class);
            if(player != null) {
                ScriptManager.invokeMethod("scripts", getScriptObject(), "interact", 
                    player, this);
            }        
            interacting = true;
        }
        else if (!Greenfoot.isKeyDown("space")){
            interacting = false;
        }
    }
    

    public void touch() {
        if(getWorld() == null) return;
        PlayerCharacter pc = (PlayerCharacter)getOneIntersectingObject(PlayerCharacter.class);
        if(!touching && pc != null) { 
            ScriptManager.invokeMethod("scripts", getScriptObject(), "touch", 
                pc, this);
            touching = true;
        }
        else if (pc == null){
            touching = false;
        }
    }
    
    public void click() {
        if(getWorld() == null) return;
        if (!clicking && Greenfoot.mousePressed(this))  
        {  
            ScriptManager.invokeMethod("scripts", getScriptObject(), "click", this);
            clicking = true;
        }  
        else if (clicking && !Greenfoot.mousePressed(this))  
        {  
            clicking = false;
        }  
    }
    
    
   /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean hasAnyoneNear(int range)
    {
        List objects = getObjectsInRange(range, null);
        if(objects.isEmpty()) {
            return true;
        }
        return false;
    }      
    
    public void resetAnimation() {
        if(southSprites.size() == 0) return;
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
    
    public void animate() {
        if(southSprites.size() == 0) return;
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
