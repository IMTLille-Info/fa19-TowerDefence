package telecom.towerdefense.gameobjects;

import telecom.towerdefense.game.TowerDefense;

public abstract class Entity extends GameObject {
	protected int lifePoint;
	protected int rangeAttack;
	protected int speedAttack;
	protected int damageAttack;
	protected int xUnit, yUnit;

	public void attack(Entity target) {
		target.takeDamage(damageAttack);
	}

	public void takeDamage(int damage) {
		lifePoint -= damageAttack;
		if (lifePoint <= 0)
			destroy();
	}
	
	public boolean isInRange(Entity e) {
		float x = this.position.x; //Position de l'entity attaquant
		float y = this.position.y;
		
		if((Math.abs(x - e.position.x) <= (TowerDefense.SCALE * this.rangeAttack)) && (Math.abs(y - e.position.y) <= (TowerDefense.SCALE * this.rangeAttack)))
			return true;
		
		return false;
	}

	public void destroy() {

	}
}
