package Main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import enums.GreenUnitType;
import support.BackgroundAdder;
import support.SuperDefense;

public class UnitWindow extends SuperDefense {
    public UnitWindow(GreenUnitType unitType , String unitNumber){
        setTitle(unitType.toString() + unitNumber);
        BackgroundAdder.addBackground(this, new ImageIcon("./images/Unit/unitBackT.png"),766,546);
        JFrame controller = MainController.getMainController();
        setLocationRelativeTo(controller);
        setVisible(true);
    }
}
