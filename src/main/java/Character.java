import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character extends Creature {

	
// sätt alla gets tillsammans 
// döpa om level
	
    private int life;
    private int speed;
    private int XP;
    private List<Item> items = new ArrayList<>();
    private int level = 0;
    private List<Potion> potions = new ArrayList<Potion>();
    private Key key;

    public static final int MAX_LIFES = 10;
    public static final int MAX_SPEED = 3;
    private Position currentPosition = new Position(0,0);
    private Position formerPosition = new Position(0,0);

    //Inför kontroll för attribut!
    public Character(int life, int speed, int health, int strength) { // borde ev startas med klara värden, samma varje gång man börjar
        super(health, strength);
    	if(life < 0 || speed < 0) 
    		throw new IllegalArgumentException("The characters attributes cannot be negative.");
    	this.life = life;
        this.speed = speed;
    }

    // sätta dessa till package protect
    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }
    
    public int getXP() {
    	return XP;
    	
    }
    
    public void useItem(Item item) {
    	addHealth(item.getHealth()); // 
    	increaseSpeed(item.getSpeed());
    	addStrength(item.getStrength()); 
    }

    public boolean addLife(int lifesToAdd) {
    	if(lifesToAdd > 0) {
            life += lifesToAdd;
            if (life > MAX_LIFES) {
            	life = 10;
            }
            return true;
        }
        return false;
    }

    public boolean removeLife() {
    	if(life == 0)
    		return false;
        life --;
        return true;
    }

    public boolean increaseSpeed(int speedIncrease) {
        if (speedIncrease > 0 && speed + speedIncrease <= MAX_SPEED) {
            speed += speedIncrease;
            return true;
        }
        return false;
    }

    public boolean decreaseSpeed(int speedDecrease) {
        if (speedDecrease > 0 && speed-speedDecrease>0) {
			speed -= speedDecrease;
            return true;
        }
        return false;
    }
    
    public Position getCurrentPosition() {
    	return currentPosition;
    }

    public Position getFormerPosition(){
        return formerPosition;
    }
    
    public void moveRight() {
    	currentPosition.setPosX(currentPosition.getPosX() + 1);
    	formerPosition.setPosX(currentPosition.getPosX()-1);
    	formerPosition.setPosY(currentPosition.getPosY());
    }
    
    public void moveLeft() {
    	currentPosition.setPosX(currentPosition.getPosX() - 1);
        formerPosition.setPosX(currentPosition.getPosX()+1);
        formerPosition.setPosY(currentPosition.getPosY());


    }
    
    public void moveUp() {
    	currentPosition.setPosY(currentPosition.getPosY() - 1);
    	formerPosition.setPosY(currentPosition.getPosY()+1);
        formerPosition.setPosX(currentPosition.getPosX());

    }
    
    public void moveDown() {
    	currentPosition.setPosY(currentPosition.getPosY() + 1);
        formerPosition.setPosY(currentPosition.getPosY()-1);
        formerPosition.setPosX(currentPosition.getPosX());

    }
    
    // null check
    public void addItem(Item item) {
    	items.add(item);
    }
      
    
    public List<Item> getItems() {
    	return new ArrayList<>(items);
    }
    
    public boolean addXP(int XPToBeAdded) {
    	if(XPToBeAdded < 0)
    		return false;
    	int oldLevel = level;
    	XP += XPToBeAdded;
    	level += XP / 100;
    	if (oldLevel != level) {
    		levelUp();
    		XP = XP % 100;
    	}
    	return true;
    }
    
    public void levelUp() {
    	int amountOfStatPoints = level;
    	if (amountOfStatPoints % 2 == 0) {
    		addHealth(amountOfStatPoints/2);
    		addStrength(amountOfStatPoints/2);
    	}
    	else {
        	addHealth(amountOfStatPoints/2 + amountOfStatPoints % 2);
        	addStrength(amountOfStatPoints/2);	
    	}
   }

    // ändra så level är eget attribut
    // ändra namn, förtydliga level hos spelare, inte karta
    public int getLevel() {
    	return level;
    }

    // vad händer om liv < 0 
    //Game over implementera?
    public void resetCharacter(){
        if(life > 0) {
            setHealth(70);
            currentPosition.setPosY(0);
            currentPosition.setPosX(0);
        }

    }
    
    public List<Potion> getPotions() {
    	return new ArrayList<Potion>(potions);
    }
    
    public void addPotionAndActivateEffect(Potion potion) {
    	potions.add(potion);
    	switch(potion.toString()) {
			case("strength"): addStrength(getStrength()); break;
			case("speed"): increaseSpeed(getSpeed()); break;
    	}
    }
    
    public void removePotionAndDeactivateEffect(Potion potion) {
    	potions.remove(potion);
    	switch(potion.toString()) {
			case("strength"): removeStrength(getStrength()/2); break;
			case("speed"): decreaseSpeed(getSpeed()/2); break;
    	}
    }
    
    public void usePotion(Potion potion) {
		if(potion.getNoOfTurnsLeft() > 0) {
			potion.decreaseNoOfTurnsLeft();
		} else if (potion.getNoOfTurnsLeft() == 0) {
			removePotionAndDeactivateEffect(potion);
		}
    }
    
    public void pickUpKey(Key key) {
    	this.key = key;
    }
    
    public boolean hasKey() {
    	if(key == null )
    		return false;
    	return true;
    }
}
