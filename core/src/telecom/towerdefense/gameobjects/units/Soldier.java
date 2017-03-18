package telecom.towerdefense.gameobjects.units;

import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.utilities.AssetLoader;

public class Soldier extends MobileEntity {

	public Soldier() {
		// super();
		this.texture = AssetLoader.soldierIdle;
		this.animateRight = AssetLoader.soldierRight;
		this.animateLeft = AssetLoader.soldierLeft;
		this.animateUp = AssetLoader.soldierUp;
		this.animateDown = AssetLoader.soldierDown;

		this.speed = 50;
		this.damageAttack = 2;
		this.lifePoint = 15;
		this.rangeAttack = 1;
		this.speedAttack = 1;
	}
}
