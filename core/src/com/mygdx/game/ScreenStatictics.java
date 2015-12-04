package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
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

/**
 * Created by 13-0123 on 2015.11.23..
 */
public class ScreenStatictics extends MyScreen{
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Label SZOVEG1, SZOVEG2, SZOVEG3, SZOVEG4, SZOVEG5, SZOVEG6, SZOVEG7, SZOVEG8, SZOVEG9, SZOVEG10;
    final Sound click_back = Gdx.audio.newSound(Gdx.files.internal("Sound/click_sound_back.mp3"));
    ScreenStatictics(){
        super();
        stage = new Stage();
        ActorBackground b = new ActorBackground();
        b.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(b);
        batch=new SpriteBatch();
        //Label nevermind = new Label("IN PROGRESS...", LABEL_STYLE_TOP);
        //nevermind.setAlignment(Align.top, Align.top);
        //stage.addActor(nevermind);


        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        SZOVEG1 = new Label("LEGJOBB PONT    \t\t\t", MyScreen.LABEL_STYLE);

        table.add(SZOVEG1);

        SZOVEG2 = new Label("LEGJOBB FUTAM", MyScreen.LABEL_STYLE);

        table.add(SZOVEG2);
        table.row();

        SZOVEG3 = new Label(ScreenGame.WIN_POINT+"\t\t\t", MyScreen.LABEL_STYLE);

        table.add(SZOVEG3);

        SZOVEG4 = new Label(ScreenGame.BEST_TIME+"", MyScreen.LABEL_STYLE);

        table.add(SZOVEG4);
        table.row();










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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
    }
}
