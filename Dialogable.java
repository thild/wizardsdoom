/**
 * Write a description of class Dialogable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Dialogable  
{
    Dialogue initiateDialogueWith(String id, Character interlocutor);
    void addDialogue(Dialogue dialogue, Character interlocutor);
    Dialogue getCurrentDialogue(Character interlocutor);
    void closeCurrentDialogue(Character interlocutor);
}
