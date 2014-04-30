/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image == null) {
            g.drawString("Image not found.", 20, 10);
        }
        else {
            g.drawImage(image, 0, 0, null);
        }
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(String file) {
       try {                
          image = ImageIO.read(new File(file));
       } catch (IOException ex) {
          System.out.println(ex.getMessage());
          image = null;
       }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.updateUI();
    }

}