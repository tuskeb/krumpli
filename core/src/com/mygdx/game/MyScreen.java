package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// https://github.com/libgdx/libgdx/wiki/box2d#creating-a-world

public class MyScreen implements Screen, InputProcessor {
	public static final float VIRTUAL_WIDTH = 640, VIRTUAL_HEIGHT = 480;
	public static final int FPS = 45;

	public static final BitmapFont sFont256bytes, sFontHoboStd;

	//Ezek a MyScreen osztályban deklaráltak, mert az összes osztályban akarom őket használni, és statikus, mivel elég csak egyszer betölteni őket.

	//Tetszőleges TTF betöltése. A Windows/font mappából bemásoltam egyet... De rengeteteg van a http://www.1001fonts.com/ oldalon.
	//Ez a rész átkonvertálja a vektoros betűket bittérképessé

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("256bytes.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		parameter.characters = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[]"; //Memóriaspórolás céljából csak a hasznosakat konvertáljuk át.
		/*
		parameter.shadowColor = Color.BLACK;
		parameter.shadowOffsetX = 3;
		parameter.shadowOffsetY = 3; ???? */
		sFont256bytes = generator.generateFont(parameter); // font256bytes size 12 pixels
		sFont256bytes.setColor(0, 0, 0, 0.5f);
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

	}

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("256bytes.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("hobostd.otf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 20;
		parameter.characters = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[]"; //Memóriaspórolás céljából csak a hasznosakat konvertáljuk át.
		sFontHoboStd = generator.generateFont(parameter); // font256bytes size 12 pixels
		sFontHoboStd.setColor(0, 0, 0, 1f);
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}

	public static final TextButton.TextButtonStyle sTextButtonStyle;

	static {
		sTextButtonStyle = new TextButton.TextButtonStyle();
		sTextButtonStyle.font = sFontHoboStd;
		sTextButtonStyle.fontColor = Color.BLACK;
		sTextButtonStyle.downFontColor = Color.RED;
		sTextButtonStyle.overFontColor = Color.valueOf("880000");
		sTextButtonStyle.pressedOffsetX = 3;
		sTextButtonStyle.pressedOffsetY = 3;
		sTextButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("greenbutton.png")), 0, 0, 255, 47));
		sTextButtonStyle.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("greenbutton.png")), 0, 49, 255, 47));
		sTextButtonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("greenbutton.png")), 0, 98, 255, 47));
	}


	//A gomb stílusát itt össze kell dobni. Hogy mindenhol egységes legyen, így az ős osztályba került ez is.

	public static final Label.LabelStyle sLabelStyle;

	static {
		sLabelStyle = new Label.LabelStyle();
		sLabelStyle.font = sFont256bytes;
		sLabelStyle.fontColor = Color.WHITE;
		sLabelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("label.png"))));
	}

	protected OrthographicCamera mCamera;
	protected Viewport mViewport;
	protected SpriteBatch mBatch;
	protected float mElapsedTime = 0;

	public MyScreen() {
		mBatch = new SpriteBatch();
		mCamera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		mViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), mCamera);
		mCamera.zoom = VIRTUAL_HEIGHT / Gdx.graphics.getHeight();
		mCamera.setToOrtho(false);
	}

	@Override
	public void render(float delta) {
		mElapsedTime += Gdx.graphics.getDeltaTime();
		mBatch.setProjectionMatrix(mCamera.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		mViewport.update(width, height);
	}

	@Override
	public void show() {

		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}


	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {
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
}
