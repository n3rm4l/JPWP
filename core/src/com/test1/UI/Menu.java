package com.test1.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.test1.GdxGameClass;
import com.test1.ekrany.Wnetrze;

public class Menu{
	
	Texture bacgroundTexture;
	public GdxGameClass object;
	
	public Menu(GdxGameClass object) {
		this.object = object;
		//super(object);
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
	    pixmap.setColor(new Color(1, 1, 0, 0.8f)); // Kolor żółty z 70% przezroczystości
	    pixmap.fill();
	    bacgroundTexture = new Texture(pixmap);
	    pixmap.dispose();
	}
	
	

	
	public void drawMenu() {
		object.batch.begin();
	    object.batch.draw(bacgroundTexture, 100, 100, 200, 200); // Pozycja i rozmiar prostokąta
	    object.batch.end();
	}
	
	
}
