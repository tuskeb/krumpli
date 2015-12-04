package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


/**
 * Created by 13-100 on 2015.12.02..
 */

public class Display extends MyActor {

    Texture img;
    SpriteBatch batch;
    Sprite sprite;

    Texture img1;
    Sprite sprite1;

    Texture img2;
    Sprite sprite2;

    /*Texture moon;
    Sprite spriteMoon;*/

    float mTimeSlider =0;

    int magassag;
    Label labelMagassag;
    String s;

    int sebesseg;
    Label labelSebesseg;
    String t;




    public Display() {
        img1 = new Texture("menu_also.png");
        sprite1 = new Sprite(img1);
        sprite1.setSize(Gdx.graphics.getHeight()/1.5f, Gdx.graphics.getWidth()/1.5f);
        sprite1.setPosition(0, 0);

        img2 = new Texture("menu_felso.png");
        sprite2 = new Sprite(img2);
        sprite2.setSize(Gdx.graphics.getHeight()/1.5f, Gdx.graphics.getWidth()/1.5f);
        sprite2.setPosition(0, 0);

        batch = new SpriteBatch();
        img = new Texture("scale.png");
        sprite = new Sprite(img);
        sprite.setSize(350, 28);
        sprite.setPosition(Gdx.graphics.getWidth()/86, Gdx.graphics.getHeight()/58);

        magassag = 0;
        s = "Magasság: "+magassag;

        labelMagassag = new Label(s, MyScreen.LABEL_STYLE);
        labelMagassag.setPosition(10, 50);
        //labelMagassag.setSize(10, 10);
        labelMagassag.setFontScale(0.5f);

        sebesseg = 0;
        t = "Sebesség: "+sebesseg;

        labelSebesseg = new Label(t, MyScreen.LABEL_STYLE);
        labelSebesseg.setPosition(10, 100);
        //labelSebesseg.setSize(10, 10);
        labelSebesseg.setFontScale(0.5f);

        /*moon = new Texture("Felszin.png");
        spriteMoon = new Sprite(moon);
        spriteMoon.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteMoon.setPosition(0,0);*/


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite1.draw(batch, 1f);

        super.draw(batch, parentAlpha);
        sprite.draw(batch);

        sprite2.draw(batch, 1f);

        labelMagassag.draw(batch, 100f);
        magassag();
        labelSebesseg.draw(batch, 100f);

        //spriteMoon.draw(batch, 1f);

    }

    public void setTimeSlider(float seconds)
    {
        mTimeSlider=seconds;
        sprite.setSize((int) (seconds * 80f), sprite.getHeight());
    }

    public float getTimeSlider()
    {
        return  mTimeSlider;
    }

    public int getMagassag() {
        return magassag;
    }

    public void setMagassag(int magassag) {
        s = "Magasság: "+magassag;
        labelMagassag.setText(s);
        this.magassag = magassag;
    }

    public int getSebesseg() {
        return sebesseg;
    }

    public void setSebesseg(int sebesseg) {
        s = "Sebesség: "+sebesseg;
        labelSebesseg.setText(s);
        this.sebesseg = sebesseg;
    }

    void magassag(){
        /*if(magassag>=0)
        magassag-=1;
        s = "Magasság: "+magassag;
        labelMagassag.setText(s);*/
    }


}


