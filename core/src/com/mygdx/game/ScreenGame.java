package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
	private TextButton button;
	static float WIN_POINT, BEST_TIME;
/*



*/

	final Sound click = Gdx.audio.newSound(Gdx.files.internal("Sound/click_sound.mp3"));
	final Sound click_back = Gdx.audio.newSound(Gdx.files.internal("Sound/click_sound_back.mp3"));
	public ScreenGame() {
		super();
		gameStage.addActor(space);
		gameStage.addActor(spaceShip); spaceShip.setPosition(Gdx.graphics.getWidth() - spaceShip.getWidth() * 4, Gdx.graphics.getHeight() - spaceShip.getHeight() / 2); spaceShip.setSize(150, 250);
		gameStage.addActor(surface);

		button = new TextButton("START/RESUME", MyScreen.TEXT_BUTTON_STYLE);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				mIsRunning=true;
				click.play();
			}
		});

		gameStage.addActor(button);

		if (Gdx.input.isTouched()) spaceShipGoUP();
		else spaceShipGoDown();
	}


	private float spX, spY, time;
	private void spaceShipGoUP(){
		spX = spaceShip.getX();
		spY = spaceShip.getY();
		time = 30;
		do {
			if (time > 0 & Gdx.input.isTouched()) {spY += 0.1; time--;}
			if(Gdx.input.isTouched()) time++;
			if(Gdx.input.getAccelerometerX()> 0) spX+=0.1;
			if(spX == Gdx.graphics.getWidth() || spY == Gdx.graphics.getHeight()) ActorSpaceship.bumm = true;
		}while (time==0);
	}

	private void spaceShipGoDown(){
		if(!Gdx.input.isTouched()) spY-=0.01;
		if(spaceShip.getY() == surface.getY()) SpaceGame.sGame.showScreen(SpaceGame.Screens.STAT);
	}


	private boolean mIsRunning;

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
						click_back.play();
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

			button.setVisible(false);
			float accelY = Gdx.input.getAccelerometerY();
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

		else if(!mIsRunning) {
			button.setVisible(true);
		}

		batch.begin();
		gameStage.act(Gdx.graphics.getDeltaTime());
		gameStage.draw();
		batch.end();


	}

}
