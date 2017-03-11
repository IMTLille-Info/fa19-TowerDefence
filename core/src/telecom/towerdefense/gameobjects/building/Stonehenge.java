package telecom.towerdefense.gameobjects.building;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.utilities.AssetLoader;

public class Stonehenge extends Entity {
	public Stonehenge() {
		this.texture = AssetLoader.stoneHenge;
		this.damageAttack = 5;
		this.speedAttack = 1;
		this.rangeAttack = 5;
		this.lifePoint = 10;
	}
}
