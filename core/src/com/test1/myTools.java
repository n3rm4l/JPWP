package com.test1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class myTools {
	
	
	myTools() {
        throw new AssertionError("Nie można tworzyć instancji klasy narzędziowej");
    }
	
	public static boolean isKeyJP(String key) {
		int k=0;
        for (int i = 19; i < 67; i++) {
            if (Gdx.input.isKeyJustPressed(i)) k=i;
           // if (Gdx.input.isKeyJustPressed(66)) System.out.println("ENETERRRRRR");
        }
        if (Gdx.input.isKeyJustPressed(111)) k= 111;
        return (Input.Keys.valueOf(key) == k);
	}
	
	
	/*		//podejście uznano za nieoptymalne
	private static String keyToStr(int i) {
		int j =Input.Keys.RIGHT;
		switch (i) {
		case 0: return "null";
		case 51: return "W";
		case 47: return "S";
		case 29: return "A";
		case 32: return "D";
		case 111: return "ESC";
		case 19: return "U";
		case 20: return "D";
		case 21: return "L";
		case 22: return "R";
		default: return "null";
		}
		
		
	}
	*/
	
	
	
	
}
