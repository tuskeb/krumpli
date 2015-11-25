package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by 13-0123 on 2015.11.25..
 */
public class ActorMenuSpaceShip extends ActorSpaceship {
    float allapot;
    double y, x, h;
    public ActorMenuSpaceShip() {
        super(new World(new Vector2(0, 0), true));
    }

    @Override
    public void act(float delta) {
        SSstep();
    }
    float a = 0;
    void SSstep(){

        if(getY() > 0) {
            a += 0.1;
            y = getY();
            x = getX();
            y--;
            h = MyScreen.VIRTUAL_HEIGHT;


            setPosition(500 + (float) Math.sin(a) * 10f, (float) y);
        }
    }
}
/*
allapot+=0.1;
        y--;
        x= Gdx.graphics.getWidth()/2 + (float)Math.sin(allapot)*100;
        if (y<-h)
        {

            y = Gdx.graphics.getHeight()+h;
        }

*/
/*
float a = 0;
        a += 0.01;
        if(getY() > 0) setPosition(getX()+a,getY()-(float)Math.sin(a));
        if(getX() < 0) setPosition(getX()-a, getY()-a);
 */
