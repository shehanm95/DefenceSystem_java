package support;

import java.util.Random;

import javax.swing.ImageIcon;

import enums.GreenUnitType;
import intefaces.MapMoveable;

public class GreenUnit extends MapUnit implements MapMoveable{
    private Position initialPosition;
    private GreenUnitType unitType;
    private static Random random = new Random();
    public GreenUnit(int x, int y, ImageIcon icon, GreenUnitType greenUnitType) {
        super(x, y, icon);
        unitType = greenUnitType;
        movePosition(setInitialPosition());
        System.out.println("this called");
    }

    public GreenUnit(Position position, ImageIcon image, GreenUnitType greenUnitType) {
        super( position.getX(),position.getY(),image);
        unitType = greenUnitType;
        movePosition(setInitialPosition());
    }

    public void backToTheBase(){
        movePosition(initialPosition);
    }

    @Override
    public Position setInitialPosition() {
        Position spawnPos = this.getPosition();
        initialPosition = new Position(random.nextInt(25)+spawnPos.getX()-25, spawnPos.getY()-18);
        return initialPosition;

    }
    
}
