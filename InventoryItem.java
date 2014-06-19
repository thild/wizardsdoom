/**
 * Write a description of class InventoryItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InventoryItem  
{

    private String name = "";
    private String description = "";
    private int sprite = 0;
    
    /**
     * Constructor for objects of class InventoryItem
     */
    public InventoryItem()
    {
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }
    
    public int getSprite() {
        return sprite;
    }

}
