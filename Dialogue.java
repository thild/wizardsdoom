import java.util.*;

/**
 * Write a description of class Dialogue here.
 * http://stackoverflow.com/questions/372915/game-logic-in-xml-files
 * https://github.com/scottbw/dialoguejs
 * http://stackoverflow.com/questions/1840154/rpg-dialogue-engine-structure
 * http://gamedev.stackexchange.com/questions/33714/should-npc-dialog-be-stored-in-xml-or-in-a-script
 * http://www.gamedev.net/topic/596071-best-technique-to-write-multiple-choice-dialogue/
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * 
 * 
 */
public class Dialogue
{
   private String message = "";
   private String feedbackMessage = "";
   private PlayerCharacter player;
   private Character interlocutor;
   private String id = "";
   private String scriptFile = "";
   private String soundToPlay = "";
   private String nextDialogue = "";
   private boolean closed;
   
   private List<Choice> choices = new ArrayList<Choice>();
   
   public Dialogue() {
   }
   
   public String getId() {
       return id;
   }
   
   public void setId(String id) {
       this.id = id;
   }

   public String getScriptFile() {
       return scriptFile;
   }
   
   public void setScriptFile(String scriptFile) {
       this.scriptFile = scriptFile;
   }

   public String getMessage() {
       return message;
   }
   
   public void setMessage(String message) {
       this.message = message;
   }

   public String getFeedbackMessage() {
       return feedbackMessage;
   }
   
   public void setFeedbackMessage(String feedbackMessage) {
       this.feedbackMessage = feedbackMessage;
   }

   public String getNextDialogue() {
       return nextDialogue;
   }
   
   public void setNextDialogue(String nextDialogue) {
       this.nextDialogue = nextDialogue;
   }

   public boolean hasNextDialogue() {
       return !nextDialogue.isEmpty();
   }
   
   public Character getInterlocutor() {
       return interlocutor;
   }
   
   public void setInterlocutor(Character interlocutor) {
       this.interlocutor = interlocutor;
   }

   public Character getPlayer() {
       return player;
   }
   
   public void setPlayer(PlayerCharacter player) {
       this.player = player;
   }
   
    public String getSoundToPlay() {
        return soundToPlay;
    }
    
    public void setSoundToPlay(String soundToPlay) {
        this.soundToPlay = soundToPlay;
    }   
  
   public void close() {
       closed = true;
   }
   
   public boolean isClosed() {
       return closed;
   }
   
   public void addChoice(Choice choice) {
       choice.setId(choices.size());
       choice.setDialogue(this);                     
       choices.add(choice);
   }

   public List<Choice> getChoices() {
       return choices;
   }
}
