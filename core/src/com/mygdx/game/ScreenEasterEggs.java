package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Created by 13-0123 on 2015.12.02..
 */
public class ScreenEasterEggs extends MyScreen {
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Table table;
    private Label TABLE_NEVERMIND, TABLE_SZOVEG1, TABLE_SZOVEG2;
    ScreenEasterEggs(){
            super();
            stage = new Stage();
            ActorBackground b = new ActorBackground();
            b.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stage.addActor(b);
            batch=new SpriteBatch();
            TABLE_NEVERMIND = new Label ("TITKOS FUNKCIÓK", MyScreen.LABEL_STYLE_TOP);
            TABLE_SZOVEG1 = new Label ("Ha 4 ujjal megérinted a képernyőt, \nakkor az űrhajó felrobban!", MyScreen.LABEL_STYLE2);
            TABLE_SZOVEG2 = new Label("Tudod mozgatni az űrhajót!", MyScreen.LABEL_STYLE2);


            table = new Table();
            table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            table.setFillParent(true);
            stage.addActor(table);

            table.add(TABLE_NEVERMIND);
            table.row().pad(ScreenHelp.PUDING);
            table.add(TABLE_SZOVEG1);
            table.row();
            table.add(TABLE_SZOVEG2);

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
