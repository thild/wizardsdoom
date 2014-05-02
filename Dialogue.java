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
    private javax.swing.JRadioButton answer4;
    private javax.swing.JRadioButton answer5;
    private javax.swing.JPanel controls;
    private javax.swing.JButton exit;
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
        answer4 = new javax.swing.JRadioButton();
        answer5 = new javax.swing.JRadioButton();
        controls = new javax.swing.JPanel();
        exit = new javax.swing.JButton();


        panel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panelKeyPressed(evt);
            }
        });
        panel.setLayout(new java.awt.GridLayout(4, 1));

        images.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout npcLayout = new javax.swing.GroupLayout(npc);
        npc.setLayout(npcLayout);
        npcLayout.setHorizontalGroup(
            npcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        npcLayout.setVerticalGroup(
            npcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
        );

        images.add(npc);

        javax.swing.GroupLayout pcLayout = new javax.swing.GroupLayout(pc);
        pc.setLayout(pcLayout);
        pcLayout.setHorizontalGroup(
            pcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        pcLayout.setVerticalGroup(
            pcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
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
        answer1.setName("answer1"); // NOI18N
        answer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerMouseClicked(evt);
            }
        });
        answer.add(answer1);

        answer2.setText("Instanbul");
        answer2.setName("answer2"); // NOI18N
        answer2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerMouseClicked(evt);
            }
        });
        answer.add(answer2);

        answer3.setText("Izmir");
        answer3.setName("answer3"); // NOI18N
        answer3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerMouseClicked(evt);
            }
        });
        answer.add(answer3);

        answer4.setText("Ahgof");
        answer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answer4ActionPerformed(evt);
            }
        });
        answer.add(answer4);

        answer5.setText("Mordor");
        answer5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answer5ActionPerformed(evt);
            }
        });
        answer.add(answer5);

        panel.add(answer);

        exit.setText("exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlsLayout = new javax.swing.GroupLayout(controls);
        controls.setLayout(controlsLayout);
        controlsLayout.setHorizontalGroup(
            controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        controlsLayout.setVerticalGroup(
            controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel.add(controls);
        
        
        canvas.add(panel);
        canvas.updateUI();

    }
   
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        System.out.println("Exiting dialogue!"); 
        dispose();

    }                                    

   
    private void answer4ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void answer5ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }      

     private void answerMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        if (evt.getComponent().getName() != "answer1") {
            SoundManager.playSound("donkey.mp3");
        }
        System.out.println(evt.getComponent().getName() + " clicked!"); 
    }    
    
    private void panelKeyPressed(java.awt.event.KeyEvent evt) {                                 
        // TODO add your handling code here:
        System.out.println(evt.getKeyChar()); 
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
        canvas.requestFocus();
        SoundManager.stopSound("dialogue.mp3");
        Greenfoot.setWorld(world);
        SoundManager.playSound("outside.mp3", true);        
    }

}
