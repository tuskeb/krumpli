package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ScreenMenu extends MyScreen {
	private final SpriteBatch batch = new SpriteBatch();
	private final Stage stage;

	public ScreenMenu() {
		super();

		stage = new Stage();

		ActorBackground actorBackground = new ActorBackground();
		actorBackground.setSize(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		stage.addActor(actorBackground);

		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		TextButton button;
		Label label = new Label("Space Game", MyScreen.LABEL_STYLE); // Ha már magyar a kezelőfelület, akkor ez is lehetne magyar
		label.setAlignment(0, 0);
		table.add(label)
				.width(500f)
				.height(130f);

		// gombok hozzáadása

		button = new TextButton("Új játék", MyScreen.TEXT_BUTTON_STYLE);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				DaniSpaceGame.sGame.showScreen(DaniSpaceGame.Screens.GAME);
			}
		});
		table.row();
		table.add(button);


		button = new TextButton("Statisztikák", MyScreen.TEXT_BUTTON_STYLE);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				DaniSpaceGame.sGame.showScreen(DaniSpaceGame.Screens.STATS);
			}
		});
		table.row();
		table.add(button);


		button = new TextButton("Súgó", MyScreen.TEXT_BUTTON_STYLE);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				DaniSpaceGame.sGame.showScreen(DaniSpaceGame.Screens.HELP);
			}
		});
		table.row();
		table.add(button);


		button = new TextButton("Kilépés", MyScreen.TEXT_BUTTON_STYLE);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.exit(0);
			}
		});
		table.row();
		table.add(button);

		ActorMenuSpaceShip spaceship = new ActorMenuSpaceShip();
		spaceship.setSize(100, 200);
		spaceship.setPosition(VIRTUAL_WIDTH - spaceship.getWidth(), VIRTUAL_HEIGHT - spaceship.getHeight());
		stage.addActor(spaceship);

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
