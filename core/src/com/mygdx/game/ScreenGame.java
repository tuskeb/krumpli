package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * A játék képernyője
 */
public class ScreenGame extends MyScreen {

	// http://pimentoso.blogspot.hu/2013/01/meter-and-pixel-units-in-box2d-game.html

	private World world = new World(new Vector2(0, -10), true);

	private Stage
			mStageControls = new Stage(),
			mStageGame = new Stage();

	ActorSpaceship spaceShip = new ActorSpaceship();
	ActorSurface surface = new ActorSurface();
	ActorBackground space = new ActorBackground();

	public ScreenGame() {
		super();

		mStageGame.addActor(space);
		mStageGame.addActor(surface);
		mStageGame.addActor(spaceShip);

	}

	private boolean mIsRunning = true;

	public void pause() {
		if (!mIsRunning) return;
		mIsRunning = false;

	}

	public void resume() {
		if (mIsRunning) return;
		mIsRunning = true;

	}

	@Override
	public void show() {
		super.show();

		// átadjuk neki a vezérlést

	}


	@Override
	public void hide() {
		super.hide();
		pause();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		world.step(delta, 1, 1);

		mStageGame.act(delta);
		mStageGame.draw();

	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.ESCAPE:
			case Input.Keys.BACK:

				break;
		}

		return false;
	}
}
