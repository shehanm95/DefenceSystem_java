package support;

import java.util.Random;

import javax.swing.ImageIcon;

import Main.DefenseSystem;
import enums.EnemyType;
import intefaces.MapMoveable;


public class EnemyMapUnit extends MapUnit implements MapMoveable {

    private Position initialPosition = new Position(0, 0);
    private Position midPosition;
    private Position finalPosition;
    private EnemyType enemyType;
    private static  Random random = new Random();
    private static int enemyTankCount = 0, enemyShipCount = 0;
    private String enemyUnitName;



    public EnemyMapUnit(PositionNType pNt) {
        super(pNt.getPosition(),  new ImageIcon("./images/enemy.png"));
        enemyType = pNt.getType();
        initialPosition = pNt.getPosition();
        //setInitialPosition();
        //setPosition(initialPosition);
        getMidPosition();
        getFinalPosition();
        typeCountIncrementorAndNameSetter();
        changeSpeed(100);
       
        
    }

    private void typeCountIncrementorAndNameSetter(){
        if(finalPosition == DefenseSystem.AirForceBasePosition || finalPosition == DefenseSystem.ArmyBasePosition){
            enemyTankCount++;
            enemyUnitName = "Enemy Tank " + String.format(" %02d",enemyTankCount);
        }else{
            enemyShipCount++;
            enemyUnitName = "Enemy Ship " + String.format(" %02d",enemyShipCount);
        }
    }


    public String getEnemyName(){
        return enemyUnitName;
    }





    
    @Override
    public void updatePosition() {
        if(positions.size() != 0){
            Position pos = positions.remove();
            this.x = pos.getX();
            this.y = pos.getY();
           //System.out.println("update FromChild");
        }else if(this.checkPositionEquality(initialPosition)){
            movePosition(midPosition);
        }
        else if(this.checkPositionEquality(midPosition)){
            movePosition(finalPosition);
        }
      //System.out.println("update FromChild");
        
      //System.out.println(this.getPosition());
    }

    public Position setInitialPosition(){
        return new Position(0,0);
    }

    public Position getMidPosition(){
        if(enemyType == EnemyType.ArmyEnemy){
            int xPos = random.nextInt(140)+21;
            int YPos = random.nextInt(50)+160;
            midPosition = new Position(xPos, YPos);
        }
        else{
            int xPos = random.nextInt(134)+195;
            int YPos = random.nextInt(20)+80;
            midPosition = new Position(xPos, YPos);
        }
        return midPosition;
    }
    public Position getFinalPosition(){
        if(enemyType == EnemyType.ArmyEnemy){
            if(random.nextInt(2)==0){
            finalPosition = DefenseSystem.AirForceBasePosition;
            }
            else{ finalPosition = DefenseSystem.ArmyBasePosition;}
        }
        else{
            finalPosition = DefenseSystem.NavyBasePosition;
        }
        return finalPosition;
    }  
    
    
    public static PositionNType SetInitialPositionAndType(){
        Position iPos = null;
        EnemyType enemyType = null;
        int typeChooser = random.nextInt(3);
        if(typeChooser < 2){enemyType =EnemyType.ArmyEnemy;}
        else{enemyType = EnemyType.NavyEnemy;}
        
        if(enemyType == EnemyType.ArmyEnemy){
            iPos = new Position(random.nextInt(20) + 5, random.nextInt(5)+20);
          
            
        }
        else{ iPos = new Position(random.nextInt(360) + 30, 1);};
        
      //System.out.println(iPos + " : " + enemyType.toString());
        return new PositionNType(iPos, enemyType);
    }
}
