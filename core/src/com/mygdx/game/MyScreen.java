package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// https://github.com/libgdx/libgdx/wiki/box2d#creating-a-world

public class MyScreen implements Screen, InputProcessor {
	public static final float VIRTUAL_WIDTH = 640, VIRTUAL_HEIGHT = 480;
	private static String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[]";
	protected static final BitmapFont FONT_256_BYTES, FONT_HOBO_STD;

	//Ezek a MyScreen osztályban deklaráltak, mert az összes osztályban akarom őket használni, és statikus, mivel elég csak egyszer betölteni őket.

	//Tetszőleges TTF betöltése. A Windows/font mappából bemásoltam egyet... De rengeteteg van a http://www.1001fonts.com/ oldalon.
	//Ez a rész átkonvertálja a vektoros betűket bittérképessé

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("256bytes.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		parameter.characters = CHARS;
		FONT_256_BYTES = generator.generateFont(parameter);
		FONT_256_BYTES.setColor(0, 0, 0, .5f);
		generator.dispose();
	}

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("hobostd.otf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 20;
		parameter.characters = CHARS;
		FONT_HOBO_STD = generator.generateFont(parameter);
		FONT_HOBO_STD.setColor(0, 0, 0, 1f);
		generator.dispose();
	}

	protected static final TextButton.TextButtonStyle TEXT_BUTTON_STYLE;

	static {
		TEXT_BUTTON_STYLE = new TextButton.TextButtonStyle();
		TEXT_BUTTON_STYLE.font = FONT_HOBO_STD;
		TEXT_BUTTON_STYLE.fontColor = Color.BLACK;
		TEXT_BUTTON_STYLE.downFontColor = Color.RED;
		TEXT_BUTTON_STYLE.overFontColor = Color.valueOf("880000");
		TEXT_BUTTON_STYLE.pressedOffsetX = 3;
		TEXT_BUTTON_STYLE.pressedOffsetY = 3;

		final Texture texture = new Texture(Gdx.files.internal("greenbutton.png"));

		TEXT_BUTTON_STYLE.up = new TextureRegionDrawable(new TextureRegion(texture, 0, 0, 255, 47));
		TEXT_BUTTON_STYLE.over = new TextureRegionDrawable(new TextureRegion(texture, 0, 49, 255, 47));
		TEXT_BUTTON_STYLE.down = new TextureRegionDrawable(new TextureRegion(texture, 0, 98, 255, 47));
	}

	protected static final Label.LabelStyle LABEL_STYLE;

	static {
		LABEL_STYLE = new Label.LabelStyle();
		LABEL_STYLE.font = FONT_256_BYTES;
		LABEL_STYLE.fontColor = Color.WHITE;

		final Texture texture = new Texture(Gdx.files.internal("label.png"));

		LABEL_STYLE.background = new TextureRegionDrawable(new TextureRegion(texture));

	}

	protected final OrthographicCamera camera;
	protected final Viewport viewport;
	protected final SpriteBatch batch;
	//protected float mElapsedTime = 0;

	public MyScreen() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		camera.zoom = VIRTUAL_HEIGHT / Gdx.graphics.getHeight();
		camera.setToOrtho(false);
	}

	@Override
	public void render(float delta) {
		//mElapsedTime += Gdx.graphics.getDeltaTime();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
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
