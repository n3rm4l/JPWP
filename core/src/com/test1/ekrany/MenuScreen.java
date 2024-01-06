package com.test1.ekrany;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.test1.GdxGameClass;

public class MenuScreen implements Screen {
	 GdxGameClass game;
	 ShapeRenderer shapeRenderer = new ShapeRenderer();
	 
	 public MenuScreen(GdxGameClass game) {
	        this.game = game;
	    }
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		 Gdx.gl.glClearColor(0, 0, 0, 1);
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 
		 shapeRenderer.setColor(Color.BLACK);
		 shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		 shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 shapeRenderer.end();
		 
		 if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
		        game.setScreen(new Wnetrze(game));
		    }
		 
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
		
	}

}
