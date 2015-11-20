package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// https://github.com/libgdx/libgdx/wiki/box2d#creating-a-world

public class MyScreen implements Screen {
	public static final int VIRTUAL_WIDTH = 640, VIRTUAL_HEIGHT = 480;
	private static String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[]";
	protected static final BitmapFont FONT_256_BYTES, FONT_HOBO_STD;

	//Ezek a MyScreen osztályban deklaráltak, mert az összes osztályban akarom őket használni, és statikus, mivel elég csak egyszer betölteni őket.

	//Tetszőleges TTF betöltése. A Windows/font mappából bemásoltam egyet... De rengeteteg van a http://www.1001fonts.com/ oldalon.
	//Ez a rész átkonvertálja a vektoros betűket bittérképessé

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SHOWG.TTF"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		parameter.characters = CHARS;
		FONT_256_BYTES = generator.generateFont(parameter);
		FONT_256_BYTES.setColor(0, 0, 0, .5f);
		generator.dispose();
	}

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SHOWG.TTF"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 30;
		parameter.characters = CHARS;
		FONT_HOBO_STD = generator.generateFont(parameter);
		FONT_HOBO_STD.setColor(0, 0, 0, 1f);
		generator.dispose();
	}

	protected static final TextButton.TextButtonStyle TEXT_BUTTON_STYLE;

	static {
		TEXT_BUTTON_STYLE = new TextButton.TextButtonStyle();
		TEXT_BUTTON_STYLE.font = FONT_HOBO_STD;
		TEXT_BUTTON_STYLE.fontColor = Color.WHITE;
		TEXT_BUTTON_STYLE.downFontColor = Color.RED;
		TEXT_BUTTON_STYLE.overFontColor = Color.valueOf("880000");
		TEXT_BUTTON_STYLE.pressedOffsetX = 3;
		TEXT_BUTTON_STYLE.pressedOffsetY = 3;
/*
		final Texture texture = new Texture(Gdx.files.internal("greenbutton.png"));

		TEXT_BUTTON_STYLE.up = new TextureRegionDrawable(new TextureRegion(texture, 0, 0, 255, 47));
		TEXT_BUTTON_STYLE.over = new TextureRegionDrawable(new TextureRegion(texture, 0, 49, 255, 47));
		TEXT_BUTTON_STYLE.down = new TextureRegionDrawable(new TextureRegion(texture, 0, 98, 255, 47));
*/
	}

	protected static final Label.LabelStyle LABEL_STYLE;

	static {
		LABEL_STYLE = new Label.LabelStyle();
		LABEL_STYLE.font = FONT_256_BYTES;
		LABEL_STYLE.fontColor = Color.WHITE;

		//final Texture texture = new Texture(Gdx.files.internal("label.png"));

		//LABEL_STYLE.background = new TextureRegionDrawable(new TextureRegion(texture));

	}

	protected final OrthographicCamera camera;
	protected final Viewport viewport;
	protected final SpriteBatch batch;

	public MyScreen() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		camera.zoom = VIRTUAL_HEIGHT / Gdx.graphics.getHeight();
		camera.setToOrtho(false);
	}


	@Override
	public void render(float delta) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}

	@Override
	public void show() { }

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void hide() {}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() { }

}
