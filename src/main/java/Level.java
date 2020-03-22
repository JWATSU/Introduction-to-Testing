import javafx.geometry.Pos;

import java.util.Scanner;

public class Level
{

    private Scanner input = new Scanner(System.in);
    private MapElement[][] gameMap;
    private Position endPosition;
    protected Character character;
    protected Monster monster;
    protected Monster strongMonster;
    private Item item;
    private int mapWidth;
    private int mapHeight;
    private final String levelName;

    public Level(Position endPosition, int mapWidth, int mapHeight, Character character, String levelName)
    {
        this.endPosition = endPosition;
        this.gameMap = new MapElement[mapHeight][mapWidth];
        this.character = character;
        this.levelName = levelName;

		this.mapWidth = gameMap[0].length;
		this.mapHeight = gameMap.length;
        gameMap[0][0] = character;
        monster = new Monster(60, 1); // OBS hårdkodning
        gameMap[1][1] = monster; // OBS randomisera i j nedan för att sätta ut monster/items? Testbarhet??
        strongMonster = new Monster(100,100);
        gameMap[1][2] = strongMonster;
        item = new Item(1, 0, 0);
        gameMap[0][2] = item;
        gameMap[2][0] = new Potion("strength");
        gameMap[2][1] = new Potion("speed");
        gameMap[1][0] = new Key();
        gameMap[0][3] = new UnExploredPlace(100); //XP for unexplored place
        gameMap[1][3] = new UnExploredPlace(75);
        gameMap[2][3] = new UnExploredPlace(25);

    }

//    public void printMap() {
//    	for(int i = 0; i < gameMap[0].length; i++) {
//    		for(int j = 0; j < gameMap.length; j++) {
//    			if(gameMap[i][j] instanceof Character) {
//    				System.out.print("X");
//    			} else if(gameMap[i][j] instanceof Monster) {
//    				System.out.print("M");
//    			}
//				else if(gameMap[i][j] instanceof Item) {
//					System.out.print("I");
//				}else {
//    				gameMap[i][j] = unOccupied;
//    				System.out.print("O");
//    			}
//    		}
//    		System.out.println();
//    	}
//    }

//    private void initialize()
//	{
//
//	}

//    //döp om?
//    public void run() {
//
//    	System.out.println("Welcome to this level!");
//    	printMap();
//    	while(!(character.getCurrentPosition().equals(endPosition))) {
//    		//vart vill du flytta?
//    		//får indata, kolla att valid
//    		String userInput = input.nextLine().toLowerCase();
//    		//kollar om man får flytta dit (anropa metod?)
//    		if(checkInput(userInput) && isMovePossible(userInput)) {
//        		//flyttar karaktär
//    			moveCharacter(userInput);
//        		printMap();
//        		//anropa metod för att kolla vad som händer på platsen (tex. här är ett monster, eller du har nått mål)
//    		}
//
//    	}
//    }

    public boolean checkInput(String s)
    {
    	s = s.toLowerCase();
        return (s.equals("a") || s.equals("w") || s.equals("d") || s.equals("s"));
    }

    public void moveCharacter(String userInput)
    {
        usePresentPotions();
        if (isMovePossible(userInput))
        {
            Position formerPos = character.getCurrentPosition();
            placeMapElementOnPosition(null, formerPos);

            switch (userInput)
            {
                case "a":
                    character.moveLeft();
                    break;
                case "w":
                    character.moveUp();
                    break;
                case "d":
                    character.moveRight();
                    break;
                case "s":
                    character.moveDown();
                    break;
            }

            checkMapElement(character.getCurrentPosition());
            placeMapElementOnPosition(character, character.getCurrentPosition());
        }
    }

    private void usePresentPotions()
    {
        if (!character.getPotions().isEmpty())
            for (Potion p : character.getPotions())
                character.usePotion(p);
    }

    public boolean isMovePossible(String userInput)
    {
        if (userInput.equals("a") && (character.getCurrentPosition().getPosX() == 0) ||
                userInput.equals("w") && (character.getCurrentPosition().getPosY() == 0) ||
                userInput.equals("s") && (character.getCurrentPosition().getPosY() == mapHeight - 1) ||
                userInput.equals("d") && (character.getCurrentPosition().getPosX() == mapWidth - 1))
        {
            return false;
        }
        return true;
    }

