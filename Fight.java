import java.util.*;
import greenfoot.core.WorldHandler;  
import greenfoot.core.Simulation;  
import java.awt.*;
import javax.swing.*;  
import java.awt.event.*;
import greenfoot.*;

/**
 * Write a description of class Dialog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fight
{
    private java.util.List<JComponent> components = new ArrayList<JComponent>();

    /**
     * Constructor for objects of class Dialog
     */
    public Fight()
    {
        initComponents();
        
        npc.setImage("./images/wizard.jpg");
        pc.setImage("./images/knight.jpg");
        
        /*
            final JPanel panel = WorldHandler.getInstance().getWorldCanvas();  
            //panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


            final JLabel lab1 = new JLabel("Label", JLabel.LEFT);
            //panel.setLayout(new FlowLayout());
            panel.add(lab1);
            lab1.setVisible(true);
            panel.updateUI();
            components.add(lab1);
            lab1.addMouseListener(new MouseListener()
            {
            public void mouseClicked(MouseEvent arg0) {
                panel.remove(lab1);
                panel.updateUI();
            }
            public void mouseEntered(MouseEvent arg0) {
            }
            public void mouseExited(MouseEvent arg0) {
            }
            public void mousePressed(MouseEvent arg0) {
            }
            public void mouseReleased(MouseEvent arg0) {
            }
            });            
            */
                
    }
    
     // Variables declaration - do not modify                     
    private javax.swing.JPanel answer;
    private javax.swing.JLabel answer1;
    private javax.swing.JLabel answer2;
    private javax.swing.JLabel answer3;
    private javax.swing.JPanel images;
    private ImagePanel npc;
    private javax.swing.JPanel panel;
    private ImagePanel pc;
    private javax.swing.JPanel question;
    private javax.swing.JLabel questionLabel;
    // End of variables declaration             
    private LayoutManager layoutManager;
    
    private void initComponents() {
        final JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        layoutManager = canvas.getLayout();
        canvas.setLayout(new javax.swing.BoxLayout(canvas, javax.swing.BoxLayout.LINE_AXIS));
        
       panel = new javax.swing.JPanel();
        images = new javax.swing.JPanel();
        npc = new ImagePanel();
        pc = new ImagePanel();
        question = new javax.swing.JPanel();
        questionLabel = new javax.swing.JLabel();
        answer = new javax.swing.JPanel();
        answer1 = new javax.swing.JLabel();
        answer2 = new javax.swing.JLabel();
        answer3 = new javax.swing.JLabel();


        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new java.awt.GridLayout(3, 1));

        images.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout npcLayout = new javax.swing.GroupLayout(npc);
        npc.setLayout(npcLayout);
        npcLayout.setHorizontalGroup(
            npcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        npcLayout.setVerticalGroup(
            npcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );

        images.add(npc);

        javax.swing.GroupLayout pcLayout = new javax.swing.GroupLayout(pc);
        pc.setLayout(pcLayout);
        pcLayout.setHorizontalGroup(
            pcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pcLayout.setVerticalGroup(
            pcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );

        images.add(pc);

        panel.add(images);

        question.setAutoscrolls(true);
        question.setLayout(new javax.swing.BoxLayout(question, javax.swing.BoxLayout.LINE_AXIS));

        questionLabel.setText("What's the capital of Turkey?");
        question.add(questionLabel);

        panel.add(question);

        answer.setAutoscrolls(true);
        answer.setLayout(new javax.swing.BoxLayout(answer, javax.swing.BoxLayout.PAGE_AXIS));

        answer1.setText("Ankara");
        answer1.setName("answer1");
        answer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerMouseClicked(evt);
            }
        });
        answer.add(answer1);

        answer2.setText("Instanbul");
        answer2.setName("answer2");
        answer2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerMouseClicked(evt);
            }
        });
        answer.add(answer2);

        answer3.setText("Izmir");
        answer3.setName("answer3");
        answer3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerMouseClicked(evt);
            }
        });        
        answer.add(answer3);

        panel.add(answer);
        
        canvas.add(panel);
        canvas.updateUI();

    }
   

     private void answerMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        System.out.println(evt.getComponent().getName() + " clicked!"); 
    }    
    
    
    /*
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        dispose();
        Greenfoot.setWorld(world);
        Simulation.getInstance().setEnabled(false);

    }   
    */
   
    public void dispose() {
        final JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        canvas.remove(panel);
        canvas.setLayout(layoutManager);
        canvas.updateUI();
    }

}
