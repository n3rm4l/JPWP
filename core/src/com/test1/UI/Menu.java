package com.test1.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;



public class Menu extends Stage{
	
	
	float sliderValue= 0.5f;
	
	float minVolume=0f;
	float maxVolume=1.0f;
	
	Image logo;
	Image backGround;
	Slider volumeSlider;
	BitmapFont font;
	private Table rootTable;
	int selected = 0;
	public boolean resume= false;
	private ShapeRenderer slider;

	public Menu (Image backGround, BitmapFont font) { 
		 rootTable = new Table();
	        addActor(rootTable);
	    this.font = font;
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
	    pixmap.setColor(new Color(1, 1, 0, 1f)); // Kolor żółty z 70% przezroczystości
	    pixmap.fill();
	    //Texture square = new Texture(pixmap);
	    pixmap.dispose();
	    update(backGround);
	    super.setDebugAll(true);	//DEBUG	ALL ACTORS
	    rootTable.setFillParent(true);
	    Texture logo = new Texture("logo_transparent.png");
		this.logo = new Image(logo);
		//volumeSlider =new Slider(minVolume, maxVolume, 0.1f, false, );
		//volumeSlider = new Slider(0, 100, 1, false, skin);
		//slider = new ShapeRenderer();
		//slider.setAutoShapeType(true);
		//volumeSlider.setValue(initialVolume);
        //addActor(backGround);
        //addActor(squareImage);
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
        // Wywołaj metodę draw() klasy bazowej
        // Przed tym możesz oczyścić ekran, jeśli chcesz
        //ScreenUtils.clear(0, 1, 0, 0.9f);
		//Image backgroundImage = new Image(bacgroundTexture);
		//backgroundImage.setBounds(R0, 0, Constants.WIDTH, Constants.HEIGHT); // Ustaw wymiary na rozmiary ekranu
		addActor(backGround);
		//addActor(squareImage);
        MenuUi();
        super.act();
        super.draw();
        if (selected==1 && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) Gdx.app.exit();
        if (selected==0 && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) resume = true;
        super.clear();	//Menu można przechować w pamięci
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
	    
	    super.addActor(rootTable);
	    
	}
	
	public void selectionLogic() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.W)||(Gdx.input.isKeyJustPressed(Input.Keys.UP))) selected--;
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)||(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)))  selected++; 
		selected %= 4;
		if (selected<0) selected += 4;
	}
	
	public Color selectionCheck(int i) {
		
		//System.out.println("i to" + i);

		if (selected==i) return Color.GOLD; else return Color.WHITE;

		
	}
	
	
	/*
	 * public void updateSliderValue() { if
	 * (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { sliderValue =
	 * Math.min(maxVolume, sliderValue + 0.01f); } else if
	 * (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { sliderValue = Math.max(0.0f,
	 * sliderValue - 0.01f); } }
	 */
	
}

	