    private void checkMapElement(Position pos)
    {
        if (isMapPosOccupied(pos))
        {
        	MapElement currentPos = gameMap[pos.getPosY()][pos.getPosX()];
            if (currentPos instanceof Potion)
            {
                character.addPotionAndActivateEffect((Potion) currentPos);
            } else if (currentPos instanceof Item)
            {
                character.addItem((Item) currentPos);
            } else if (currentPos instanceof Monster) {
            	fight((Monster) currentPos);
            } else if(currentPos instanceof Key) {
            	character.pickUpKey((Key) currentPos);
            } else if(currentPos instanceof UnExploredPlace) {
            	character.addXP(((UnExploredPlace) currentPos).getXP());
            }
        }
        

    }

    public boolean isMapPosOccupied(Position pos)
    {
        if (gameMap[pos.getPosY()][pos.getPosX()] == null)
            return false;
        return true;
    }

    public void takeItem(Character player, Monster monster)
    {
        player.addItem(monster.getItem());

    }

    public void fight(Monster monster)
	{
    	while(!character.dies()) {
    	    attack(character, monster);
    		if(monster.dies())
    			return;
    		attack(monster, character);
    	}  character.removeLife();
    	character.resetCharacter();
    }



    public void attack(Creature attacker, Creature defender)
    {
        defender.removeHealth(attacker.getStrength() * 10);
        if (defender.dies())
        {
            if (attacker instanceof Character)
            {
                ((Character)attacker).addXP(((Monster) defender).getXP());
                takeItem((Character) attacker, (Monster) defender);
            }
        }
    }

//    public void playerAttacks(Character attacker, Monster defender) {
//    	defender.removeHealth(attacker.getStrength()*10);
//    	if(defender.dies()) {
//    		attacker.addNoOfMonstersKilled();
//    	}
//    	
//    }
//    
//    public void monsterAttacks(Monster attacker, Character defender) {
//    	defender.removeHealth(attacker.getStrength()*10);
//		if (defender.dies()) {
//			character.resetCharacterIfDead();
//		}
//    }

//    public Position getEndPosition() {
//    	return endPosition;
//    }

    public boolean isCompleted()
    {
        return endPosition.equals(character.getCurrentPosition()) && ((character.hasKey() && character.getLevel() >= 2) || isAllMonstersKilled());
    }
    
    public boolean isAllMonstersKilled() {
    	for(int i = 0; i < gameMap.length; i++) {
    		for(int j = 0; j < gameMap[0].length; j++) {
    			if(gameMap[i][j] instanceof Monster) 
    				return false;
    		}
    	}
    	return true;
    }

    private void placeMapElementOnPosition(MapElement me, Position pos)
    {
        gameMap[pos.getPosY()][pos.getPosX()] = me;
    }


    public void fightMenu(String userInput){
//the fight menu is meant to run when a fight sequence is initiated. That gives the user an option to choose what to do during the fight.
        // can be implemented in full at a later stage when if we make an interface.
        switch(userInput){
            case "fight":
                attack(character,monster);
                if(!monster.dies()){
                    attack(monster, character);
                }
                break;
            case "item":
                //is meant to print item menu and give a choice to either use an item or go back to fightMenu.
                //implemented atm that it only uses an item since we dont have an interface and cant test user input
                itemMenu("use");
                break;
            case "run":
                character.getCurrentPosition().setPosX(character.getFormerPosition().getPosX());
                character.getCurrentPosition().setPosY(character.getFormerPosition().getPosY());
                break;

        }
    }

    public void itemMenu(String userInput){

        switch(userInput){
            case "use":
             character.useItem(character.getItems().get(0));

                break;
            case "back":
                //is meant to send you back to fightMenu if you dont want to use item,
                //can't implement atm because need a way to test methods that needs userinput


                //fightMenu();

                break;
        }

    }
}

