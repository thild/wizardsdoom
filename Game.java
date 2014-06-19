import greenfoot.*;
import java.security.*;


/**
 * This class is a façade (http://pt.wikipedia.org/wiki/Fa%C3%A7ade) for the most important functions in the GameScriptFacade.
 * 
 * @author Tony Alexander Hild
 * @version 0.1a
 */
public final class Game
{
    
    public static final int WEST = 0;
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;    
    public static final int NONE = -1;    

    private static SecureRandom random = new SecureRandom();
    private static Inventory inventory = new Inventory();

    private Game() {}
    
    public static void update() {
        SceneManager.getInstance().update();
    }
    
    public static void playSound(String sound) {
        SoundManager.getInstance().playSound(sound);
    }
    
    public static void playLoopSound(String sound) {
        SoundManager.getInstance().playSound(sound, true);
    }

    public static void stopSound(String sound) {
        SoundManager.getInstance().stopSound(sound);
    }
    
    public static void addEntity(Entity entity) {
        SceneManager.getInstance().addEntity(entity);
    }
    
    public static Entity getEntity(String name) {
        return SceneManager.getInstance().getEntity(name);
    }
    
    public static void setCurrentWorld(World world) {
        SceneManager.getInstance().setCurrentWorld(world);
    }
    
    public static World getCurrentWorld() {
        return SceneManager.getInstance().getCurrentWorld();
    }
    
    public static void setActionWorld(ActionWorld world) {
        SceneManager.getInstance().setActionWorld(world);
    }
    
    public static ActionWorld getActionWorld() {
        return SceneManager.getInstance().getActionWorld();
    }
    
    public static void resumeToActionWorld() {
        SceneManager.getInstance().resumeToActionWorld();
    }
    
    public static void setPc(PlayerCharacter pc) {
        SceneManager.getInstance().setPc(pc);
    }
    
    public static PlayerCharacter getPc() {
        return SceneManager.getInstance().getPc();
    }
    
    public static Dialogue getDialogue(String id) {
        return DialogueManager.getDialogue(id);
    }
    
    public static boolean isDialogueClosed(String id) {
        return DialogueManager.isDialogueClosed(id);
    }
    
    public static Dialogue openDialogue(String id, PlayerCharacter player, Character interlocutor) {
        return DialogueManager.openDialogue(id, player, interlocutor);
    }
    
    public static boolean exitDialogue() {
        return DialogueManager.exitDialogue();
    }

    public static void enableDialogueChoices(boolean enabled) {
        DialogueManager.enableDialogueChoices(enabled);
    }
   
    public static Dialogue redirectToDialogue(String id, PlayerCharacter player, Character interlocutor) {
        return DialogueManager.redirectToDialogue(id, player, interlocutor);
    }
    
    public static void updateDialogueMessages() {    
        DialogueManager.updateDialogueMessages();
    }
   
    public static boolean closeDialogue(String id) {
        return DialogueManager.closeDialogue(id);
    }    
    
    public static void setScene(Scene scene) {
        SceneManager.getInstance().setScene(scene);
    }
 
    public static String generateRandomString() {
        return new java.math.BigInteger(130, random).toString(32);
    }
    
    public static Scene getScene() {
        return SceneManager.getInstance().getScene();
    }
    
    public static void addScene(Scene scene) {
        SceneManager.getInstance().addScene(scene);
    }
    
    public static Inventory getInventory() {
        return inventory;
    }
    
    public static void openInventory() {
        inventory.openInventory();
    }
    
    public static void addInventoryItem(InventoryItem item) {
        inventory.addItem(item);
    }
 
}