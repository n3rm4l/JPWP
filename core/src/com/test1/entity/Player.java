package com.test1.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.test1.Constants;
import java.util.Date;

public class Player{
	

	
	private static final int DIRECTIONS = 4, STEPS = 3; 
	public float stateTime= 0;
	public Destination destination;
	public float x, y;	// Pozycja gracza
	int tilesX , tilesY, NEG;	//Bieżący ruch gracza

	
	public Animation<TextureRegion>[] posAnimation = new Animation[4];
	//public Animation<TextureRegion>chosenAnimation;
	
	//metoda przekształcająca TEXTURERegion2D w 1D		//Na razie niewykorzystywana
	public TextureRegion[] flatten(TextureRegion[][] input_2D) {
		int rows = input_2D.length, cols = input_2D[0].length;
		TextureRegion[] tmp = new TextureRegion[rows * cols];
		int pos = 0;
		for (TextureRegion[] row: input_2D) {
			System.arraycopy(row, 0, tmp, pos, row.length);
			pos += row.length;
		}
		return tmp;
	}
	
	public Player() {
		//public Texture mainHeroSheet;
		Texture mainHeroSheet = new Texture(Gdx.files.internal("Sheets/Hero.png"));

		TextureRegion[][] allFrames = new TextureRegion[DIRECTIONS][STEPS];  //CZY Moge to skrocic
		//allFrames = flatten(TextureRegion.split(mainHeroSheet, Constants.TILE_SIZE, Constants.TILE_SIZE));	//to się przyda
		allFrames = TextureRegion.split(mainHeroSheet, Constants.TILE_SIZE, Constants.TILE_SIZE);
		int index = 0;
		for (TextureRegion[] row : allFrames) {
			posAnimation[index++] = new Animation<TextureRegion>(0.025f, row);
		}
		x = Gdx.graphics.getWidth() / 2 - 2* Constants.TILE_SIZE;
	    y =  Gdx.graphics.getHeight() / 2 - 2* Constants.TILE_SIZE;

	    destination = new Destination(x, y);
	}
	
	public void cancelMove() {
		destination.x = destination.x-tilesX*Constants.TILE_SIZE*2;
		destination.y = destination.y-tilesY*Constants.TILE_SIZE*2;
		//może efekt dwiękowy
		//Sound punchingSound = Gdx.audio.newSound(Gdx.files.internal("punching.wav"));
        //punchingSound.play();
        Gdx.audio.newSound(Gdx.files.internal("Sound/punching.wav")).play();
	}
	
	public void setMove() {
		//TODO nie zakładam ruchu "na ukos"	//można nadpisać x i y, ale to jest pardziej przejrzyste
		if (x != (float)destination.x) {
			x = (float)destination.x;
		} else if (y != (float)destination.y) y = (float)destination.y;




		if (Gdx.input.isKeyJustPressed(Keys.W)) 		{ 	tilesX = 0; 	tilesY = 1; NEG = 0;} //3
		else if (Gdx.input.isKeyJustPressed(Keys.S)) 	{	tilesX = 0;		tilesY = -1; NEG = 1;} //0
		else if (Gdx.input.isKeyJustPressed(Keys.A))  	{	tilesX = -1; 	tilesY = 0; NEG = 1;} //1
		else if (Gdx.input.isKeyJustPressed(Keys.D)) 	{	tilesX = 1; 	tilesY = 0; NEG = 0;}  //2
		else { 
		tilesX = 0; 
		tilesY = 0;
		NEG=-1;
		//chosenAnimation = posAnimation[tilesX+2*tilesY+1+NEG];// moze delete
		//int k = tilesX+2*tilesY+1+NEG;
		//chosenAnimation = new Animation<TextureRegion>(posAnimation[k].getFrameDuration(), 
        //        Arrays.copyOf(posAnimation[k].getKeyFrames(), posAnimation[k].getKeyFrames().length));
		//new Animation<TextureRegion>(0.025f, row)
		}

		destination.x = (int)this.x + tilesX * Constants.TILE_SIZE *2;  //mnoznik
		destination.y = (int)this.y + tilesY * Constants.TILE_SIZE *2;	//mnoznik
		//System.out.println("After operation d_x\t"+ destination.x);
		//System.out.println("After operation d_y\t"+ destination.y);
		//return(posAnimation[tilesX+2*tilesY+1+NEG]);	//po zakonczeniu ruchu
	}
	
	public Animation<TextureRegion> Move(float delta) {
		//t =0.25s -> 128 px speed =16
		x += 10*delta* Constants.TILE_SIZE * tilesX;
		y += 10* delta *Constants.TILE_SIZE * tilesY;
		return(posAnimation[tilesX+2*tilesY+1+NEG]);
	}

	
	
	
}
