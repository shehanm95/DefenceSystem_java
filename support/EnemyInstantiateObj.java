package support;


public class EnemyInstantiateObj implements Runnable {
    
    Thread instantiateThread = new Thread(this);
    DefenseMap map = DefenseMap.getDefenseMap();
    private static EnemyInstantiateObj obj = null;
    private EnemyInstantiateObj(){
        instantiateThread.start();
    }

    public static EnemyInstantiateObj getEnemyInstantiate(){
        if (obj == null) {
            obj = new EnemyInstantiateObj();
        }
        return obj;
    }
    @Override
    public void run() {
        
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        while (true) {
            try {
                EnemyMapUnit enemy = getNewEnemy();
                map.addUnitsToMap(enemy);
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private EnemyMapUnit getNewEnemy() {
        EnemyMapUnit enemy = new EnemyMapUnit();
        return enemy;
        
    }


}
