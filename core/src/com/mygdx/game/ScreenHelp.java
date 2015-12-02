package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import sun.security.jgss.GSSCaller;

//36303948028 tuskeb Tel
/**
 * A súgó képernyője
 */
public class ScreenHelp extends MyScreen{
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Table table;
    private Label TABLE_NEVERMIND, TABLE_NEVERMIND2, TABLE_NEVERMIND3;
    private Label TABLE_SZOVEG1;
    private TextButton button;
    public ScreenHelp() {
        super();
        stage = new Stage();
        ActorBackground b = new ActorBackground();
        b.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(b);
        batch=new SpriteBatch();
        TABLE_NEVERMIND = new Label("Hogyan Játszd?", MyScreen.LABEL_STYLE);
        TABLE_NEVERMIND2 = new Label("", MyScreen.LABEL_STYLE);
        TABLE_NEVERMIND3 = new Label("KÉSZÍTŐK", MyScreen.LABEL_STYLE);
        TABLE_SZOVEG1 = new Label("ASDFGHJKLKJHGFDFGHJKLJHGFDFGHJ\nASDFGHJKLKJHGFDFGHJKLJHGFDFGHJ\nASDFGHJKLKJHGFDFGHJKLJHGFDFGHJ", MyScreen.LABEL_STYLE2); //30 kar



        table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.setFillParent(true);
        stage.addActor(table);

        Label label1 = new Label("SÚGÓ",MyScreen.LABEL_STYLE_TOP);
        label1.setAlignment(Align.top, Align.center);
        table.add(label1).width(500f).height(130f);
        table.row();
        table.add(TABLE_NEVERMIND);
        table.row();
        table.add(TABLE_SZOVEG1);
        table.row();
        table.add(TABLE_NEVERMIND2);
        table.row();
        table.add(TABLE_NEVERMIND3);

        button = new TextButton("EASTER EGGS", MyScreen.TEXT_BUTTON_STYLE);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SpaceGame.sGame.showScreen(SpaceGame.Screens.EGGS);
            }
        });
        stage.addActor(button); button.setVisible(false);
        if(Gdx.input.isTouched(1)){ button.setVisible(true);button.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());}


    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
    }
}
