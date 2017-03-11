package telecom.towerdefense.gameobjects;

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
		if(lifePoint <= 0) destroy();
	}
	
	public void destroy() {
		
	}
}
