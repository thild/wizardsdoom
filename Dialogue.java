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
 */
public class Dialogue
{
   private String message;
   private Character npc;
   private Character pc;
   private String id;
   private String scriptFile;
   
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

   public Character getNpc() {
       return npc;
   }
   
   public void setNpc(Character npc) {
       this.npc = npc;
   }

   public Character getPc() {
       return pc;
   }
   
   public void setPc(Character pc) {
       this.pc = pc;
   }
  
   public List<Choice> getChoices() {
       return choices;
   }
   
   public void setChoices(List<Choice> choices) {
       this.choices = choices;
   }   
}
