package telecom.towerdefense.gameobjects.units;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.utilities.AssetLoader;

public class Soldier extends MobileEntity {
	public Soldier() {
		this.texture = AssetLoader.enemyIdle;
	}
}
