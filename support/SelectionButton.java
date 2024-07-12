package support;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import Main.DefenseSystem;
import Main.MainController;
import Main.UnitWindow;

public class SelectionButton extends BackgroundPanel implements MouseInputListener {
    private UnitWindow unitWindow;
    private BackgroundPanel unitImage;
    private JLabel unitStatus = new JustifiedLabel("getToolTipText()", 70);


    private ProgressBar energyBar;
    private ProgressBar strengthBar;
    private GreenUnit greenUnit = null;
    Unit type = null;
    MainController mainController = MainController.getMainController();
    
    public SelectionButton(Unit type){
        super(new ImageIcon("./images/SelectionButton.png"), new Dimension(88,98));
        this.type = type;
        setBorder(new EmptyBorder(0, 0, 0, 0));
        unitWindow =new UnitWindow(Unit.Helicopter);
        unitWindow.setTitle(type.toString());
        initComponents(type);
        addMouseListener(this);
        mainController.setUnit( getGreenUnit());
        
        
    }

    void initComponents(Unit type){
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
        mainController.setUnit( getGreenUnit());
         
    }

    public GreenUnit getGreenUnit(){
        if(greenUnit == null){
            ImageIcon image = new ImageIcon("./images/unit.png");
            if(type == Unit.Helicopter)  greenUnit = new GreenUnit(227, 260, image);
            if(type == Unit.Tank)  greenUnit = new GreenUnit(88, 272, image);
            if(type == Unit.Helicopter)  greenUnit = new GreenUnit(273, 185, image);
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

