package com.test1;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//import com.test1.Rozgrywka;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("JPWP");
		
		config.setWindowedMode(Constants.WIDTH, Constants.HEIGHT);
		config.setDecorated(false);
		//config.setResizable(false);	//--> zbędne dla setDecorated=0
		new Lwjgl3Application(new GdxGameClass(), config);
	}
}
