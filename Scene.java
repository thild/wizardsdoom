import java.util.*;

/**
 * Write a description of class Scene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scene  
{
    
    private Scene[] neighbourScenes = new Scene[4];
    private List<Entity> entities = new ArrayList<Entity>();   
    private String background = "bg.png";
    private int width;
    private int height;
    private String name;
    private String soundToPlay;
    
    /**
     * Constructor for objects of class Scene
     */
    public Scene()
    {
        this.name = Game.generateRandomString();
    }
    
    public Scene(String name)
    {
        this.name = name;
    }
    
    public Scene getNeighbourScene(Direction direction) {
        return neighbourScenes[direction.getValue()];
    }

    public void setNeighbourScene(Direction direction, Scene scene) {
        neighbourScenes[direction.getValue()] = scene;
        switch(direction) {
            case NORTH:
                scene.neighbourScenes[Direction.SOUTH.getValue()] = this;
                break;
            case SOUTH:
                scene.neighbourScenes[Direction.NORTH.getValue()] = this;
                break;
            case WEST:
                scene.neighbourScenes[Direction.EAST.getValue()] = this;
                break;
            case EAST:
                scene.neighbourScenes[Direction.WEST.getValue()] = this;
                break;
        }
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
    
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    
    public void clearEntities() {
        entities.clear();
    }
    
    
    /**
     * Add a entity to the scene
     *
     * @param entity to be added to the scene
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }    

    public String getBackground() {
        return this.background;
    }
    
    public void setBackground(String background) {
        this.background = background;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   
    public String getSoundToPlay() {
        return soundToPlay;
    }
    
    public void setSoundToPlay(String soundToPlay) {
        this.soundToPlay = soundToPlay;
    }       
    
}
