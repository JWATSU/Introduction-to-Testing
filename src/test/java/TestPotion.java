
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPotion {

	Potion potion = new Potion("speed");
	
	@Test
	public void testIfPotionAndStringObjectIsNotEqual() {
		assertFalse(potion.equals("speed"));
	}
	
	@Test
	public void testIfTwoEqualPotionsHaveSameHashCode() {
		Potion otherPotion = new Potion("Speed");
		assertTrue(potion.equals(otherPotion));
		assertEquals(potion.hashCode(), otherPotion.hashCode());
	}
}
