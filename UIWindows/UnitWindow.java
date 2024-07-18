package UIWindows;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import MainClass.DefenseSystem;
import enums.GreenUnitType;
import enums.JustifiedLabelAlignment;
import enums.StrengthLevels;
import intefaces.MsgReceivable;
import support.BackgroundAdder;
import support.DefenseLabel;
import support.EnemyMapUnit;
import support.GreenUnit;
import support.ImageButton;
import support.JustifiedLabel;
import support.MyCheckBox;
import support.SuperDefense;
import support.unitDetail.UnitDetail;

public class UnitWindow extends SuperDefense implements MsgReceivable {
    
    private ImageButton sendMessageButton;
    private JPanel buttonPanelEnemyDetails1 = new JPanel();
    private ImageButton missileOperationButton;
    private ImageButton stopHereButton;
    private MyCheckBox positionChangeAllowCBox;
    private GreenUnit greenUnit;
    private JPanel noNearestEnemyPanel;
    private ImageButton shootButton;
    private ImageButton followButton;
    private boolean positionChangeAllow = true;
    private DefenseLabel areaClearStatusLabel;
    MainController controller;
    MessageSender massageSender;
    private String unitName;
    private JScrollPane msgScroller;
    private JTextArea msgTextArea;
    private DefenseLabel noMessageLabel;
    private UnitDetail unitDetail;




    public UnitWindow(GreenUnit greenUnit , String unitNumber){
        this.greenUnit = greenUnit;
        greenUnit.setUnitWindow(this);
        unitName = greenUnit.getGreenUnitType().toString() + unitNumber;
        setTitle(unitName);
        BackgroundAdder.addBackground(this, new ImageIcon("./images/Unit/unitBack.png"),766,546);
        controller = MainController.getMainController();
        setLocationRelativeTo(controller);
        setVisible(true);
        initComponents();
        setAreaClearLabel(controller.getAreaClearStatus());
        
    }


    private void initComponents(){
        this.setLayout(null);

        

        areaClearStatusLabel = new DefenseLabel("| Area Not Cleared.", 12);
        this.add(areaClearStatusLabel);
        areaClearStatusLabel.setBounds(275, 16, 230, 13);
        
        msgScroller = new JScrollPane();
       getContentPane().add(msgScroller);
       msgScroller.setBounds(37, 54, 351, 98);
       msgScroller.setOpaque(false);
       msgScroller.setBorder(null);
       msgScroller.getViewport().setOpaque(false);

       msgTextArea = new JTextArea();
       msgScroller.add(msgTextArea);
       msgTextArea.setLineWrap(true);
       msgTextArea.setEditable(false);
       msgTextArea.setOpaque(false);
       msgTextArea.setFont(DefenseSystem.defenseFont);
       msgTextArea.setForeground(DefenseSystem.PrimaryfontColor);
       msgScroller.setViewportView(msgTextArea);
        
        sendMessageButton = new ImageButton("./images/Unit/SendMessageButton.png");
        this.add(sendMessageButton);
        sendMessageButton.setBounds(34, 175, 359, 25);
        sendMessageButton.addActionListener((e)->{
            sendMessage(this, new MsgReceivable[]{controller});
        });

        noNearestEnemyPanel = new JPanel();
        add(noNearestEnemyPanel);
        noNearestEnemyPanel.setBounds(37, 260, 359, 60);
        DefenseLabel noEnemyLabel1 = new DefenseLabel("No Enemy contact detected", 14);
        DefenseLabel noEnemyLabel2 = new DefenseLabel("within the designated engagement zone", 14);
        noNearestEnemyPanel.setOpaque(false);
        noNearestEnemyPanel.add(noEnemyLabel1);
        noNearestEnemyPanel.add(noEnemyLabel2);

        add(buttonPanelEnemyDetails1);
        buttonPanelEnemyDetails1.setBounds(21,372,400,30);
        buttonPanelEnemyDetails1.setOpaque(false);
        buttonPanelEnemyDetails1.setLayout(new FlowLayout(FlowLayout.LEFT ,13,0));
        
       shootButton = new ImageButton("./images/Unit/shootButton.png", 0);
       shootButton.addActionListener((e)->shoot());
       buttonPanelEnemyDetails1.add(shootButton);
       
       followButton = new ImageButton("./images/Unit/followButton.png", 0);
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
        
        
        positionChangeAllowCBox = new MyCheckBox("                                 ",true);
        positionChangeAllowCBox.addActionListener((e)->positionChangeAllow());
        add(positionChangeAllowCBox);
        positionChangeAllowCBox.setBorder(null);
        positionChangeAllowCBox.setFocusPainted(false);
        positionChangeAllowCBox.setBorderPainted(false);
        positionChangeAllowCBox.setBounds(227,467,200,25);
        positionChangeAllowCBox.setOpaque(false);

        DefenseLabel letMainControllerTo = new DefenseLabel("let Main Controller to", 12);
        add(letMainControllerTo);
        letMainControllerTo.setBounds(247,458,200,25);
        DefenseLabel changeThePosition = new DefenseLabel("Change the Position", 12); 
        add(changeThePosition);
        changeThePosition.setBounds(247,472,200,25);


        noMessageLabel = new DefenseLabel("- No Messages To Display - ", 13);
        noMessageLabel.setBounds(57,80,313,42);
        getContentPane().add(noMessageLabel);
        noMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        unitDetail = new UnitDetail();
        unitDetail.setBounds(417, 15, 319,241);
        getContentPane().add(unitDetail);

    }

