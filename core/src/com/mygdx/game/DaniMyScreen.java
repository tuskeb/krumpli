package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

public class DaniMyScreen implements Screen {
    public final static float VIRTUAL_WIDTH = 640, VIRTUAL_HEIGHT = 480;

    public static BitmapFont font256bytes, fontHoboStd, fontshowg;

    public static TextButton.TextButtonStyle textButtonStyle;
    public static Label.LabelStyle labelStyle;
    public static Label.LabelStyle labelNull;

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected SpriteBatch batch;
    protected float elapsedTime = 0;
    public final Game game;
    private Texture backGroundTexture = new Texture(Gdx.files.internal("Space.jpg"));


    public DaniMyScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        camera.zoom = VIRTUAL_HEIGHT / Gdx.graphics.getHeight();
        camera.setToOrtho(false);
        camera.update();

        if (fontshowg == null) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SHOWG.TTF"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            generator = new FreeTypeFontGenerator(Gdx.files.internal("SHOWG.TTF"));
            parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 20;
            parameter.characters = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[]."; //Memóriaspórolás céljából csak a hasznosakat konvertáljuk át.
            fontshowg = generator.generateFont(parameter); // font256bytes size 12 pixels
            fontshowg.setColor(0, 0, 0, 1f);
            generator.dispose();
        }

        if (textButtonStyle == null) {
            textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = fontshowg;
            textButtonStyle.fontColor = Color.WHITE;
            textButtonStyle.downFontColor = Color.GREEN;
            textButtonStyle.overFontColor = Color.valueOf("880000");
            textButtonStyle.pressedOffsetX = 1;
            textButtonStyle.pressedOffsetY = 1;
        }
        if (labelStyle == null) {
            labelStyle = new Label.LabelStyle();
            labelStyle.font = fontshowg;
            labelStyle.fontColor = Color.WHITE;
            labelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Label1.png"))));
        }
        if(labelNull == null){
            labelNull = new Label.LabelStyle();
            labelNull.font = fontshowg;
            labelNull.fontColor = Color.WHITE;

        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backGroundTexture, 0, 0);
        batch.end();
    }

    public void create () {
        batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override @SuppressWarnings("unused")
    public void show() {}

    @Override@SuppressWarnings("unused")
    public void hide() {}

    @Override @SuppressWarnings("unused")
    public void pause() {}

    @Override @SuppressWarnings("unused")
    public void resume() {}

    @Override @SuppressWarnings("unused")
    public void dispose(){}
}
