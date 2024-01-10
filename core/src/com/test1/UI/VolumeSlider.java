package com.test1.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.test1.GdxGameClass;

public class VolumeSlider extends Slider {
	GdxGameClass object;
	//public ProgressBar progressBar;	//DU
	//int k=1;

	VolumeSlider(GdxGameClass object) {
		super(0, 100, 5, false, createSkin()); 
		this.object = object; // Przekazanie przez referencję można sobie modyfikować obiekt
		setValue(50);
		
//		progressBar = new ProgressBar(0f, 1f, 0.01f, false, createSkin(k));	//DO USUNIECIA
//		progressBar.setValue(0.95f);	//DU
		//progressBar.setSize(500, 25);	//DU
	}
/*
	private static ProgressBar.ProgressBarStyle createSkin(int i) {		TO TO
		Skin skin = new Skin();
		Texture backgroundTexture = new Texture("UI/bar1.png");
		Texture knobTexture = new Texture("UI/knob2.png");
		skin.add("background", backgroundTexture);
		skin.add("knob", knobTexture);
		
		//NinePatch backgroundNinePatch = new NinePatch(backgroundTexture);
	 
	    // Określanie rozciąganych obszarów w osi Y
		//backgroundNinePatch.setTopHeight(backgroundNinePatch.getTopHeight() * 2);  // Dwukrotne zwiększenie wysokości obszaru górnej krawędzi
		//backgroundNinePatch.setPadTop(10 * 2);  // Przesunięcie obszaru górnej krawędzi o 10 pikseli w górę
	    //knobNinePatch.setTopHeight(14);
	   // knobNinePatch.setBottomHeight(14);
	    
//		ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
//		progressBarStyle.background = skin.getDrawable("background");
//		//progressBarStyle.knob = skin.getDrawable("knob");
//		progressBarStyle.knob = skin.getDrawable("knob");
//		 progressBarStyle.knobBefore = progressBarStyle.knob;
		progressBarStyle.knobBefore = progressBarStyle.knob;

		return progressBarStyle;
	}
*/
	private static Skin createSkin() {
		Skin skin = new Skin();
		Texture backgroundTexture = new Texture("UI/bar1.png");
		Texture knobTexture = new Texture("UI/knob1.png");
		skin.add("background", backgroundTexture);
		skin.add("knob", knobTexture);
		

		Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
		sliderStyle.background = skin.getDrawable("background");
		sliderStyle.knob = skin.getDrawable("knob");
		skin.add("default-horizontal", sliderStyle);
		return skin;
	}

	public void pressHandle() {
		int value = (int) getValue();
		int step = (int) getStepSize();

		
		/*			//fajny sposob na ograniczenie syfu na ekranie
		 * intTest++; if (intTest==100) { System.out.println("volume in loop: " +
		 * object.backgroundMusic.getVolume()); intTest=0; }
		 */
		 

		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			setValue(value + step); // Przesuń slider w prawo o jeden krok
			System.out.println("should be: \t" + (value + step));
			System.out.println("is: \t" + getValue());
			object.setVolume(getValue() / 100f); // zmien glosnosc gry
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			setValue(value - step); // przesuń slider w lewo o step
			object.setVolume(value / 100f); // zmniejsz głośność
		}
	}

	public void setMusic(boolean play) {
		if (play && !object.backgroundMusic.isPlaying()) {
			System.out.println("Should play");
			object.backgroundMusic.play();
		} else if (!play && object.backgroundMusic.isPlaying()) {
			object.backgroundMusic.stop();
			System.out.println("Should stop play");
		}
	}

}