    private void positionChangeAllow() {
        positionChangeAllow = ( positionChangeAllow == true) ? false : true; 
        System.out.println(positionChangeAllow); 
    }

    public boolean getPositionChangePermission(){
        return positionChangeAllow; 
    }


    private StrengthLevels getStrengthLevels(EnemyMapUnit nearestEnemyMapUnit, int distance){
        // LOW,MEDIUM,HIGH,STRONG,CLOSED
        if(nearestEnemyMapUnit == null ) return StrengthLevels.LOW;
        else{
            if(distance < 45) return StrengthLevels.STRONG;
            else if(distance < 60) return StrengthLevels.HIGH;
            else if(distance < 65) return StrengthLevels.MEDIUM;
            else if(distance < 10) return StrengthLevels.CLOSED;
        }
        return StrengthLevels.LOW;
    }

    void attackButtonActivator(boolean noNearestEnemyPanelB,boolean followButtonB,boolean shootButtonB, boolean missileOperationButtonB ){
        noNearestEnemyPanel.setVisible(noNearestEnemyPanelB);
        followButton.setEnabled(followButtonB);
        shootButton.setEnabled(shootButtonB); 
        missileOperationButton.setEnabled(missileOperationButtonB);
    }

    public void updateNearestEnemyDetails(EnemyMapUnit nearestEnemyMapUnit , int distance) {
        StrengthLevels strength  = getStrengthLevels(nearestEnemyMapUnit, distance);

        switch (strength) {
            case LOW:
                attackButtonActivator(true, false, false,false);
                break;
                case MEDIUM:
                attackButtonActivator(false, false, false,true);
                break;
                case HIGH:
                attackButtonActivator(false, true, false,true);
                break;
                case STRONG:
                attackButtonActivator(false, true, true,true);
                break;
                case CLOSED:
                attackButtonActivator(false, true, true,true);
                break;
           default:
           attackButtonActivator(true, false, false,false);
           break;
        }
        this.unitDetail.updateDetails(greenUnit);
        //System.out.println(strength);
        
    revalidate();
    this.repaint();
    }

    public void setAreaClearLabel(boolean areaCleared){
        
        if(areaCleared) areaClearStatusLabel.setText("| AREA CLEARED");
        else areaClearStatusLabel.setText("| AREA NOT CLEARED.");
        this.revalidate();
        this.repaint();
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

    @Override
    public MessageSender getMessageSender() {
        if(massageSender == null)massageSender = new MessageSender();
        return massageSender;
    }


    @Override
    public String getSenderName() {
        return  unitName ;
    }


    @Override
    public JTextArea getMsgDisplayArea() {
        return msgTextArea;
    }


    @Override
    public GreenUnitType getGreenUnitType() {
        return greenUnit.getGreenUnitType();
    }


    @Override
    public DefenseLabel getNoMessageLabel() {
        return noMessageLabel;
    }
}
