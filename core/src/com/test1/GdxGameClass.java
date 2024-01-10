package com.test1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.test1.ekrany.LogoStart;
			/**
			 * Podejście zmodyfikoano
			 * Przeniesiono logikę wstępną i do klasy AbstractImpScreen
			 */
public class GdxGameClass extends Game {
	//public SpriteBatch batch;	//TO DELETE %%%%%%%%%%%%%%%%%%%%
	//public BitmapFont font, font2;
	public BitmapFont[] font = new BitmapFont[2];
	 FreeTypeFontGenerator[] generator = new FreeTypeFontGenerator[2];	//F1 -> PIxelify F2-> Press_Start_2P
	 FreeTypeFontParameter parameter;
	 
	 public Music backgroundMusic;
	 float volume;
	 
	//int _1x;
	/*
	public GdxGameClass() {
        super();
        //Gdx.init();
    }
	*/
	@Override
	public void create () {
		//batch = new SpriteBatch();
        generator[0] = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/font.ttf"));
		generator[1] = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/font1.ttf"));
        parameter = new FreeTypeFontParameter();
		parameter.size = 64;
		parameter.color = new Color(210/255f, 105/255f, 30/255f, 1);
		font[0] = generator[0].generateFont(parameter);
		font[1] = generator[1].generateFont(parameter);
		
		
		//Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

	    
		setScreen(new LogoStart(this));
	}
/*
	@Override
	public void render () {
		
		//batch.begin();
		//font.draw(batch, "Hello, World!", 64, 64);
		//batch.end();
		super.render();
	}
*/	
	/*
	public void fontSize(int size) {
		if (parameter.size == size) return; 
			parameter.size = size;
			font1 = generatorF1.generateFont(parameter);
	}
	*/
	public BitmapFont getFont(int font, int size, Color color) {
		parameter.size = size;
		parameter.color = color;
		this.font[font] = generator[font].generateFont(parameter);
		return this.font[font];
	}
	
	public BitmapFont getFont(int font, int size) {
		getFont(font, size ,this.font[font].getColor());
		return  this.font[font];
	}
	
	public BitmapFont getFont(int font, Color color) {
		getFont(font, (int)this.font[font].getCapHeight() ,color);
		return  this.font[font];
	}
	public void setVolume(float f) {
		backgroundMusic.setVolume(f);
		System.out.println("volume setted: " + f);
	}
	
	public float getVolume() {
		return backgroundMusic.getVolume();
	}
	
	
	/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
	
	
}

