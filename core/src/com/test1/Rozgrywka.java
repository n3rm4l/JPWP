package com.test1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.test1.ekrany.LogoStart;
import com.test1.ekrany.Wnetrze;

public class Rozgrywka extends Game {
	public SpriteBatch batch;
	//int _1x;
	
	public Rozgrywka() {
        super();
    }
	
	@Override
	public void create () {
		//super.create();
		batch = new SpriteBatch();
		setScreen(new LogoStart(this));
		
	}

	@Override
	public void render () {
		super.render();
	}
	/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}
