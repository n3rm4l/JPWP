package com.test1.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class UseMeskin {
//progressbar section
	boolean fillBars;
	String[] versions =  {
		"BarGreen","BarRed","BarYellow" ,
		/*	// unused
		 * "barYellow",
		 * "barBlue",
		*/
	};
	
	private Skin skin = new Skin();
	NinePatch backgroundPatch;
	NinePatch knobPatch;
	


	void UseMeSkin (boolean filledBars){
		this.fillBars = filledBars;
		
	}

	public void setupUIbackground() {
		backgroundPatch= new NinePatch(new Texture(Gdx.files.internal("UI/dialog.png")), 10, 10, 10, 10);
		
	}
	
	public void setupBars() {
		backgroundPatch= new NinePatch(new Texture(Gdx.files.internal("UI/barBackground.png")), 10, 10, 10, 10);
		for (String nazwa : versions) {
			if(Gdx.files.internal("UI/knob"+nazwa+".png").exists())
			knobPatch= new NinePatch(new Texture(Gdx.files.internal("UI/knob"+nazwa+".png")), 10, 10, 10, 10);
			ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
			progressBarStyle.background = new NinePatchDrawable(backgroundPatch);
			progressBarStyle.knob = new NinePatchDrawable(knobPatch);
			if (fillBars) progressBarStyle.knobBefore = new NinePatchDrawable(knobPatch);
			skin.add("knob"+ nazwa +"-style", progressBarStyle);
			}
		}
	
		/*
		VisProgressBar progressBar = new VisProgressBar(0, 100, 0.1f, false, progressBarStyle);
		progressBar.setValue(50);
		*/
	/*
	stage.addActor(progressBar);

	stage.act(Gdx.graphics.getDeltaTime());
	stage.draw();
	*/
	
		
	//Metody należy użwyać tylko po uprzednim  załadowaniu odpowiednich skórek
	public Skin getSkins() {
		return this.skin;
	}
}

