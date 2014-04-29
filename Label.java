import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)  
import java.awt.*;
/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    //private GreenfootImage button_1 = new GreenfootImage("image_button_no_pressed.png");    
    //private GreenfootImage button_2 = new GreenfootImage("image_button_pressed.png");    
    
    private boolean mouseDown = false;
    private boolean mouseOver;    
    private String text = "Label";
        
    public Label() {
        draw(text, 20, Color.BLACK, new Color(0, 0, 0, 0));
    }
    
    public Label(String text)  
    {  
        this.text = text;
        draw(text, 20, Color.BLACK, new Color(0, 0, 0, 0));
    }  
    
    private void draw(String text, int fontsize, Color fontColor, Color bgColor) {
        // create the text image  
        GreenfootImage txtImg = new GreenfootImage(text, fontsize, fontColor, bgColor);  
        // create the base image  
        GreenfootImage img = new GreenfootImage(txtImg.getWidth()+20, txtImg.getHeight()+10);  
        img.setColor(bgColor);  
        img.fill();  
        // draw text image on base image  
        img.drawImage(txtImg, 10, 5);  
        setImage(img);  
    }
    
    public void act() {
        if (!mouseDown && Greenfoot.mousePressed(this)) {      
            //setImage(button_2);   
            mouseDown = true; // here  
        }      
        if (mouseDown && Greenfoot.mouseClicked(this)) {    
            //setImage(button_1);    
            mouseDown = false; // and here  
            //add also the methods you want to execute here;    
        }  
        if (!mouseOver && Greenfoot.mouseMoved(this))  
        {  
            //setImage("Start/2.png");  
            mouseOver = true;
            draw(text, 20, Color.RED, new Color(0, 0, 0, 0));
        }  
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this))  
        {  
            //setImage("Start/1.png");  
            mouseOver = false;  
            draw(text, 20, Color.BLACK, new Color(0, 0, 0, 0));
        }                
    }     
    
    public void setText(String text)  
    {  
        this.text = text;
        draw(text, 20, Color.BLACK, new Color(0, 0, 0, 0));
    }      
}
