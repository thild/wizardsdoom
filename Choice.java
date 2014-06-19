/**
 * Write a description of class Choice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface Choice {
   
    public int getId();
   
    public void setId(int id);
    
    public String getMessage();
    
    public void setMessage(String message);
    
    public boolean wasChosen();
    
    public void markAsChosen();
  
    public void setDialogue(Dialogue dialogue);
   
    public Dialogue getDialogue();
    
}
