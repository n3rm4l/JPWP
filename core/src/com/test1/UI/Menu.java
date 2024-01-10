package com.test1.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.test1.GdxGameClass;


public class Menu extends Stage{
	
	
	float sliderValue= 0.5f;
	float minVolume=0f;
	float maxVolume=1.0f;
	VolumeSlider volumeSlider;
	Image logo;
	Image backGround;
	BitmapFont font;
	private Table rootTable;
	int selected = 0;
	public boolean resume= false;
	
	
	public Menu (Image backGround, GdxGameClass object) { 
		 rootTable = new Table();
	        addActor(rootTable);
	    this.font = object.font[0];
	    //sposob na prostokąt fajny
		//Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
	    //pixmap.setColor(new Color(1, 1, 0, 1f)); // Kolor żółty z 70% przezroczystości
	    //pixmap.fill();
	    //pixmap.dispose();
	    update(backGround);
	    ///super.setDebugAll(true);	//DEBUG	ALL ACTORS
	    rootTable.setFillParent(true);
	    Texture logo = new Texture("logo_transparent.png");
		this.logo = new Image(logo);
		volumeSlider = new VolumeSlider(object);
	}
	
	
	public void update(Image backGround) {
		super.clear();
		backGround.setColor(1, 1, 1, 0.2f);
		this.backGround = backGround;
		addActor(backGround);
		//addActor(squareImage);
	}

	
	@Override
    public void draw() {

		addActor(backGround);
        MenuUi();
        super.act();
        super.draw();
       
       selectedAction();
        super.clear();	//To tylko menu ale rzeczywiście lepiej korzystać z akcji aktora
        
        
        /*
        slider.begin();
        slider.setColor(Color.GOLD);
        slider.rect(100, 100, 200, 32); // Pozycja i rozmiar ramki
        slider.end();
        slider.setColor(Color.WHITE);
        slider.rect(102, 102, 200 * sliderValue, 30); // Wypełnienie suwaka
        slider.end();
        */
     // Aktualizacja wartości suwaka 
       // updateSliderValue();
        
    }
	
	
	public void MenuUi() {
	    rootTable.clear(); // Wyczyść zawartość tabeli
	    logo.setFillParent(false);
	    logo.setBounds((backGround.getWidth()-logo.getWidth())/2, backGround.getHeight()-logo.getHeight(), 400, 400);
	    super.addActor(logo);
	    
	    String[] str ={"RETURN TO GAME", "EXIT GAME", "CHANGE VOLUME", "."};
		
	    rootTable.row();
	    rootTable.add(logo).height(350);
	    selectionLogic();
	    for (int i = 0; i < 3; i++) {
	    	Label label = new Label(str[i], new Label.LabelStyle(font, selectionCheck(i)));
	    	label.setAlignment(Align.center);
	        rootTable.row();
	        rootTable.add(label).height(80); // Dodaj nowy wiersz i ustaw jego wysokość na 64 piksele   
	    }
	    rootTable.row();
	    
	    //rootTable.add(volumeSlider).width(500).height(80);	ODK
	    rootTable.add(volumeSlider).width(500).height(80);
	    super.addActor(rootTable);
	    
	}
	
	public void selectionLogic() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.W)||(Gdx.input.isKeyJustPressed(Input.Keys.UP))) selected--;
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)||(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)))  selected++; 
		selected %= 3;
		if (selected<0) selected += 3;
	}
	
	public Color selectionCheck(int i) {
		
		//System.out.println("i to" + i);
		if (selected==i) return Color.GOLD; else  return Color.WHITE;

		
	}
	
	
	public void selectedAction() {
		 switch (selected) {
	        case 0:
	            volumeSlider.setMusic(false);
	            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
	                resume = true;
	            }
	            break;
	        case 1:
	            volumeSlider.setMusic(false);
	            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
	                Gdx.app.exit();
	            }
	            break;
	        case 2:
	            {
	            	volumeSlider.setMusic(true);
	                volumeSlider.pressHandle();
	                
	            }
	            break;
	    }
	}
	
	/*
	 * public void updateSliderValue() { if
	 * (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { sliderValue =
	 * Math.min(maxVolume, sliderValue + 0.01f); } else if
	 * (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { sliderValue = Math.max(0.0f,
	 * sliderValue - 0.01f); } }
	 */
	
}

	

