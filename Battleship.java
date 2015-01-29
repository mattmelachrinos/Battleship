import java.util.Arrays;
import java.io.Console;
import java.util.ArrayList;

class ship{
	public int[][] position = new int[3][2];
	private int numOfHits;
	private boolean shipIsVertical;   // true = vertical    false = horizontal
	private boolean shipPlaced;
	
	public void incNumOfHits(){
		numOfHits++;
	}
	public int getNumOfHits(){
		return numOfHits;
	}
	
	public void placeShip(){
		if (shipPlaced == False){
			shipIsVertical = (boolean) (Math.random() < 0.5);

			if (shipIsVertical){  
				position[0][0] = (int) (Math.random() * 7);
				position[0][1] = (int) (Math.random() * 5);
				
				position[1][0] = position[2][0] = position[0][0];
				
				position[1][1] = position[0][1];
				position[1][1] = position[1][1] + 1;
				position[2][1] = position[1][1];
				position[2][1] = position[2][1] + 1;
			} else { //Ship is Horizontal
				position[0][0] = (int) (Math.random() * 5);
				position[0][1] = (int) (Math.random() * 7);
				
				position[1][1] = position[2][1] = position[0][1];
				
				position[1][0] = position[0][0];
				position[1][0] = position[1][0] + 1;
				position[2][0] = position[1][0];
				position[2][0] = position[2][0] + 1;
			} 
			System.out.println(Arrays.deepToString(position));
		}else{
			System.out.println("Error: Ship already placed");
		}
		
	}
}
class game{
	public ship[] shipArr = new ship[3];

	
	public void startGame(){
		shipArr[0] = new ship();
		shipArr[0].placeShip();
		shipArr[1] = new ship();
		shipArr[1].placeShip();
		shipArr[2] = new ship();
		shipArr[2].placeShip();  
	}
	
	public void playgame(){
		int numOfGuesses = 0;
		int guessX, guessY;
		int[][] guess = new int[1][2];
		String inputx, inputy;
		boolean notfound;
		
		while(shipArr[0].getNumOfHits() <3 || shipArr[1].getNumOfHits() <3 || shipArr[2].getNumOfHits() <3){
			notfound = true;
			
			System.out.println("x value of shot.");
			inputx = System.console().readLine();
			guessX = Integer.parseInt(inputx);
			
			System.out.println("y value of shot.");
			inputy = System.console().readLine();
			guessY = Integer.parseInt(inputy);
			
			guess[0][0] = guessX;
			guess[0][1] = guessY;
			
			found:
			for(int a = 0; a< shipArr.length; a++){
				for (int i=0; i<3; i++){
					if (guess[0][0] == shipArr[a].position[i][0]){
						if(guess[0][1]== shipArr[a].position[i][1]){
						//HITTTTT
						System.out.println("HITTTT");
						shipArr[a].incNumOfHits();
						if(shipArr[a].getNumOfHits() == 3){
							System.out.println("You sunk ship " + ++a);
						} else{
						System.out.println("You hit ship " + ++a);
						}
						notfound = false;
						
						break found;
						}
					}
				}
			}	
			if(notfound){
				System.out.println("MISSSSSSSSSSSSSSSSSSSSs");
			}
			numOfGuesses++;
			
		}
		System.out.println("YOU WIN!!!");
		System.out.println("How many guesses you made: " + numOfGuesses);
	}
	
}
public class Battleship{
	
	public static void main(String[] Args){
		game Game1 = new game();
		Game1.startGame();
		Game1.playgame();
	}
}



