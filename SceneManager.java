import greenfoot.*;
import java.util.*;


/**
 * This class manages the game scenes
 * 
 * @author Tony Alexander Hild
 * @version 0.1a
 */
public final class SceneManager  
{

    public static final int EDGE = 5;
    
    /**
     * Entity buffer. This buffer may be in Game class?
     */
    private static Map<String, Entity> entities = new HashMap<String, Entity>();    
    
    private static Map<String, Scene> scenes = new HashMap<String, Scene>();    
    
    /**
     * Current world.
     */
    private static World currentWorld;
    
    
    /**
     * Action world;
     */
    private static ActionWorld actionWorld;
    
    /**
     * Current active hero.
     */
    private static PlayerCharacter currentPc;
    
    /**
     * Current active scene.
     */
    private static Scene currentScene;
    
    /**
     * Singleton object
     */
    private final static SceneManager instance = new SceneManager();
    
    /**
     * Constructor for objects of class SceneManager
     */
    private SceneManager() {
    }
    
    public static SceneManager getInstance() {
        return instance;
    }
    
    public void update() {
        for(Map.Entry<String, Entity> entry : entities.entrySet()) {
            if(currentScene.getEntities().contains(entry.getValue())) return;
            entry.getValue().act();
        }
    }
    
    /**
     * Add a entity to the game
     *
     * @param entity to be added to the game
     */
    public void addEntity(Entity entity) {
        entities.put(entity.getName(), entity);
    }
    
    /**
     * Gets an entity from the game by a name
     *
     * @param name The entity's name
     * @return Returns a entity
     */
    public <T extends Entity> T getEntity(String name) {
        return (T)entities.get(name);
    }
    
    /**
     * Set the current game world
     *
     * @param world the world to be set
     */
    public void setCurrentWorld(World world) {
        currentWorld = world;
        Greenfoot.setWorld(world);
    }
    
    /**
     * Gets the current game world.
     *
     * @return A World object.
     */
    public World getCurrentWorld() {
        return currentWorld;
    }
    
    /**
     * Gets the current action world.
     *
     * @return A ActionWorld object.
     */
    public ActionWorld getActionWorld() {
        return actionWorld;
    }
    
    /**
     * Sets the current action world.
     *
     */
    public void setActionWorld(ActionWorld world) {
        currentWorld = world;
        actionWorld = world;
    }
    
    public void resumeToActionWorld() {
        currentWorld = actionWorld;
        if(Game.isRunning()) {
            SoundManager.getInstance().playSound(getScene().getSoundToPlay(), true);
        }
        Greenfoot.setWorld(actionWorld);
    }
    
    /**
     * Gets the current game player character.
     *
     * @return A PlayerCharacter object.
     */
    public PlayerCharacter getPc() {
        return currentPc;
    }
    
    /**
     * Sets the current active player character.
     *
     * @param playerCharacter The player character to be set.
     */
    public void setPc(PlayerCharacter pc) {
        currentPc = pc;
    }
    
    
    public void setScene(Scene scene) {
        if (!scenes.containsKey(scene.getName())) {
            scenes.put(scene.getName(), scene);
        }
        if(currentScene != null) {
            currentScene.setEntities(getAllEntities());
            for (Entity e : currentScene.getEntities()) {
                e.reset();
            }
        }
        currentScene = scene;
        clearWorld();
        for(Entity e : currentScene.getEntities())
        {
            currentWorld.addObject(e, 
                                   e.getX(), 
                                   e.getY());
        }
        currentWorld.setBackground(scene.getBackground());
    }

    
    public void setScene(Direction direction) {
        Scene scene = currentScene.getNeighbourScene(direction);
        if(scene != null) {
            switch(direction) {
                case NORTH:
                    currentPc.setY(currentWorld.getHeight() - EDGE);
                    break;
                case SOUTH:
                    currentPc.setY(EDGE);
                    break;
                case WEST:
                    currentPc.setX(currentWorld.getWidth() - EDGE);
                    break;
                case EAST:
                    currentPc.setX(EDGE);
                    break;
            }
            setScene(scene);
        }
    }
    
    public void addScene(Scene scene) {
        scenes.put(scene.getName(), scene);
        if(currentWorld != null) {
            scene.setWidth(currentWorld.getWidth());
            scene.setHeight(currentWorld.getHeight());
        }
    }
    
    public Scene getScene() {
        return currentScene;
    }
    
    public void playMusic() {
        SoundManager.getInstance().playSound(getScene().getSoundToPlay(), true);
    }
    
    public void pauseMusic() {
        SoundManager.getInstance().pauseSound(getScene().getSoundToPlay());
    }
    
    private void clearWorld() {
        if(currentWorld == actionWorld) {
            currentWorld.removeObjects(currentWorld.getObjects(null));
        }
    }
    
    private List<Entity> getAllEntities() {
        return (List<Entity>)currentWorld.getObjects(Entity.class);
    }
}
