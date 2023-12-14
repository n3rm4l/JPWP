package com.test1.ekrany;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.test1.Constants;
import com.test1.Rozgrywka;
import com.test1.entity.Player;

public class Wnetrze implements Screen{
	Player gracz;
	
	Texture img;
	float x, y;
	TextureRegion currentFrame = new TextureRegion();
	
	Rozgrywka object;
	
	public Wnetrze(Rozgrywka object) {
		this.object = object;
	}
	//TmxMapLoader mapLoader = new TmxMapLoader();
	
	@Override
	public void show() {
		//img = new Texture("badlogic.jpg");
		
	    gracz = new Player(new Sprite());
	    this.currentFrame = gracz.posAnimation[0].getKeyFrame( 0.025f, true);
	    //gracz.destination.x =x; gracz.destination.y =y;
	    
	}

	@Override
	public void render(float delta) {
		if(gracz.destination.isReached(gracz.x, gracz.y)) {
			gracz.setMove();
			gracz.stateTime = 0;
		} else {
			this.currentFrame = gracz.Move(delta).getKeyFrame(gracz.stateTime, true);
		}
	    gracz.stateTime += delta;	 
	    
		ScreenUtils.clear(0, 1, 0.995f, 1);
		//currentFrame.flip(false, true);
		
		object.batch.begin();
		object.batch.setColor(1, 1, 1, 1);
		
		 object.batch.draw(currentFrame, gracz.x, gracz.y, currentFrame.getRegionWidth() * 4f, currentFrame.getRegionHeight() * 4f);
		
		object.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		object.batch.dispose();
		img.dispose();
	}

}
