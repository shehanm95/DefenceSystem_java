package support;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;

public class MapUnit {
    int x, y;
    ImageIcon icon;
    Queue<Position> positions = new LinkedList<>();

    public MapUnit(int x, int y, ImageIcon icon) {
        this.x = x;
        this.y = y;
        this.icon = icon;

        
        
        for (Position position : positions) {
            System.out.println(position.getX());
        }
    }

    public void updatePosition(){
        if(!positions.isEmpty()){
            Position pos = positions.remove();
            this.x = pos.getX();
            this.y = pos.getY();
        }
    }


    public void movePosition(Position newPosition){
        positions.clear();
        float Dy =  Math.abs(( (this.y - newPosition.getY()))/20);
        float Dx = Math.abs((this.x - newPosition.getX())/20);
        if(this.y > newPosition.getY()){ Dy = Dy* -1;}
        if(this.x > newPosition.getX()){Dx*=-1;}

        for (int i = 0; i < 20; i++) {
            positions.add(new Position((int) (this.x + (Dx* (i+1))),(int) (this.y + (Dy* (i+1)))));
        }
        positions.add(new Position(newPosition.getX(),newPosition.getY()));
    }

    public int getIconWidth() {
        return icon.getIconWidth();
    }

    public int getIconHeight() {
        return icon.getIconHeight();
    }
}
