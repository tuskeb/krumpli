package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



/**
 * A menü.
 menük:
 Új játék
 Statisztika vagy valami hasonló
 Súgó
 (Kilépés)
 */
public class DaniScreenMenu extends DaniMyScreen {
    private Stage stage;
    private Table table;
    public DaniScreenMenu(final Game game) {
        super(game);
        stage = new Stage();

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton buttonNewGame = new TextButton("Új játék", textButtonStyle);
        buttonNewGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //TODO Need Action!
            }
        });
        TextButton buttonStatictis = new TextButton("Statisztikák", textButtonStyle);
        buttonStatictis.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //TODO Need Action!
            }
        });
        TextButton buttonHelp = new TextButton("Súgó", textButtonStyle);
        buttonHelp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        TextButton buttonExit = new TextButton("Kilépés", textButtonStyle);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.exit(0);
            }
        });
        Label label1= new Label("Space Game", labelNull);
        label1.setAlignment(0, 0);
        table.add(label1).width(500f).height(130f);
        table.row();
        table.row();
        table.add(buttonNewGame);
        table.row();
        table.add(buttonStatictis);
        table.row();
        table.add(buttonHelp);
        table.row();
        table.add(buttonExit);

    }


    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.end();


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        super.show();
    }

}
