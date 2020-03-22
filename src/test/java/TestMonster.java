import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestMonster {
	Item item = new Item();
	Monster monster = new Monster(2, 1, item);

	@Test
	public void testGetLife() {
		assertEquals(monster.getHealth(), 2);
	}
	
	@Test
	public void testGetStrength() {
		assertEquals(monster.getStrength(), 1);
	}
	
	@Test
	public void testGetItem() {
		assertEquals(monster.getItem(), item);
	}
	
	@Test
	public void testRemoveLifes() {
		monster.removeHealth(1);
		assertEquals(monster.getHealth(), 1);
		
	}
	
	@Test
	public void testDies() {
		monster.removeHealth(2);
		assertTrue(monster.dies());
	}
}
