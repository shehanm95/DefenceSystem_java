package UIWindows;
import javax.swing.*;

import MainClass.DefenseSystem;
import enums.GreenUnitType;
import intefaces.MsgReceivable;
import support.*;
import support.unitDetail.UnitDetail;

import java.awt.*;
import java.util.Arrays;

public class MainController extends JFrame implements MsgReceivable {
    
    JButton setPositionButton = new ImageButton("images/setPositionButton.png",43,31);
    ImageButton shehanButton = new ImageButton("images/shehanButton.png",0);
    private GreenUnitType unitType = GreenUnitType.MainController;
    
    JSlider XSlider = new JSlider(JSlider.HORIZONTAL,0 , 380,20);
    JSlider YSlider = new JSlider(JSlider.VERTICAL,0 , 292,20);

    DefenseMap map = DefenseMap.getDefenseMap();
    RotatingImagePanel radarRotator = new RotatingImagePanel("images/RadarRotator.png");
    
    JPanel launchButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
    //deploy unit buttons
    JButton deployHeliButton = new ImageButton("images/LaunchHeli.png");
    JButton deployTankButton = new ImageButton("images/LaunchTank.png");
    JButton deploySubButton = new ImageButton("images/LaunchSub.png");
    int heliNum=0,tankNum=0,subNum=0;

    JButton backToBase = new ImageButton("images/backToBase.png");

