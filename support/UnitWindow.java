package support;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Main.MainController;

public class UnitWindow extends JFrame {
    public UnitWindow(Unit type){
        setTitle("Tank");
        BackgroundAdder.addBackground(this, new ImageIcon("./images/Unit/unitBackT.png"));
        JFrame controller = MainController.getMainController();
        setLocationRelativeTo(controller);
        setVisible(true);
    }
}
