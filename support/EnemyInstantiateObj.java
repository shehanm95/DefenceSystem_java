package support;

import java.util.LinkedList;
import java.util.Queue;

import UIWindows.MainController;

public class EnemyInstantiateObj implements Runnable {
    
    Thread instantiateThread = new Thread(this);
    DefenseMap map = DefenseMap.getDefenseMap();
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
        
            try {
                Thread.sleep(12);
                //MainController.getMainController().scanArea();
               
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        while (true) {
            try {
                EnemyMapUnit enemy = getNewEnemy();
                map.addUnitsToMap(enemy);
                Thread.sleep(10000);
                MainController.getMainController().setEnemyDetectedLabelActive();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private EnemyMapUnit getNewEnemy() {
        EnemyMapUnit enemy = new EnemyMapUnit(EnemyMapUnit.SetInitialPositionAndType());
        allEnemiesInMap.add(enemy);
        return enemy;
    }


}
