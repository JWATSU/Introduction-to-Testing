import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCharacter {

    Character character = new Character(9, 1, 100, 3);

    @Test
    public void TestLifeInCharacter() {
        assertEquals(9, character.getLife());
    }

    @Test
    public void TestSpeedInCharacter() {
        assertEquals(1, character.getSpeed());

    }

    @Test
    public void addingLifesOverMax() {
		character.addLife(100);
        assertEquals(10, character.getLife());

    }

    @Test
    public void addingLifesUnderMax() {
        assertTrue(character.addLife(1));

    }

    @Test
    public void testIfLifesAreAdded() {
        character.addLife(1);
        assertEquals(10, character.getLife());
    }

    @Test
    public void removingLifes() {
        assertTrue(character.removeLife());

    }

    @Test
    public void testIfLifesAreRemoved() {
        character.removeLife();
        assertEquals(8, character.getLife());
    }
    @Test
	public void speedIncreaseUnderMaxLimit()
	{
		assertTrue(character.increaseSpeed(2));

	}
	@Test
	public void speedIncreaseOverMaxLimit()
	{
		assertFalse(character.increaseSpeed(10));
	}
	@Test
	public void testIfSpeedIncreaseIsCorrect()
	{
		character.increaseSpeed(2);
		assertEquals(3, character.getSpeed(), "Current speed is: "+character.getSpeed());
	}
	@Test
	public void addingNegativeLifes()
	{
		assertFalse(character.addLife(-1));
	}
	@Test
	public void removingLifeWhenLifeAtZero()
	{
		Character c = new Character(0, 1, 1, 1);
		assertFalse(c.removeLife());
	}
	@Test
	public void increasingWithNegativeSpeed()
	{
		assertFalse(character.increaseSpeed(-1));
	}
	@Test
	public void decreasingWithNegativeSpeed()
	{
		assertFalse(character.decreaseSpeed(-1));
	}

	@Test
	public void decreaseSpeedUnderMinLimit()
	{
    	assertFalse(character.decreaseSpeed(2));
	}

	@Test
	public void decreaseSpeedOverMinLimit()
	{
		character.increaseSpeed(1);
    	assertTrue(character.decreaseSpeed(1));
	}

	@Test
	public void testIfSpeedDecreaseIsCorrect()
	{
    	character.increaseSpeed(2);
    	character.decreaseSpeed(1);
    	assertEquals(2,character.getSpeed());
	}
	
	@Test
	public void testmoveDown() {
		character.moveDown();
		assertEquals(character.getCurrentPosition(), new Position(0, 1));
	}
	
	@Test
	public void testMovingRight() {
		character.moveRight();
		assertEquals(character.getCurrentPosition(), new Position(1, 0));
	}
	
//	@Test
//	public void testMovingHorizontalWithSpeedAtTwo() {
//		character.increaseSpeed(1);
//		character.moveRight();
//		assertEquals(character.getCurrentPosition(), new Position(2, 0));
//	}
	
	@Test
	public void testLevelingUpXpToZeroAndChangeOfLevel() {
		character.addXP(200);
		
		assertEquals(0, character.getXP());
		assertEquals(2, character.getLevel());
	}
	
	@Test
	public void testLevelingUpXpNotZeroAndChangeOfLevel() {
		character.addXP(350);
		
		assertEquals(3, character.getLevel());
		assertEquals(50, character.getXP());
	}

	@Test
	public void testAddingOddPointsToAttributesWhenLevelingUp() {
		character.removeHealth(2);
		character.addXP(300);
		
		assertEquals(100, character.getHealth());
		assertEquals(4, character.getStrength());
		assertEquals(3, character.getLevel());
		
	}
	
	@Test
	public void testAddingEvenPointsToAttributesWhenLevelingUp() {
		character.removeHealth(1);
		character.addXP(200);
		
		assertEquals(100, character.getHealth());
		assertEquals(4, character.getStrength());
		assertEquals(2, character.getLevel());
		
	}
	
	@Test
	public void testRemovingNegativeAmountOfHealth(){
		assertFalse(character.removeHealth(-1));
	}
	
	@Test
	public void testRemovingPositivAmountOfHealth() {
		assertTrue(character.removeHealth(1));
		assertEquals(99, character.getHealth());
	}
	
	@Test
	public void testAddingNegativeHealth() {
		assertFalse(character.addHealth(-1));
	}
	
	@Test
	public void testAddingPositiveHealthBelowMaxHealthLimit() {
		character.removeHealth(50);
		assertTrue(character.addHealth(40));
		assertEquals(90, character.getHealth());
	}
	
	@Test
	public void testAddingPositiveHealthAboveMaxHealthLimit() {
		assertTrue(character.addHealth(1));
	}
	
	@Test
	public void testRemovingNegativeAmountOfStrength(){
		assertFalse(character.removeStrength(-1));
	}
	
	@Test
	public void testRemovingPositivAmountOfStrength() {
		assertTrue(character.removeStrength(1));
		assertEquals(2, character.getStrength());
	}
	
	@Test
	public void testAddingNegativeStrength() {
		assertFalse(character.addStrength(-1));
	}
	
	@Test
	public void testAddingPositiveStrengthBelowMaxStrengthLimit() {
		assertTrue(character.addStrength(2));
		assertEquals(5, character.getStrength());
	}
	
	@Test
	public void testAddingPositiveStrengthAboveMaxStrengthLimit() {
		assertTrue(character.addStrength(8));
	}

	@Test
	public void testAddingNegativeAmountOfXP() {
		assertFalse(character.addXP(-1));
	}
	
	@Test
	public void testAddingNonPositiveAmountOfXP() {
		assertTrue(character.addXP(1));
	}
	
	@Test
	public void testToRemoveMoreStrengthThanCreatureHas() {
		assertTrue(character.removeStrength(4));
		assertEquals(0, character.getStrength());
	}
	
	@Test
	public void testCreatingCharacterWithNegativeLife() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Character(-1, 1, 1, 1);
		});
	}
	
	@Test
	public void testCreatingCharacterWithNegativeSpeed() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Character(1, -1, 1, 1);
		});
	}
	
	@Test
	public void testCreatingCharacterWithNegativeHealth() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Character(1, 1, -1, 1);
		});
	}
	
	@Test
	public void testCreatingCharacterWithNegativeStrength() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Character(1, 1, 1, -1);
		});
	}

}
