package support;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import MainClass.DefenseSystem;
import UIWindows.MainController;
import UIWindows.UnitWindow;
import enums.GreenUnitType;

public class SelectionButton extends BackgroundPanel implements MouseInputListener {
    private UnitWindow unitWindow;
    private BackgroundPanel unitImage;
    private JLabel unitStatus = new JustifiedLabel("getToolTipText()", 70);

   
    private ProgressBar energyBar;
    private ProgressBar strengthBar;
    private GreenUnit greenUnit = null;
    GreenUnitType type = null;
    MainController mainController = MainController.getMainController();
    private DefenseMap map = DefenseMap.getDefenseMap();
    String unitNum = "";
    
    
    
    public SelectionButton(GreenUnitType type , String unitNum){
        super(new ImageIcon("./images/SelectionButton.png"), new Dimension(88,98));
        this.type = type;
        this.unitNum = unitNum;
        setBorder(new EmptyBorder(0, 0, 0, 0));
        unitWindow =new UnitWindow(type, unitNum);
        initComponents(type);
        addMouseListener(this);
        greenUnit = getGreenUnit();
        mainController.setMapUnit( greenUnit);
        map.setGreenSelectorPosition(greenUnit);
        
    }

    void initComponents(GreenUnitType type){
        setLayout(null);

        ImageIcon img = new ImageIcon("./images/"+type.toString()+".png");
        unitImage = new BackgroundPanel(img, 0);
        unitImage.setOpaque(false);
        unitImage.setBounds(7, 5, img.getIconWidth(), img.getIconHeight());
        add(unitImage);

        unitStatus.setText("In Patrol");
        unitStatus.setFont(new Font("Ebrima",1, 13));
        unitStatus.setForeground(DefenseSystem.PrimaryfontColor);
        unitStatus.setBounds(0, 47, 80,15 );
        add(unitStatus);


        energyBar = new ProgressBar(100, 60, 4, DefenseSystem.PrimaryfontColor);
        energyBar.setBounds(20, 71, 60, 4);
        add(energyBar);


        strengthBar = new ProgressBar(100, 60, 4, DefenseSystem.PrimaryfontColor);
        strengthBar.setBounds(20, 86, 60, 4);
        add(strengthBar);
    }
    int health = 100;
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
       System.out.println("Clicked on button");
        unitWindow.setVisible(true);
        energyBar.changeValue(health);
        health -=20;
        System.out.println(health);
        greenUnit = getGreenUnit();
        mainController.setMapUnit(greenUnit );
        map.setGreenSelectorPosition(greenUnit);
         
    }

    public GreenUnit getGreenUnit(){
        if(greenUnit == null){
            ImageIcon image = new ImageIcon("./images/unit.png");
            if(type == GreenUnitType.Submarine)  greenUnit = new GreenUnit(DefenseSystem.NavyBasePosition, image,GreenUnitType.Submarine, unitNum);
            if(type == GreenUnitType.Tank)  greenUnit = new GreenUnit(DefenseSystem.ArmyBasePosition, image,GreenUnitType.Tank, unitNum);
            if(type == GreenUnitType.Helicopter)  greenUnit = new GreenUnit(DefenseSystem.AirForceBasePosition, image,GreenUnitType.Helicopter, unitNum);
            DefenseMap.getDefenseMap().addUnitsToMap(greenUnit);
        }
        return greenUnit;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}

