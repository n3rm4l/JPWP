package com.test1.ekrany;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.test1.GdxGameClass;

public class MenuScreen implements Screen {
	 GdxGameClass object;
	 ShapeRenderer shapeRenderer = new ShapeRenderer();
	 Texture texture;
	 
	 public MenuScreen(GdxGameClass object) {
	        this.object = object;
	    }
	@Override
	public void show() {
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
	    pixmap.setColor(new Color(1, 1, 0, 0.1f)); // Kolor żółty z 70% przezroczystości
	    pixmap.fill();
	    texture = new Texture(pixmap);
	    pixmap.dispose();
		
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*
		 Gdx.gl.glClearColor(0, 0, 0, 0.0f);
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
		 shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		 shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 shapeRenderer.setColor(new Color(0, 1, 0, 0.5f));
		 shapeRenderer.rect(100, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 shapeRenderer.end();
		 
		 if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
		        game.setScreen(game.Wnetrze);
		    }
		 */
		object.batch.begin();
	    object.batch.draw(texture, 100, 100, 200, 200); // Pozycja i rozmiar prostokąta
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
		
	}

}
