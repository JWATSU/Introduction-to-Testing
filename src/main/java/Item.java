
public class Item extends MapElement{
	private int health;
	private int strength;
	private int speed;

	public Item(){

	}

	public Item(int health, int speed,int strength) {
		this.health= health;
		this.speed=speed;
		this.strength=strength;
		
	}
	
//	public int healthToAdd() {
//		return health;
//	}

	public int getHealth(){
		return health;
	}

	public int getSpeed(){
		return speed;
	}

	public int getStrength(){
		return strength;
		}

}
