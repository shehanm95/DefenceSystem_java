package support;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import MainClass.DefenseSystem;
import UIWindows.MainController;
import UIWindows.UnitWindow;
import enums.GreenUnitStatus;
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
    private ImageIcon inPatrolImage;
    private ImageIcon perishedImage;
    
    
    
    
    public SelectionButton(GreenUnitType type , String unitNum){
        super(new ImageIcon("./images/SelectionButton.png"), new Dimension(88,98));
        this.type = type;
        this.unitNum = unitNum;
        setBorder(new EmptyBorder(0, 0, 0, 0));
        initComponents(type);
        addMouseListener(this);
        greenUnit = getGreenUnit();
        unitWindow =new UnitWindow(this, greenUnit, unitNum);
        mainController.setMapUnit( greenUnit);
        map.setGreenSelectorPosition(greenUnit);
        
    }

    

    void initComponents(GreenUnitType type){
        setLayout(null);

        inPatrolImage = new ImageIcon("./images/"+type.toString()+".png");
        perishedImage = new ImageIcon("./images/Red"+type.toString()+".png");
        unitImage = new BackgroundPanel(inPatrolImage, 0);
        unitImage.setOpaque(false);
        unitImage.setBounds(7, 5, inPatrolImage.getIconWidth(), inPatrolImage.getIconHeight());
        add(unitImage);

        unitStatus.setText("In Patrol");
        unitStatus.setFont(new Font("Ebrima",1, 13));
        unitStatus.setForeground(DefenseSystem.PrimaryfontColor);
        unitStatus.setBounds(0, 47, 80,15 );
        add(unitStatus);


        energyBar = new ProgressBar(100, 60, 4, DefenseSystem.PrimaryfontColor);
        energyBar.setBounds(20, 71, 60, 4);
        add(energyBar);


        strengthBar = new ProgressBar(20, 60, 4, DefenseSystem.PrimaryfontColor);
        strengthBar.setBounds(20, 86, 60, 4);
        add(strengthBar);
    }
    int health = 100;
    private boolean perished = false;
    @Override
    public void mouseClicked(MouseEvent e) {
       System.out.println("Clicked on button");
        unitWindow.setVisible(true);
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



    public void setStatus(GreenUnitStatus unitStatus) {

        switch (unitStatus) {
            case IN_PETROL:
                    this.unitStatus.setText(unitStatus.toString());
                    this.unitStatus.setForeground(DefenseSystem.PrimaryfontColor);
                    break;
            case IN_FIGHT:
                    this.unitStatus.setText(unitStatus.toString());
                    this.unitStatus.setForeground(DefenseSystem.darkRed);
                    break;
            case PERISHED:
                    this.unitStatus.setText("");
                    unitImage.setBackgroundImage(perishedImage);
                    this.setBackgroundImage(new ImageIcon("./images/Perished.png"));
                    perished = true;
                    break;
                    
                    default:
                    this.unitStatus.setText(unitStatus.toString());
                    this.unitStatus.setForeground(DefenseSystem.PrimaryfontColor);
                break;
        }
        

    }



    public void updateBars(int energy, int soldierCount) {
        energyBar.changeValue(energy);
        strengthBar.changeValue(soldierCount);
    }



    public boolean isPerished() {
        return perished;
    }
    public void closeUnitWindow() {
        unitWindow.dispose();
    }
}

