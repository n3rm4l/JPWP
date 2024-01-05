package com.test1.ekrany;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.test1.Constants;
import com.test1.Rozgrywka;
import com.test1.bugTest.TestGrid;
import com.test1.entity.Player;
import com.test1.mapLogic.MapManager;

public class Wnetrze implements Screen{
	Player gracz;
	
	Texture img;
	float x, y;
	TextureRegion currentFrame = new TextureRegion();
	TestGrid grid;
	Rozgrywka object;
	MapManager mapManager;	//nowe
	BitmapFont font;
	
	public Wnetrze(Rozgrywka object) {
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
	    //gracz.destination.x =x; gracz.destination.y =y;
	    
	}

	@Override
	public void render(float delta) {
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
	    
		ScreenUtils.clear(0, 1, 0.995f, 1);
		//currentFrame.flip(false, true);
		
		 
		 //mapManager.mapRenderer.setView(camera);
		 mapManager.renderMap();
		
		object.batch.begin();
		object.batch.setColor(1, 1, 1, 1);
		
		 object.batch.draw(currentFrame, gracz.x, gracz.y, currentFrame.getRegionWidth() * 2f, currentFrame.getRegionHeight() * 2f);

		 //TestGrid grid = new TestGrid();
		 //grid.draw();
		 
		object.batch.end();
		
		//grid.setColor(0, 0.55f, 0, 1f);
		 grid.shapeRenderer.setColor(0, 0.55f, 0, 1); // Ustaw kolor na zielony
		 //grid.draw();
		 
		 object.batch.begin();
	     font.draw(object.batch, "player coordinates: " + mouseX + ", " + mouseY +"\n    Collision: " + mapManager.collisionControl(mouseX, mouseY), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
	     object.batch.end();

		 
		//mapManager.renderLayer();
		
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
