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

    Texture blank;
    Sprite sBlank;
    float mTimeSlider =0;

    int magassag;
    Label labelMagassag;
    String s;

    int sebesseg;
    Label labelSebesseg;
    String t;


    public Display() {
        batch = new SpriteBatch();
        img = new Texture("scale.png");
        sprite = new Sprite(img);
        sprite.setSize(350, 20);
        sprite.setPosition(0, 0);

        blank = new Texture("Blank.png");
        sBlank = new Sprite(blank);
        sBlank.setPosition(sprite.getWidth(),0);

        magassag = -1235792983;
        s = "Magasság: "+magassag;

        labelMagassag = new Label(s, MyScreen.LABEL_STYLE);
        labelMagassag.setPosition(10, 30);
        //labelMagassag.setSize(10, 10);
        labelMagassag.setFontScale(0.5f);

        sebesseg = 567357;
        t = "Sebesség: "+sebesseg;

        labelSebesseg = new Label(t, MyScreen.LABEL_STYLE);
        labelSebesseg.setPosition(10, 50);
        //labelSebesseg.setSize(10, 10);
        labelSebesseg.setFontScale(0.5f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
        labelMagassag.draw(batch, 100f);
        magassag();
        labelSebesseg.draw(batch, 100f);

    }

    public void setTimeSlider(float seconds)
    {
        mTimeSlider=seconds;
        sprite.setSize((int) (seconds * 100f), sprite.getHeight());
    }

    public float getTimeSlider()
    {
        return  mTimeSlider;
    }


    public void setBlank(){

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


