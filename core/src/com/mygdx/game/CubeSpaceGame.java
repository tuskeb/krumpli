package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;


// http://www.gamefromscratch.com/post/2014/08/27/LibGDX-Tutorial-13-Physics-with-Box2D-Part-1-A-Basic-Physics-Simulations.aspx


public class CubeSpaceGame extends Game implements ApplicationListener {

    public static CubeSpaceGame sGame = new CubeSpaceGame();
    private SpriteBatch batch;
    private static Screen[] mScreens;

    private Stage teszt;



    public enum Screens {
        MENU(0), HELP(1), GAME(2);

        private int value;

        private Screens(int value) {
            this.value = value;
        }

    }


    ;

    public void showScreen(Screens screen) {
        setScreen(mScreens[screen.value]);
    }

    @Override
    public void create() {

        teszt = new Stage();
        ActorBackground actorBackground = new ActorBackground();
        actorBackground.setSize(MyScreen.VIRTUAL_WIDTH, MyScreen.VIRTUAL_HEIGHT);
        //teszt.addActor(new ActorBackground());

        ActorSpaceship actorSpaceship = new ActorSpaceship(new World(new Vector2(0, 0), true));
        actorSpaceship.setSize(100,200);
        actorSpaceship.setPosition(300,150);
        teszt.addActor(actorBackground);
        teszt.addActor(actorSpaceship);
        batch=new SpriteBatch();
        //showScreen(Screens.MENU);
        setScreen(new ScreenMenu());
    }


	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        teszt.act(Gdx.graphics.getDeltaTime());
        teszt.draw();
		//batch.draw(img, 0, 0);
		batch.end();
	}

}
