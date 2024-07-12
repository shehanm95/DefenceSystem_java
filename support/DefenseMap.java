package support;

import java.awt.Graphics;
import java.util.Stack;

import javax.swing.JPanel;

import intefaces.MapMoveable;

public class DefenseMap extends JPanel implements Runnable {
    private Thread mapThread;
    Stack<MapMoveable> units = new Stack<>();
    
    private static DefenseMap map = null;
    private DefenseMap() {
        mapThread = new Thread(this);
        mapThread.start();
        setOpaque(false);
        
       
    }
    public static DefenseMap getDefenseMap(){
        if(map == null) map = new DefenseMap();
        return map;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (MapMoveable mapUnit : units){
            int size =(int) (mapUnit.getIconHeight()/4)+1;
            g.drawImage(mapUnit.getImage(), mapUnit.getX(), mapUnit.getY(), size,size , this);
        }
    }

    @Override
    public void run() {
        while (true) {
            for (MapMoveable mapUnit : units) {
                mapUnit.updatePosition();
               // System.out.println(mapUnit.x + " "+ mapUnit.y);
            }
            repaint();
            try {
                Thread.sleep(1000); // Adjust the sleep time to control the update rate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void addUnitsToMap(MapUnit unit){
        units.add((MapMoveable) unit);
    }
    public void addUnitsToMap(EnemyMapUnit unit){
        units.add(unit);
    }
    public void removeUnitsFromMap(){
        //do necessary changes to the class and implement the method effectively
    }
    public void calculateDistance(MapUnit unit1, MapUnit unit2){
        //do necessary changes to the class and implement the method effectively
    }
    public void moveTowards(MapUnit unit1, int positionX, int positionY){
        //do necessary changes to the class and implement the method effectively
    }

}

