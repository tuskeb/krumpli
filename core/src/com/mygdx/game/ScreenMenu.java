package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * A menü.
 */
 /*
	menük:
        Új játék
        Statisztika vagy valami hasonló
        Súgó
        (Kilépés)
        */
public class ScreenMenu extends MyScreen {
	private SpriteBatch batch;
	private Skin skin;
	private Stage stage;
	private Table table;
	public ScreenMenu() {
		super();
		stage = new Stage();
		ActorBackground b = new ActorBackground();
		b.setSize(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		stage.addActor(b);
		batch=new SpriteBatch();


		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);


		TextButton button1 = new TextButton("Új játék", MyScreen.TEXT_BUTTON_STYLE);
		button1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				DaniSpaceGame.sGame.showScreen(DaniSpaceGame.Screens.GAME);
			}
		});
		TextButton button2 = new TextButton("Statisztikák", MyScreen.TEXT_BUTTON_STYLE);
		button2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				//TODO NEed Statictics class!!
			}
		});
		TextButton button3 = new TextButton("Súgó", MyScreen.TEXT_BUTTON_STYLE);
		button3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				DaniSpaceGame.sGame.showScreen(DaniSpaceGame.Screens.HELP);
			}
		});
		TextButton button4 = new TextButton("Exit(Optional)", MyScreen.TEXT_BUTTON_STYLE);
		button4.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				System.exit(0);
			}
		});
		Label label1 = new Label("Space Game",MyScreen.LABEL_STYLE);
		label1.setAlignment(0, 0);
		table.add(label1).width(500f).height(130f);
		table.row();
		table.add(button1);
		table.row();
		table.add(button2);
		table.row();
		table.add(button3);
		table.row();
		table.add(button4);

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
