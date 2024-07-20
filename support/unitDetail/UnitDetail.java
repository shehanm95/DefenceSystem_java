package support.unitDetail;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import MainClass.DefenseSystem;
import UIWindows.MainController;
import support.BackgroundPanel;
import support.DefenseLabel;
import support.GreenUnit;
import support.MapUnit;
import support.ProgressBar;

public class UnitDetail extends BackgroundPanel {

    private BackgroundPanel unitImagePanel;
   //MainController mainController = MainController.getMainController();
    private ImageIcon unitImage = new ImageIcon("./images/Unit/details/Helicopter.png");
    private DefenseLabel unitName;
    private DefenseLabel energyLabel;
    private DefenseLabel strengthLabel;
    private DefenseLabel ammoLabel;
    private DefenseLabel HeavyWeaponLabel;
    private DefenseLabel latLabel;
    private DefenseLabel LonLabel;
    private int prevX =0;
    private ProgressBar energyBar;
    private int prevY = 0;

    public UnitDetail() {
        super(new ImageIcon("./images/Unit/unitDetail.png"),new Dimension(319,241));
        setOpaque(false);
        initComponents();
        updateDetails(null);

    }

    private void initComponents() {
        setLayout(null);

        unitImagePanel = new BackgroundPanel(unitImage, 0);
        unitImagePanel.setPreferredSize(new Dimension(40,40));
        unitImagePanel.setBounds(87, 85, 156, 61);
        unitImagePanel.setOpaque(false);
        this.add(unitImagePanel);


        unitName = new DefenseLabel("NOT SELECTED", 14);
        unitName.setBounds(8, 2, 150, 20);
        add(unitName);


        energyLabel = new DefenseLabel("Energy", 8);
        energyLabel.setBounds(12, 27, 200, 20);
        add(energyLabel);
        
        energyBar = new ProgressBar(100, 71, 6, DefenseSystem.PrimaryfontColor);
        energyBar.setBounds(48, 34, 100, 8);
        add(energyBar);
        
        
        strengthLabel = new DefenseLabel("strength           00/00", 9);
        strengthLabel.setBounds(11, 38, 200, 20);
        add(strengthLabel);
        
        ammoLabel= new DefenseLabel("ammo            0000/0000", 9);
        ammoLabel.setBounds(12, 49, 200, 20);
        add(ammoLabel);
        
        HeavyWeaponLabel = new DefenseLabel("Missile               00/00", 9);
        HeavyWeaponLabel.setBounds(12, 60, 200, 20);
        add(HeavyWeaponLabel);



        latLabel = new DefenseLabel("Lat : ", 10);
        latLabel.setBounds(13, 206, 200, 20);
        add(latLabel);
        
        LonLabel = new DefenseLabel("Lon : ", 10);
        LonLabel.setBounds(13, 217, 200, 20);
        add(LonLabel);

    }

    public void updateDetails(GreenUnit greenMapUnit){
       if(greenMapUnit != null){
        changeUnitImagePanelSImage(greenMapUnit);
        unitName.setText(greenMapUnit.getUnitDisplayName());
        energyBar.changeValue(greenMapUnit.getEnergy());
        strengthLabel.setText(String.format("strength               %02d/20", greenMapUnit.getSoldierCountStrengthCount() ));                    
        ammoLabel.setText(String.format("ammo            %04d/4000",greenMapUnit.getAmmoCount()));                
        String heavyWeapon = greenMapUnit.getHeavyWeapon().toString();                
        String heavyWeaponVale = ""+greenMapUnit.getHeavyWeaponAmmoCount();                
        HeavyWeaponLabel.setText(heavyWeapon + "                 "+  heavyWeaponVale+"/20");
        if(greenMapUnit.getX() != prevX || greenMapUnit.getY() != prevY ){
            int x = greenMapUnit.getX();
            int y = greenMapUnit.getY();
            latLabel.setText("LAT  : "+ (x + "." + (MapUnit.random.nextInt(8999)+1000)));
            LonLabel.setText("LON : "+ (greenMapUnit.getY() + "." + (MapUnit.random.nextInt(8999)+1000)));
            prevX = x;
            prevY = y;
        }
        //System.out.println("main controller detail window updated");
       }
        // unitImagePanel.revalidate();
        // unitImagePanel.repaint();
        //this.revalidate();
        // this.repaint();
    }

    private void changeUnitImagePanelSImage(GreenUnit greenMapUnit) {
        // TODO Auto-generated method stub
        unitImage = new ImageIcon("./images/Unit/details/"+greenMapUnit.getGreenUnitType().toString() +".png");
        unitImagePanel.setBackgroundImage(unitImage); 
    }
  
}
