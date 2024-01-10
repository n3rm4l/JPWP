package com.test1.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.test1.Constants;

public class DialogBox extends VisDialog {
	

	 public VisLabel dialogTextLabel;
	 private Cell<VisLabel> labelCell;
	 float bugFixWidth = 0;
	 float bugFixHeight = 0;
	 private boolean updateDone = false;
	 
	 public DialogBox(String tekst, BitmapFont font) {
	        super("");
	        // Inicjalizacja VisUI, jeśli jeszcze nie została zainicjalizowana
	        if (!VisUI.isLoaded()) {
	            VisUI.load();
	        }
	        Texture backgroundTexture = new Texture("UI/dialogi3.png"); // Zmień ścieżkę na właściwą
	        Image backgroundImage = new Image(backgroundTexture);
	        this.setBackground(backgroundImage.getDrawable());
	        
	        //najprostsza możliwa realizacja położenia
	        this.setSize(Constants.WIDTH, Constants.HEIGHT/2); // Szerokość całego ekranu i połowa wysokości
	        this.setPosition(0, 0); // W dolnej części ekranu
	        
	        dialogTextLabel = new VisLabel(tekst);
	        dialogTextLabel.setWrap(true); // Włączenie zawijania tekstu
	        dialogTextLabel.setStyle(new LabelStyle(font, null));
	        //dialogTextLabel.setWidth(Constants.WIDTH-200);
	        //dialogTextLabel.setBounds()
	        //this.text(dialogTextLabel);
	        this.labelCell = this.getContentTable().add(dialogTextLabel).expand().fill().width(Constants.WIDTH-300).height(Constants.HEIGHT/2-100);
//	        bugFixWidth= labelCell.getActor().getWidth();
//	        bugFixHeight= labelCell.getActor().getHeight();
//	        labelCell.
//	        update();
	 }   

	 public void wrapTextToFillBackground() {

		 
		 
		 
		 float scale = labelCell.getActor().getFontScaleX()+0.1f;
		 float avaliableWidth = labelCell.getMaxWidth()-bugFixWidth*1.1f;
		 float avaliableHeight = labelCell.getMaxHeight()-bugFixHeight*1.1f;

		 if (avaliableWidth  > 0 && avaliableHeight  > 0) {
			 
			 labelCell.getActor().setFontScale(scale);
			 bugFixWidth *= 1.1;
			 bugFixHeight *= 1.1;
			 
			 
		 } else {
				/*
				 * if (updateDone) return; float tempW
				 * =bugFixWidth/labelCell.getActor().getFontScaleX(); float tempH
				 * =bugFixHeight/labelCell.getActor().getFontScaleX();
				 * 
				 * 
				 * tempH /= labelCell.getMaxHeight();
				 * 
				 * 
				 * tempH; 
				 * labelCell.getActor().setFontScale(scale);
				 */
			 updateDone = true;
		 }
	 }
	 
}
