package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;


// https://github.com/libgdx/libgdx/wiki/box2d#creating-a-world

public class MyScreen implements Screen {
	public static final float VIRTUAL_WIDTH = 640, VIRTUAL_HEIGHT = 480;
	private static String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";
	protected static final BitmapFont FONT_256_BYTES, FONT_HOBO_STD, FONT_HOBO_STD_TOP, FONT_CALIBRI;

	//Ezek a MyScreen osztályban deklaráltak, mert az összes osztályban akarom őket használni, és statikus, mivel elég csak egyszer betölteni őket.

	//Tetszőleges TTF betöltése. A Windows/font mappából bemásoltam egyet... De rengeteteg van a http://www.1001fonts.com/ oldalon.
	//Ez a rész átkonvertálja a vektoros betűket bittérképessé

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ShowcardGothic.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 45;
		parameter.characters = CHARS;
		FONT_256_BYTES = generator.generateFont(parameter);
		FONT_256_BYTES.setColor(0, 0, 0, .5f);
		generator.dispose();
	}


	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ShowcardGothic.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		parameter.characters = CHARS;
		FONT_HOBO_STD = generator.generateFont(parameter);
		FONT_HOBO_STD.setColor(0, 0, 0, 1f);
		generator.dispose();
	}

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COOPBL.TTF"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 55;
		parameter.characters = CHARS;
		FONT_HOBO_STD_TOP = generator.generateFont(parameter);
		FONT_HOBO_STD_TOP.setColor(0, 0, 0, 1f);
		generator.dispose();
	}

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("calibri.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 27;
		parameter.characters = CHARS;
		FONT_CALIBRI = generator.generateFont(parameter);
		FONT_CALIBRI.setColor(0, 0, 0, 1f);
		generator.dispose();
	}

	protected static final TextField.TextFieldStyle JAVA_NYAVANYOG;

	static	{
		JAVA_NYAVANYOG = new TextField.TextFieldStyle();
		JAVA_NYAVANYOG.font = FONT_HOBO_STD;
		JAVA_NYAVANYOG.fontColor = Color.WHITE;
	}

	protected static final TextField.TextFieldStyle JAVA_NYAVANYOG2;

	static	{
		JAVA_NYAVANYOG2 = new TextField.TextFieldStyle();
		JAVA_NYAVANYOG2.font = FONT_256_BYTES;
		JAVA_NYAVANYOG2.fontColor = Color.WHITE;
	}

	protected static final TextArea.TextFieldStyle JAVA_NYAVANYOG3;

	static	{
		JAVA_NYAVANYOG3 = new TextArea.TextFieldStyle();
		JAVA_NYAVANYOG3.font = FONT_HOBO_STD;
		JAVA_NYAVANYOG3.fontColor = Color.WHITE;
	}

	protected static final TextButton.TextButtonStyle TEXT_BUTTON_STYLE;

	static {
		TEXT_BUTTON_STYLE = new TextButton.TextButtonStyle();
		TEXT_BUTTON_STYLE.font = FONT_HOBO_STD;
		TEXT_BUTTON_STYLE.fontColor = Color.WHITE;
		TEXT_BUTTON_STYLE.downFontColor = Color.GREEN;
		TEXT_BUTTON_STYLE.overFontColor = Color.valueOf("880000");
		TEXT_BUTTON_STYLE.pressedOffsetX = 3;
		TEXT_BUTTON_STYLE.pressedOffsetY = 3;
	}

	protected static final Label.LabelStyle LABEL_STYLE;

	static {
		LABEL_STYLE = new Label.LabelStyle();
		LABEL_STYLE.font = FONT_256_BYTES; // 45
		LABEL_STYLE.fontColor = Color.WHITE;
	}

	protected static final Label.LabelStyle LABEL_STYLE_TOP;

	static {
		LABEL_STYLE_TOP = new Label.LabelStyle();
		LABEL_STYLE_TOP.font = FONT_HOBO_STD_TOP;
		LABEL_STYLE_TOP.fontColor = Color.WHITE;
	}

	protected static final Label.LabelStyle LABEL_STYLE2;

	static {
		LABEL_STYLE2 = new Label.LabelStyle();
		LABEL_STYLE2.font = FONT_CALIBRI; //40
		LABEL_STYLE2.fontColor = Color.WHITE;
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
		Gdx.input.setCatchBackKey(true);
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
	public void show() {

	}


}
