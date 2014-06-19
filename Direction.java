/**
 * Write a description of class Direction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Direction
{
    WEST(Game.WEST),
    NORTH(Game.NORTH),
    EAST(Game.EAST),
    SOUTH(Game.SOUTH),
    NONE(Game.NONE);
    
    private int value;

    private Direction(int value) {
            this.value = value;
    }
    
    public int getValue() {
        return value;
    }    
    
    public static Direction fromInt(int direction) {
        switch(direction) {
            case Game.WEST:
                return WEST;
            case Game.NORTH:
                return NORTH;
            case Game.EAST:
                return EAST;
            case Game.SOUTH:
                return SOUTH;
            default:
                return NONE;
        }
    }
}
