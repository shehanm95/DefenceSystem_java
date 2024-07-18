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
    
    BackgroundPanel receivedMessagePanel = new BackgroundPanel(new ImageIcon("images/ReceivedMessagesBack.png"), 0);
    DefenseLabel messageFrom = new DefenseLabel("No Received Messages",13);
    JustifiedLabel messageBody = new JustifiedLabel("Message Body will Display here.",94);

    private GreenUnit mapUnit;
    private MyCheckBox clearAreaCheckBox;
    private boolean areaCleared;
    private MessageSender massageSender;
    private JScrollPane msgScroller;
    private JTextArea msgTextArea;
    private DefenseLabel noMessageLabel;
    private UnitDetail unitDetail;
    


    private EnemyInstantiateObj enemySpawner;

    
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

        receivedMessagePanel.setLayout(null);
        messageFrom.setFont(new Font("",1,14));
        messageBody.setFont(new Font("",1,12));
        
        
        messageFrom.setForeground(DefenseSystem.PrimaryfontColor);
        messageFrom.setHorizontalAlignment(SwingConstants.CENTER);
        messageBody.setForeground(DefenseSystem.PrimaryfontColor);
        messageBody.setBackground(Color.GRAY);
        messageFrom.setBounds(0,4,128,20);
        messageBody.setBounds(5,44,128,60);
        
        receivedMessagePanel.add(messageFrom);
        receivedMessagePanel.add(messageBody);

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
        System.out.println("deployHeli");
        SelectionButton heli = new SelectionButton(GreenUnitType.Helicopter , String.format(" %02d", ++heliNum));
        selectionButtonPanel.add(heli); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    private void deployTank(){
        System.out.println("deployTank");
        SelectionButton tank = new SelectionButton(GreenUnitType.Tank , String.format(" %02d", ++tankNum));
        selectionButtonPanel.add(tank); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    private void deploySub(){
        System.out.println("deploySub");
        SelectionButton sub = new SelectionButton(GreenUnitType.Submarine , String.format(" %02d", ++tankNum));
        selectionButtonPanel.add(sub); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    private void callBack(){
        this.mapUnit.movePosition(this.mapUnit.getInitialPosition());
    }
    private void sendMessageAll(){
        messageFrom.setText("Tank 01");
        messageBody.setText("send troops to the west, we are ready to attack");
        //System.out.println("send message to all units ");
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



}
