import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)  
import java.awt.*;
import java.awt.image.*; 
import java.text.*;
import java.awt.font.*;
import java.util.*;
import java.awt.geom.*;

/**
 * Simple speech bubble notification.
 * TODO: draw the speech bubble base on font metrics.
 * See http://stackoverflow.com/questions/1524855/how-to-calculate-the-fonts-width
 * TODO: Resize the speech bubble based on text width and height (set max values for each).
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpeechBubble extends Actor
{
    private static final int PADDING = 8;
    private static final int HALF_PADDING = PADDING / 2;
    private static final int TEXT_PADDING = PADDING + 4;
    private static final int TRIANGLE_X = 20;
    private static final int TRIANGLE_HEIGHT = 30;
    private static final int TRIANGLE_WIDTH = 10;
    private static final int HALF_TRIANGLE_WIDTH = TRIANGLE_WIDTH / 2;
    private String text;
    private boolean tbEdge = false;
    private boolean weEdge = false;
    private BufferedImage image;
    
    private Character character;
    
    private static final
        Hashtable<TextAttribute, Object> map =
           new Hashtable<TextAttribute, Object>();
 
    static {
        map.put(TextAttribute.FAMILY, "DejaVu Sans");
        map.put(TextAttribute.SIZE, new Float(12.0));
    } 
 
    /**
     * Constructor for objects of class SpeechBubble
     */
    public SpeechBubble(Character character)  
    {  
        this(character, "");
    }
   
    /**
     * Constructor for objects of class SpeechBubble
     */
    public SpeechBubble(Character character, String text)  
    {  
        this.character = character;
        this.text = text;
        GreenfootImage gi = new greenfoot.GreenfootImage(200, 100);
        image = gi.getAwtImage();
        setImage(gi);  
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void paint() {
        this.text = text;
        AttributedString aString = new AttributedString(text, map);
        
        //setBackground(Color.white);
        Graphics g = image.getGraphics();
        
        Graphics2D g2d = (Graphics2D)g;
        

        g2d.setComposite(AlphaComposite.Src);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        
        
        g2d.setColor(new Color(255,255,255,0));
        g2d.fillRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
        
        g2d.setColor(Color.YELLOW);
        g2d.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
        
        //first draw the speech bubble
        
        // Save the graphics object color and font so that we may restore
        // it later
        Color origColor = g2d.getColor();
        Font origFont = g2d.getFont();

        int width = image.getWidth() - PADDING - 1;
        int height = image.getHeight() - TRIANGLE_HEIGHT - PADDING - 1;
        
        int x = HALF_PADDING;
        int y = HALF_PADDING;
        
        if (tbEdge) {
            y = HALF_PADDING + TRIANGLE_HEIGHT;
        }
            
        
        // Draw the base shape -- the rectangle the image will fit into as well as its outline
        
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(x, y, 
                          width, 
                          height, 
                          PADDING, PADDING);
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(x, y, 
                          width, 
                          height, 
                          PADDING, PADDING);
        //drawTriangle(g2d, height);
        
        //draw the speech bubble triangle
        x = relativeX();
        int triangleTop = height + HALF_PADDING;
        int triangleBottom = triangleTop + TRIANGLE_HEIGHT + HALF_PADDING;
        
        if (tbEdge) {
            triangleTop = y + 1;
            triangleBottom = 0;
        }
        
        g2d.setColor(Color.GREEN);
        
        g2d.fillPolygon(new int[] {x - HALF_TRIANGLE_WIDTH, 
                                   x + HALF_TRIANGLE_WIDTH, 
                                   x}, 
                        new int[] {triangleTop, 
                                   triangleTop, 
                                   triangleBottom}, 3);
        g2d.setColor(Color.BLACK);

        
        g2d.drawLine(x - HALF_TRIANGLE_WIDTH, tbEdge ? triangleTop - 1 : triangleTop, 
                     x, triangleBottom);
        g2d.drawLine(x + HALF_TRIANGLE_WIDTH, tbEdge ? triangleTop - 1 : triangleTop, 
                     x, triangleBottom);

       
        g2d.setColor(Color.BLACK);       

        // The LineBreakMeasurer used to line-break the paragraph.
        LineBreakMeasurer lineMeasurer = null;
     
        // index of the first character in the paragraph.
        int paragraphStart = 0;
     
        // index of the first character after the end of the paragraph.
        int paragraphEnd = 0;
        
        // Create a new LineBreakMeasurer from the paragraph.
        // It will be cached and re-used.
        if (lineMeasurer == null) {
            AttributedCharacterIterator paragraph = aString.getIterator();
            paragraphStart = paragraph.getBeginIndex();
            paragraphEnd = paragraph.getEndIndex();
            FontRenderContext frc = g2d.getFontRenderContext();
            lineMeasurer = new LineBreakMeasurer(paragraph, frc);
        }
 
        // Set break width to width of Component.
        float breakWidth = (float)width - TEXT_PADDING;
        float drawPosY = TEXT_PADDING;
        if (tbEdge) {
            drawPosY = TEXT_PADDING + TRIANGLE_HEIGHT;
        }
        // Set position to the index of the first character in the paragraph.
        lineMeasurer.setPosition(paragraphStart);
 
        // Get lines until the entire paragraph has been displayed.
        while (lineMeasurer.getPosition() < paragraphEnd) {
 
            // Retrieve next layout. A cleverer program would also cache
            // these layouts until the component is re-sized.
            TextLayout layout = lineMeasurer.nextLayout(breakWidth);
 
            // Compute pen x position. If the paragraph is right-to-left we
            // will align the TextLayouts to the right edge of the panel.
            // Note: this won't occur for the English text in this sample.
            // Note: drawPosX is always where the LEFT of the text is placed.
            float drawPosX = layout.isLeftToRight()
                ? TEXT_PADDING : breakWidth - layout.getAdvance();
 
            // Move y-coordinate by the ascent of the layout.
            drawPosY += layout.getAscent();
 
            // Draw the TextLayout at (drawPosX, drawPosY).
            layout.draw(g2d, drawPosX, drawPosY);
 
            // Move y-coordinate in preparation for next layout.
            drawPosY += layout.getDescent() + layout.getLeading();
        }
        
        // Restore the font and color
        g.setColor(origColor);
        g.setFont(origFont);        
    }    

    private void drawTriangle() {
        drawTriangle((Graphics2D)image.getGraphics(), image.getHeight() - TRIANGLE_HEIGHT);
        
    }
    
    private void drawTriangle(Graphics2D g2d, int height)
    {
        // Next is to draw the pointy part that indicates who is speaking
        g2d.setColor(Color.GREEN);
        
        int x = relativeX();
       
        //draw the speech bubble triangle
        g2d.fillPolygon(new int[] {x - TRIANGLE_WIDTH / 2, 
                                   x + TRIANGLE_WIDTH / 2, 
                                   x}, 
                        new int[] {height - HALF_PADDING - 1, 
                                   height - HALF_PADDING - 1, 
                                   height + TRIANGLE_HEIGHT}, 3);
        g2d.setColor(Color.BLACK);
        
        
        g2d.drawLine(x - TRIANGLE_WIDTH / 2, height - HALF_PADDING - 1, 
                     x, height + TRIANGLE_HEIGHT);
        g2d.drawLine(x + - TRIANGLE_WIDTH / 2, height - HALF_PADDING - 1, 
                     x, height + TRIANGLE_HEIGHT);

       
        g2d.setColor(Color.BLACK);       
    }
    
    /*
    
    private BufferedImage paint() {
        Graphics2D g = ...;
        Point2D loc = ...;
        Font font = Font.getFont("Helvetica-bold-italic");
        FontRenderContext frc = g.getFontRenderContext();
        TextLayout layout = new TextLayout("This is a string", font, frc);
        layout.draw(g, (float)loc.getX(), (float)loc.getY());
        
        Rectangle2D bounds = layout.getBounds();
        bounds.setRect(bounds.getX()+loc.getX(),
                      bounds.getY()+loc.getY(),
                      bounds.getWidth(),
                      bounds.getHeight());
        g.draw(bounds);    
    }*/
    
    private int relativeX() {
        if(getWorld() == null) return 0;
        int sbHalfWidth = this.getImage().getWidth() / 2;
        int charHalfWidth = character.getImage().getWidth() / 2;
        
        int sbX = this.getX() - sbHalfWidth;
        int charX = character.getX();

        int width = this.getImage().getWidth();
        
        int x = charX - sbX;
        if(x <= PADDING)  {
            x = PADDING + 1;
        }
        else if (x >= width - PADDING - 1)
        {
            x = width - PADDING - 1;
        }
        return x;
    }
    
    public void act() {
        if(getWorld() == null) return;
        if (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("escape")) {    
            character.stopSpeak();  
        }  
        
        int sbHeight = this.getImage().getHeight();
        int charHeight = character.getImage().getHeight();
        
        int sbWidth = this.getImage().getWidth();
        int charWidth = character.getImage().getWidth();
        
        int sbHalfHeight = sbHeight / 2;
        int charHalfHeight = charHeight / 2;

        int sbHalfWidth = sbWidth / 2;
        int charHalfWidth = charWidth / 2;
        
        int sceneWidth = Game.getScene().getWidth();
        int sceneHeight = Game.getScene().getWidth();

        int xOffset = sbHalfWidth - charHalfWidth;
        int yOffset = sbHalfHeight + charHalfHeight;

        int x = 0;
        int y = 0;
        /*
        if (character.getY() <= sbHalfHeight + charHalfHeight + 30 &&
            sceneWidth - character.getX() - charHalfWidth - 1 <= sbHalfWidth + charHalfWidth + 40) {
            x = character.getX() - 110;
            y = 41;
            setLocation(x, y);
            if(!onEdge) {
                onEdge = true;
                paint();
            }
            
        }
        
        else if(character.getY() <= sbHalfHeight + charHalfHeight + 30) {
            x = character.getX() + 105;
            y = 41;
            setLocation(x, y);
            onEdge = false;
        }
        else if(sceneWidth - character.getX() - charHalfWidth - 1 <= sbHalfWidth + charHalfWidth + 40) {
            x = character.getX() - 95;
            y = character.getY() - 60;
            setLocation(x, y);
            if(!onEdge) {
                onEdge = true;
                paint();
            }
        }
        */
        if (character.getY() <= sbHeight && 
            (character.getX() <= sbHalfWidth || 
             character.getX() >= sceneWidth - sbHalfWidth -1)) {
            if (this.getX() + sbHalfWidth >= sceneWidth - 1) {
                x = sceneWidth - sbHalfWidth;
            }
            else if (this.getX() - sbHalfWidth < 0) {
                x = sbHalfWidth;
            }
            else {
                x = getX();
            }
            y = character.getY() + yOffset;
            setLocation(x, y);
            tbEdge = true;
            weEdge = true;
            paint();
        }
        else if (character.getY() <= sbHeight) {
            x = character.getX();
            y = character.getY() + yOffset;
            setLocation(x, y);
            tbEdge = true;
            weEdge = false;
            paint();
            
        }
        else if(character.getX() <= sbHalfWidth || 
                character.getX() >= sceneWidth - sbHalfWidth -1) {
            if (this.getX() + sbHalfWidth >= sceneWidth - 1) {
                x = sceneWidth - sbHalfWidth;
            }
            else if (this.getX() - sbHalfWidth < 0) {
                x = sbHalfWidth;
            }
            else {
                x = getX();
            }
            y = character.getY() - yOffset;
            setLocation(x, y);
            weEdge = true;
            tbEdge = false;
            paint();
        }
        else {
            x = character.getX();
            y = character.getY() - yOffset;
            setLocation(x, y);
            tbEdge = false;
            weEdge = false;
            paint();
        }
        
    }
        
}
