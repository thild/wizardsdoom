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
public class Dialogue
{
    private java.util.List<JComponent> components = new ArrayList<JComponent>();
    private WizardWorld world;

    /**
     * Constructor for objects of class Dialog
     */
    public Dialogue(WizardWorld world)
    {
        this.world = world;
        initComponents();
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
    private javax.swing.JLabel answer1;
    private javax.swing.JLabel answer2;
    private javax.swing.JLabel answer3;
    private javax.swing.JPanel answers;
    private javax.swing.JButton exit;
    private javax.swing.JPanel npc;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pc;
    private javax.swing.JPanel pictures;
    private javax.swing.JPanel question;
    private javax.swing.JLabel questionText;
    // End of variables declaration             
    private LayoutManager layoutManager;
    
    private void initComponents() {
        final JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        layoutManager = canvas.getLayout();
        canvas.setLayout(new javax.swing.BoxLayout(canvas, javax.swing.BoxLayout.LINE_AXIS));
        
        panel = new javax.swing.JPanel();
        
        question = new javax.swing.JPanel();
        pictures = new javax.swing.JPanel();
        pc = new javax.swing.JPanel();
        npc = new javax.swing.JPanel();
        questionText = new javax.swing.JLabel();
        answers = new javax.swing.JPanel();
        answer1 = new javax.swing.JLabel();
        answer2 = new javax.swing.JLabel();
        answer3 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();

        panel.setLayout(new java.awt.GridLayout(3, 1));

        question.setLayout(new java.awt.GridLayout(2, 1));

        pictures.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout pcLayout = new javax.swing.GroupLayout(pc);
        pc.setLayout(pcLayout);
        pcLayout.setHorizontalGroup(
            pcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        pcLayout.setVerticalGroup(
            pcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        pictures.add(pc);

        javax.swing.GroupLayout npcLayout = new javax.swing.GroupLayout(npc);
        npc.setLayout(npcLayout);
        npcLayout.setHorizontalGroup(
            npcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        npcLayout.setVerticalGroup(
            npcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        pictures.add(npc);

        question.add(pictures);

        questionText.setText("jLabel1");
        question.add(questionText);

        panel.add(question);

        answers.setLayout(new javax.swing.BoxLayout(answers, javax.swing.BoxLayout.Y_AXIS));

        answer1.setText("jLabel1");
        answer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Answer choosed.");
            }
        });
        answers.add(answer1);

        answer2.setText("jLabel2");
        answers.add(answer2);

        answer3.setText("jLabel3");
        answers.add(answer3);

        panel.add(answers);

        exit.setText("Exit");
        /*
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });*/
        panel.add(exit);
        
        canvas.add(panel);
        canvas.updateUI();

    }
    
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        dispose();
        Greenfoot.setWorld(world);
        Simulation.getInstance().setEnabled(false);

    }   
    
    public void dispose() {
        final JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        canvas.remove(panel);
        canvas.setLayout(layoutManager);
        canvas.updateUI();
    }

}
