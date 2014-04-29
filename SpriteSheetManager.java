import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;
/**
 * Write a description of class SpriteManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public final class SpriteSheetManager  
{
   
    private static Map<String, GreenfootImage> map = new HashMap<String, GreenfootImage>();
    
    /**
     * This uses an ID where 0 will be the first image on the sheet.
     * The block width is the width of a SQUARE block on the sheet,
     * the returned image will also be a SQUARE block.
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
