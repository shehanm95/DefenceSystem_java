package MainClass;

import java.awt.Color;

import UIWindows.MainController;
import support.Position;

/**
 * DefenceSystem
 */
public class DefenseSystem {
    public final static Color PrimaryfontColor = new Color(111, 193, 110);
    public final static Position ArmyBasePosition = new Position (86, 269);
    public final static Position NavyBasePosition = new Position (262,175);
    public final static Position AirForceBasePosition = new Position (331, 261);
    public static void main(String[] args) {
        MainController.getMainController();        
    }
}