import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestItem {

    Item item = new Item(2,1,2);

    @Test
    public void testGetLife() {
        assertEquals(item.getHealth(), 2);
    }

    @Test
    public void testGetSpeed(){
        assertEquals(item.getSpeed(),1);
    }

    @Test
    public void testGetStrength(){assertEquals(item.getStrength(),2);}
}
