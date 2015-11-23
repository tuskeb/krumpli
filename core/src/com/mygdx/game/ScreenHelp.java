package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.Skinnable;

/**
 * A súgó képernyője
 */
public class ScreenHelp extends MyScreen{
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Table table;
    private TextField TABLE_NEVERMIND, TABLE_NEVERMIND2, TABLE_NEVERMIND3;
    public ScreenHelp() {
        super();

        stage = new Stage();
        ActorBackground b = new ActorBackground();
        b.setSize(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        stage.addActor(b);
        batch=new SpriteBatch();
        TABLE_NEVERMIND = new TextField("ASDF", MyScreen.JAVA_NYAVANYOG);
        TABLE_NEVERMIND2 = new TextField("ASDF2", MyScreen.JAVA_NYAVANYOG);
        TABLE_NEVERMIND3 = new TextField("ASDF3", MyScreen.JAVA_NYAVANYOG);


        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label label1 = new Label("SÚGÓ",MyScreen.LABEL_STYLE);
        label1.setAlignment(100, 100);
        label1.setPosition(320, 240);
        table.add(label1).width(500f).height(130f);
        table.row();
        table.add(TABLE_NEVERMIND);
        table.row();
        table.add(TABLE_NEVERMIND2);
        table.row();
        table.add(TABLE_NEVERMIND3);


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
