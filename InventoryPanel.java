import java.util.*;
import greenfoot.core.WorldHandler;  
import greenfoot.core.Simulation;  
import java.awt.*;
import javax.swing.*;  
import java.awt.event.*;
import greenfoot.*;

/**
 * Write a description of class InventoryPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InventoryPanel  
{
    
    private InventoryItem inventoryItem;

    /**
     * Creates new form Canvas2
     */
    public InventoryPanel() {
        initComponents();
    }

    private void initComponents() {
        
        final JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        if(panel != null) {
            panel.removeAll();
            canvas.remove(panel);
        }
        layoutManager = canvas.getLayout();
        canvas.setLayout(new javax.swing.BoxLayout(canvas, javax.swing.BoxLayout.LINE_AXIS));
        

        itemsGroup = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        infoPanel = new javax.swing.JPanel();
        pcPanel = new ImagePanel();
        statsPanel = new JPanel();
        itemsPanel = new javax.swing.JPanel();
        itemButton = new javax.swing.JRadioButton();
        controlsPanel = new javax.swing.JPanel();
        useButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();


        panel.setLayout(new java.awt.GridLayout(3, 1));

        infoPanel.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout pcPanelLayout = new javax.swing.GroupLayout(pcPanel);
        pcPanel.setLayout(pcPanelLayout);
        pcPanelLayout.setHorizontalGroup(
            pcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        pcPanelLayout.setVerticalGroup(
            pcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        infoPanel.add(pcPanel);

        statsPanel.setAutoscrolls(true);

        javax.swing.GroupLayout statsPanelLayout = new javax.swing.GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        infoPanel.add(statsPanel);

        panel.add(infoPanel);

        itemsPanel.setAutoscrolls(true);
        itemsPanel.setLayout(new javax.swing.BoxLayout(itemsPanel, javax.swing.BoxLayout.PAGE_AXIS));

        itemsGroup.add(itemButton);
        itemButton.setText("Ahgof");
        
        
        for(final InventoryItem item : Game.getInventory().getItems()) {
            itemButton = new javax.swing.JRadioButton();
            itemsGroup.add(itemButton);
            itemButton.setText(item.getDescription());
            itemButton.addActionListener(new java.awt.event.ActionListener() {
                int i = 0;
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    inventoryItem = item;
                }
            });
            itemsPanel.add(itemButton);
        }

        panel.add(itemsPanel);

        controlsPanel.setLayout(new javax.swing.BoxLayout(controlsPanel, javax.swing.BoxLayout.LINE_AXIS));

        useButton.setText("use");
        useButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useButtonActionPerformed(evt);
            }
        });
        controlsPanel.add(useButton);

        exitButton.setText("exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        controlsPanel.add(exitButton);

        panel.add(controlsPanel);

          canvas.add(panel);
        canvas.updateUI();

    }// </editor-fold>                        

    private void useButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        Game.getInventory().use(inventoryItem);
    }                                         

    private void itemButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
    }                                          

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        dispose();
    }                                          

    // Variables declaration - do not modify                     
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JRadioButton itemButton;
    private javax.swing.ButtonGroup itemsGroup;
    private javax.swing.JPanel itemsPanel;
    private javax.swing.JPanel panel;
    private ImagePanel pcPanel;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JButton useButton;
    // End of variables declaration       
    
    private LayoutManager layoutManager;
    
   public void dispose() {
        final JPanel canvas = WorldHandler.getInstance().getWorldCanvas();
        canvas.remove(panel);
        canvas.setLayout(layoutManager);
        canvas.updateUI();
        canvas.requestFocus();
        SceneManager.getInstance().resumeToActionWorld();
    }
    
}
