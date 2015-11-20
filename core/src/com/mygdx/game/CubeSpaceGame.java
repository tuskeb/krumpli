package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        mScreens = new Screen[]{
                new ScreenMenu(),
                new ScreenHelp(),
                new ScreenGame()
        };
        teszt = new Stage();
        teszt.addActor(new ActorBackground());

batch=new SpriteBatch();
        //showScreen(Screens.MENU);
        setScreen(new ScreenMenu());
    }


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        teszt.draw();
		//batch.draw(img, 0, 0);
		batch.end();
	}

}
