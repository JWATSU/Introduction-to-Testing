
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestPosition
{
//    @Test
//    public void createPositionObjectWithInvalidXValueOverLimit()
//    {
//        Assertions.assertThrows(IllegalArgumentException.class, ()->
//        {
//            Position position = new Position(15, 2);
//        });
//    }
//    @Test
//    public void createPositionObjectWithInvalidXValueUnderLimit()
//    {
//        Assertions.assertThrows(IllegalArgumentException.class, ()->
//        {
//            Position position = new Position(-1, 2);
//        });
//    }
//    @Test
//    public void createPositionObjectWithInvalidYValueOverLimit()
//    {
//        Assertions.assertThrows(IllegalArgumentException.class, ()->
//        {
//            Position position = new Position(2, 10);
//        });
//    }
//    @Test
//    public void createPositionObjectWithInvalidYValueUnderLimit()
//    {
//        Assertions.assertThrows(IllegalArgumentException.class, ()->
//        {
//            Position position = new Position(2, -1);
//        });
//    }
	
	@Test
	public void testIfTwoPositionsWithSameCoordinatesAreEqual() {
		assertTrue(new Position(3, 2).equals(new Position(3,2)));
	}
	
	@Test
	public void testIfTwoPositionsWithDifferentCoordinatesAreNotEqual() {
		assertFalse(new Position(3, 2).equals(new Position(2,2)));
	}
	
	@Test
	public void testIfPositionIsNotEqualToString() {
		assertNotEquals(new Position(3,2), "hej");
	}
	
	@Test
	public void testIfPositionToStringMethodWorks() {
		assertTrue((new Position(3,2)).toString().equals("(3, 2)"));
	}
	
	@Test
	public void testGetMethods() {
		Position p = new Position(3,2);
		assertEquals(3, p.getPosX());
		assertEquals(2, p.getPosY());
	}
}