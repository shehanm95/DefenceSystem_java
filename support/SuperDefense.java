package support;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import MainClass.DefenseSystem;
import UIWindows.MainController;
import UIWindows.MessageSender;
import enums.GreenUnitStatus;
import enums.GreenUnitType;
import enums.StrengthLevels;
import intefaces.MsgReceivable;
import support.unitDetail.UnitDetail;

public class SuperDefense extends JFrame implements MsgReceivable {
    
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
    private MainController controller;
    private MessageSender massageSender;
    private String unitName;
    private JScrollPane msgScroller;
    private JTextArea msgTextArea;
    private DefenseLabel noMessageLabel;
    private UnitDetail unitDetail;
    private JPanel enemydetailsDisplayArea;
    private JTextArea enemyDetailsText;
    private JTextArea unitDetailsText;
    private ProgressBar enemyHealthBar;
    private EnemyMapUnit nearestEnemyUnit;
    private Random random = new Random();
    private EnemyMapUnit lastMessageEnemy;
    private boolean isButtonsDeactivated;
    private String enemyDetailNote;
    private String finalNote;
    private SelectionButton selectionButton;




    public SuperDefense(SelectionButton selectionButton, GreenUnit greenUnit , String unitNumber){

        this.greenUnit = greenUnit;
        this.selectionButton = selectionButton;
        greenUnit.setUnitWindow(this);
        unitName = greenUnit.getGreenUnitType().toString() + unitNumber;
        setTitle(unitName);
        setIconImage(new ImageIcon("./images/"+greenUnit.getGreenUnitType()+"Icon.png").getImage());
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
        noNearestEnemyPanel.setVisible(false);
        

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

        
        missileOperationButton = new ImageButton("./images/"+this.getGreenUnitType().toString()+"H.png",0);
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


        enemydetailsDisplayArea = new JPanel();
        enemydetailsDisplayArea.setLayout(null);
        add(enemydetailsDisplayArea);
        enemydetailsDisplayArea.setBounds(37, 220, 359, 148);

        DefenseLabel observerDetectionLabel = new DefenseLabel("Observer Detections", 15, DefenseSystem.darkRed);
        observerDetectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        observerDetectionLabel.setBounds(0, 0, enemydetailsDisplayArea.getWidth(), 20);
        enemydetailsDisplayArea.add(observerDetectionLabel);
        enemydetailsDisplayArea.setOpaque(false);
        enemydetailsDisplayArea.setVisible(false);


        enemyDetailsText = new JTextArea("Enemy Name     : Enemy Tank 01\nDistance          : 28KM\nEnemy Life        : 72%\n\nEnemy Tank 01 detected as nearest Enemy\nTo this helicopter 01".toUpperCase());
        enemyDetailsText.setEditable(false); 
        enemyDetailsText.setOpaque(false);
        enemyDetailsText.setFont(new Font("" , 1 , 13));
        enemyDetailsText.setForeground(DefenseSystem.darkRed);
        enemyDetailsText.setWrapStyleWord(true);
        enemyDetailsText.setLineWrap(true);
        enemyDetailsText.setBounds(0, 26, enemydetailsDisplayArea.getWidth(), 120);
        enemydetailsDisplayArea.add(enemyDetailsText);


        enemyHealthBar = new ProgressBar(100, 136, 12, DefenseSystem.darkRed , DefenseSystem.darkRed, 1);
        enemyHealthBar.setBounds(148, 65, 136,12);
        enemydetailsDisplayArea.add(enemyHealthBar);
        enemyHealthBar.changeValue(40);

        DefenseLabel unitDetailsLabel = new DefenseLabel("<html><u>UNIT DETAILS.</u></html>", 15);
        unitDetailsLabel.setBounds(425, 268, 200, 15);
        add(unitDetailsLabel);


        unitDetailsText = new JTextArea("");
        unitDetailsText.setEditable(false); 
        unitDetailsText.setOpaque(false);
        unitDetailsText.setFont(new Font("" , 1 , 13));
        unitDetailsText.setForeground(DefenseSystem.PrimaryfontColor);
        unitDetailsText.setWrapStyleWord(true);
        unitDetailsText.setLineWrap(true);
        unitDetailsText.setBounds(425, 290, 200, 120);
        add(unitDetailsText);

        
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
            else if(distance < 20) return StrengthLevels.CLOSED;
        }
        return StrengthLevels.LOW;
    }

    void attackButtonActivator(boolean noNearestEnemyPanelB,boolean followButtonB,boolean shootButtonB, boolean missileOperationButtonB ){
        noNearestEnemyPanel.setVisible(noNearestEnemyPanelB);
        enemydetailsDisplayArea.setVisible(!noNearestEnemyPanelB);
        followButton.setEnabled(followButtonB);
        shootButton.setEnabled(shootButtonB); 
        missileOperationButton.setEnabled(missileOperationButtonB);
    }

    public void updateWindowDetails(EnemyMapUnit nearestEnemyMapUnit , int distance) {
        
        if(greenUnit.isDeath()){
            if(!isButtonsDeactivated){
                attackButtonActivator(false, false, false,false);
                finalNote = this.unitName.toUpperCase() + " IS KILLED BY " + nearestEnemyMapUnit.getEnemyName().toUpperCase();
                MessageSender.autoMessage(this, "IS KILLED BY " + nearestEnemyUnit.getEnemyName().toUpperCase());
                enemyDetailsText.setText(enemyDetailNote+ "\n\n"+finalNote+"".toUpperCase());
                stopHereButton.setEnabled(false);
                isButtonsDeactivated = true;
                selectionButton.setStatus(GreenUnitStatus.PERISHED);
                if(this.greenUnit == controller.getCurrentGreenMapUnit()){
                    controller.setCurrantGreenMapUnitDeath();
                }

                selectionButton.updateBars(this.greenUnit.getEnergy(), this.greenUnit.getSoldierCountStrengthCount());
                
            };
            //System.out.println("death");
            return;

        } 
        StrengthLevels strength  = getStrengthLevels(nearestEnemyMapUnit, distance);
        if(nearestEnemyMapUnit != null) {
            setEnemyDetails(nearestEnemyMapUnit , distance);
            nearestEnemyUnit = nearestEnemyMapUnit;
        }
        
        switch (strength) {
            case LOW:
                attackButtonActivator(true, false, false,false);
                selectionButton.setStatus(GreenUnitStatus.IN_PETROL);
                break;
                case MEDIUM:
                attackButtonActivator(false, false, false,true);
                selectionButton.setStatus(GreenUnitStatus.IN_FIGHT);
                break;
                case HIGH:
                attackButtonActivator(false, true, false,true);
                //takeDamage(2);
                break;
                case STRONG:
                attackButtonActivator(false, true, true,true);
                takeDamage(1);
                break;
                case CLOSED:
                attackButtonActivator(false, true, true,false);
                takeDamage(3);
                break;
           default:
           attackButtonActivator(true, false, false,false);
           selectionButton.setStatus(GreenUnitStatus.IN_PETROL);
           break;
        }
        this.unitDetail.updateDetails(greenUnit);
        //System.out.println(strength);
        
    revalidate();
    this.repaint();
    }



    public void updateUnitDetailText() {
        unitDetailsText.setText("Unit Name    : "+unitName+"\nEnergy          : "+ greenUnit.getEnergy()+"%\nStrength        : "+ greenUnit.getSoldierCountStrengthCount()+"/20");
       
    }


    public void setAreaClearLabel(boolean areaCleared){
        
        if(areaCleared) areaClearStatusLabel.setText("| AREA CLEARED");
        else areaClearStatusLabel.setText("| AREA NOT CLEARED.");
        this.revalidate();
        this.repaint();
    }

    private void follow() {
        greenUnit.movePosition(nearestEnemyUnit.getPosition());
    }


    private void shoot() {
        if(greenUnit.getAmmoCount() > 0){
            greenUnit.reduceAmmoCount();
            nearestEnemyUnit.reduceEnergy(6);
        }
    }


    private void missileOperation() {
        if(greenUnit.getHeavyWeaponAmmoCount() > 0){
            greenUnit.reduceHeavyWeaponAmmoCount();
            nearestEnemyUnit.reduceEnergy(20);
        }else{
            String heavyWeapon = this.greenUnit.getHeavyWeapon().toString();
            JOptionPane.showMessageDialog(this, heavyWeapon + " are out of stock for this unit,\nPlease go to a DEFENSE BASE and refill it,", heavyWeapon + " have finished !",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stopHere() {
        this.greenUnit.stopHere();
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

    private void setEnemyDetails(EnemyMapUnit eUnit , int distance){
        enemyDetailNote = "Enemy Name     : "+eUnit.getEnemyName() +"\nDistance            : "+distance+"KM\nEnemy Life        : "+eUnit.getEnergy()+"%";
        finalNote = eUnit.getEnemyName() + " detected as nearest Enemy\nTo "+this.unitName+".";
        enemyDetailsText.setText(enemyDetailNote+ "\n\n"+finalNote+"".toUpperCase());
        enemyHealthBar.changeValue(eUnit.getEnergy());
        
    }



    private void takeDamage(int damageMultiplier){
        if(nearestEnemyUnit.isDeath()) return;
        if(this.greenUnit.getEnergy() <= 0 ){
            return;} 

        int num =random.nextInt(4+damageMultiplier);
        if(num < 7)
         {
            this.greenUnit.reduceEnergy(MapUnit.random.nextInt(6)+1);
        }
        else{
            this.greenUnit.reduceEnergy(random.nextInt(10)+7+damageMultiplier);
        }
        if(this.nearestEnemyUnit != lastMessageEnemy){
            MessageSender.autoMessage(this, "is attacked by " + nearestEnemyUnit.getEnemyName());
            lastMessageEnemy = nearestEnemyUnit;
        }
        selectionButton.updateBars(this.greenUnit.getEnergy(), this.greenUnit.getSoldierCountStrengthCount());
    }

    
}
