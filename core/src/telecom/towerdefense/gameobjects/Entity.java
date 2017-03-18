package telecom.towerdefense.gameobjects;

import java.sql.Timestamp;

import telecom.towerdefense.game.TowerDefense;

public abstract class Entity extends GameObject {
	protected int lifePoint;
	protected int maxLifePoint;
	protected int rangeAttack;
	protected int speedAttack;
	protected int damageAttack;
	protected int xUnit, yUnit;

	public int getxUnit() {
		return xUnit;
	}

	public int getyUnit() {
		return yUnit;
	}

	private Timestamp lastAttackTime = null;

	public void attack(Entity target) throws Exception {
		if (lastAttackTime == null) {
			lastAttackTime = new Timestamp(System.currentTimeMillis());
		}

		if ((System.currentTimeMillis() - lastAttackTime.getTime()) >= (speedAttack * 1000f)) {
			target.takeDamage(damageAttack);
			lastAttackTime = new Timestamp(System.currentTimeMillis());
		}

		/*
		 * int newLife = target.getLifePoint() - damageAttack; if (newLife <= 0)
		 * throw new Exception(); target.setLifePoint(newLife);
		 */
	}

	public void takeDamage(int damage) throws Exception {
		lifePoint -= damage;
		if (lifePoint <= 0)
			throw new Exception();
	}

	public boolean isInRange(Entity e) {
		float x = this.position.x; // Position de l'entity attaquant
		float y = this.position.y;

		if ((Math.abs(x - e.position.x) <= (TowerDefense.SCALE * this.rangeAttack))
				&& (Math.abs(y - e.position.y) <= (TowerDefense.SCALE * this.rangeAttack)))
			return true;

		return false;
	}

	public int getLifePoint() {
		return lifePoint;
	}

	public void setLifePoint(int lifePoint) {
		this.lifePoint = lifePoint;
	}

	public int getRangeAttack() {
		return rangeAttack;
	}

	public void setRangeAttack(int rangeAttack) {
		this.rangeAttack = rangeAttack;
	}

	public int getSpeedAttack() {
		return speedAttack;
	}

	public void setSpeedAttack(int speedAttack) {
		this.speedAttack = speedAttack;
	}

	public int getDamageAttack() {
		return damageAttack;
	}

	public void setDamageAttack(int damageAttack) {
		this.damageAttack = damageAttack;
	}

	public int getMaxLifePoint() {
		return maxLifePoint;
	}
	
	

}
