package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;



/**
 * A súgó képernyője
 */
public class ScreenHelp extends MyScreen{
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Table table;
    private TextField TABLE_NEVERMIND, TABLE_NEVERMIND2, TABLE_NEVERMIND3;
    private TextField TABLE_SZOVEG1;
    public ScreenHelp() {
        super();
        stage = new Stage();
        ActorBackground b = new ActorBackground();
        b.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(b);
        batch=new SpriteBatch();
        TABLE_NEVERMIND = new TextField("Hogyan Játszd?", MyScreen.JAVA_NYAVANYOG);
        TABLE_NEVERMIND2 = new TextField("ASDFGGHJKLÉQWERTZUIOPASDFGHJKLSDFGHJKDSDFGHJKLKJHGFDFGHJK", MyScreen.JAVA_NYAVANYOG);
        TABLE_NEVERMIND3 = new TextField("KÉSZÍTŐK", MyScreen.JAVA_NYAVANYOG2);
        TABLE_SZOVEG1 = new TextField("ASDFGGHJKLÉQWERTZUIOPASDFGHJKLSDFGHJKDSDFGHJKLKJHGFDFGHJK", MyScreen.JAVA_NYAVANYOG2);


        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(Gdx.graphics.getHeight());
        stage.addActor(table);

        Label label1 = new Label("SÚGÓ",MyScreen.LABEL_STYLE);
        label1.setPosition(Gdx.graphics.getWidth()/2, VIRTUAL_HEIGHT);
        table.add(label1);
        table.row();
        table.row();
        table.add(TABLE_NEVERMIND);
        table.row();
        table.add(TABLE_SZOVEG1);
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
