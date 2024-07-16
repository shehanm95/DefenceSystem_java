package support;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import UIWindows.UnitWindow;
import enums.GreenUnitType;
import intefaces.MapMoveable;

public class GreenUnit extends MapUnit implements MapMoveable , ActionListener{
    private Position initialPosition;
    public Position getInitialPosition() {
        return initialPosition;
    }

    private GreenUnitType unitType;
    private String unitName;private UnitWindow unitWindow;
    private Timer timer;
    private static Random random = new Random();
    private EnemyMapUnit nearestEnemy = null;
    private boolean inAttack = false;
    private boolean followingEnemy;
    private int nearestEnemyDistance = Integer.MAX_VALUE;
    private final int ENEMY_DETECT_DISTANCE = 70;
    private static EnemyInstantiateObj enemySpawner = EnemyInstantiateObj.getEnemyInstantiate();
   
    
    //instance block
    {   timer = new Timer(500, this);
        timer.start();
    }

    public String getUnitName() {
        return unitName;
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

    public EnemyMapUnit FindNearestEnemy(){
        Queue<EnemyMapUnit> allEnemies = enemySpawner.getAllEnemyList();
        EnemyMapUnit nearestEnemy = null;
        nearestEnemyDistance = Integer.MAX_VALUE;
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

    public void setUnitWindow(UnitWindow unitWindow){
        this.unitWindow = unitWindow;
    }

    public EnemyMapUnit gEnemyMapUnit(){
        return nearestEnemy;
    }

    public GreenUnitType getGreenUnitType(){
        return unitType;
    }

    public UnitWindow getUnitWindow(){
        return unitWindow;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!inAttack && !followingEnemy){
            FindNearestEnemy();
        }

            if(unitWindow != null) {
                //System.out.println("window detected");
                if(nearestEnemyDistance < ENEMY_DETECT_DISTANCE){
                    unitWindow.updateNearestEnemyDetails(nearestEnemy, nearestEnemyDistance);
                }
                else if (unitWindow != null){
                    unitWindow.updateNearestEnemyDetails(null, nearestEnemyDistance);
                    //System.out.println("nearest Enemy :" + nearestEnemy.getEnemyName() + " : " + nearestEnemyDistance);
                }
                
            }else{
                //System.out.println("window not detected");

            }
    }

    
}
