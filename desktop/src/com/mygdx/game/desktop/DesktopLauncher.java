package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyScreen;
import com.mygdx.game.SpaceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name","pendroid");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)MyScreen.VIRTUAL_WIDTH;
		config.height = (int)MyScreen.VIRTUAL_HEIGHT;
        config.resizable = false;
		new LwjglApplication(SpaceGame.sGame, config);
	}
}
