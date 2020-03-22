import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestLevel {

	Character character = new Character(3, 1, 100, 3);
    Level level = new Level(new Position(2,2),4,3, character, "Levelns namn");

    Item strengthItem = new Item(0,0,1);
    Item testItem = new Item(10,0, 0);
    @Test
    public void testIfCharacterAtEnd() {
  //  	assertNotEquals(

    }
    
    @Test
    public void testCheckInput() {
    	assertTrue(level.checkInput("a"));
    	assertTrue(level.checkInput("w"));
    	assertTrue(level.checkInput("s"));
    	assertTrue(level.checkInput("d"));
    	assertFalse(level.checkInput("q"));
    }
    
    @Test
    public void testMoveCharacterRight() {
    	level.moveCharacter("d");
    	assertEquals(level.character.getCurrentPosition(), new Position(1, 0));
    }
    
    @Test
    public void testMoveCharacterDown() {
    	level.moveCharacter("s");
    	assertEquals(level.character.getCurrentPosition(), new Position(0, 1));
    }
    
    @Test
    public void testMoveCharacterWithUnvalidMove() {
    	Position formerPos = level.character.getCurrentPosition();
    	level.moveCharacter("a");
    	assertEquals(formerPos, level.character.getCurrentPosition());
    }
    
    @Test
    public void testIsMovePossibleWhenMovingTheLeftOutsideOfMap() {
    	assertFalse(level.isMovePossible("a"));
    }
    
    @Test
    public void testIsMovePossibleWhenMovingUpOutsideOfMap() {
    	assertFalse(level.isMovePossible("w"));
    }
    
    @Test
    public void testIsMovePossibleWhenMovingDownOutsideOfMap() {
    	level.moveCharacter("s");
    	level.moveCharacter("s");
    	assertFalse(level.isMovePossible("s"));
    }
    
    @Test
    public void testIsMovePossibleWhenMovingRightOutsideOfMap() {
    	level.moveCharacter("d");
    	level.moveCharacter("d");
    	level.moveCharacter("d");
    	assertFalse(level.isMovePossible("d"));
    }
    
    @Test
    public void testIsMapElementOccupied() {
    	Position pos = level.character.getCurrentPosition();
    	assertTrue(level.isMapPosOccupied(pos));
    }

 //   @Test
  //  public void testUseLifeItem(){
   //     level.takeItem();
    //    assertEquals(4,level.character.getLife());
   // }
    
    @Test
    public void testFightWhenMonsterDies(){

    	level.attack(level.character, level.monster);
    	level.attack(level.monster, level.character);

//    	level.playerAttacks(level.character, level.getMonster());
//    	level.monsterAttacks(level.getMonster(), level.character);
    	level.character.useItem(testItem);

    	level.attack(level.character, level.monster);

//    	level.playerAttacks(level.character, level.getMonster());

    	assertTrue(level.monster.dies());
    	assertEquals(100, level.character.getHealth());
    	assertEquals(1, level.character.getXP());
    	
    }


    
    @Test
    public void testFightWhenPlayerLeaves() {
		level.moveCharacter("d");
		level.moveCharacter("s");
		level.fightMenu("run");


		assertEquals(level.character.getFormerPosition(),level.character.getCurrentPosition());
    }
    
    
    //ligger ett item på plats [0][2]
    @Test
    public void testIfCharacterPicksUpItemOnMap() {
    	level.moveCharacter("d");
    	level.moveCharacter("d");
    	
    	assertFalse(level.character.getItems().isEmpty());
    }

    @Test
	public void testIfCharacterIsResetWhenDead(){
    	level.moveCharacter("d");
    	level.moveCharacter("s");
		Monster monster = new Monster(100, 100);
    	level.fight(monster);

		assertEquals(70, level.character.getHealth());
		assertEquals(new Position(0,0),level.character.getCurrentPosition());
		assertEquals(2,level.character.getLife());
	}

	@Test
	public void testIfLifeReaches0(){
		Monster monster = new Monster(100, 100);

    	level.fight(monster);
    	level.fight(monster);
    	level.fight(monster);
		level.fight(monster);

		assertEquals(0,level.character.getLife());
	}
    
    @Test
    public void testIfSuperStrengthAndStrengthPotionDisapearsAfterThreeTurns() {
    	level.moveCharacter("s");
    	level.moveCharacter("s");
    	Potion strengthPotion = new Potion("strength");
    	
    	//strength = 3 innan strength-potion, dubblas när man plockar upp den
    	assertTrue(level.character.getPotions().contains(strengthPotion));

    	assertEquals(6, level.character.getStrength());
    	
    	level.moveCharacter("w");
    	assertEquals(6, level.character.getStrength());
    	assertTrue(level.character.getPotions().contains(strengthPotion));

    	level.moveCharacter("s");
    	assertEquals(6, level.character.getStrength());
    	assertTrue(level.character.getPotions().contains(strengthPotion));
    	
    	level.moveCharacter("w");
    	assertEquals(6, level.character.getStrength());
    	assertTrue(level.character.getPotions().contains(strengthPotion));
    	
    	level.moveCharacter("s");
    	assertEquals(3, level.character.getStrength());
    	assertTrue(level.character.getPotions().isEmpty());
    	
    }
    
    @Test
    public void testIfSuperStrengthAndSuperSpeedDisappearsAfterThreeTurns() {
    	Potion strengthPotion = new Potion("strength");
    	Potion speedPotion = new Potion("speed");
    	int oringinalStrength  = level.character.getStrength();
    	int originalSpeed = level.character.getSpeed();
    	
    	level.moveCharacter("s");
    	level.moveCharacter("s");
    	
    	assertTrue(level.character.getPotions().contains(strengthPotion));
    	assertEquals(2*oringinalStrength, level.character.getStrength());
    	assertEquals(originalSpeed, level.character.getSpeed());
    	
    	level.moveCharacter("d"); //picks up speedPotion   	
    	assertEquals(2*oringinalStrength, level.character.getStrength());
    	assertTrue(level.character.getPotions().contains(speedPotion));
    	assertEquals(2*originalSpeed, level.character.getSpeed());

    	level.moveCharacter("a"); 
    	level.moveCharacter("d"); 	

    	
    	//strength försvinner
    	level.moveCharacter("a"); 	
    	assertFalse(level.character.getPotions().contains(strengthPotion));
    	assertEquals(oringinalStrength, level.character.getStrength());
    	assertTrue(level.character.getPotions().contains(speedPotion));
    	assertEquals(2*originalSpeed, level.character.getSpeed());
    	
    	level.moveCharacter("d"); 	
    	assertFalse(level.character.getPotions().contains(strengthPotion));
    	assertEquals(oringinalStrength, level.character.getStrength());
    	assertFalse(level.character.getPotions().contains(speedPotion));
    	assertEquals(originalSpeed, level.character.getSpeed());
    	
    }
    
    //Monster is present at [1][1]
    //Monster will hurt character so health is 90
    //Character will win item in monster and Xp from monster
    @Test
    public void testMovingCharacterToPosWithMonsterAndCheckThatFightHappen() {
    	level.moveCharacter("d");
    	level.moveCharacter("s");
    	
    	assertTrue(level.monster.dies());
    	assertFalse(level.character.getItems().isEmpty());
    	assertEquals(90, level.character.getHealth());
    	assertEquals(1, level.character.getXP());	
    	
    }

    @Test
    public void testMovingToStrongMonsterAndDying(){
		level.moveCharacter("s");
		level.moveCharacter("d");
		level.moveCharacter("d");


		//assertTrue(level.character.dies());
		assertEquals(new Position(0,0),level.character.getCurrentPosition());
		assertEquals(2, character.getLife());




	}

    @Test
	public void testUsingItemFromMenu(){
		Item testItem = new Item(0,0, 1);
		level.character.addItem(testItem);
		level.itemMenu("use");


    	assertEquals(4,level.character.getStrength());
	}

	@Test
	public void fightMonsterThroughFightMenu(){
    	level.fightMenu("fight");
    	level.fightMenu("fight");




		assertTrue(level.monster.dies());
		assertFalse(level.character.getItems().isEmpty());
		assertEquals(90, level.character.getHealth());
		assertEquals(1, level.character.getXP());
	}

	@Test
	public void testUseItemFromFightMenu(){
    	character.addItem(strengthItem);
    	level.fightMenu("item");
    	//level.itemMenu("use");

    	assertEquals(4,character.getStrength());
	}
	
	@Test
	public void testIfLevelIsCompletedIfIsAtEndPosAndHasKilledAllMonsters() {
		level.moveCharacter("d");
		level.moveCharacter("s");
		level.character.useItem(new Item(0, 0, 10));
		level.moveCharacter("d");
		level.moveCharacter("s");

		assertTrue(level.isCompleted());
	}
	
	@Test
	public void testIfLevelIsCompletedWhenAtEndPosAndHasKeyAndIsAtLevelTwo() {
		level.moveCharacter("s");
		level.moveCharacter("w");
		level.moveCharacter("d");
		level.moveCharacter("d");
		level.moveCharacter("d");
		level.moveCharacter("s");
		level.moveCharacter("s");
		level.moveCharacter("a");

		assertTrue(level.isCompleted());
	}
	
	@Test
	public void testIfLevelIsNotCompletedWhenAtEndPosAndIsAtLevelTwo() {
		level.moveCharacter("d");
		level.moveCharacter("d");
		level.moveCharacter("d");
		level.moveCharacter("s");
		level.moveCharacter("s");
		level.moveCharacter("a");
		
		assertFalse(level.isCompleted());
	}
	
	@Test
	public void testIfLevelIsNotCompletedWhenAtEndPosAndHasKey() {
		level.moveCharacter("s");
		level.moveCharacter("s");
		level.moveCharacter("d");
		level.moveCharacter("d");
		
		assertFalse(level.isCompleted());
	}
	
	@Test
	public void testIfLevelIsNotCompletedWhenAtEndPos() {
		level.moveCharacter("d");
		level.moveCharacter("s");
		level.moveCharacter("s");
		level.moveCharacter("d");
		
		assertFalse(level.isCompleted());
	}
	
	@Test
	public void testIfLevelIsNotCompletedWhenNotAtEndPos() {
		assertFalse(level.isCompleted());
	}
}
