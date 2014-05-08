import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.core.WorldHandler; 
 
/**
 * Write a description of class WizardWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WizardWorld extends World
{
    //http://gamedevelopment.tutsplus.com/articles/how-to-build-a-jrpg-a-primer-for-game-developers--gamedev-6676
    private GreenfootSound music;
    
    /**
     * Constructor for objects of class WizardWorld.
     * 
     */
    public WizardWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(672, 525, 1);
        setBackground("bg.png");

        //workaround to remove orphan dialogues
        final javax.swing.JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        for (java.awt.Component p : canvas.getComponents()) {
            canvas.remove(p);
        }
        
        populate();
        
        /*
        Dialogue d0002 = new Dialogue();
        d0002.setId("0002");
        d0002.setMessage("What's is the capital of Turkey");
        //d0002.setPc("knight");
        //d0002.setNpc("wizard");
        Choice c = new Choice();
        c.setMessage("Teste");
        c.setConditionFunction("choice1Condition");
        c.setAcceptFunction("choice1Accept");
        d0002.getChoices().add(c); 
        
        Dialogue.writeDialogue(d0002);
        d0002 = Dialogue.readDialogue("0002");
        System.out.println(d0002.getMessage());
        */
    }
    
    public void started() {
        super.started();
        SoundManager.playSound("outside.mp3", true);
    }
    
    public void stopped() {
        SoundManager.stopSound("outside.mp3");
    }
    
   
    /**
     * Populate the world with a fixed scenario of Knights and Wizards.
     */    
    public void populate()
    {
        Wizard w1 = new Wizard();
        addObject(w1, 300, 150);
        
        Wizard w2 = new Wizard();
        addObject(w2, 100, 70);

        Knight l1 = new Knight();
        addObject(l1, 420, 300);

        /*
        Knight l2 = new Knight();
        addObject(l2, 100, 250);

        Knight l3 = new Knight();
        addObject(l3, 70, 490);

        Knight l4 = new Knight();
        addObject(l4, 230, 60);

        Knight l5 = new Knight();
        addObject(l5, 50, 180);
        
        Knight l6 = new Knight();
        addObject(l6, 400, 90);
        */
    }
    
    public void act() {
         if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("enter")) {
             unfreeze();
             removeObjects(getObjects(SpeechBubble.class));
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
