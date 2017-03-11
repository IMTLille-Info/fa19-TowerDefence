package telecom.towerdefense.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public abstract class MobileEntity extends Entity {
	protected Vector2 direction;
	protected double speed;
	protected Animation animateLeft, animateRight, animateUp, animateDown, animateAttack;
	
	public MobileEntity() {
		direction = new Vector2(0, 0);
		speed = 1;
	}
	
	public Animation getCurrentAnimation() {
		return this.animateRight;
	}
	
	public Vector2 getDirection() {
		return direction;
	}
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
