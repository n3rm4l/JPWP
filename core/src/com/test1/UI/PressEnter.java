package com.test1.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.test1.Constants;

public class PressEnter extends VisDialog {
	


	Drawable drawable[] = new Drawable[2];
	
	 //public VisLabel dialogTextLabel;
	 //private Cell<VisLabel> labelCell;
	public float togglePerioid=0.5f;
	float tilToggle;
	 private boolean toggle = false;
	 
	 public PressEnter() {
	        super("");
	        // Inicjalizacja VisUI, jeśli jeszcze nie została zainicjalizowana
	        if (!VisUI.isLoaded()) {
	            VisUI.load();
	        }
	        Texture backgroundTexture = new Texture("UI/dialogEnterp.png"); 
	        Image backgroundImage = new Image(backgroundTexture);
	        drawable[0] = backgroundImage.getDrawable();
	        backgroundTexture = new Texture("UI/dialogEnternp.png"); 
	        backgroundImage = new Image(backgroundTexture);
	        drawable[1] = backgroundImage.getDrawable();
	        //backgroundTexture. backgroundTexture2 =new Texture("UI/dialogEnternp.png");
	        
	        //Drawable draweble = backgroundImage.getDrawable();
	        //backgroundImage[1] = new Image(backgroundTexture);
	        
	        this.setBackground(drawable[0]);
	        
	        //najprostsza możliwa realizacja położenia
	        this.setSize(Constants.TILE_SIZE*2*4, Constants.TILE_SIZE*2*4); // Szerokość całego ekranu i połowa wysokości
	       
	        this.setPosition(Constants.WIDTH-getWidth(), Constants.HEIGHT-getHeight()); // W dolnej części ekranu

	 }   


	 public void update(float deltaTime) {
		 if (tilToggle>0) {
			 tilToggle -= deltaTime;
			 return;
		 };
		 if (toggle) this.setBackground(drawable[1]);
		 else this.setBackground(drawable[0]);
		 tilToggle = togglePerioid;
		 toggle = !toggle;
	 }
	 
}
