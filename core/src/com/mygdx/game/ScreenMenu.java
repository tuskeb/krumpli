package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * A menü.
 */
public class ScreenMenu extends MyScreen {
	private Stage stage;
	private SpriteBatch batch;
	public ScreenMenu() {
		super();
		stage = new Stage();
		ActorBackground b = new ActorBackground();
		b.setSize(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		stage.addActor(b);


		batch=new SpriteBatch();
		//showScreen(Screens.MENU);
//LOL
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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.end();
	}


}
