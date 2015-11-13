package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * A játék képernyője
 */
public class ScreenGame extends MyScreen {

	private World mWorld = new World(new Vector2(0, -10), true);

	private Stage
			mStageControls = new Stage(),
			mStageGame = new Stage();

	ActorSpaceship actorSpaceShip = new ActorSpaceship();
	ActorSurface mActorSurface = new ActorSurface();
	ActorBackground actorBackground = new ActorBackground();

	public ScreenGame() {
		super();

		mStageGame.addActor(actorBackground);
		mStageGame.addActor(mActorSurface);
		mStageGame.addActor(actorSpaceShip);

		//mWorld.setGravity(new Vector2(0, -10));

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
		mWorld.step(delta, 1, 1);
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
