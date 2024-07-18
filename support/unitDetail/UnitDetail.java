package support.unitDetail;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import UIWindows.MainController;
import support.BackgroundPanel;
import support.GreenUnit;

public class UnitDetail extends BackgroundPanel {

    private BackgroundPanel unitImagePanel;
   //MainController mainController = MainController.getMainController();
    private ImageIcon unitImage = new ImageIcon("./images/Unit/details/Helicopter.png");

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


    }

    public void updateDetails(GreenUnit greenMapUnit){
       if(greenMapUnit != null){
        changeUnitImagePanelSImage(greenMapUnit);
       
        //System.out.println("main controller detail window updated");
       }
        // unitImagePanel.revalidate();
        // unitImagePanel.repaint();
        // this.revalidate();
        // this.repaint();
    }

    private void changeUnitImagePanelSImage(GreenUnit greenMapUnit) {
        // TODO Auto-generated method stub
        unitImage = new ImageIcon("./images/Unit/details/"+greenMapUnit.getGreenUnitType().toString() +".png");
        unitImagePanel.setBackgroundImage(unitImage); 
    }




    
}
