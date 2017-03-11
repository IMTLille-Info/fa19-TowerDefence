package telecom.towerdefense.gameobjects.units;

import com.badlogic.gdx.graphics.g2d.Animation;

import telecom.towerdefense.gameobjects.Entity;
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
	}
}
