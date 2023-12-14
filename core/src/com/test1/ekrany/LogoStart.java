package com.test1.ekrany;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;
import com.test1.Rozgrywka;

public class LogoStart implements Screen{

	 BitmapFont font1;
	 GlyphLayout tekst1; 
	
	Texture logo;
	Rozgrywka object;
	
	float x, y;
	float timer,alpha = 0;
	public LogoStart(Rozgrywka object) {
		this.object = object;
		logo = new Texture("LOGO.png");
		
		font1 = new BitmapFont(Gdx.files.internal("Fonts/Press_Start_2P/pressStart2P.fnt"));
		font1.setColor(1.0f, 0.843f, 0.0f, 1.0f);
		tekst1 = new  GlyphLayout(font1, "PRESS ANY KEY \n          TO START");

		
	}
	
	public void setAlpha() {
		if (timer <=1) alpha=timer;
		else if (timer >= 2 && timer <= 4) alpha = 4 - timer;
		alpha = Math.min(1, Math.max(0, alpha));
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		x = (Gdx.graphics.getWidth() - logo.getWidth()) / 2f;
	    y = (Gdx.graphics.getHeight() - logo.getHeight()) / 2f;

	}

	@Override
	public void render(float delta) {
		timer += delta;
		ScreenUtils.clear(0, 0, 0, 1);
		object.batch.begin();
		
		setAlpha();
		object.batch.setColor(1, 1, 1, alpha);
		object.batch.draw(logo, x, y);
		
		if (timer >= 4) {
			//ScreenUtils.clear(0, 0, 0, 1);
			font1.draw(object.batch, tekst1, (Gdx.graphics.getWidth()- tekst1.width)/2f, (Gdx.graphics.getHeight()+tekst1.height)/2f);
			 if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
				 System.out.println("LOG - Gamescreen");
				 this.dispose();
				object.setScreen(new Wnetrze(object));
			 }
	    } 
	    
		object.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		//object.batch.dispose();
		//logo.dispose();
		Gdx.input.setInputProcessor(null);
	}

}
