
public class UnExploredPlace extends MapElement{

	private int XP = 0;
	
	//inför koll
    public UnExploredPlace(int XP) {
    	if(XP < 0) {
    		throw new IllegalArgumentException("Negative XP is not valid.");
    	}
    	this.XP = XP;
    }

    
    public int getXP() {
    	int oldXP = XP;
    	XP = 0;
    	return oldXP;
    }
    
}
