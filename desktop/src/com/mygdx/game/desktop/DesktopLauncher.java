package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyScreen;
import com.mygdx.game.SpaceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name","CorrectUserName");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Krumplee";
		config.foregroundFPS = MyScreen.FPS;
		config.backgroundFPS = MyScreen.FPS;
		new LwjglApplication(SpaceGame.sGame, config);
	}
}
