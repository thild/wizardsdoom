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
public final class DialogueManager  
{
  
    /**
     * All dialogs have an id
     */
    private static Map<String, Dialogue> dialogues = new HashMap<String, Dialogue>();    
    
    /**
     * Singleton object
     */
    private final static DialogueManager instance = new DialogueManager();

    
    /**
     * Constructor for objects of class DialogueManager
     */
    private DialogueManager() {
    }
    
    public static DialogueManager getInstance() {
        return instance;
    }    

    /**
     * Returns a dialogue based on id
     *
     * @param id of dialogue
     * @return return a dialogue
     */
    public Dialogue getDialogue(String id) {
        return dialogues.get(id);
    }
    
    /**
     * Gets a value indicating if dialogue is closed.
     *
     * @param id A parameter
     * @return The return value
     */
    public boolean isDialogueClosed(String id) {
        return dialogues.get(id).isClosed();
    }

    public Dialogue getDialogue(String id, PlayerCharacter player, 
                                          Character interlocutor) {
        Dialogue d = dialogues.get(id);
        if (d == null) {
            d = (Dialogue)ScriptManager.invokeMethod("scripts", 
                                                     interlocutor.getName(), 
                                                     "getDialogue",
                                                     id, player,
                                                     interlocutor);
                                                     
            if (d == null) {return null;}
            dialogues.put(id, d);
        }
        return d;
    }
    
    /**
     * Open a dialogue based on id
     *
     * @param id A parameter
     * @return The return value
     */
    public Dialogue openDialogue(String id, PlayerCharacter player, Character interlocutor) {
        Dialogue d = getDialogue(id, player, interlocutor);
        if (d == null || d.isClosed()) {return d;}
        d.setFeedbackMessage("");
        if(SceneManager.getInstance().getCurrentWorld() instanceof DialogueWorld) {
           DialogueWorld dw =  (DialogueWorld)SceneManager.getInstance().getCurrentWorld();
           dw.getDialoguePanel().updateDialogue(d);
        }
        else {
            SceneManager.getInstance().setCurrentWorld(new DialogueWorld(d));
        }
        SoundManager.getInstance().stopSound(SceneManager.getInstance().getScene().getSoundToPlay());
        SoundManager.getInstance().playSound(d.getSoundToPlay(), true);
        return d;
    }
   
    
    /**
     * Open a dialogue based on id
     *
     * @param id A parameter
     * @return The return value
     */
    public Dialogue redirectToDialogue(String id, PlayerCharacter player, Character interlocutor) {
        return openDialogue(id, player, interlocutor);
    }    
    
    public void updateDialogueMessages() {
        if(SceneManager.getInstance().getCurrentWorld() instanceof DialogueWorld) {
           DialogueWorld dw =  (DialogueWorld)SceneManager.getInstance().getCurrentWorld();
           dw.getDialoguePanel().updateDialogueMessages();
        }
    }
    
    /**
     * Close the dialogue.
     * When a dialogue is closed it cannot be reopened.
     *
     * @param id The dialogue id
     * @return true if dialogue could be closed or false if dialogue does not exists. 
     */
    public boolean closeDialogue(String id) {
        if (dialogues.get(id) != null) {
            dialogues.get(id).close();
            return true;
        }
        return false;
    }
    
    public boolean exitDialogue() {
        if(SceneManager.getInstance().getCurrentWorld() instanceof DialogueWorld) {
           DialogueWorld dw =  (DialogueWorld)SceneManager.getInstance().getCurrentWorld();
           dw.getDialoguePanel().dispose();
           return true;
        }
        return false;
    }
    
    
    public void enableDialogueChoices(boolean enabled) {
        if(SceneManager.getInstance().getCurrentWorld() instanceof DialogueWorld) {
           DialogueWorld dw =  (DialogueWorld)SceneManager.getInstance().getCurrentWorld();
           dw.getDialoguePanel().enableDialogueChoices(enabled);
        }
    }
    
   /**
    * Read a dialogue serialized in a xml file.
    *
    * @param id The id of the dialogue. The dialogue file must be placed in ./dialogues/"id".xml.
    * @return The dialogue deserialized.
    */
   public Dialogue readDialogue(String id) {
       try{
           XMLDecoder d = new XMLDecoder(
                              new BufferedInputStream(
                                  new FileInputStream("./dialogues/" + id + ".xml")), null, null, 
                                  bluej.runtime.ExecServer.getCurrentClassLoader());
           Object result = d.readObject();
           d.close();
           return (Dialogue)result;
       }
       catch(IOException ioEx)
       {
           ioEx.printStackTrace();
       }
       return null;
   }
   
   /**
    * Serialize a dialogue to a xml file.
    * The dialogue will be serialized in ./dialogues/"id".xml.
    *
    * @param dialogue The dialogue to be serialized.
    */
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
