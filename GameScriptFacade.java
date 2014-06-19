import greenfoot.*;

/**
 * This class is a façade (http://pt.wikipedia.org/wiki/Fa%C3%A7ade) for the most important functions in the GameScriptFacade.
 * 
 * @author Tony Alexander Hild
 * @version 0.1a
 */
public final class GameScriptFacade
{
    
    private static GameScriptFacade instance;
    
    private GameScriptFacade() {
    }
   
    public static GameScriptFacade getInstance() {
        if(instance == null) {
            instance = new GameScriptFacade();
        }
        return instance;
    }
    
    public void playSound(String sound) {
        Game.playSound(sound);
    }
    
    public void playLoopSound(String sound) {
        Game.playLoopSound(sound);
    }

    public void stopSound(String sound) {
        Game.stopSound(sound);
    }
    
    public void addEntity(Entity entity) {
        Game.addEntity(entity);
    }
    
    public Entity getEntity(String name) {
        return Game.getEntity(name);
    }
    
    public void setCurrentWorld(World world) {
        Game.setCurrentWorld(world);
    }
    
    public Dialogue getDialogue(String id) {
        return Game.getDialogue(id);
    }
    
    public boolean isDialogueClosed(String id) {
        return Game.isDialogueClosed(id);
    }
    
    public Dialogue openDialogue(String id, PlayerCharacter player, Character interlocutor) {
        return Game.openDialogue(id, player, interlocutor);
    }
    
    public Dialogue redirectToDialogue(String id, PlayerCharacter player, Character interlocutor) {
        return Game.redirectToDialogue(id, player, interlocutor);
    }
    
    public void enableDialogueChoices(boolean enabled) {
        Game.enableDialogueChoices(enabled);
    }
    

    public boolean exitDialogue() {
        return Game.exitDialogue();
    }
    
    public void updateDialogueMessages() {    
        Game.updateDialogueMessages();
    }
   
    public boolean closeDialogue(String id) {
        return Game.closeDialogue(id);
    }    
    
    public void addInventoryItem(InventoryItem item) {
        Game.addInventoryItem(item);
    }
    
   
}