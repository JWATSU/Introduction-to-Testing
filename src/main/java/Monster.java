
public class Monster extends Creature{
	private Item item;
	
	public Monster(int health, int strength, Item item) {
		super(health, strength);
		this.item = item;
	}
	
	public Monster(int health, int strength) {
		super(health, strength);
	}
	
	public int getXP() {
		return getStrength(); //?
	}
	
	public Item getItem() {
		return item;
	}
	
	
	
}
