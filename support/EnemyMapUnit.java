package support;

import java.util.Random;

import javax.swing.ImageIcon;

import Main.DefenseSystem;
import intefaces.MapMoveable;

enum EnemyType {ArmyEnemy , NavyEnemy}
public class EnemyMapUnit extends MapUnit implements MapMoveable {

    private Position initialPosition = new Position(0, 0);
    private Position midPosition;
    private Position finalPosition;
    private EnemyType enemyType;
    private  Random random = new Random();
    public EnemyMapUnit() {
        super(-1, -1,  new ImageIcon("./images/enemy.png"));
        setInitialPosition();
        setPosition(initialPosition);
        movePosition(getMidPosition());
        finalPosition = getFinalPosition();
    }

    
    @Override
    public void updatePosition() {
        if(positions.size() != 0){
            Position pos = positions.remove();
            this.x = pos.getX();
            this.y = pos.getY();
           // System.out.println("update FromChild");
        }else if(this.checkPositionEquality(midPosition)){
            movePosition(finalPosition);
        }
      //  System.out.println("update FromChild");
        
      //   System.out.println(this.getPosition());
    }

    public Position setInitialPosition(){
        Position iPos;
        int typeChooser = random.nextInt(3);
        if(typeChooser < 2){enemyType =EnemyType.ArmyEnemy;}
        else{enemyType = EnemyType.NavyEnemy;}
        
        if(enemyType == EnemyType.ArmyEnemy){
            iPos = new Position(random.nextInt(20) + 5, random.nextInt(5)+20);
          
            
        }
        else{ iPos = new Position(random.nextInt(360) + 30, 1);};
        
        System.out.println(iPos + " : " + enemyType.toString());
        return iPos;

    

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
}
