package com.test1.ekrany;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.test1.Constants;
import com.test1.GdxGameClass;
import com.test1.UI.Menu;
import com.test1.bugTest.TestGrid;
import com.test1.entity.Player;
import com.test1.mapLogic.MapManager;

public class Wnetrze extends AbstractImpScreen{

	Player gracz;
	float x, y;
	TextureRegion currentFrame = new TextureRegion();
	MapManager mapManager;
	Image freezedFrame;	//W kilku miejscach
	
	//przyjrzyj sie temu ew. modyfikacja
	public BitmapFont font;	//czemu to jest ten font???? może test
	Music backgroundMusic;
	Menu menu;
	
	//FrameBuffer frameBuffer;
	Texture img2;		//[TEST]
	TestGrid grid;		//[bugfix]
	int n = 0;	//testowe
	boolean fontset= false;

	
	
	public Wnetrze(GdxGameClass object) {
		super(object);
	}
	
	@Override
	public void show() {
		grid = new TestGrid();
		System.out.println("[TEST]\tTworzenie wnetrza");
		img2 = new Texture("badlogic.jpg");
		System.out.println("[TEST]\tTworzenie tekstury TEST img");
		
	    gracz = new Player(); //T
	    this.currentFrame = gracz.posAnimation[0].getKeyFrame( 0.025f, true); //T
	    mapManager = new MapManager();	//poszlo out na razie
	    font = new BitmapFont();	//MOZE NIEPOTRZEBNE
	    backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/AIVA_sound.mp3"));
	    backgroundMusic.setLooping(true);
	    backgroundMusic.setVolume(0.2f);
	    backgroundMusic.play();
	    object.getFont(1, 64);
	    object.getFont(0, 64);
	    font =object.font[1];


	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		
		if (!pause) 	generalLogic(delta);
		else  {
			menu.draw(); 
			if (menu.resume) {
				pause = false;
				menu.resume = false;
				backgroundMusic.play();
			}
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
	        pause = !pause;
	        if (pause) {
	        	backgroundMusic.pause();
	        	FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Constants.WIDTH, Constants.HEIGHT, false);
	        	frameBuffer.begin();
	        	//System.out.println("\t\t GRACZ X V2\t" + gracz.x);
				batch.begin();
				mapManager.renderMap();
				batch.draw(currentFrame, gracz.x, gracz.y, currentFrame.getRegionWidth() * 2f, currentFrame.getRegionHeight() * 2f);
				batch.end();
		        frameBuffer.end();
		        
		        freezedFrame = new Image(frameBuffer.getColorBufferTexture());	//2133
		        freezedFrame.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT); 
		        freezedFrame.setScaleY(-1f);
		        freezedFrame.setBounds(0, Constants.HEIGHT, Constants.WIDTH, Constants.HEIGHT);
		        
		        
		        
		        //TextureRegion textureRegion = new TextureRegion(texture);
		        //currentFrame2.setRegion(frameBuffer.getColorBufferTexture()); 
		        //currentFrame2.flip(false, true);
		        
		        
		        //frameBufferTexture = frameBuffer.getColorBufferTexture();	//TEST
		        //frameBuffer.dispose();	//	TEST

	        	if (menu == null) {
	        		menu = new Menu(freezedFrame, object.font[0]);
	        		} else menu.update(freezedFrame);

	        } else backgroundMusic.play();
		}
	}
		
		
	
	
	public void generalLogic(float delta) {
		int mouseX = (int)gracz.x/64;	//bugfix
		int mouseY = (int)gracz.y/64;	//bugfix
		
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
	    
		//currentFrame.flip(false, true);
		batch.setColor(1, 1, 1, 1);
		batch.begin();
		mapManager.renderMap();
		//System.out.println("\t GRACZ X V1\t" + gracz.x);
		batch.draw(currentFrame, gracz.x, gracz.y, currentFrame.getRegionWidth() * 2f, currentFrame.getRegionHeight() * 2f);
		batch.end();
		
		//grid.setColor(0, 0.55f, 0, 1f);
		
		 if (Constants.bugfixMode) {
			 batch.begin();
			 fontset = (fontset) ? true : object.getFont(1,24);
		     object.font[1].draw(batch, "player coordinates: " + mouseX + ", " + mouseY +"\n    paused?: " + pause, 0, 64);
		     batch.end();
		     
		     grid.shapeRenderer.setColor(0, 0.55f, 0, 1); // Ustaw kolor na zielony
			 grid.draw();
			 grid.shapeRenderer.end();
		 }
	}
	
	
}