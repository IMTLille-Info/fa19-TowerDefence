package telecom.towerdefense.maps;

import java.util.List;

import telecom.towerdefense.gameobjects.MobileEntity;

public class Wave implements Runnable {

	private Map currentMap;
	private AI currentAi;
	private List<MobileEntity> listEnemyUnits;

	public Wave(Map currentMap, AI currentAi, List<MobileEntity> listEnemyUnits) {
		this.currentAi = currentAi;
		this.currentMap = currentMap;
		this.listEnemyUnits = listEnemyUnits;
	}

	@Override
	public void run() {
		for (MobileEntity enemy : listEnemyUnits) {
			currentMap.getListEnemyUnits().add(enemy);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
	}
}