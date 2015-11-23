package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javafx.scene.control.Skin;

/**
 * Created by 13-0123 on 2015.11.23..
 */
public class ScreenStatictics extends MyScreen{
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    ScreenStatictics(){
        super();
        stage = new Stage();
        ActorBackground b = new ActorBackground();
        b.setSize(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        stage.addActor(b);
        batch=new SpriteBatch();
        TextField nevermind;
        nevermind = new TextField("COMING SOON!", MyScreen.JAVA_NYAVANYOG);
        stage.addActor(nevermind);
    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
