
public class Potion extends MapElement{
	private String name;
	private int noOfTurnsLeft;
	
	public Potion(String name) {
		this.name = name.toLowerCase();
		noOfTurnsLeft = 3;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof Potion))
			return false;
		return(name.equalsIgnoreCase(((Potion) other).name));
	}
	
	public void decreaseNoOfTurnsLeft() {
		noOfTurnsLeft--;
	}
	
	public int getNoOfTurnsLeft() {
		return noOfTurnsLeft;
	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override 
	public String toString() {
		return name;
	}
}
