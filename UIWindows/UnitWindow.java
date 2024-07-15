package UIWindows;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import MainClass.DefenseSystem;
import enums.GreenUnitType;
import enums.JustifiedLabelAlignment;
import support.BackgroundAdder;
import support.DefenseLabel;
import support.ImageButton;
import support.JustifiedLabel;
import support.SuperDefense;

public class UnitWindow extends SuperDefense {
    
    private DefenseLabel messageFromLabel = new DefenseLabel("Message From", 14); 
    private JustifiedLabel incomingMessage = new JustifiedLabel("initial Message",350,JustifiedLabelAlignment.Top_Left);
    private ImageButton sendMessageButton;
    private JPanel buttonPanelEnemyDetails1 = new JPanel();
    private ImageButton missileOperationButton;
    private ImageButton stopHereButton;
    private JCheckBox areaClearedCheckBox;
    private boolean areaClearedBool;




    public UnitWindow(GreenUnitType unitType , String unitNumber){
        setTitle(unitType.toString() + unitNumber);
        BackgroundAdder.addBackground(this, new ImageIcon("./images/Unit/unitBack.png"),766,546);
        JFrame controller = MainController.getMainController();
        setLocationRelativeTo(controller);
        setVisible(true);

        initComponents();
    }


    private void initComponents(){
        this.setLayout(null);

        this.add(messageFromLabel);
        messageFromLabel.setBounds(28, 22, 230, 13);
        
        this.add(incomingMessage);
        incomingMessage.setBounds(44, 57, 355, 20);
        incomingMessage.setForeground(DefenseSystem.PrimaryfontColor);
        
        sendMessageButton = new ImageButton("./images/Unit/SendMessageButton.png");
        this.add(sendMessageButton);
        sendMessageButton.setBounds(34, 175, 359, 25);
        sendMessageButton.addActionListener((e)->sendMessage());


        add(buttonPanelEnemyDetails1);
        buttonPanelEnemyDetails1.setBounds(21,372,400,30);
        buttonPanelEnemyDetails1.setOpaque(false);
        buttonPanelEnemyDetails1.setLayout(new FlowLayout(FlowLayout.LEFT ,13,0));
        
       ImageButton shootButton = new ImageButton("./images/Unit/shootButton.png", 0);
       shootButton.addActionListener((e)->shoot());
       buttonPanelEnemyDetails1.add(shootButton);
       
       ImageButton followButton = new ImageButton("./images/Unit/followButton.png", 0);
       followButton.addActionListener((e)->follow());
       buttonPanelEnemyDetails1.add(followButton);

        
        missileOperationButton = new ImageButton("./images/Unit/missileButton.png",0);
        add(missileOperationButton);
        missileOperationButton.setBounds(37,405,359,30);
        missileOperationButton.addActionListener((e)->missileOperation());

        stopHereButton = new ImageButton("./images/Unit/stopHere.png",0);
        add(stopHereButton);
        stopHereButton.setBounds(20,465,195,25);
        stopHereButton.addActionListener((e)->stopHere());
        
        
        areaClearedCheckBox = new JCheckBox("                                              ", null, areaClearedBool);
        areaClearedCheckBox.addActionListener((e)->{System.out.println("area cleared :" + areaClearedBool);});
        add(areaClearedCheckBox);
        areaClearedCheckBox.setBorder(null);
        areaClearedCheckBox.setFocusPainted(false);
        areaClearedCheckBox.setBorderPainted(false);
        areaClearedCheckBox.setBounds(227,467,70,25);
        areaClearedCheckBox.setOpaque(false);

        




    }


    private Object follow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'follow'");
    }


    private Object shoot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
    }


    private Object missileOperation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'missileOperation'");
    }

    private Object stopHere() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopHere'");
    }


    private Object sendMessage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

}
