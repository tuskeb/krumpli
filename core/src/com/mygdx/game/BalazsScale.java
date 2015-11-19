package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Timer;

public class BalazsScale extends ApplicationAdapter implements InputProcessor{
    SpriteBatch batch;
    Texture img;
    int mag = 0;
    Sprite sprite;
    boolean movingRight = false;
    long startTime, time;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("scale.png");
        sprite = new Sprite(img);
        sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2,
                Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render () {
        //mag+=1;
        /*if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            sprite.translateX(-2f);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            sprite.translateX(2f);*/
        long startTime = System.currentTimeMillis();
        //long elapsedTime = 0;
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            time = System.currentTimeMillis();
            /*time%=100000000;
            time%=100000;
            time/=3;*/
            time%=10000000;
            Gdx.app.log("elapsedTime: ",time+"");
            if((time - startTime) > 2000){
                sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2,
                        Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);
            }
            sprite.translateY(1f);
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            sprite.translateY(-1f);
        }
        startTime = System.currentTimeMillis();
            /*sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);*/
        /*if(Gdx.input.isButtonPressed(Input.Keys.LEFT))
            sprite.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY());
        if(movingRight)
            sprite.translateX(1f);*/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        time = 0;
        batch.draw(sprite, sprite.getX(), sprite.getY());
        batch.end();
    }

    @Override
    public void dispose(){
        img.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode==Input.Keys.LEFT)
            sprite.translateX(-1f);

        if(keycode==Input.Keys.RIGHT)
            movingRight=true;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode==Input.Keys.RIGHT)
            movingRight=false;
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
        //sprite.setPosition(screenX,Gdx.graphics.getHeight() - screenY);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if(amount > 0)
            sprite.translateY(1f);
        if(amount < 0)
            sprite.translateY(-1f);
        return false;
    }
}
