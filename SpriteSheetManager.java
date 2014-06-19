import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;
/**
 * This class manages all the game sprites.
 * 
 * @author Tony Alexander Hild
 * @version 0.1a
 */
public final class SpriteSheetManager  
{
   
    /**
     * Sprites buffer
     */
    private static Map<String, GreenfootImage> map = new HashMap<String, GreenfootImage>();
    
    private SpriteSheetManager(){}

    /**
     * This uses an ID where 0 will be the first image on the sheet.
     * The block width is the width of a SQUARE block on the sheet,
     * the returned image will also be a SQUARE block.
     * @param spriteSheetFile The sprite sheet image file.
     * @param id The sprite id
     * <p>
     * The sprite is returned by the following algorithm. <br />
     * int cols = spriteSheet.getWidth() / blockWidth; <br />
     * int x = (id % cols) * blockWidth;  <br />
     * int y = (id / cols) * blockWidth;
     * </p>
     * @param blockWidth The sprite block width. Ex.: if the block width was 32 the sprite size is 32x32 pixels.
     * @return A GreenfootImage base on the sprite.
     */
    public static GreenfootImage getSprite(String spriteSheetFile, int id, int blockWidth) {
        if(map.containsKey(spriteSheetFile + id)) {
            return map.get(spriteSheetFile + id);
        }
        GreenfootImage spriteSheet = new GreenfootImage(spriteSheetFile);
        BufferedImage spriteSheetB = spriteSheet.getAwtImage();
        
        int cols = spriteSheet.getWidth() / blockWidth;
        int x = (id % cols) * blockWidth;
        int y = (id / cols) * blockWidth;

        GreenfootImage img = new GreenfootImage(blockWidth, blockWidth);
        
        BufferedImage imgB = img.getAwtImage();
        
        //http://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#drawImage%28java.awt.Image,%20int,%20int,%20int,%20int,%20int,%20int,%20int,%20int,%20java.awt.image.ImageObserver%29
        imgB.getGraphics().drawImage(spriteSheetB, 0, 0, blockWidth, blockWidth,
                x, y, x + blockWidth, y + blockWidth, null);
        map.put(spriteSheetFile + id, img);
        return img;
    }

}
