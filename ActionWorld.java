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
        Game.setActionWorld(this);

        //workaround to remove orphan dialogues
        final javax.swing.JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        for (java.awt.Component p : canvas.getComponents()) {
            canvas.remove(p);
        }
        
        populate();
    }
    
    public void act() {
        super.act();
        Game.update();
    }
    
    public void started() {
        super.started();
    }
    
    public void stopped() {
    }
    
   
    /**
     * Populate the world with a fixed scenario of Knights and Wizards.
     */    
    public void populate()
    {
        
        ScriptManager.invokeMethod("scripts", "bootstrap", "createGame");
        
        /*
        Scene scene = new Scene("village");
        scene.setSoundToPlay("outside");
        Game.addScene(scene);
        
        Wizard w1 = new Wizard("wizard");
        w1.setLocation(300, 150);
        scene.addEntity(w1);
        
        Knight l1 = new Knight("knight");
        l1.setLocation(420, 300);
        scene.addEntity(l1);

        NonPlayerCharacter thief = new NonPlayerCharacter("thief");
        thief.setSprite(3); //initialSprite
        thief.setMoveRadius(0); //radius of movement
        thief.setSouthSprites(3, 5);
        thief.setWestSprites(15, 17);
        thief.setEastSprites(27, 29);
        thief.setNorthSprites(39, 41);
        thief.setLocation(100, 400);
        thief.setCharacterImage("thief.jpg");
        scene.addEntity(thief);
        
        scene.setBackground("bg.png");
        
        Game.setScene(scene);
        Game.setPc(l1);
        
        Scene north = new Scene("north village");
        Game.addScene(north);
        
        north.addEntity(l1);
        
        thief = new NonPlayerCharacter("thief2");
        thief.setSprite(3); //initialSprite
        thief.setMoveRadius(0); //radius of movement
        thief.setSouthSprites(3, 5);
        thief.setWestSprites(15, 17);
        thief.setEastSprites(27, 29);
        thief.setNorthSprites(39, 41);
        thief.setLocation(100, 400);
        thief.setCharacterImage("thief.jpg");

        north.addEntity(thief);
        north.setBackground("north.png");
        scene.setNeighbourScene(Direction.NORTH, north);
        */
    }
    
    public void addObject(Actor object, int x, int y) {
        super.addObject(object, x, y);
        if (object instanceof Entity) {
            Game.addEntity((Entity)object);
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
