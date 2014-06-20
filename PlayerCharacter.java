import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class PlayableCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PlayerCharacter extends Character implements Dialogable
{
     private Map<Character, Queue<Dialogue>> dialogues = new HashMap<Character, Queue<Dialogue>>();
     private Map<Character, Queue<Dialogue>> finalizedDialogues = new HashMap<Character, Queue<Dialogue>>();
     private boolean inventoryIsOpen;

    public PlayerCharacter(String name) {
        this.setName(name);
    }
    
    public void act() {
        super.act();
        move();
        inventory();
        Direction direction = atWorldEdge();
        if(direction != Direction.NONE) {
            SceneManager.getInstance().setScene(direction);
        }
    }
    
    public void inventory() {
        if(getWorld() == null) return;
        if(Greenfoot.isKeyDown("i") && !inventoryIsOpen) { 
            Game.openInventory();
            inventoryIsOpen = true;
        }
        else if (!Greenfoot.isKeyDown("i")){
            inventoryIsOpen = false;
        }    
    }
    
    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean canMove(Direction direction)
    {
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
        
        int minX = 0;
        int minY = 0;
        int maxX = width - 1;
        int maxY = height - 1;
        
                                          
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
        if(Greenfoot.isKeyDown("up"))
        {
            if (getDirection() != Direction.NORTH) {
                setDirection(Direction.NORTH);
            }
            else {
                if(!canMove()) return;
            }
            animate();
            setLocation(getX(), getY() - (getSpeed() / 10));
        }
        else if(Greenfoot.isKeyDown("down"))
        {
            if (getDirection() != Direction.SOUTH) {
                setDirection(Direction.SOUTH);
            }
            else {
                if(!canMove()) return;
            }
            animate();
            setLocation(getX(), getY() + (getSpeed() / 10));
        }
        else if(Greenfoot.isKeyDown("left"))
        {
            if (getDirection() != Direction.WEST) {
                setDirection(Direction.WEST);
            }
            else {
                if(!canMove()) return;
            }
            animate();
            setLocation(getX() - (getSpeed() / 10), getY());
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            if (getDirection() != Direction.EAST) {
                setDirection(Direction.EAST);
            }
            else {
                if(!canMove()) return;
            }
            animate();
            setLocation(getX() + (getSpeed() / 10), getY());
        }
    }
    
    /**
     * Initiate a dialogue with interlocutor.
     * The dialogue is configured byscript and returned.
     *
     * @param id The dialogue id
     * @param interlocutor The dialogue interlocutor
     * @return The dialogue initiated.
     */
    public Dialogue initiateDialogueWith(String id, Character interlocutor) {
        Dialogue d = DialogueManager.getInstance().openDialogue(id, this, interlocutor);
        if(d == null || d.isClosed()) return null;
        addDialogue(d, interlocutor);
        return d;
    }
    
    public Dialogue queueDialogue(String id, Character interlocutor) {
        Dialogue d = DialogueManager.getInstance().openDialogue(id, this, interlocutor);
        if(d == null || d.isClosed()) return null;
        addDialogue(d, interlocutor);
        return d;
    }
    
    public boolean hasQueuedDialogue(Character interlocutor) {
        Queue<Dialogue> queue = dialogues.get(interlocutor);
        if(queue != null) {
            return queue.size() > 0;
        }
        return false;
    }
    
    
    public Dialogue initiateNextDialogueWith(Character interlocutor) {
        //Dialogue d = DialogueManager.createDialogue(id, this, interlocutor);
        //addDialogue(d, interlocutor);
        return null;
    }
    
    public void addDialogue(Dialogue dialogue, Character interlocutor) {
        if (dialogue == null) { return;}
        Queue<Dialogue> queue = null;
        if(dialogues.get(interlocutor) == null) {
            queue = new ArrayDeque<Dialogue>();
            dialogues.put(dialogue.getInterlocutor(), queue);
        }
        queue.offer(dialogue);
    }
    
    public Dialogue getCurrentDialogue(Character interlocutor) {
        return dialogues.get(interlocutor).peek();
    }
    
    public void closeCurrentDialogue(Character interlocutor) {
        Dialogue current = getCurrentDialogue(interlocutor);
        if(current != null) {
            current.close();
            Dialogue dialogue = dialogues.get(interlocutor).poll();
            if(dialogue.hasNextDialogue()) {
                Dialogue nextDialogue = DialogueManager.getInstance().getDialogue(dialogue.getNextDialogue());
                addDialogue(nextDialogue, interlocutor);
            }
            Queue<Dialogue> queue = null;
            if(finalizedDialogues.get(interlocutor) == null) {
                queue = new ArrayDeque<Dialogue>();
                finalizedDialogues.put(dialogue.getInterlocutor(), queue);
            }
            queue.offer(dialogue);
        }
    }
    

}
