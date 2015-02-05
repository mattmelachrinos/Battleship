import java.util.Arrays;
import java.io.Console;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/*
	Known Bugs...
		1. you can hit the same spot on a ship over and over and the ship will sink
		2. ships can overlap

*/
class ship{
	private int numOfHits = 0;
	private boolean shipIsVertical;   		// true = vertical    false = horizontal
	protected boolean shipPlaced = false;
	protected boolean isSunk = false;
	private int size = 0;
	public int[][] position;
	private String name;
	
	public ship(int sizeOfShip){
		size = sizeOfShip;
		shipIsVertical = (boolean) (Math.random() < 0.5);
		
		if(size == 2){        				//Ship is a Destroyer
			position = new int[2][2];
			name = "destroyer";

			if (shipIsVertical){  
				position[0][0] = (int) (Math.random() * 8);
				position[0][1] = (int) (Math.random() * 6);
				
				position[1][0] = position[0][0];
				
				position[1][1] = position[0][1] + 1;

			} else { //Ship is Horizontal
				position[0][0] = (int) (Math.random() * 6);
				position[0][1] = (int) (Math.random() * 8);
				
				position[1][1] = position[0][1];

				position[1][0] = position[0][0] + 1;
			} 
		}
		else if(size == 3){   				//Ship is a Submarine
			position = new int[3][2];
			name = "Submarine";
			
			if (shipIsVertical){  
				position[0][0] = (int) (Math.random() * 7);
				position[0][1] = (int) (Math.random() * 5);
				
				position[1][0] = position[2][0] = position[0][0];
				
				position[1][1] = position[0][1] + 1;
				position[2][1] = position[0][1] + 2;
			} else { //Ship is Horizontal
				position[0][0] = (int) (Math.random() * 5);
				position[0][1] = (int) (Math.random() * 7);
				
				position[1][1] = position[2][1] = position[0][1];
				
				position[1][0] = position[0][0] + 1;
				position[2][0] = position[0][0] + 2;
			} 

		}
		else if(size == 4){  				//Ship is Aircraft Carrier
			position = new int[4][2];
			name = "aircraft carrier";
			
			if (shipIsVertical){ 
				position[0][0] = (int) (Math.random() * 6);
				position[0][1] = (int) (Math.random() * 4);
				
				position[2][0] = position[3][0] = position[1][0] = position[0][0];
				position[1][1] = position[0][1] + 1;
				position[2][1] = position[0][1] + 2;
				position[3][1] = position[0][1] + 3;
			} else { //Ship is Horizontal
				position[0][0] = (int) (Math.random() * 4);
				position[0][1] = (int) (Math.random() * 6);
			
				position[2][1] = position[3][1] = position[1][1] = position[0][1];
				position[1][0] = position[0][0] + 1;
				position[2][0] = position[0][0] + 2;
				position[3][0] = position[0][0] + 3;
			}
		}
		System.out.println(Arrays.deepToString(position));
	}
	public String getName(){
		return name;
	}
	public boolean getShipIsVertical(){
		return shipIsVertical;
	}
	public void incNumOfHits(){
		numOfHits++;
		updateIsSunk();
	}
	public boolean getIsSunk(){
		return isSunk;
	}
	private	void updateIsSunk(){
		if (numOfHits == size){
			isSunk = true;
		}
	}
	public int getNumOfHits(){
		return numOfHits;
	}
}
class game{
	public ship[] shipArr = new ship[3];

	
	public void startGame(){
		shipArr[0] = new ship(2);			//Creating a Destroyer
		shipArr[1] = new ship(3);			//Creating a Submarine 
		shipArr[2] = new ship(4); 			//Creating a Aircraft Carrier 
	}
	
	public void playgame(){
		int numOfGuesses = 0;
		int guessX, guessY;
		int[][] guess = new int[1][2];
		String inputx, inputy;
		boolean notfound;
		
		while(!shipArr[0].getIsSunk() || !shipArr[1].getIsSunk() || !shipArr[2].getIsSunk()){
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
			for(int a = 0; a < shipArr.length; a++){
				for (int i=0; i < shipArr[a].position.length; i++){
					if (guess[0][0] == shipArr[a].position[i][0]){
						if(guess[0][1]== shipArr[a].position[i][1]){
						//HITTTTT
						System.out.println("HITTTT");
						shipArr[a].incNumOfHits();
						if(shipArr[a].getIsSunk()){
							System.out.println("You sunk your opponent's " + shipArr[a].getName());
						} else{
						System.out.println("You hit a ship!");
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
		System.out.println("It took you " + numOfGuesses + " guesses to sink all of the enemies ships.");
	}
	
}
public class Battleship{
	
	public static void main(String[] Args){
		game Game1 = new game();
		String response;
		while(true){
			Game1.startGame();
			Game1.playgame();
			System.out.println("Would you like to play again? (y/n)");
			response = System.console().readLine();
			if (response == "n"){
				break;
			}
		}
	}
}



