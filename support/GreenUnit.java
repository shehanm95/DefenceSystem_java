package support;

import java.util.Queue;
import java.util.Random;

import javax.swing.ImageIcon;

import enums.GreenUnitType;
import intefaces.MapMoveable;

public class GreenUnit extends MapUnit implements MapMoveable{
    private Position initialPosition;
    private GreenUnitType unitType;
    private String unitName;
    private static Random random = new Random();
    private static EnemyInstantiateObj enemySpawner = EnemyInstantiateObj.getEnemyInstantiate();
    public GreenUnit(int x, int y, ImageIcon icon, GreenUnitType greenUnitType, String unitName) {
        super(x, y, icon);
        unitType = greenUnitType;
        movePosition(setInitialPosition());
        System.out.println("this called");
        this.unitName = unitName;
    }

    public GreenUnit(Position position, ImageIcon image, GreenUnitType greenUnitType, String unitName) {
        super( position.getX(),position.getY(),image);
        unitType = greenUnitType;
        movePosition(setInitialPosition());
        this.unitName = unitName;
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

    public EnemyMapUnit getNearestEnemy(){
        Queue<EnemyMapUnit> allEnemies = enemySpawner.getAllEnemyList();
        EnemyMapUnit nearestEnemy = null;
        int nearestEnemyDistance = Integer.MAX_VALUE;
        if(allEnemies.size() >0){
            for (EnemyMapUnit enemyMapUnit : allEnemies) {
                // if(this.unitType == GreenUnitType.Army){

                // }
                int consideringEnemyDistance = this.calculateDistance(enemyMapUnit.getPosition());
                if(nearestEnemyDistance > consideringEnemyDistance){
                    nearestEnemyDistance = consideringEnemyDistance;
                    nearestEnemy = enemyMapUnit;
                }
            }
            return nearestEnemy;
        }
        return null;
    }
    
}
