package com.test1.mapLogic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.test1.Constants;
import com.test1.Interakcje;

public class InteractionManager {
	
	int possibleInteraction;
	
	public InteractionManager() {
		possibleInteraction = 0;
	}
	
	public void interactionEvent() {
		
	}
	
	public boolean setInteraction(int xTiles, int yTiles) {
	        	for (Interakcje obiekt : Constants.interactions) {
	        			int possibleInteraction = (obiekt.x == xTiles && obiekt.y == yTiles) ? obiekt.interakcja : 0;
	        			if (possibleInteraction != 0) {
	        				 if (Gdx.input.isKeyPressed(Input.Keys.C)) System.out.println("Possible interaction\t"+possibleInteraction);
	        				return true;
	        			}
	        	} return false;
	        }
	    
		
	
}
