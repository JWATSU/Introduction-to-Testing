
abstract public class Creature extends MapElement {
	private int health;
	private int strength;
	public static final int MAX_HEALTH = 100;
	public static final int MAX_STRENGTH = 10;

	public Creature(int health, int strength) {
		if(health < 0 || strength < 0)
			throw new IllegalArgumentException("Attributes of a Creature cannot be negative.");
		this.health = health;
		this.strength = strength;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getStrength() {
		return strength;
	}

    public boolean removeHealth(int healthToBeRemoved) {
    	if(healthToBeRemoved < 0) 
    		return false;
    	health -= healthToBeRemoved;
    	if(health < 0) 
    		health = 0;
    	return true;
    }
	
	public boolean dies() {
		if(health == 0) {
			return true;
		}
		return false;
	}
	
    // max och min kontroll
    // alla add/increase bör vara samma
    // samma med remove/decrease
    // borde också ha samma returvärde, dvs boolean
    public boolean addHealth(int healthToBeAdded) {
    	if(healthToBeAdded < 0)
    		return false;
    	health += healthToBeAdded;
    	if(health > MAX_HEALTH)
    		health = MAX_HEALTH;
    	return true;
    }
    
    public boolean addStrength(int strengthToBeAdded) {
    	if(strengthToBeAdded < 0)
    		return false;
    	strength += strengthToBeAdded;
    	if(strength > MAX_STRENGTH)
    		strength = MAX_STRENGTH;
    	return true;
    }
    
    public boolean removeStrength(int strengthToRemove) {
    	if(strengthToRemove < 0)
    		return false;
    	strength -= strengthToRemove;
    	if(strength < 0) 
    		strength = 0;
    	return true;
    }
    
    public void setHealth(int health) {
    	this.health = health;
    }
}
