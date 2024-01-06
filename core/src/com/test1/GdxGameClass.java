package com.test1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.test1.ekrany.LogoStart;

public class GdxGameClass extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	 FreeTypeFontGenerator generator;
	 FreeTypeFontParameter parameter;
	//int _1x;
	
	public GdxGameClass() {
        super();
        //Gdx.init();
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/font.ttf"));
        parameter = new FreeTypeFontParameter();
		parameter.size = 64;
		parameter.color = new Color(210/255f, 105/255f, 30/255f, 1);
		font = generator.generateFont(parameter);
		Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
		VisUI.load();
	    VisUI.setDefaultTitleAlign(Align.center);
	    VisUI.getSkin().add("default", labelStyle);
	    
		setScreen(new LogoStart(this));
	}

	@Override
	public void render () {
		
		//batch.begin();
		//font.draw(batch, "Hello, World!", 64, 64);
		//batch.end();
		super.render();
	}
	
	public void fontSize(int size) {
		if (parameter.size == size) return; 
			parameter.size = size;
			font = generator.generateFont(parameter);

		
	}
	
	
	/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}

