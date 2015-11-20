package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * A játék képernyője
 */
public class ScreenGame extends MyScreen {

	// https://github.com/libgdx/libgdx/wiki/A-simple-game
	// http://pimentoso.blogspot.hu/2013/01/meter-and-pixel-units-in-box2d-game.html

	private World world = new World(new Vector2(0, -10), true);
Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	private Stage
			controlsStage = new Stage(),
			gameStage = new Stage();

	private ActorSpaceship spaceShip = new ActorSpaceship(world);
	private ActorSurface surface = new ActorSurface(world);
	private ActorBackground space = new ActorBackground(world);

	public ScreenGame() {
		super();

		//gameStage.addActor(space);
		//gameStage.addActor(surface);
		gameStage.addActor(spaceShip);

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

		gameStage.act(delta);

        debugRenderer.render(world, camera.combined);

		gameStage.draw();
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
