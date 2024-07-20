package support;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import UIWindows.UnitWindow;
import enums.GreenUnitType;
import enums.HeavyWeapon;
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
    private int soldierCount = 20;
    private int ammoCount = 2000;
    private int heavyWeaponAmmoCount = 20;
    private HeavyWeapon heavyWeapon;
    private static EnemyInstantiateObj enemySpawner = EnemyInstantiateObj.getEnemyInstantiate();
   
    
    //instance block
    {   timer = new Timer(500, this);
        timer.start();
        setDeathIcon(new ImageIcon("./images/deathUnit.png"));
    }

    public String getUnitName() {
        return unitName;
    }
    
    public GreenUnit(int x, int y, ImageIcon icon, GreenUnitType greenUnitType, String unitName) {
        super(x, y,new ImageIcon("./images/unit.png"));
        unitType = greenUnitType;
        setHeavyWeapon(greenUnitType);
        movePosition(setInitialPosition());
     //   System.out.println("this called");
        this.unitName = unitName;
        this.setSpeed();
    }



    public GreenUnit(Position position, ImageIcon image, GreenUnitType greenUnitType, String unitName) {
        super( position.getX(),position.getY(),new ImageIcon("./images/unit.png"));
        unitType = greenUnitType;
        setHeavyWeapon(greenUnitType);
        movePosition(setInitialPosition());
        this.unitName = unitName;
        this.setSpeed();
    }

    public void backToTheBase(){
        movePosition(initialPosition);
    }

    private void setSpeed(){
        if(unitType == GreenUnitType.Helicopter)changeSpeed(20); // default 45  
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
               if(enemyMapUnit.isDeath() == false){
                int consideringEnemyDistance = this.calculateDistance(enemyMapUnit.getPosition());
                if(nearestEnemyDistance > consideringEnemyDistance){
                    nearestEnemyDistance = consideringEnemyDistance;
                    nearestEnemy = enemyMapUnit;
                }
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

    public String getUnitDisplayName() {
        return unitType + unitName;
    }

    public int getSoldierCountStrengthCount() {
        int energy = this.getEnergy();
        if(energy <= 0){
            soldierCount = 0;
        }else{
            soldierCount = (int) energy/5;
        }
        return soldierCount;
    }
    public HeavyWeapon getHeavyWeapon() {
        return heavyWeapon;
    }
    public String setUnitDisplayName() {
        return unitType + unitName;
    }

    public void setAmmoCount(int ammoCount) {
       this.ammoCount = ammoCount;
    }
    public int getAmmoCount() {
       return ammoCount;
    }

    public void setHeavyWeapon(GreenUnitType unityType) {
        if(unityType == GreenUnitType.Helicopter){
            heavyWeapon = HeavyWeapon.Missile;
        } else if(unityType == GreenUnitType.Submarine){
            heavyWeapon = HeavyWeapon.Torpedo;
        }else if (unityType == GreenUnitType.Tank){
            heavyWeapon = HeavyWeapon.Shell;
        }
    }

    public void reduceAmmoCount() {
        if(ammoCount > 0){
            ammoCount--;
        }
    }

    public int getHeavyWeaponAmmoCount() {
        return heavyWeaponAmmoCount;
    }

    public void reduceHeavyWeaponAmmoCount() {
        if(heavyWeaponAmmoCount > 0){
            heavyWeaponAmmoCount--;
        }
    }

    
}
