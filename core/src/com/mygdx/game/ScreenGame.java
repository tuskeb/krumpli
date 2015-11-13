package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * A játék képernyője
 */
public class ScreenGame extends MyScreen {

	private Stage
			mStageControls = new Stage(),
			mStageGame = new Stage();

	ActorSpaceship actorSpaceShip = new ActorSpaceship();
	ActorSurface mActorSurface = new ActorSurface();
	ActorBackground actorBackground = new ActorBackground();

	public ScreenGame(Game game) {
		super(game);

		mStageGame.addActor(actorBackground);
		mStageGame.addActor(mActorSurface);
		mStageGame.addActor(actorSpaceShip);

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
		Gdx.input.setInputProcessor(mStageControls);

	}

	@Override
	public void hide() {
		super.hide();
		pause();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

	}


}
