import java.util.Scanner;

public class Game {
	public static void main(String[] args)
	{	
		System.out.println("Starting ...");
		Position position = new Position(2,2);
		Character character = new Character(2, 3, 100, 3);
		Level level = new Level(position, 4, 3, character, "Level-1");

		
		while(true){
			if(level.character.getLife() == 0) {
				level = new Level(position, 4, 3, character, "Level-1");
				level.character = new Character(2, 3, 100, 3);
				System.out.println("dead");
			}
			
			level.moveCharacter("s");
			level.moveCharacter("s");
			level.moveCharacter("d");
			level.moveCharacter("w");
			level.moveCharacter("w");
			level.moveCharacter("d");
			level.moveCharacter("d");
			level.moveCharacter("s");
			level.moveCharacter("a");

			if(level.isCompleted()) 
				System.out.println("level is completed");
		}

		
	}
}
