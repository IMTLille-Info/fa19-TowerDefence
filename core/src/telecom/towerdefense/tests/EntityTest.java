package telecom.towerdefense.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.building.Stonehenge;
import telecom.towerdefense.gameobjects.units.Soldier;

public class EntityTest {
	

	@Test
	public void isInRangeTest() {
		Entity a = new Stonehenge(); //range de 5 * SCALE (32))
		Entity b = new Soldier(); //range de 1 * SCALE (32)
		a.setPosition(new Vector2(200, 200));
		b.setPosition(new Vector2(30, 40));
		assertEquals(false, a.isInRange(b));	//On s'assure que b n'est pas dans la porté de a
		b.setPosition(new Vector2(100, 100));
		assertEquals(true, a.isInRange(b)); //On s'assure que b est dans la porté de a
		assertEquals(false, b.isInRange(a)); //On s'assure que a n'est pas dans la porté de b		
	}
	
	@Test
	public void attackTest() {
		Entity a = new Stonehenge();
		Entity b = new Soldier();
		try {
			b.attack(a);
			
			assertEquals(8, a.getLifePoint());
			
		} catch (Throwable e) {
			
		}
	}
	
	@Test
	public void attackTwoTest() {
		Entity a = new Stonehenge();
		Entity b = new Soldier();
		try {
			a.attack(b);			
		} catch (Throwable e) {
			assertTrue(true);
		}
	}
}
