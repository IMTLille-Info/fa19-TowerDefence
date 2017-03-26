package telecom.towerdefense.maps;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.utilities.AssetLoader;

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
		for(MobileEntity enemy :  listEnemyUnits) {
			currentMap.getListEnemyUnits().add(enemy);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
	}
}