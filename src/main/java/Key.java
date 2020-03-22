
public class Key extends MapElement{
	private String name;
	
	public Key(String name) {
		this.name = name;
	}
	
	public Key() {
		this("");
	}
	
//	public boolean equals(Object other) {
//		if(!(other instanceof Key))
//			return false;
//		Key k = (Key) other;
//		return(name.equals(k.name));
//	}
}