    JPanel selectionButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,4,0));
    
    JPanel[] sectionButtons = new JPanel[4];
    
    //holds scan area , protected status
    JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100,100));

    JButton scanAreaButton = new ImageButton("images/ScanArea.png");
    BackgroundPanel areaClearedStatus = new BackgroundPanel(new ImageIcon("images/clearStatus-Cleared.png"), new Dimension(130,100));
    
    JButton sendMessageToAllButton = new ImageButton("images/SendMessageToAll.png",1);
    
    BackgroundPanel receivedMessagePanel = new BackgroundPanel(new ImageIcon("images/ReceivedMessagesBack.png"),0);
    
    private GreenUnit mapUnit;
    private MyCheckBox clearAreaCheckBox;
    private boolean areaCleared;
    private MessageSender massageSender;
    private JScrollPane msgScroller;
    private JTextArea msgTextArea;
    private DefenseLabel noMessageLabel;
    private UnitDetail unitDetail;
    
    
    
    private EnemyInstantiateObj enemySpawner;
    
    private DefenseLabel totalSoldierCountLabel;
    private DefenseLabel totalEnergyCountLabel;
    private DefenseLabel totalAmmoCountLabel;
    private int totalAmmoCount;
    private int totalEnergyCount;
    private int totalSoldierCount;
    private final int MAX_SELECTION_BUTTON_LIMIT = 4;

    
    private static MainController mainController = null;

    private MainController(){
        BackgroundAdder.addBackground(this,new ImageIcon("images/backGround.png"),978, 588);
        // Create a custom panel with overridden paintComponent method
        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        initComponents();
        setResizable(false);
        setVisible(true);
        disableSliders();
        
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public static MainController getMainController(){
        if(mainController == null) mainController = new MainController();
        return mainController;
    }


    void initComponents(){
        setLayout(null);

        //Sliders

        getContentPane().add(setPositionButton);
        setPositionButton.setOpaque(false);
        setPositionButton.setBounds(21,71,53,40);
        setPositionButton.addActionListener((e)->setPosition());

        getContentPane().add(shehanButton);
        shehanButton.setOpaque(false);
        shehanButton.setBounds(624,13,shehanButton.getButtonWidth(), shehanButton.getButtonHeight());


        getContentPane().add(XSlider);
        XSlider.setOpaque(false);
        XSlider.setBounds(88,79,380,20);
        
        getContentPane().add(YSlider);
        YSlider.setOpaque(false);
        YSlider.setBounds(30,122,40,302);


        XSlider.addChangeListener((e)->{setPosition();});
        YSlider.addChangeListener((e)->{setPosition();});
        getContentPane().add(map);
        map.setBounds(88,122, 380, 302);
        

        //Scan Area
        getContentPane().add(scanAreaButton);
        scanAreaButton.addActionListener((e)-> scanArea());
        scanAreaButton.setOpaque(false);
        scanAreaButton.setBounds(488,114,128,27);


        //Deploy buttons 
        getContentPane().add(launchButtonPanel);
        launchButtonPanel.add(deployHeliButton);
        launchButtonPanel.add(deployTankButton);
        launchButtonPanel.add(deploySubButton);
        launchButtonPanel.setOpaque(false);
        launchButtonPanel.setBounds(32, 458, 239, 40);
        
        deployHeliButton.addActionListener((e)-> deployHeli());
        deployHeliButton.setPreferredSize(new Dimension(60, 39));             
    
        deployTankButton.addActionListener((e)-> deployTank());
        deployTankButton.setPreferredSize(new Dimension(60, 39));           
    
        deploySubButton.addActionListener((e)-> deploySub());
        deploySubButton.setPreferredSize(new Dimension(60, 39));  
        
        
        //Call Back
        getContentPane().add(backToBase);
        backToBase.addActionListener((e)-> callBack());
        backToBase.setBounds(138, 510, 102, 19); 
        
        
        clearAreaCheckBox = new MyCheckBox("Area cleared", false);
        clearAreaCheckBox.addActionListener((e)->areaClearMessage());
        clearAreaCheckBox.setBounds(22, 504, 120, 30);

        clearAreaCheckBox.setOpaque(false);
        add(clearAreaCheckBox);
        



        //selectionButtonPanel
        
        getContentPane().add(selectionButtonPanel);
        selectionButtonPanel.setOpaque(false);
        //selectionButtonPanel.add(new SelectionButton(Unit.Helicopter));
        
        selectionButtonPanel.setBounds(243, 439, 368, 104); 

        middlePanel.setBounds(488,138,128,310);
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        middlePanel.setOpaque(false);
        getContentPane().add(middlePanel);
        

        //setting middle panel things
        areaClearedStatus.setOpaque(false);

        middlePanel.add(areaClearedStatus);
        middlePanel.add(sendMessageToAllButton);
        sendMessageToAllButton.addActionListener((e)->{
            if(getMsgReceivables().length <= 1){
                JOptionPane.showMessageDialog(null, "You Have to initialize Units first", "No war units found !", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                sendMessage(mainController, getMsgReceivables());
            }
        });
        middlePanel.add(receivedMessagePanel);
        receivedMessagePanel.setBounds(10, 10, 128, 129);

        receivedMessagePanel.setLayout(null);

        totalSoldierCountLabel = new DefenseLabel("00/80", 13,DefenseSystem.backgroundCor);
        totalSoldierCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalSoldierCountLabel.setBounds(0,29,128,20);

        totalEnergyCountLabel = new DefenseLabel("000/400", 13,DefenseSystem.backgroundCor);
        totalEnergyCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalEnergyCountLabel.setBounds(0,64,128,20);

        totalAmmoCountLabel = new DefenseLabel("0000/8000", 13,DefenseSystem.backgroundCor);
        totalAmmoCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalAmmoCountLabel.setBounds(0,99,128,20);
        
        receivedMessagePanel.add(totalSoldierCountLabel);
        receivedMessagePanel.add(totalEnergyCountLabel);
        receivedMessagePanel.add(totalAmmoCountLabel);

        getContentPane().add(radarRotator);
        radarRotator.setOpaque(false);
        radarRotator.setVisible(false);
        radarRotator.setBounds(145, 137, 267, 267);

       msgScroller = new JScrollPane();
       getContentPane().add(msgScroller);
       msgScroller.setBounds(638, 348, 280, 180);
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



       noMessageLabel = new DefenseLabel("- No Messages To Show -", 13);
       getContentPane().add(noMessageLabel);
       noMessageLabel.setBounds(690,417,313,42);


       unitDetail = new UnitDetail();
       unitDetail.setBounds(623, 60,319,241);
       getContentPane().add(unitDetail);
       
    }

    private void areaClearMessage() {
        areaCleared = areaCleared ? false : true;
        System.out.println(areaCleared);
        for (Component selectionButton : selectionButtonPanel.getComponents()) {
            SelectionButton button = (SelectionButton) selectionButton;
            button.getGreenUnit().getUnitWindow().setAreaClearLabel(areaCleared);
        }
    }


    public GreenUnit getCurrentGreenMapUnit() {
        return mapUnit;
    }


    public void setMapUnit(GreenUnit unit) {
        this.mapUnit = unit;
        map.setGreenSelectorPosition(unit);
        enableSliders();
    }
    public void scanArea(){
        System.out.println("area Scanned");
        middlePanel.repaint();
        enemySpawner = EnemyInstantiateObj.getEnemyInstantiate();
        areaClearedStatus.repaint();
        radarRotator.setVisible(true);
        
    }

    public void setEnemyDetectedLabelActive(){
        areaClearedStatus.setBackgroundImage(new ImageIcon("images/clearStatus-Detected.png"));
    }
    private void deployHeli(){
        if(!isCanAdd()) return;
        System.out.println("deployHeli");
        SelectionButton heli = new SelectionButton(GreenUnitType.Helicopter , String.format(" %02d", ++heliNum));
        selectionButtonPanel.add(heli); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    private void deployTank(){
        if(!isCanAdd()) return;
        System.out.println("deployTank");
        SelectionButton tank = new SelectionButton(GreenUnitType.Tank , String.format(" %02d", ++tankNum));
        selectionButtonPanel.add(tank); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    private void deploySub(){
        if(!isCanAdd()) return;
        System.out.println("deploySub");
        SelectionButton sub = new SelectionButton(GreenUnitType.Submarine , String.format(" %02d", ++tankNum));
        selectionButtonPanel.add(sub); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    private boolean isCanAdd() {
        for (Component comp : selectionButtonPanel.getComponents()) {
            SelectionButton button = (SelectionButton) comp;
            if(button.isPerished()){
                button.closeUnitWindow();
                selectionButtonPanel.remove(comp);

            }
        }
        if(selectionButtonPanel.getComponentCount() < MAX_SELECTION_BUTTON_LIMIT) return true;
        else{
            JOptionPane.showMessageDialog(this, "You can maintain up to four active units simultaneously.", "Max deployment limit reached !",JOptionPane.ERROR_MESSAGE );
            return false;
        } 
    }

    private void callBack(){
        if(mapUnit != null){
            this.mapUnit.movePosition(this.mapUnit.getInitialPosition());
        }
    }
    

    private void setPosition(){
        if(this.mapUnit != null && this.mapUnit.getUnitWindow().getPositionChangePermission()){
        int yValue = YSlider.getMaximum() - YSlider.getValue();
        if(this.mapUnit != null)mapUnit.movePosition(new Position(XSlider.getValue(), yValue));}
        //System.out.println("call back all to the bases");
    }


    public boolean getAreaClearStatus() {
        return areaCleared;
    }

    public MsgReceivable[] getMsgReceivables() {
        // getting all the unit windows int to array;
        Component[] subUnits = selectionButtonPanel.getComponents();
        MsgReceivable[] msgReceivables = new MsgReceivable[subUnits.length + 1];
        msgReceivables[0] = this;
        for (int i = 1; i < msgReceivables.length; i++)
            msgReceivables[i] =((SelectionButton) subUnits[i-1]).getGreenUnit().getUnitWindow();

        return msgReceivables;
    }




    @Override
   public MessageSender getMessageSender() {
        if(massageSender == null)massageSender = new MessageSender();
        return massageSender;
    }


    @Override
    public String getSenderName() {
        return "Main Controller";
    }


    @Override
    public JTextArea getMsgDisplayArea() {
        return msgTextArea;
    }


    @Override
    public GreenUnitType getGreenUnitType() {
        return unitType;
    }


    @Override
    public DefenseLabel getNoMessageLabel() {
        return noMessageLabel;
    }

    public void setCurrantGreenMapUnitDeath() {
        this.mapUnit = null;
        map.setGreenSelectorPosition(null);
        disableSliders();
    }

    private void disableSliders() {
        XSlider.setEnabled(false);
        YSlider.setEnabled(false);
    }
    private void enableSliders() {
        XSlider.setEnabled(true);
        YSlider.setEnabled(true);
    }

    public void updateFieldStatus() {

        Component[] buttons = selectionButtonPanel.getComponents();
        totalAmmoCount = 0;
        totalEnergyCount = 0;
        totalSoldierCount = 0;
        for (Component component : buttons) {
            GreenUnit greenUnit = ((SelectionButton) component).getGreenUnit();
                totalAmmoCount += greenUnit.getAmmoCount();                        
                totalEnergyCount += greenUnit.getEnergy();                      
                totalSoldierCount += greenUnit.getSoldierCountStrengthCount();  
        }

        totalAmmoCountLabel.setText(String.format("%04d/8000", totalAmmoCount));
        totalEnergyCountLabel.setText(String.format("%02d/400", totalEnergyCount));
        totalSoldierCountLabel.setText(String.format("%02d/80", totalSoldierCount));
    }

}
