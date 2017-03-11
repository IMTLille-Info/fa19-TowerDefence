package telecom.towerdefense.maps;

import com.badlogic.gdx.Gdx;

import telecom.towerdefense.gameobjects.MobileEntity;

public class AI {

	private Map currentMap = null;

	public AI(Map currentMap) {
		this.currentMap = currentMap;
	}

	public void updateEnemyUnit() {
		for (MobileEntity enemy : currentMap.getListEnemyUnits()) {
			
			//Mise à jour de la position en fonction de la direction
			enemy.move();
		}
	}

	public void updateUnit() {

	}

	public void updateBuilding() {

	}

	public Map getMap() {
		return currentMap;
	}

	public void setMap(Map currentMap) {
		this.currentMap = currentMap;
	}

}
