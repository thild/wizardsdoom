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
    private Dialogue dialogue;
    private Choice choice;
    private boolean closing = false;
    /**
     * Constructor for objects of class Dialog
     */
    public DialoguePanel(Dialogue dialogue)
    {
        this.dialogue = dialogue;
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
    private ImagePanel interlocutor;
    private javax.swing.JPanel panel;
    private ImagePanel pc;
    private javax.swing.JPanel question;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JLabel feedbackLabel;
    // End of variables declaration      
    private LayoutManager layoutManager;
    
    private void initComponents() {
        final JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        if(panel != null) {
            panel.removeAll();
            canvas.remove(panel);
        }
        layoutManager = canvas.getLayout();
        canvas.setLayout(new javax.swing.BoxLayout(canvas, javax.swing.BoxLayout.LINE_AXIS));
        
        answerGroup = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        images = new javax.swing.JPanel();
        interlocutor = new ImagePanel();
        pc = new ImagePanel();
        question = new javax.swing.JPanel();
        questionLabel = new javax.swing.JLabel();
        feedbackLabel = new javax.swing.JLabel();
        answers = new javax.swing.JPanel() {
            @Override
            public void setEnabled(boolean enabled) {
                super.setEnabled(enabled);
                for (Component component : getComponents()) {
                    component.setEnabled(enabled);
                }
            }
        };
        controls = new javax.swing.JPanel();
        ok = new javax.swing.JButton();

        panel.setLayout(new java.awt.GridLayout(4, 1));

        images.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout interlocutorLayout = new javax.swing.GroupLayout(interlocutor);
        interlocutor.setLayout(interlocutorLayout);
        interlocutorLayout.setHorizontalGroup(
            interlocutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        interlocutorLayout.setVerticalGroup(
            interlocutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
        );

        images.add(interlocutor);

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

        interlocutor.setImage("./images/" + dialogue.getInterlocutor().getCharacterImage());
        pc.setImage("./images/" + dialogue.getPlayer().getCharacterImage());
        
        images.add(pc);

        panel.add(images);

        question.setAutoscrolls(true);
        question.setLayout(new javax.swing.BoxLayout(question, javax.swing.BoxLayout.PAGE_AXIS));

        questionLabel.setText(dialogue.getMessage());
        question.add(questionLabel);

        feedbackLabel.setText(dialogue.getFeedbackMessage());
        question.add(feedbackLabel);

        panel.add(question);

        answers.setAutoscrolls(true);
        answers.setLayout(new javax.swing.BoxLayout(answers, javax.swing.BoxLayout.PAGE_AXIS));
        
        for(final Choice c : dialogue.getChoices()) {
            answer = new javax.swing.JRadioButton();
            answerGroup.add(answer);
            answer.setText(c.getMessage());
            answer.addActionListener(new java.awt.event.ActionListener() {
                int i = 0;
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    choice = c;
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
        //if (closing) {
        //    dispose();
        //    return;
        //}
        // TODO add your handling code here:
        ScriptManager.invokeMethod("scripts", 
                                       dialogue.getInterlocutor().getName(), 
                                       "evaluateDialogue",
                                       dialogue, choice);

        //if(dialogue.isClosed() || choice instanceof ExitChoice) {
        //    closing = true;
        //    answers.setEnabled(false);
        //}
    }                                    
   
    private void answerActionPerformed(java.awt.event.ActionEvent evt) {                                        
          // TODO add your handling code here:
        //SoundManager.playSound("donkey");
    }                                       

    public void updateDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
        initComponents();
    }
    

    public void updateDialogueMessages() {    
        questionLabel.setText(dialogue.getMessage());
        feedbackLabel.setText(dialogue.getFeedbackMessage());
        //if(disableChoices) {
        //    answers.setEnabled(false);
        //}
    }
   

    public void enableDialogueChoices(boolean enabled) {
       answers.setEnabled(enabled);
    }
    
    
    public void dispose() {
        final JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        canvas.remove(panel);
        canvas.setLayout(layoutManager);
        canvas.updateUI();
        canvas.requestFocus();
        Game.resumeToActionWorld();
    }

}
