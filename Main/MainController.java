package Main;
import javax.swing.*;
import support.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainController extends JFrame {
    
    
    JSlider XSlider = new JSlider(JSlider.HORIZONTAL,0 , 100,20);
    JSlider YSlider = new JSlider(JSlider.VERTICAL,0 , 100,20);

    RotatingImagePanel radarRotator = new RotatingImagePanel("images/RadarRotator.png");
        
    

    JPanel launchButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
    //deploy unit buttons
    JButton deployHeliButton = new ImageButton("images/LaunchHeli.png");
    JButton deployTankButton = new ImageButton("images/LaunchTank.png");
    JButton deploySubButton = new ImageButton("images/LaunchSub.png");

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

    
    private static MainController mainController = null;

    private MainController(){
        BackgroundAdder.addBackground(this,new ImageIcon("images/backGround.png"));
        // Create a custom panel with overridden paintComponent method
       
        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setSize(978, 588);
        initComponents();
        setResizable(false);
        setVisible(true);
    }


    public static MainController getMainController(){
        if(mainController == null) mainController = new MainController();
        return mainController;
    }

    void initComponents(){
        setLayout(null);

        //Sliders
        getContentPane().add(XSlider);
        XSlider.setOpaque(false);
        XSlider.setBounds(78,79,407,20);
        
        getContentPane().add(YSlider);
        YSlider.setOpaque(false);
        YSlider.setBounds(30,110,40,323);


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
        //messageFrom.setPreferredSize(new Dimension(20, 20));
       // messageBody.setPreferredSize(new Dimension(50, 20));


        
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

    void scanArea(){
        areaClearedStatus.setBackgroundImage(new ImageIcon("images/clearStatus-Detected.png"));
        System.out.println("area Scanned");
        middlePanel.repaint();
        areaClearedStatus.repaint();
        radarRotator.setVisible(true);
    }
    void deployHeli(){
        System.out.println("deployHeli");
        SelectionButton heli = new SelectionButton(Unit.Helicopter);
        selectionButtonPanel.add(heli); 
        selectionButtonPanel.revalidate();
        selectionButtonPanel.repaint();
    }
    void deployTank(){
        System.out.println("deployTank");
    }
    void deploySub(){
        System.out.println("deploySub");
    }
    void callBack(){
        System.out.println("call back all to the bases");
    }
    void sendMessageAll(){
        messageFrom.setText("Tank 01");
        messageBody.setText("send troops to the west, we are ready to attack");
        System.out.println("send message to all units ");
    }



}
