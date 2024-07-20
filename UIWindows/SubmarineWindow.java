package UIWindows;

import MainClass.DefenseSystem;
import intefaces.UnitObserver;
import support.DefenseLabel;
import support.GreenUnit;
import support.ProgressBar;
import support.SelectionButton;
import support.SuperDefense;

public class SubmarineWindow extends SuperDefense implements UnitObserver {

    GreenUnit unit;
    private ProgressBar oxygenBar;
    private DefenseLabel oxygenValueLabel;
    private int oxygenLevel;
    public SubmarineWindow(SelectionButton selectionButton, GreenUnit greenUnit, String unitNumber) {
        super(selectionButton, greenUnit, unitNumber);
        unit = greenUnit;

        DefenseLabel OxygenLabel = new DefenseLabel("Oxygen Level : ", 14);
        OxygenLabel.setBounds(425, 360, 200, 13);
        add(OxygenLabel);

        oxygenValueLabel = new DefenseLabel("100/100 ", 13);
        oxygenValueLabel.setBounds(425, 382, 200, 13);
        add(oxygenValueLabel);

        oxygenBar = new ProgressBar(100, 200, 13, DefenseSystem.PrimaryfontColor,DefenseSystem.PrimaryfontColor,2);
        oxygenBar.setBounds(480, 381, 200, 13);
        add(oxygenBar);
        updateOxygenLevel();
    }
   public void updateOxygenLevel() {
        if(unit.isDeath())return;
        
       if(oxygenLevel > 10){
        oxygenLevel-=1;
       } else{
        oxygenLevel= 100;
       }

       oxygenBar.changeValue(oxygenLevel);
       oxygenValueLabel.setText(oxygenLevel+"/100" );
    }
    @Override
    public int getAmmoCount() {
            return unit.getAmmoCount();            
    }

    @Override
    public int getEnergy() {
            return unit.getEnergy();                
    }

    @Override
    public int getSoldierCountStrengthCount() {
            return unit.getSoldierCountStrengthCount();            
    }
}
