package support;

import java.util.LinkedList;
import java.util.Queue;

import UIWindows.MainController;

public class EnemyInstantiateObj implements Runnable {
    
    Thread instantiateThread = new Thread(this);
    DefenseMap map = DefenseMap.getDefenseMap();
    private long spawnTime = 28000;
    private boolean scanning;
    private static EnemyInstantiateObj obj = null;
    private EnemyInstantiateObj(){
        instantiateThread.start();
    }
    private static Queue<EnemyMapUnit> allEnemiesInMap = new LinkedList<>();

    public static EnemyInstantiateObj getEnemyInstantiate(){
        if (obj == null) {
            obj = new EnemyInstantiateObj();
        }
        return obj;
    }

    public Queue<EnemyMapUnit> getAllEnemyList(){
        return allEnemiesInMap;
    }

    @Override
    public void run() {
        
      if(scanning){
        System.out.println("entered");
        try {
            Thread.sleep(10000);
            //MainController.getMainController().scanArea();
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (true) {
                System.out.println("entered");
                try {
                    EnemyMapUnit enemy = getNewEnemy();
                    map.addUnitsToMap(enemy);
                    Thread.sleep(getTimeToSpawn());
                    MainController.getMainController().setEnemyDetectedLabelActive();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
      }
    }


    


    private long getTimeToSpawn() {

        if(spawnTime > 15000) spawnTime -= 1000;
        return spawnTime;
    }

    private EnemyMapUnit getNewEnemy() {
        EnemyMapUnit enemy = new EnemyMapUnit(EnemyMapUnit.SetInitialPositionAndType());
        allEnemiesInMap.add(enemy);
        return enemy;
    }

    public void instantiate() {
       
        scanning = true;
      
    }


}
