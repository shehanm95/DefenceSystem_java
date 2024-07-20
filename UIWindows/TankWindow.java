package UIWindows;

import intefaces.UnitObserver;
import support.GreenUnit;
import support.SelectionButton;
import support.SuperDefense;

public class TankWindow extends SuperDefense implements UnitObserver {
   
    GreenUnit unit;
    public TankWindow(SelectionButton selectionButton, GreenUnit greenUnit, String unitNumber) {
        super(selectionButton, greenUnit, unitNumber);
        unit = greenUnit;
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
