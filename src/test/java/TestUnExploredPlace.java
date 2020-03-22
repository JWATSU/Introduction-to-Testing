import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUnExploredPlace {

	@Test
	public void testCreatingInstanceWithNegativeXPThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new UnExploredPlace(-1);
		});
	}
}
