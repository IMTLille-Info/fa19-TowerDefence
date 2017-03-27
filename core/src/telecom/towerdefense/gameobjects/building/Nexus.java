package telecom.towerdefense.gameobjects.building;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.maps.Map;
import telecom.towerdefense.utilities.AssetLoader;

public class Nexus extends Entity {
	public Nexus() {
		this.texture = AssetLoader.nexus;

		this.maxLifePoint = 50;
		this.lifePoint = maxLifePoint;
		this.xUnit = 2;
		this.yUnit = 2;
		this.manaCost = Map.MANA_MAX + 1;
		this.manaWin = 0;
	}
}
