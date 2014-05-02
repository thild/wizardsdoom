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
    private static Map<String, Entity> entities = new HashMap<String, Entity>();    
    
    public SceneManager()
    {
    }
    
    public static SceneManager getInstance() {
        return instance;
    }
    
    public static void addEntity(String name, Entity entity) {
        entities.put(name, entity);
    }
    
    public static <T extends Entity> T getEntity(String name) {
        return (T)entities.get(name);
    }
    
}
