package UIWindows;
import javax.swing.*;

import MainClass.DefenseSystem;
import enums.GreenUnitType;
import support.*;
import java.awt.*;

public class MainController extends JFrame {
    
    JButton setPositionButton = new ImageButton("images/setPositionButton.png",43,31);
    ImageButton shehanButton = new ImageButton("images/shehanButton.png",0);
    
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

    JButton callBackButton = new ImageButton("images/BackAll.png");

    JPanel selectionButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,4,0));
    
    JPanel[] sectionButtons = new JPanel[4];
    
    //holds scan area , protected status
    JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100,100));

    JButton scanAreaButton = new ImageButton("images/ScanArea.png");
    BackgroundPanel areaClearedStatus = new BackgroundPanel(new ImageIcon("images/clearStatus-Cleared.png"), new Dimension(130,100));
    
    JButton sendMessageToAllButton = new ImageButton("images/SendMessageToAll.png",1);
    
    BackgroundPanel receivedMessagePanel = new BackgroundPanel(new ImageIcon("images/ReceivedMessagesBack.png"), 0);
    JLabel messageFrom = new JustifiedLabel("No Received Messages",100);
    JLabel messageBody = new JustifiedLabel("Message Body will Display here.",94);

    private GreenUnit mapUnit;
    
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
        EnemyInstantiateObj.getEnemyInstantiate();
        
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
        getContentPane().add(callBackButton);
        callBackButton.addActionListener((e)-> callBack());
        callBackButton.setBounds(32, 507, 210, 30); 
        
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
        sendMessageToAllButton.addActionListener((e)->sendMessageAll());
        middlePanel.add(receivedMessagePanel);

        receivedMessagePanel.setLayout(null);
        messageFrom.setFont(new Font("",1,14));
        messageBody.setFont(new Font("",1,12));
        
        
        messageFrom.setForeground(DefenseSystem.PrimaryfontColor);
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

       
    }

    public GreenUnit getMapUnit() {
        return mapUnit;
    }


    public void setMapUnit(GreenUnit unit) {
        this.mapUnit = unit;
    }
    void scanArea(){
        areaClearedStatus.setBackgroundImage(new ImageIcon("images/clearStatus-Detected.png"));
        System.out.println("area Scanned");
        middlePanel.repaint();
        areaClearedStatus.repaint();
        radarRotator.setVisible(true);
    }
    void deployHeli(){
        System.out.println("deployHeli");
        SelectionButton heli = new SelectionButton(GreenUnitType.Helicopter , String.format(" %02d", ++heliNum));
        selectionButtonPanel.add(heli); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    void deployTank(){
        System.out.println("deployTank");
        SelectionButton tank = new SelectionButton(GreenUnitType.Tank , String.format(" %02d", ++tankNum));
        selectionButtonPanel.add(tank); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    void deploySub(){
        System.out.println("deploySub");
        SelectionButton sub = new SelectionButton(GreenUnitType.Submarine , String.format(" %02d", ++tankNum));
        selectionButtonPanel.add(sub); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    void callBack(){
        this.mapUnit.movePosition(this.mapUnit.getInitialPosition());
    }
    void sendMessageAll(){
        messageFrom.setText("Tank 01");
        messageBody.setText("send troops to the west, we are ready to attack");
        //System.out.println("send message to all units ");
    }

    void setPosition(){
        int yValue = YSlider.getMaximum() - YSlider.getValue();
        if(this.mapUnit != null)mapUnit.movePosition(new Position(XSlider.getValue(), yValue));
        //System.out.println("call back all to the bases");
    }



}