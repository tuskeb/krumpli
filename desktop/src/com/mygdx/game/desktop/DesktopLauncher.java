package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyScreen;
import com.mygdx.game.SpaceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Krumpli!!";
		config.foregroundFPS = MyScreen.FPS;
		config.backgroundFPS = MyScreen.FPS;
		new LwjglApplication(SpaceGame.sGame, config);
	}
}
