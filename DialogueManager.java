import java.io.*;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Write a description of class DialogueManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DialogueManager  
{
  
    private static DialogueManager instance = new DialogueManager();
    private static Map<String, DialogueStatus> dialogues = new HashMap<String, DialogueStatus>();    
    
    /**
     * Constructor for objects of class DialogueManager
     */
    public DialogueManager()
    {
    }
    
    public static DialogueManager getInstance() {
        return instance;
    }
    
    public Dialogue getDialogue(String id) {
        return dialogues.get(id).dialogue;
    }
    
    public boolean isDialogueClosed(String id) {
        return dialogues.get(id).closed;
    }
    
    public boolean openDialogue(String id) {
        DialogueStatus ds = dialogues.get(id);
        if (ds != null || ds.closed == true) {
            return false;
        }
        else if (ds == null) {
            ds = new DialogueStatus();
            Dialogue d = readDialogue(id);
            if (d == null) {return false;}
            
        }
        return true;
    }
    /*
    public DialogueWorld(WizardWorld world, Dialogue dialogue)
    {    
        super(672, 525, 1); 
        DialoguePanel dialoguePanel = new DialoguePanel(world, dialogue);
        SoundManager.stopSound("outside.mp3");
        SoundManager.playSound("dialogue.mp3", true);

    }    
    */
    public boolean closeDialogue(String id) {
        if (dialogues.get(id) != null) {
            dialogues.get(id).closed = true;
            return true;
        }
        return false;
    }
    
   public Dialogue readDialogue(String id) {
       try{
           XMLDecoder d = new XMLDecoder(
                              new BufferedInputStream(
                                  new FileInputStream("./dialogues/" + id + ".xml")), null, null, 
                                  bluej.runtime.ExecServer.getCurrentClassLoader());
           Object result = d.readObject();
           d.close();
           System.out.println(result.getClass());
           if (result instanceof Dialogue){
               return (Dialogue)result;
           }
       }
       catch(IOException ioEx)
       {
           ioEx.printStackTrace();
       }
       return null;
   }
   
   public void writeDialogue(Dialogue dialogue) {
        try{
            XMLEncoder e = new XMLEncoder(
                new BufferedOutputStream(
                new FileOutputStream("./dialogues/" + dialogue.getId() +  ".xml")));
            e.writeObject(dialogue);
            e.close();        
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
   }
}
