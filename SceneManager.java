import greenfoot.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Write a description of class SceneManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public final class SceneManager  
{

    private static SceneManager instance = new SceneManager();
    private Map<String, Entity> entities = new HashMap<String, Entity>();    
    private World world;
    
    public SceneManager()
    {
    }
    
    public static SceneManager getInstance() {
        return instance;
    }
    
    public void addEntity(Entity entity) {
        entities.put(entity.getName(), entity);
    }
    
    public <T extends Entity> T getEntity(String name) {
        return (T)entities.get(name);
    }
    
    public void setWorld(World world) {
        this.world = world;
        Greenfoot.setWorld(world);
        
        //export all heros to the new world?
    }
    
}
