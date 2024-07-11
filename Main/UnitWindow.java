package Main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import support.BackgroundAdder;
import support.SuperDefense;
import support.Unit;

public class UnitWindow extends SuperDefense {
    public UnitWindow(Unit type){
        setTitle("Tank");
        BackgroundAdder.addBackground(this, new ImageIcon("./images/Unit/unitBackT.png"),766,546);
        JFrame controller = MainController.getMainController();
        setLocationRelativeTo(controller);
        setVisible(true);
    }
}
