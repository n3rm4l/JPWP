package com.test1.UI;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;


public class Menu extends Stage{
	
	Texture bacgroundTexture;
	//public GdxGameClass object;
	private Table rootTable;
	
	
	public Menu () {
		 rootTable = new Table();
	        addActor(rootTable);
		
		
		//this.object = object;
		//super(object);
	        /*
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
	    pixmap.setColor(new Color(1, 1, 0, 0.8f)); // Kolor żółty z 70% przezroczystości
	    pixmap.fill();
	    bacgroundTexture = new Texture(pixmap);
	    pixmap.dispose();
	    */
	}
	
	@Override
    public void draw() {
        // Wywołaj metodę draw() klasy bazowej
        // Przed tym możesz oczyścić ekran, jeśli chcesz
        ScreenUtils.clear(0, 0, 0, 1);
        super.draw();
    }

	/*
	public void drawMenu() {
		object.batch.begin();
	    object.batch.draw(bacgroundTexture, 100, 100, 200, 200); // Pozycja i rozmiar prostokąta
	    object.batch.end();
	}
	*/
	
}
