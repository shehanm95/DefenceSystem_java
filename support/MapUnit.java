package support;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.ImageIcon;

public class MapUnit {
    int x, y;
    ImageIcon icon;
    Queue<Position> positions = new LinkedList<>();
    int speed = 80;
    int steps = 80;

    public MapUnit(int x, int y, ImageIcon icon) {
        this.x = x;
        this.y = y;
        this.icon = icon;
       
    }
    public MapUnit(Position position, ImageIcon icon) {
        this.x = position.getX();
        this.y = position.getY();
        this.icon = icon;

        
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
        //positions.add(this.getPosition());
        int distance = calculateDistance(newPosition);
        steps = calculateSteps(distance);
        System.out.println("final Position : " + newPosition);
        if(Math.round(steps) ==0)steps = 1;
        float Dy =  Math.abs(( (this.y - newPosition.getY()))/steps);
        float Dx = Math.abs((this.x - newPosition.getX())/steps);
        if(this.y > newPosition.getY()){ Dy = -Dy;}
        if(this.x > newPosition.getX()){Dx= -Dx;}

        Position bfrNewPos = this.getPosition();
        int i = 1;
        int lastDist = 1000;
        while(calculateDistance(bfrNewPos, newPosition) < lastDist) {
            lastDist = calculateDistance(bfrNewPos, newPosition);
            bfrNewPos = new Position( (int) (this.x + (Dx* (i+1))), (int) (this.y + (Dy* (i+1))));
            positions.add(bfrNewPos);
            i++;
        }
        // for (int i = 0; i < steps; i++) {
        //     bfrNewPos = new Position( (int) (this.x + (Dx* (i+1))), (int) (this.y + (Dy* (i+1))));
        //     positions.add(bfrNewPos);
        // }
        // int distanceToFinalPos = Math.abs(calculateDistance(bfrNewPos,newPosition));
        // if(distanceToFinalPos > Math.abs(Dx) |distanceToFinalPos > Math.abs(Dy) ){
        //     bfrNewPos = new Position( (int) (bfrNewPos.getX() + Dx), (int) (bfrNewPos.getY() + Dy));
        //     positions.add(bfrNewPos); 
        // }

        // Position bfrNewPos = this.getPosition();
        // int i = 1;
        // System.out.println("before loop called");
        // do{
        //     System.out.println("distance : " + calculateDistance(bfrNewPos, newPosition));
        //     bfrNewPos = new Position( (int) (this.x + (Dx* (i++))), (int) (this.y + (Dy* i)));
        //     positions.add(bfrNewPos);
        // }while(calculateDistance(bfrNewPos, newPosition)> Math.abs(Dx));
        
        positions.add(new Position(newPosition.getX(),newPosition.getY()));
    }

    private int calculateSteps(int distance) {
        //System.out.println("row steps : " + (distance/(394/steps)));

        float div = (394/speed);
        if(Math.round(div) == 0){
            div = 1;
        }
        float tempSteps =  (distance/div); 
        if(Math.round(tempSteps) == 0){
            tempSteps = 1;
        }

        return (int)tempSteps ;}

    public int getIconWidth() {
        return icon.getIconWidth();
    }

    public int getIconHeight() {
        return icon.getIconHeight();
    }


    public int calculateDistance(Position position){
        float xDistance = Math.abs(this.x - position.getX());
        float yDistance = Math.abs(this.y - position.getY());
        
        double distance = Math.sqrt((yDistance*yDistance) + (xDistance*xDistance));
       // System.out.println("Distance : " + distance);
        return (int) distance;

    }
    public int calculateDistance(Position position1, Position position2){
        float xDistance = Math.abs(position2.getX() - position1.getX());
        float yDistance = Math.abs(position2.getY() - position1.getY());
        
        double distance = Math.sqrt((yDistance*yDistance) + (xDistance*xDistance));
       // System.out.println("Distance : " + distance);
        return (int) distance;

    }

    public void setPosition(Position position){
        positions.clear();
        this.x = position.getX();
        this.y = position.getY();
        positions.add(position);
    }

    public boolean checkPositionEquality(Position unit){
        if(this.x == unit.getX() && this.y == unit.getY()) return true;
        return false;
    }

    public Position getPosition(){
        return new Position(this.x, this.y);
    }
    public Image getImage() {return icon.getImage();}
    public int getX() {return this.x;}
    public int getY() {return this.y;}

    

}
