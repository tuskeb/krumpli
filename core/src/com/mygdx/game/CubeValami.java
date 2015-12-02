package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by 13-100 on 2015.12.02..
 */

public class CubeValami extends MyActor {

    Texture img;
    SpriteBatch batch;
    Sprite sprite;

    Texture blank;
    Sprite sBlank;

    float le = 0f;


    public void create () {
        batch = new SpriteBatch();
        img = new Texture("scale.png");
        sprite = new Sprite(img);
        sprite.setSize(20, 100);
        sprite.setPosition(0,0);

        /*blank = new Texture("Blank.png");
        sBlank = new Sprite(blank);
        sBlank.setPosition(Gdx.graphics.getWidth() - sprite.getWidth(), Gdx.graphics.getHeight() - le);
*/
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }
/*
    public void setBlank(){
        le+= sprite.getHeight()/2;
    }

*/
}


