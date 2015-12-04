package com.mygdx.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class ScreenMenu extends MyScreen {
    private final SpriteBatch batch = new SpriteBatch();
    private final Stage stage;


    final Music s = Gdx.audio.newMusic(Gdx.files.internal("moonlightshadow.mp3"));

    private Display display;

    public ScreenMenu() {
        super();

        stage = new Stage() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.ESCAPE:
                    case Input.Keys.BACK:

                        System.exit(0);
                        break;
                }

                return false;
            }
        };

        final Sound click = Gdx.audio.newSound(Gdx.files.internal("Sound/click_sound.mp3"));



        ActorBackground actorBackground = new ActorBackground();
        actorBackground.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(actorBackground);

        ActorMenuSpaceShip spaceship = new ActorMenuSpaceShip();
        spaceship.setSize(150, 250);
        spaceship.setPosition(Gdx.graphics.getWidth() - spaceship.getWidth() * 4, Gdx.graphics.getHeight() - spaceship.getHeight() / 2);
        stage.addActor(spaceship);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton button;
        Label label = new Label("Holdra szállás szimulátor", MyScreen.LABEL_STYLE_TOP); // Ha már magyar a kezelőfelület, akkor ez is lehetne magyar
        label.setAlignment(Align.center, Align.center);
        table.add(label)
                .width(500f)
                .height(130f);

        // gombok hozzáadása

        final float ROW_HEIGHT = 75f;

        button = new TextButton("Új játék", MyScreen.TEXT_BUTTON_STYLE);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SpaceGame.sGame.showScreen(SpaceGame.Screens.GAME);
                click.play();
            }
        });
        table.row().height(ROW_HEIGHT);
        table.add(button);


        button = new TextButton("Rekord", MyScreen.TEXT_BUTTON_STYLE);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SpaceGame.sGame.showScreen(SpaceGame.Screens.STAT);
                click.play();
            }
        });
        table.row().height(ROW_HEIGHT);
        table.add(button);


        button = new TextButton("Súgó", MyScreen.TEXT_BUTTON_STYLE);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SpaceGame.sGame.showScreen(SpaceGame.Screens.HELP);
                click.play();
            }
        });
        table.row().height(ROW_HEIGHT);
        table.add(button);


        button = new TextButton("Kilépés", MyScreen.TEXT_BUTTON_STYLE);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
                click.play();
            }
        });
        table.row().height(ROW_HEIGHT);
        table.add(button);

        //s.loop();



        /*
        display =new Display();
        display.setTimeSlider(2f);
        stage.addActor(display);
        */
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }


    @Override
    public void hide() {
        super.hide();
        s.pause();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        s.setVolume(0.5f);
        s.play();
    }



    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
    }
    //display.setMagassag(400); A RENDERBEN VOLT...
    //display.setTimeSlider(display.getTimeSlider()-0.01f); A RENDERBEN VOLT...
}
