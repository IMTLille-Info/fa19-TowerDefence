package telecom.towerdefense.gameobjects.building;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.utilities.AssetLoader;

public class Stonehenge extends Entity {
	public Stonehenge() {
		this.texture = AssetLoader.stoneHenge;
		this.damageAttack = 5;
		this.speedAttack = 1;
		this.rangeAttack = 5;
		this.maxLifePoint = 10;
		this.lifePoint = maxLifePoint;
		this.xUnit = 1;
		this.yUnit = 1;
		this.manaCost = 50;
		this.manaWin = 0;
	}
}
