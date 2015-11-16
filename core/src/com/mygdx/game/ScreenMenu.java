package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * A menü.
 */
public class ScreenMenu extends MyScreen {
	public ScreenMenu() {
		super();
	}

    /*
	menük:
        Új játék
        Statisztika vagy valami hasonló
        Súgó
        (Kilépés)
     */

	@Override
	public void render(float delta) {
		//super.render(delta);
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}


}
