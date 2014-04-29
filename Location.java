/**
 * Write a description of class Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Location  
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;

    /**
     * Constructor for objects of class Location
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
