import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory  
{
    private static Map<String, InventoryItem> map = new HashMap<String, InventoryItem>();
    
    private String inventorySprite = "";
    
    /**
     * Constructor for objects of class InventoryItem
     */
    public Inventory()
    {
    }

    public void addItem(InventoryItem item) {
        map.put(item.getName(), item);
    }
    
    public Collection<InventoryItem> getItems() {
        return map.values();
    }
    
    public void setInventorySprite(String inventorySprite) {
        this.inventorySprite = inventorySprite;
    }
    
    public String getInventorySprite() {
        return inventorySprite;
    }
    
    public void use(InventoryItem item) {
        ScriptManager.invokeMethod("scripts", "inventory", "use", item);
    }
    
    public void openInventory() {
        if(!(Game.getCurrentWorld() instanceof InventoryWorld)) {
            Game.setCurrentWorld(new InventoryWorld());
        }
    }
}
