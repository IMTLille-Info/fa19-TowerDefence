package telecom.towerdefense.gameobjects;

import com.badlogic.gdx.Gdx;
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
	
	public void move() {
		if(!direction.isZero()) {
			direction.nor();
			if(direction.x == 1 && direction.y == 0) //Vers la droite
				position.x += (speed * Gdx.graphics.getDeltaTime());
			else if (direction.x == -1 && direction.y == 0)
				position.x -= (speed * Gdx.graphics.getDeltaTime());
			else if (direction.x == 0 && direction.y == 1)
				position.y += (speed * Gdx.graphics.getDeltaTime());
			else if (direction.x == 0 && direction.y == -1)
				position.y -= (speed * Gdx.graphics.getDeltaTime());
		}
	}

	public Animation getCurrentAnimation() {
		direction.nor();
		if (direction.x == 1 && direction.y == 0)
			return this.animateRight;
		else if (direction.x == 0 && direction.y == 1)
			return this.animateUp;
		else if (direction.x == -1 && direction.y == 0)
			return this.animateLeft;
		else if (direction.x == 0 && direction.y == -1)
			return this.animateDown;

		return null;
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
