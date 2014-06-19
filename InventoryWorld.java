import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InventoryWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InventoryWorld extends World
{

    private InventoryPanel inventoryPanel;
    
    /**
     * Constructor for objects of class InventoryWorld.
     * 
     */
    public InventoryWorld()
    {    
        super(672, 525, 1); 
        inventoryPanel = new InventoryPanel();
        //SoundManager.stopSound("outside");
        //SoundManager.playSound("Inventory", true);

    }
    
    public InventoryPanel getInventoryPanel() {
        return inventoryPanel;
    }
    
}
