public class Position
{
    private int posX;
    private int posY;

    public Position(int posX, int posY)
    {
        setPosX(posX);
        setPosY(posY);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX)
    {
//        if(posX < 0 || posX > 2)
//        {
//            throw new IllegalArgumentException("x out of range");
//        }
        this.posX = posX;
    }

    public void setPosY(int posY)
    {
//        if(posY < 0 || posY > 2)
//        {
//            throw new IllegalArgumentException("y out of range");
//        }
        this.posY = posY;
    }
    
    public boolean equals(Object other) {
    	if(!(other instanceof Position)) 
    		return false;
    	Position p = (Position) other;
    	if(posX == p.posX && posY == p.posY)
    		return true;
    	return false;
    }
    
    @Override
    public String toString() {
    	return "(" + posX + ", " + posY + ")";
    } 
}
