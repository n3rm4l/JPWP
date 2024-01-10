package com.test1.ekrany;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;
import com.test1.Constants;
import com.test1.GdxGameClass;
import com.test1.myTools;
import com.test1.UI.DialogBox;
import com.test1.UI.Menu;
import com.test1.UI.PressEnter;
import com.test1.bugTest.TestGrid;
import com.test1.entity.Player;
import com.test1.mapLogic.InteractionManager;
import com.test1.mapLogic.MapManager;
import com.kotcrab.vis.ui.widget.VisWindow;


public class Wnetrze extends AbstractImpScreen{

	Player gracz;
	float x, y;
	int mouseX;
	int mouseY;
	TextureRegion currentFrame = new TextureRegion();
	MapManager mapManager;
	InteractionManager interactionMan;
	
	//przyjrzyj sie temu ew. modyfikacja
	public BitmapFont font;	//czemu to jest ten font???? może test
	public BitmapFont fontSet;
	Menu menu;
	DialogBox dialogBox;
	PressEnter enterToggle;
	
	
	//FrameBuffer frameBuffer;
	Texture img2;		//[TEST]
	TestGrid grid;		//[bugfix]
	int n = 0;	//testowe
	//boolean fontset= false;

	
	
	public Wnetrze(GdxGameClass object) {
		super(object);
		
	}
	
	@Override
	public void show() {
		fontSet = object.getFont(0,24);
		interactionMan = new InteractionManager();
		grid = new TestGrid();
		System.out.println("[TEST]\tTworzenie wnetrza");
		img2 = new Texture("badlogic.jpg");
		System.out.println("[TEST]\tTworzenie tekstury TEST img");
		
	    gracz = new Player(); //T
	    this.currentFrame = gracz.posAnimation[0].getKeyFrame( 0.025f, true); //T
	    mapManager = new MapManager();	//poszlo out na razie
	    font = new BitmapFont();	//MOZE NIEPOTRZEBNE
	    object.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/AIVA_sound.mp3"));
	    object.backgroundMusic.setLooping(true);
	    object.backgroundMusic.setVolume(0.5f);
	    object.backgroundMusic.play();
	    
	    object.getFont(0, 64);
	    font =object.getFont(1, 64);
	    
	    VisUI.load(SkinScale.X1);
		final Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		String loremIpsum = "Witaj\n Witaj w buildzie gry stanowaiacej realiizacje projektu z JPWP";
		//String loremIpsum = "Witaj w czyms co mialo byc gra serious game\n przejdz przez 12 miesiecy Witaj w czyms co mialo byc gra serious game\\n przejdz przez 12 miesiecy";
		dialogBox = new DialogBox(loremIpsum, object.getFont(1, 30, Color.GOLD));
		stage.addActor(dialogBox);
		
		enterToggle =new PressEnter();
		//stage.addActor(enterToggle);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		//dialogBox.update();
		pauseStateLogic(delta);
		 mouseX = (int)gracz.x/64;	//bugfix
		 mouseY = (int)gracz.y/64;	//bugfix
		 if (myTools.isKeyJP("Enter")) dialogBox.fadeOut(0.5f);
		 enterToggle.update(delta);
		 if (Gdx.input.isKeyPressed(Input.Keys.C)) System.out.println("updated");
		 
		 
		if (Constants.bugfixMode) {
			 batch.begin();
			 //fontset = (fontset) ? true : object.getFont(0,24);
			 //fontSet.draw(batch, "player coordinates: " + mouseX + ", " + mouseY +"\n    paused?: " + pause, 0, 64);
			 //interactionMan.setInteraction(mouseX, mouseY);
			 fontSet.draw(batch, "Interaction: " + interactionMan.setInteraction(mouseX, mouseY) +"\n    paused?: " + pause, 0, 64);
		     batch.end();
		     
		     grid.shapeRenderer.setColor(0, 0.55f, 0, 1); // Ustaw kolor na zielony
			 grid.draw();
			 grid.shapeRenderer.end();
		 }
	}
		
	/*	
	 public void actorReactToKey(String key, Actor actor) {
		 if (myTools.isKeyJP(key)) {
				if (actor.getStage()!=null) {
					//isVisible -> czy nie jest schowany
					actor.fadeOut();
					dialogBox.fadeOut(0.5f);
				} else stage.addActor(actor.fadeIn());
			 }
	*/
	public void generalLogic(float delta) {
		//int keyCode = Input.Keys.ESCAPE;
		//System.out.println("keyCode"+keyCode);

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
		handleInteraction();
		 
		
		
		
		
		
		//if (interactionMan.setInteraction(mouseX, mouseY))
		//setToggle(interactionMan.setInteraction(mouseX, mouseY));


		 
		
			 
			 
			 /*
			 if (actor.getStage()!=null) {
					//isVisible -> czy nie jest schowany
					enterToggle.fadeOut(0.5f);
					dialogBox.fadeOut(0.5f);
				} else stage.addActor(enterToggle.fadeIn(0.5f));
		 }
		 */
		 
		 
		 	//dialogBox.check();
		 	//dialogBox.update();
		 	
		 	
			stage.act();
			//Debugkey = C,,, pokaze update sceny i dalsze metody
			
			stage.draw();
	}	
	
	public void pauseStateLogic(float delta) {
		if (!pause) {
			generalLogic(delta);
		} else  {
			menu.draw(); 
			if (menu.resume) {
				pause = false;
				menu.resume = false;
				object.backgroundMusic.play();
			}
		}
		if (myTools.isKeyJP("Escape")) {
	        pause = !pause;
	        if (pause) {
	        	if (menu == null) {
	        		menu = new Menu(captureFrame(), object );	//A wystarczyło Stage i stworzyć menu extends wnetrze
	        		} else menu.update(captureFrame());

	        } else object.backgroundMusic.play();
		}
	}
	
	public Image captureFrame() {
    	FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Constants.WIDTH, Constants.HEIGHT, false);
    	frameBuffer.begin();
		batch.begin();
		mapManager.renderMap();
		batch.draw(currentFrame, gracz.x, gracz.y, currentFrame.getRegionWidth() * 2f, currentFrame.getRegionHeight() * 2f);
		batch.end();
        frameBuffer.end();
        
        Image freezedFrame = new Image(frameBuffer.getColorBufferTexture());
        freezedFrame.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT); 
        freezedFrame.setScaleY(-1f);
        freezedFrame.setBounds(0, Constants.HEIGHT, Constants.WIDTH, Constants.HEIGHT);
        return freezedFrame;
	}
	
	public void handleInteraction() {
		if(interactionMan.setInteraction(mouseX, mouseY)) {
			if (enterToggle.getStage()==null) stage.addActor(enterToggle.fadeIn(0.5f));
		} else if (enterToggle.getStage()!=null){
			enterToggle.fadeOut(0.5f);
		}
	}
	/*
	public void hideShowToggle() {
		if(!toggle) {
			
		}
		
		
		if(!toggle) {
			if (enterToggle.getStage()!=null) enterToggle.fadeOut(0.5f);
		}
		 
			
		} else stage.addActor(enterToggle.fadeIn(0.5f));
	}
	*/
}