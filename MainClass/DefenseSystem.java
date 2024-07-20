package MainClass;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import UIWindows.MainController;
import support.FontCreator;
import support.Position;

/**
 * DefenceSystem
 */
public class DefenseSystem {
    public final static Color PrimaryfontColor          = new Color(111, 193, 110);
    public final static Color darkRed                   = new Color(255, 0, 30);
    public final static Color backgroundCor             = new Color(8, 16, 116);
    public final static Position ArmyBasePosition       = new Position (86, 269);
    public final static Position NavyBasePosition       = new Position (262,175);
    public final static Position AirForceBasePosition   = new Position (331, 261);
    public final static Font defenseFont                = new Font("" , 1, 12);
    public static void main(String[] args) {
        MainController.getMainController();        
    }
}