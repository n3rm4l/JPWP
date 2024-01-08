package com.test1.ekrany;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.test1.Constants;
import com.test1.GdxGameClass;

public abstract class AbstractImpScreen implements Screen{

	public GdxGameClass object;
	protected Stage stage;
	private OrthographicCamera camera;
	protected SpriteBatch batch;
	boolean pause = false;
	
	
	public AbstractImpScreen(GdxGameClass object){
		this.object = object;
		makecamera();
		makeStage();
		batch = new SpriteBatch();	// główny batch -> przeniesiono z gdxgameclass
	}
	
	private void makecamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
        camera.update();
	}

	private void makeStage() {
		// TODO Auto-generated method stub
		Viewport viewPort = new StretchViewport(Constants.WIDTH, Constants.HEIGHT , camera);	//viewport na wypadek uruchmienia aplikacji na miejszym ekranie
		stage = new Stage(viewPort);
		Gdx.input.setInputProcessor(stage);
	}




	@Override
	public void show() {
		// TODO może implementacja czcionek globalnych
		//pamiętaj o modyfikacji metod na ekranach o wywołanie tej metody jeśli się na to zdecydujesz
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
		
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
		object.dispose();
		Gdx.input.setInputProcessor(null);
	}

}
