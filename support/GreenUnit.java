package support;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import enums.GreenUnitType;
import intefaces.MapMoveable;

public class GreenUnit extends MapUnit implements MapMoveable , ActionListener{
    private Position initialPosition;
    public Position getInitialPosition() {
        return initialPosition;
    }

    private GreenUnitType unitType;
    private String unitName;
    private Timer timer;
    private static Random random = new Random();
    EnemyMapUnit nearestEnemy = null;
    private boolean inAttack = false;
    private boolean followingEnemy;
    private static EnemyInstantiateObj enemySpawner = EnemyInstantiateObj.getEnemyInstantiate();
   
    
    //instance block
    {   timer = new Timer(500, this);
        timer.start();
    }
    
    public GreenUnit(int x, int y, ImageIcon icon, GreenUnitType greenUnitType, String unitName) {
        super(x, y, icon);
        unitType = greenUnitType;
        movePosition(setInitialPosition());
     //   System.out.println("this called");
        this.unitName = unitName;
        this.setSpeed();
    }

    public GreenUnit(Position position, ImageIcon image, GreenUnitType greenUnitType, String unitName) {
        super( position.getX(),position.getY(),image);
        unitType = greenUnitType;
        movePosition(setInitialPosition());
        this.unitName = unitName;
        this.setSpeed();
    }

    public void backToTheBase(){
        movePosition(initialPosition);
    }

    private void setSpeed(){
        if(unitType == GreenUnitType.Helicopter)changeSpeed(45);
        if(unitType == GreenUnitType.Tank)changeSpeed(70);
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
            this.nearestEnemy = nearestEnemy;
            return nearestEnemy;
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!inAttack && !followingEnemy){
            getNearestEnemy();
            // if(nearestEnemy.getEnemyName() != null) System.out.println("from GreenMApUnit nearest Enemy to " + this.unitName + " : " + nearestEnemy.getEnemyName());
        }
    }

    

    
}
