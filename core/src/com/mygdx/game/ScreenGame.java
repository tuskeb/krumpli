package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
	private ActorBackground space = new ActorBackground();

	public ScreenGame() {
		super();

		gameStage.addActor(spaceShip);
		//gameStage.addActor(space);
		gameStage.addActor(surface);

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
		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode)
				{
					case Input.Keys.BACK:
					case Input.Keys.ESCAPE:
						SpaceGame.sGame.showScreen(SpaceGame.Screens.MENU);
						break;
				}
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}
		});
	}


	@Override
	public void hide() {
		super.hide();
		pause();
	}


	@Override
	public void render(float delta) {

		super.render(delta);

		if(mIsRunning) {

			final float accelY = Gdx.input.getAccelerometerY();
			if (accelY > 2) {
				spaceShip.setRocketState(ActorSpaceship.RocketType.left, true);
			} else if (accelY < -2) {
				spaceShip.setRocketState(ActorSpaceship.RocketType.right, true);
			} else {
				spaceShip.setRocketState(ActorSpaceship.RocketType.left, false);
				spaceShip.setRocketState(ActorSpaceship.RocketType.right, false);
			}

			world.step(delta, 1, 1);

			gameStage.act(delta);

			debugRenderer.render(world, camera.combined);

		}

		gameStage.draw();


		if(!mIsRunning) {

		}
	}

}
