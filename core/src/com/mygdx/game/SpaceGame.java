package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceGame extends Game implements ApplicationListener {

	public static SpaceGame sGame = new SpaceGame();


	private static Screen[] sScreens = {
			new ScreenMenu(),
			new ScreenHelp(),
			new ScreenGame()
	};

	public enum Screens {
		MENU(0),HELP(1),GAME(2);

		private Screen value;

		private Screens(int value) {
			this.value = sScreens[value];
		}

	};

	public void showScreen(Screens screen) {
		setScreen(screen.value);
	}

	@Override
	public void create() {
		showScreen(Screens.GAME);
	}

/*
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
}
