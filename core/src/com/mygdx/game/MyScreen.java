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

public class MyScreen implements Screen {
    public final static float VIRTUAL_WIDTH = 640, VIRTUAL_HEIGHT = 480;

    public static BitmapFont font256bytes, fontHoboStd;

    public static TextButton.TextButtonStyle textButtonStyle;
    public static Label.LabelStyle labelStyle;

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected SpriteBatch batch;
    protected float elapsedTime = 0;
    public final Game game;

    public MyScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        camera.zoom = VIRTUAL_HEIGHT / Gdx.graphics.getHeight();
        camera.setToOrtho(false);
        camera.update();


        //Ezek a MyScreen osztályban deklaráltak, mert az összes osztályban akarom őket használni, és statikus, mivel elég csak egyszer betölteni őket.

        //Tetszőleges TTF betöltése. A Windows/font mappából bemásoltam egyet... De rengeteteg van a http://www.1001fonts.com/ oldalon.
        //Ez a rész átkonvertálja a vektoros betűket bittérképessé

        if (font256bytes == null) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("256bytes.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 50;
            parameter.characters = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[]"; //Memóriaspórolás céljából csak a hasznosakat konvertáljuk át.
        /*
		parameter.shadowColor = Color.BLACK;
		parameter.shadowOffsetX = 3;
		parameter.shadowOffsetY = 3; ???? */
            font256bytes = generator.generateFont(parameter); // font256bytes size 12 pixels
            font256bytes.setColor(0, 0, 0, 0.5f);
            generator.dispose(); // don't forget to dispose to avoid memory leaks!
        }
        if (fontHoboStd == null) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("256bytes.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            generator = new FreeTypeFontGenerator(Gdx.files.internal("hobostd.otf"));
            parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 20;
            parameter.characters = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[]"; //Memóriaspórolás céljából csak a hasznosakat konvertáljuk át.
            fontHoboStd = generator.generateFont(parameter); // font256bytes size 12 pixels
            fontHoboStd.setColor(0, 0, 0, 1f);
            generator.dispose(); // don't forget to dispose to avoid memory leaks!
        }


        //A gomb stílusát itt össze kell dobni. Hogy mindenhol egységes legyen, így az ős osztályba került ez is.

        if (textButtonStyle == null) {
            textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = fontHoboStd;
            textButtonStyle.fontColor = Color.BLACK;
            textButtonStyle.downFontColor = Color.RED;
            textButtonStyle.overFontColor = Color.valueOf("880000");
            textButtonStyle.pressedOffsetX = 3;
            textButtonStyle.pressedOffsetY = 3;
            textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("greenbutton.png")), 0, 0, 255, 47));
            textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("greenbutton.png")), 0, 49, 255, 47));
            textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("greenbutton.png")), 0, 98, 255, 47));
        }

        if (labelStyle == null) {
            labelStyle = new Label.LabelStyle();
            labelStyle.font = font256bytes;
            labelStyle.fontColor = Color.WHITE;
            labelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("label.png"))));
        }


    }

    @Override
    public void render(float delta) {
        elapsedTime += Gdx.graphics.getDeltaTime();
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
}
