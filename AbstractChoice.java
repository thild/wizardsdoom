/**
 * Write a description of class Choice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public abstract class AbstractChoice implements Choice {
    
   private int id;
   private String message;
   private boolean wasChosen;
   private Dialogue dialogue;
   
    public AbstractChoice() {
    
    }

    public AbstractChoice(String message) {
        this.message = message;
    }
   
    public AbstractChoice(int id, String message) {
        this.id = id;
        this.message = message;
    }
   
    public int getId() {
        return id;
    }
   
    public void setId(int id) {
        this.id = id;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }    
    
    
    public boolean wasChosen() {
        return wasChosen;
    }
    
    public void markAsChosen() {
        this.wasChosen = true;
    }

    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }
    
    public Dialogue getDialogue() {
        return this.dialogue;
    }
    
}
