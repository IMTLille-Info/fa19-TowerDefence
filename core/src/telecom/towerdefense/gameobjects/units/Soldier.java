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
		this.maxLifePoint = 15;
		this.lifePoint = maxLifePoint;
		this.rangeAttack = 2;
		this.speedAttack = 1;
		this.xUnit = 1;
		this.yUnit = 1;
	}
}
