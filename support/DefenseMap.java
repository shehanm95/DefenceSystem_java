package support;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Stack;

import javax.swing.JPanel;

import javax.swing.ImageIcon;

public class DefenseMap extends JPanel implements Runnable {
    private Thread mapThread;
    Stack<MapUnit> units = new Stack<>();
    public DefenseMap() {
        mapThread = new Thread(this);
        mapThread.start();
        units.add(new MapUnit(20, 23, new ImageIcon("./images/unit.png")));

        for (MapUnit mapUnit : units) {
            mapUnit.movePosition(new Position(200, 200));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (MapUnit mapUnit : units){
            int size =(int) (mapUnit.getIconHeight()/4)+1;
            g.drawImage(mapUnit.icon.getImage(), mapUnit.x, mapUnit.y, size,size , this);
           
        }
        

        
    }

    @Override
    public void run() {
        while (true) {
            for (MapUnit mapUnit : units) {
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


    public void addUnitsToMap(){
        //do necessary changes to the class and implement the method effectively
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

