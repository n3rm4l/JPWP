package com.test1.ekrany;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.test1.Constants;
import com.test1.GdxGameClass;
import com.test1.UI.Menu;
import com.test1.bugTest.TestGrid;
import com.test1.entity.Player;
import com.test1.mapLogic.MapManager;

public class Wnetrze implements Screen{
	Player gracz;

	
	
	//Menu menuStage;
	Texture img;
	float x, y;
	TextureRegion currentFrame = new TextureRegion();
	TestGrid grid;
	public GdxGameClass object;
	MapManager mapManager;	//nowe
	BitmapFont font;
	Music backgroundMusic;
	Menu menu;
	boolean pause = false;
	
	
	public Wnetrze(GdxGameClass object) {
		this.object = object;
		 grid = new TestGrid();

	}

	
	@Override
	public void show() {
		//img = new Texture("badlogic.jpg");
		
	    gracz = new Player(new Sprite());
	    this.currentFrame = gracz.posAnimation[0].getKeyFrame( 0.025f, true);
	    mapManager = new MapManager();	//nowe
	    font = new BitmapFont();
	    backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/AIVA_sound.mp3"));
	    backgroundMusic.setLooping(true);
	    backgroundMusic.setVolume(0.2f);
	    backgroundMusic.play();
	    object.fontSize(24);
	    
	}

	@Override
	public void render(float delta) {
		
		if (!pause) {	generalLogic(delta);}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
	        pause = !pause;
	        if (pause) {
	        	 if (menu == null) {menu = new Menu();}
	        			 //System.out.println("IS NULL :(");}
                backgroundMusic.pause();
                Gdx.gl.glClearColor(0, 0, 0, 1);  // Czyść ekran na czarno
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                object.batch.begin();
                //menu.act(delta);
                //menu.draw();
                object.batch.end();
                //object.setScreen(menu);
				//menu.render(delta);
            } else {
                backgroundMusic.play();
            }
		}    
	}

	public void generalLogic(float delta) {
		int mouseX = (int)gracz.x/64;	//bugfix
		int mouseY = (int)gracz.y/64;	//bugfix
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		
		if(gracz.destination.isReached(gracz.x, gracz.y)) {
			gracz.setMove();
			gracz.stateTime = 0;
		} else {
			this.currentFrame = gracz.Move(delta).getKeyFrame(gracz.stateTime, true);
			if (mapManager.collisionControl(gracz.destination.x/64, gracz.destination.y/64)==1) {
				gracz.cancelMove();
				gracz.setMove();	// reset aktualnego żądania ruchu 
			};	
		}
	    gracz.stateTime += delta;	 
	    
		//ScreenUtils.clear(0, 1, 0.995f, 1);
		//currentFrame.flip(false, true);
		
		object.batch.begin();
		mapManager.renderMap();
		object.batch.setColor(1, 1, 1, 1);
		
		 object.batch.draw(currentFrame, gracz.x, gracz.y, currentFrame.getRegionWidth() * 2f, currentFrame.getRegionHeight() * 2f);

		 //TestGrid grid = new TestGrid();
		 //grid.draw();
		 
		object.batch.end();
		
		//grid.setColor(0, 0.55f, 0, 1f);
		
		 if (Constants.bugfixMode) {
			 object.batch.begin();
			 object.fontSize(24);
		     object.font.draw(object.batch, "player coordinates: " + mouseX + ", " + mouseY +"\n    paused?: " + pause, 0, 64);
		     object.batch.end();
		     grid.shapeRenderer.setColor(0, 0.55f, 0, 1); // Ustaw kolor na zielony
			 grid.draw();
		 }
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
