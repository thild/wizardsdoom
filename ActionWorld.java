import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.core.WorldHandler; 

/**
 * Write a description of class ActionWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ActionWorld extends World
{
    /**
     * Constructor for objects of class ActionWorld.
     * 
     */
    public ActionWorld()
    {    
        // Create a new world with 672x525 cells with a cell size of 1x1 pixels.
        super(672, 525, 1);
        SceneManager.getInstance().setActionWorld(this);

        //workaround to remove orphan dialogues
        final javax.swing.JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        for (java.awt.Component p : canvas.getComponents()) {
            canvas.remove(p);
        }
        
        Game.setup();

    }
    
    public void act() {
        super.act();
        SceneManager.getInstance().update();
    }
    
    public void started() {
        super.started();
        Game.start();
    }
    
    public void stopped() {
        Game.pause();
    }
    
   
    public void addObject(Actor object, int x, int y) {
        super.addObject(object, x, y);
        if (object instanceof Entity) {
            SceneManager.getInstance().addEntity((Entity)object);
        }
    }
    
    public void freeze() {
        for(Object character : getObjects(Character.class)) {
            ((Character)character).freeze();
        }
    }
    
   public void unfreeze() {
        for(Object character : getObjects(Character.class)) {
            ((Character)character).unfreeze();
        }
    }    
}
