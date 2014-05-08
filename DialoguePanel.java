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
public class DialoguePanel
{
    private java.util.List<JComponent> components = new ArrayList<JComponent>();
    private WizardWorld world;
    private Dialogue dialogue;
    private Choice chosen;

    /**
     * Constructor for objects of class Dialog
     */
    public DialoguePanel(WizardWorld world, Dialogue dialogue)
    {
        this.world = world;
        this.dialogue = dialogue;
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
            public void mouseOked(MouseEvent arg0) {
            }
            public void mousePressed(MouseEvent arg0) {
            }
            public void mouseReleased(MouseEvent arg0) {
            }
            });            
            */
                
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JRadioButton answer;
    private javax.swing.ButtonGroup answerGroup;
    private javax.swing.JPanel answers;
    private javax.swing.JPanel controls;
    private javax.swing.JButton ok;
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
        
        answerGroup = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        images = new javax.swing.JPanel();
        npc = new ImagePanel();
        pc = new ImagePanel();
        question = new javax.swing.JPanel();
        questionLabel = new javax.swing.JLabel();
        answers = new javax.swing.JPanel();
        controls = new javax.swing.JPanel();
        ok = new javax.swing.JButton();

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

        answers.setAutoscrolls(true);
        answers.setLayout(new javax.swing.BoxLayout(answers, javax.swing.BoxLayout.PAGE_AXIS));
        
        for(final Choice c : dialogue.getChoices()) {
            answer = new javax.swing.JRadioButton();
            answerGroup.add(answer);
            answer.setText(c.getMessage());
            answer.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chosen = c;
                    //answerActionPerformed(evt);
                    System.out.println(dialogue.getPc().getSpeed());
                    System.out.println(dialogue.getPc().getPower());
                    System.out.println(dialogue.getPc().getHealth());
                }
            });
            answers.add(answer);
        }
        
        panel.add(answers);

        ok.setText("Ok");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlsLayout = new javax.swing.GroupLayout(controls);
        controls.setLayout(controlsLayout);
        controlsLayout.setHorizontalGroup(
            controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        controlsLayout.setVerticalGroup(
            controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel.add(controls);
        
        
        canvas.add(panel);
        canvas.updateUI();

    }
   
    private void okActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        System.out.println("Oking dialogue!"); 
        ScriptManager.invokeFunction(dialogue.getScriptFile(), 
                                     chosen.getAcceptFunction(), 
                                     dialogue.getPc().getName(), 
                                     dialogue.getNpc().getName());
        
        dispose();

    }                                    
   
    private void answerActionPerformed(java.awt.event.ActionEvent evt) {                                        
          // TODO add your handling code here:
        SoundManager.playSound("donkey.mp3");
    }                                       

   
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
